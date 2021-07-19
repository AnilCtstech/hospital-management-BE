package com.citiustech.hms.inboxmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citiustech.hms.inboxmanagement.dto.SendNoteVO;
import com.citiustech.hms.inboxmanagement.dto.SentNoteVO;
import com.citiustech.hms.inboxmanagement.entity.Note;
import com.citiustech.hms.inboxmanagement.entity.NoteResponse;
import com.citiustech.hms.inboxmanagement.mapper.MapStructMapper;
import com.citiustech.hms.inboxmanagement.repository.NoteRepository;
import com.citiustech.hms.inboxmanagement.repository.NoteResponseRepository;
import com.citiustech.hms.inboxmanagement.util.JwtUtil;

@Service
public class NoteServiceImpl implements NotesService {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private NoteRepository noteRepo;

	@Autowired
	private NoteResponseRepository noteResponseRepo;
	
	@Autowired
	protected MapStructMapper mapper;
	
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public String sendNotes(String authorization, SendNoteVO sendNotes) {
		
		String token = authorization.substring(7);

		System.out.println(jwtUtil.extractAllClaims(token));

		long id = jwtUtil.extractAllClaims(token).get("id", Long.class);

		Note note=mapper.sendNoteVOToNote(sendNotes);
		note.setFromEmployeeId(id);

		noteRepo.save(note);
		return "Note send succucessfully!";
	}

	@Override
	public List<SentNoteVO> getAllSentNotes(String authorization,String pageNo) {
		// jwtUtil.extractClaim(authorization,claims->claims.get("id",String.class));

		String token = authorization.substring(7);

		System.out.println(jwtUtil.extractAllClaims(token));

		long id = jwtUtil.extractAllClaims(token).get("id", Long.class);

		Pageable pageable = PageRequest.of(Integer.parseInt(pageNo), 10, Sort.by("dateTime").descending());

		Page<Note> page = noteRepo.findByfromEmployeeId(id, pageable);
		long size=page.getTotalElements();
		System.out.println("size"+size);
		List<SentNoteVO> noteVO = new ArrayList<>();
		List<Note> notes = null;
		if (page.hasContent()) {
			notes = page.getContent();

			notes.stream().forEach(emp -> {
				noteVO.add(mapper.noteTosentNoteVO(emp));

			});
			noteVO.forEach(n -> {
					String url="http://localhost:8080/user/employee/{id}";
					HttpHeaders headers=new HttpHeaders();
					headers.add("Authorization",authorization);
					HttpEntity<String> entity=new HttpEntity<String>(headers);
					ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class,n.getToEmployeeId());
					n.setCollectionSize(size);
					n.setToEmployee(res.getBody());
			}
					);
		}
		System.out.println(noteVO);
		return noteVO;				
	}

	@Override
	public List<SentNoteVO> getAllRecievedNotes(String authorization) {
		String token = authorization.substring(7);

		System.out.println(jwtUtil.extractAllClaims(token));

		long id = jwtUtil.extractAllClaims(token).get("id", Long.class);

		Pageable pageable = PageRequest.of(0, 10, Sort.by("dateTime").ascending());


		Page<Note> page = noteRepo.findByToEmployeeId(3, pageable);
		long size=page.getTotalElements();
		System.out.println("size"+size);
		List<SentNoteVO> noteVO = new ArrayList<>();
		List<Note> notes = null;
		if (page.hasContent()) {
			notes = page.getContent();

			notes.stream().forEach(emp -> {
				noteVO.add(mapper.noteTosentNoteVO(emp));

			});
			noteVO.forEach(n -> {
					String url="http://localhost:8080/user/employee/{id}";
					HttpHeaders headers=new HttpHeaders();
					headers.add("Authorization",authorization);
					HttpEntity<String> entity=new HttpEntity<String>(headers);
					ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class,n.getFromEmployeeId());
					n.setCollectionSize(size);
					n.setFromEmployee(res.getBody());
			}
					);
		}
		System.out.println(noteVO);
		return noteVO;
	}
	
	@Override
	public void deleteSentNote(long id) {
		Optional<Note> note=noteRepo.findById(id);
		Note updatedNote=null;
		if(note.isPresent()) {
			updatedNote=note.get();
			updatedNote.setFromEmployeeId(0);
		}
		noteRepo.save(updatedNote);
		System.out.println("");
}

	@Override
	public String sendNotesResponse(long id, String authorization, String responseMsg) {
//		String token = authorization.substring(7);
//
//		System.out.println(jwtUtil.extractAllClaims(token));
//
//		long empId = jwtUtil.extractAllClaims(token).get("id", Long.class);

		
		NoteResponse noteResponse=new NoteResponse();
		
		Optional<Note> note=noteRepo.findById(id);
		if(note.isPresent()) {
			if(!responseMsg.equals(null)) {	
				noteResponse.setResponseMessage(responseMsg);
				noteResponse.setNote(note.get());
				noteResponseRepo.save(noteResponse);
				return "Note send succucessfully!";
			}
		}	
		return "Error while sending Note";

	}

	@Override
	public List<SentNoteVO> getAllRecievedNotes1(String authorization, String pageNo) {
		String token = authorization.substring(7);

		System.out.println(jwtUtil.extractAllClaims(token));

		long id = jwtUtil.extractAllClaims(token).get("id", Long.class);

		Pageable pageable = PageRequest.of(Integer.parseInt(pageNo), 10, Sort.by("dateTime").ascending());

		Page<Note> page = noteRepo.findByToEmployeeId(id, pageable);
		long size=page.getTotalElements();
		System.out.println("size"+size);
		List<SentNoteVO> noteVO = new ArrayList<>();
		List<Note> notes = null;
		if (page.hasContent()) {
			notes = page.getContent();

			notes.stream().forEach(emp -> {
				noteVO.add(mapper.noteTosentNoteVO(emp));

			});
			noteVO.forEach(n -> {
					String url="http://localhost:8080/user/employee/{id}";
					HttpHeaders headers=new HttpHeaders();
					headers.add("Authorization",authorization);
					HttpEntity<String> entity=new HttpEntity<String>(headers);
					ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class,n.getFromEmployeeId());
					n.setCollectionSize(size);
					n.setFromEmployee(res.getBody());
			}
					);
		}
		System.out.println(noteVO);
		return noteVO;
	}

	@Override
	public List<NoteResponse> getSendNotesResponse(long id,String pageNo) {
		
		Pageable pageable = PageRequest.of(Integer.parseInt(pageNo), 10,
				Sort.by("responseDateTime").ascending());
		
		Note note=noteRepo.findById(id).get();
		
		Page<NoteResponse> page=noteResponseRepo.findAllByNote(note,pageable);
		List<NoteResponse> noteResponse = null;
		if (page.hasContent()) {
			noteResponse = page.getContent();
		}
		return noteResponse;
	}
	@Override
	public void deleteReceivedNote(long id) {
		Optional<Note> note=noteRepo.findById(id);
		Note updatedNote=null;
		if(note.isPresent()) {
			updatedNote=note.get();
			updatedNote.setToEmployeeId(0);
		}
		noteRepo.save(updatedNote);
		System.out.println("");
}
}
