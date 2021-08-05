package com.citiustech.hms.inboxmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.citiustech.hms.inboxmanagement.dto.SendNoteVO;
import com.citiustech.hms.inboxmanagement.dto.SentNoteVO;
import com.citiustech.hms.inboxmanagement.entity.NoteResponse;
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
	public String sendNotes(@RequestHeader("authorization") String authorization,
			@RequestBody SendNoteVO sendNotes) {
		String msg=notesService.sendNotes(authorization,sendNotes);	

		return msg;

	}
	
	@PostMapping("/send-note-response/{id}")
	public String sendNoteResponse(@PathVariable long id,@RequestHeader("authorization") String authorization,
			@RequestBody String responseMsg) {
		String msg=notesService.sendNotesResponse(id,authorization,responseMsg);	
		return msg;
		
	}
	
	@GetMapping("/sent-note")
	public ResponseEntity<List<SentNoteVO>> getAllSentNotes(@RequestParam String page,@RequestHeader("authorization") String authorization){
		List<SentNoteVO> notes=notesService.getAllSentNotes(authorization,page);	
		return ResponseEntity.ok(notes);
	}
	
//	@GetMapping("/received-note/")
//	public ResponseEntity<List<SentNoteVO>> getAllReceivedNotes(@RequestHeader("authorization") String authorization){
//		List<SentNoteVO> notes=notesService.getAllRecievedNotes(authorization);	
//		return ResponseEntity.ok(notes);
//	}
	
	@DeleteMapping("/sent/delete/{id}")
	public String deleteNote(@PathVariable long id) {
		notesService.deleteSentNote(id);
		return "NOTE DELETED";
	}
	@GetMapping("/received-note")
	public ResponseEntity<List<SentNoteVO>> getAllReceivedNotes(@RequestParam String page,@RequestHeader("authorization") String authorization){
		List<SentNoteVO> notes=notesService.getAllRecievedNotes1(authorization,page);	
		return ResponseEntity.ok(notes);
	}
	
	@GetMapping("/send-note-response/{id}")
	public ResponseEntity<List<NoteResponse>> getSendNoteResponse(@RequestParam String page,@PathVariable Long id) {
		List<NoteResponse> noteResponse=notesService.getSendNotesResponse(id,page);	
		if(!noteResponse.equals(null)) {
			return new ResponseEntity<List<NoteResponse>>(noteResponse, HttpStatus.OK);
		}
		return new ResponseEntity<List<NoteResponse>>(noteResponse, HttpStatus.NO_CONTENT);		
	}
	
	
	@DeleteMapping("/received/delete/{id}")
	public String deleteReceivedNote(@PathVariable long id) {
		notesService.deleteReceivedNote(id);
		return "NOTE DELETED";
	}

	
}
