package com.citiustech.hms.inboxmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.inboxmanagement.entity.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
	Optional<Slot> findBySlots(String name);
}
