package com.citiustech.hms.inboxmanagement.service;

import com.citiustech.hms.inboxmanagement.dto.SendNoteVO;

public interface NotesService {

	public String sendNotes(String authorization, SendNoteVO sendNotes);

	
}
