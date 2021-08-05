package com.citiustech.hms.inboxmanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.hms.inboxmanagement.entity.Note;
import com.citiustech.hms.inboxmanagement.entity.NoteResponse;

public interface NoteResponseRepository extends JpaRepository<NoteResponse, Long> {

	Optional<NoteResponse> findByNote(long note);

	Page<NoteResponse> findAllByNote(Note note, Pageable pageable);
	
}
