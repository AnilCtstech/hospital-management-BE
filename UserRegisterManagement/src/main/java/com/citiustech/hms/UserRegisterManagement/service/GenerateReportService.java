package com.citiustech.hms.UserRegisterManagement.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.citiustech.hms.UserRegisterManagement.dto.MedicationDTO;
import com.citiustech.hms.UserRegisterManagement.dto.ProceduresDTO;
import com.citiustech.hms.UserRegisterManagement.dto.VitalSignsDTO;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.repository.PatientRepository;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

@Service
public class GenerateReportService implements ReportGenerator {

	@Autowired
	PatientRepository patientRepository;

	static String fileName;

	public String createReport(long id) {
		Document document = new Document();
		String filePath = null;
		try {
			String reportFolder = System.getProperty("user.dir");
			reportFolder = reportFolder + "/patient_reports/";
			File file = new File(reportFolder);
			if (!file.exists()) {
				file.mkdir();
			}
			fileName = String.valueOf(id) + "_patient_report" + ".pdf";
			filePath = reportFolder + String.valueOf(id) + "_patient_report" + ".pdf";
			System.out.println("current directory for report " + System.getProperty("user.dir"));
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();

			document.add(new Paragraph("\n\n"));
			Date date = new Date();
			Instant instanceNow2 = date.toInstant();
			Font mainFont = FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK);
			Chunk chunk = new Chunk("Patient Report" + "\n", mainFont);

			Font patientFont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			document.add(chunk);

			LineSeparator ls = new LineSeparator();
			document.add(new Chunk(ls));

			document.add(new Paragraph("\n"));

			Optional<Patient> patient = patientRepository.findById(id);
			if (patient.isPresent()) {
				Patient localPatient = patient.get();
				document.add(new Chunk("Date: " + instanceNow2.toString()));
				document.add(new Paragraph("\n"));
				Chunk patientChunk = new Chunk(
						"Patient Name: " + localPatient.getFirstName() + " " + localPatient.getLastName() + "\n");
				document.add(patientChunk);
				document.add(new Chunk("Patient Number : " + localPatient.getContactNo() + "\n"));
				document.add(new Chunk("Patient Email : " + localPatient.getEmail() + "\n"));
				document.add(new Chunk("Patient Gender : " + localPatient.getGender() + "\n"));
				document.add(new Paragraph("\n"));
			}

			PdfPTable vitalTable = new PdfPTable(6);
			vitalTable.setWidthPercentage(100);

			PdfPTable medicationTable = new PdfPTable(6);
			medicationTable.setWidthPercentage(100);

			PdfPTable procedureTable = new PdfPTable(3);
			procedureTable.setWidthPercentage(100);

			List<MedicationDTO> medicationsForPatient = getMedicationsForPatient(id);

			List<VitalSignsDTO> vitalSignsForPatient = getVitalSignsForPatient(id);

			List<ProceduresDTO> procedures = getProcedures(id);

			addTableHeaderForViatlSigns(vitalTable);
			addRowsForVitalSigns(vitalTable, vitalSignsForPatient);
			// addRows(vitalTable);
			document.add(new Paragraph("\n"));

			addTableHeaderForMedication(medicationTable);
			addRowsForMedication(medicationTable, medicationsForPatient);
			document.add(new Paragraph("\n"));

			addTableHeaderForProcedures(procedureTable);
			addRowsForProcedure(procedureTable, procedures);
			document.add(new Paragraph("\n"));

			// addTableHeader(vitalTable);
			// addRows(vitalTable);
			// addCustomRows(vitalTable);
			document.add(new Chunk("Vitals "));
			document.add(vitalTable);
			document.add(new Paragraph("\n\n"));
			document.add(new Chunk("Medications "));
			document.add(medicationTable);
			document.add(new Paragraph("\n\n"));
			document.add(new Chunk("Procedures "));
			document.add(procedureTable);
			document.add(new Paragraph("\n\n"));

			document.close();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

		return filePath;
	}

	private void addTableHeaderForViatlSigns(PdfPTable table) {
		Stream.of("Date", "Height", "Weight", "BloodPressure", "BodyTemperature", "RespirationRate")
				.forEach(columnTitle -> {
					PdfPCell header = new PdfPCell();
					header.setBackgroundColor(BaseColor.LIGHT_GRAY);
					header.setBorderWidth(2);
					header.setPhrase(new Phrase(columnTitle));
					table.addCell(header);
				});
	}

	private void addRowsForVitalSigns(PdfPTable table, List<VitalSignsDTO> list) {
		for (VitalSignsDTO vitalSigns : list) {
			table.addCell(vitalSigns.getCreatedAt().toLocaleString());
			table.addCell(vitalSigns.getHeight() + "");
			table.addCell(vitalSigns.getWeight() + "");
			table.addCell(vitalSigns.getBloodPressure());
			table.addCell(vitalSigns.getBodyTemperature());
			table.addCell(vitalSigns.getRespirationRate());
		}
		// table.addCell("row 1, col 1");
	}

	private void addTableHeaderForMedication(PdfPTable table) {
		Stream.of("Date", "DrugName", "DrugGenericName", "DrugBrandName", "DrugStrength", "DrugForm")
				.forEach(columnTitle -> {
					PdfPCell header = new PdfPCell();
					header.setBackgroundColor(BaseColor.LIGHT_GRAY);
					header.setBorderWidth(2);
					header.setPhrase(new Phrase(columnTitle));
					table.addCell(header);
				});
	}

	private void addRowsForMedication(PdfPTable table, List<MedicationDTO> list) {
		for (MedicationDTO medication : list) {
			table.addCell(medication.getCreatedAt().toLocalDateTime().toString());
			table.addCell(medication.getDrugName());
			table.addCell(medication.getDrugGenericName());
			table.addCell(medication.getDrugBrandName());
			table.addCell(medication.getDrugStrength());
			table.addCell(medication.getDrugForm());
		}

	}

	private void addTableHeaderForProcedures(PdfPTable table) {
		Stream.of("Date", "ProcedureCode", "ProcedureDescription").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRowsForProcedure(PdfPTable table, List<ProceduresDTO> list) {
		for (ProceduresDTO procedures : list) {
			table.addCell(procedures.getCreatedAt().toLocalDateTime().toString());
			table.addCell(procedures.getProcedureCode());
			table.addCell(procedures.getProcedureDescription());

		}
	}

	public static List<MedicationDTO> getMedicationsForPatient(long id) {
		String url = "http://localhost:8085/medication/" + String.valueOf(id);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// headers.set("Authorization", "Bearer " + token);
		headers.set("Authorization", "Bearer ");
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<List<MedicationDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<MedicationDTO>>() {
				});
		return response.getBody();

	}

	public static List<VitalSignsDTO> getVitalSignsForPatient(long id) {
		String url = "http://localhost:8086/vitalsigns/vitalsigns/" + String.valueOf(id);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// headers.set("Authorization", "Bearer " + token);
		headers.set("Authorization", "Bearer ");
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<List<VitalSignsDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<VitalSignsDTO>>() {
				});
		return response.getBody();

	}

	public static List<ProceduresDTO> getProcedures(long id) {
		String url = "http://localhost:8087/saveprocedure/procedures/" + String.valueOf(id);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// headers.set("Authorization", "Bearer " + token);
		headers.set("Authorization", "Bearer ");
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<List<ProceduresDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<ProceduresDTO>>() {
				});
		return response.getBody();

	}

	public static String callGetApi(String url, String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return response.getBody().toString();
	}

	public static List<Long> callGetListApi(String url, String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		List<Long> idList = new ArrayList<Long>();
		try {
			ResponseEntity<List<Long>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<Long>>() {
					});
			return response.getBody();
		} catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
		}
		return null;

	}

	public ResponseEntity<Resource> downloadFile(String file) {
		// String file = reportService.createReport(id);
		InputStreamResource resource = null;
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		try {
			resource = new InputStreamResource(new FileInputStream(file));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return ResponseEntity.ok().headers(header).contentLength(file.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	public ResponseEntity<byte[]> downloadFileTEST(String file) {
		Path path = Paths.get(file);
		byte[] pdfContents = null;
		File file1 = new File(file);
		ResponseEntity<byte[]> response = null;
//		InputStreamResource resource;
//		ResponseEntity<Object> responseEntity = null;
		try {
			pdfContents = Files.readAllBytes(path);
			// resource = new InputStreamResource(new FileInputStream(file1));

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = fileName;
			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);

//			responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
//					.contentType(MediaType.parseMediaType("application/txt")).body(resource);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

//	private void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException {
//		Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
//		Image img = Image.getInstance(path.toAbsolutePath().toString());
//		img.scalePercent(10);

//		PdfPCell imageCell = new PdfPCell(img);
//		table.addCell(imageCell);

//		PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
//		horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(horizontalAlignCell);
//
//		PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
//		verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//		table.addCell(verticalAlignCell);
}

//	private void addTableHeader(PdfPTable table) {
//		Stream.of("column header 1", "column header 2", "column header 3").forEach(columnTitle -> {
//			PdfPCell header = new PdfPCell();
//			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//			header.setBorderWidth(2);
//			header.setPhrase(new Phrase(columnTitle));
//			table.addCell(header);
//		});
//	}

//	private void addRows(PdfPTable table) {
//		table.addCell("row 1, col 1");
//		table.addCell("row 1, col 2");
//		table.addCell("row 1, col 3");
//	}

//}
