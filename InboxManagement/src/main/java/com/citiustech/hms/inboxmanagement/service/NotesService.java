package com.citiustech.hms.inboxmanagement.service;

import java.util.List;

import com.citiustech.hms.inboxmanagement.dto.SendNoteVO;
import com.citiustech.hms.inboxmanagement.dto.SentNoteVO;
import com.citiustech.hms.inboxmanagement.entity.Note;
import com.citiustech.hms.inboxmanagement.entity.NoteResponse;

public interface NotesService {

	public String sendNotes(String authorization, SendNoteVO sendNotes);

	List<SentNoteVO> getAllSentNotes(String authorization);

	List<SentNoteVO> getAllRecievedNotes(String authorization);

	void deleteSentNote(long id);

	public String sendNotesResponse(long id, String authorization, String responseMsg);

	public List<SentNoteVO> getAllRecievedNotes1(String authorization, String page);

	public List<NoteResponse> getSendNotesResponse(long id, String page);

	public void deleteReceivedNote(long id);

}
