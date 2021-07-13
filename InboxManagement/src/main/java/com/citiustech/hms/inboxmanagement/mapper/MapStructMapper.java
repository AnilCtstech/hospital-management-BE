package com.citiustech.hms.inboxmanagement.mapper;

import org.mapstruct.Mapper;

import com.citiustech.hms.inboxmanagement.dto.SendNoteVO;
import com.citiustech.hms.inboxmanagement.entity.Note;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	public SendNoteVO noteToSendNoteVO(Note note);

	public Note sendNoteVOToNote(SendNoteVO sendNoteVo);

}
