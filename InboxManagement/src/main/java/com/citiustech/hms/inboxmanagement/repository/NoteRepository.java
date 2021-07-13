package com.citiustech.hms.inboxmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.hms.inboxmanagement.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
