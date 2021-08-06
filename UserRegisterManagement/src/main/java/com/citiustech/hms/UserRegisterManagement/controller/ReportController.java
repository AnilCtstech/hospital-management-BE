package com.citiustech.hms.UserRegisterManagement.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.stream.FileImageInputStream;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.UserRegisterManagement.service.GenerateReportService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

	@Autowired
	GenerateReportService reportService;

//	@GetMapping("/pdf_report/{id}")
//	public ResponseEntity<Resource> generateReport(@PathVariable long id) {
//		String file = reportService.createReport(id);
//		return reportService.downloadFile(file);
//
//	}
	
	@GetMapping("/pdf_report/{id}")
	public ResponseEntity<byte[]> generateReport1(@PathVariable long id) {
		String file = reportService.createReport(id);
		return reportService.downloadFileTEST(file);

	}

}
