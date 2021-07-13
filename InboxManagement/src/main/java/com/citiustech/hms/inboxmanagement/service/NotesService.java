package com.citiustech.hms.inboxmanagement.service;

import java.util.List;

import com.citiustech.hms.inboxmanagement.dto.SendNoteVO;
import com.citiustech.hms.inboxmanagement.entity.Note;

public interface NotesService {

	public String sendNotes(String authorization, SendNoteVO sendNotes);

	List<Note> getAllNotes(String authorization);

}
