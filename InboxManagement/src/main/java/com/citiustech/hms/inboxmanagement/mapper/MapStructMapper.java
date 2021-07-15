package com.citiustech.hms.inboxmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.citiustech.hms.inboxmanagement.dto.SendNoteVO;
import com.citiustech.hms.inboxmanagement.dto.SentNoteVO;
import com.citiustech.hms.inboxmanagement.entity.Note;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	public SendNoteVO noteToSendNoteVO(Note note);

	public Note sendNoteVOToNote(SendNoteVO sendNoteVo);
	
	@Mapping(target ="toEmployee",ignore = true)
	@Mapping(target ="fromEmployee",ignore = true)
	public SentNoteVO noteTosentNoteVO(Note note);

}
