package com.citiustech.hms.inboxmanagement.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.inboxmanagement.dto.SlotResponseDTO;
import com.citiustech.hms.inboxmanagement.entity.Slot;
import com.citiustech.hms.inboxmanagement.repository.SlotRepository;

@Service
public class SlotService {
	
	@Autowired
	private SlotRepository slotRepository;
	
	public List<Long> getAllSlots(){
		List<Slot> tempList = slotRepository.findAll();
		List<Long> responseList = new ArrayList<>();
		for (Slot slot : tempList) {
			responseList.add(slot.getSlotId());
		}
		
		return responseList;
	}
	
	public String getSlotById(long id) {
		Optional<Slot> optional = slotRepository.findById(id);
		String slots = "";
		if(optional.isPresent()) {
			Slot slot1 = optional.get();
			slots = slot1.getSlots();
		}
		
		return slots;
		
	}
	
	public List<Slot> getSlotByName(String name) {
		Optional<Slot> optional = slotRepository.findBySlots(name);
		List<Slot> slot = new ArrayList<>();
		if(optional.isPresent()) {
			Slot slot1 = optional.get();
			slot.add(slot1);
		}
		return slot;
	}
	
}
