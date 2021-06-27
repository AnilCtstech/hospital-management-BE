package com.citiustech.hms.inboxmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.inboxmanagement.dto.SendNoteDto;
import com.citiustech.hms.inboxmanagement.service.NotesService;

@RestController
@RequestMapping("/note")
public class NoteController {
	@Autowired
	private NotesService notesService;
	
	@PostMapping("/sendNote")
	public String sendNotes(
			@RequestHeader("authorization") String authorization,	@RequestBody SendNoteDto sendNotes) {
		
		notesService.sendNotes(authorization,sendNotes);
	
		
		return "ok";
		
	}
}
