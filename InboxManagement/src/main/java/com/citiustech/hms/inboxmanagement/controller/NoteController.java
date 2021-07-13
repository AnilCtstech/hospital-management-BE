package com.citiustech.hms.inboxmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.citiustech.hms.inboxmanagement.dto.SendNoteVO;
import com.citiustech.hms.inboxmanagement.entity.Note;
import com.citiustech.hms.inboxmanagement.service.NotesService;

@RestController
@RequestMapping("/note")
@CrossOrigin("http://localhost:4200")
public class NoteController {
	@Autowired
	private NotesService notesService;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@PostMapping("/sendNote")
//	public String sendNotes(
//			@RequestHeader("authorization") String authorization,	@RequestBody SendNoteVO sendNotes) {
//		
//		notesService.sendNotes(authorization,sendNotes);
//	
//		
//		return "ok";
//		
//	}
	@PostMapping("/send-note")
	public String sendNotes(
			@RequestHeader("authorization") String authorization,@RequestBody SendNoteVO sendNotes) {

		//		ResponseEntity<Profile[]> response = restTemplate.getForEntity(
//				"http://localhost:8080/user/employee/name", Profile[].class);		
//		
//		response.getBody();
		String msg=notesService.sendNotes(authorization,sendNotes);	
		return msg;
		
	}
	
	@GetMapping("/sent-note")
	public ResponseEntity<List<Note>> getAllNotes(@RequestHeader("authorization") String authorization){
		List<Note> notes=notesService.getAllNotes(authorization);	
		return ResponseEntity.ok(notes);
	}
	
	
}
