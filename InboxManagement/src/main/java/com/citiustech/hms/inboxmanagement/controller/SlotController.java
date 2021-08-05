package com.citiustech.hms.inboxmanagement.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.citiustech.hms.inboxmanagement.dto.AppointmentEmployeeResponseDTO;
import com.citiustech.hms.inboxmanagement.dto.SlotResponseDTO;
import com.citiustech.hms.inboxmanagement.entity.Appointment;
import com.citiustech.hms.inboxmanagement.entity.Slot;
import com.citiustech.hms.inboxmanagement.service.SlotService;

@RestController
@RequestMapping("/slot")
@CrossOrigin("http://localhost:4200")
public class SlotController {
	
	@Autowired
	private SlotService slotService;
	
	@Autowired
	private AppointmentController aptController;
	
	@PostMapping("/available")	
		public List<Slot> getAvailableSlots(@RequestBody String idAndAppointmentDate){
			System.out.println("Appointment Date : "+idAndAppointmentDate.toString());
			//LocalDateTime localDate = LocalDateTime.parse(date);
			List<Long> responseList = null;
			if(aptController.getSlotIdByEmployeeIdAndAppointmentDate(idAndAppointmentDate).size()>0) {
				responseList = aptController.getSlotIdByEmployeeIdAndAppointmentDate(idAndAppointmentDate);
				System.out.println("aptController.getAppointmentsByDate(date) : "+responseList.size());
			}
			List<Long> slotListAll = slotService.getAllSlots();
			System.out.println("slotListAll before : "+slotListAll.size());
			if(aptController.getSlotIdByEmployeeIdAndAppointmentDate(idAndAppointmentDate).size()>0) {
				if(slotListAll.removeAll(responseList)) {
					System.out.println("slotListAll after : "+slotListAll.size());
				}
			}
			
			List<Slot> slotListAvail = new LinkedList<>();
			for (Long slotId : slotListAll) {
				Slot slot = new Slot();
				slot.setSlotId(slotId);
				System.out.println("Slot : "+ slotId);
				slot.setSlots(getSlotById(slotId));
				slotListAvail.add(slot);
			}
			return slotListAvail;
		}
	
	@PostMapping("/id")
	public String getSlotById(@RequestBody long id) {
		return slotService.getSlotById(id);
	}
	
	@PostMapping("/slotname")
	public List<Slot> getSlotBySlotName(@RequestBody String name) {
		List<Slot> slots = slotService.getSlotByName(name);
		return slots;
	}

}
