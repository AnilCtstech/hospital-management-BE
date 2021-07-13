package com.citiustech.hms.inboxmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.inboxmanagement.dto.SendNoteVO;
import com.citiustech.hms.inboxmanagement.entity.Note;
import com.citiustech.hms.inboxmanagement.mapper.MapStructMapper;
import com.citiustech.hms.inboxmanagement.repository.NoteRepository;

@Service
public class NoteServiceImpl implements  NotesService {

	
	@Autowired
	private NoteRepository noteRepo;
	
	@Autowired
	protected MapStructMapper mapper;
	
	@Override
	public String sendNotes(String authorization, SendNoteVO sendNotes) {
		
		//System.out.println("authorization"+authorization);
		System.out.println(sendNotes);
		
		sendNotes.setToEmployeeId(0);
		Note note=mapper.sendNoteVOToNote(sendNotes);
		noteRepo.save(note);
		return "Note send succucessfully!";
	}

}
