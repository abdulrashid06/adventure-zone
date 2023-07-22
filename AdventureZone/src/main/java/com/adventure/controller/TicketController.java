package com.adventure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adventure.model.Ticket;
import com.adventure.service.TicketServiceImplements;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adventureZone")
public class TicketController {
    
    @Autowired
    private TicketServiceImplements ticketService;
	
	
	@PostMapping("/tickets")
	public ResponseEntity<Ticket> addTicket(@Valid @RequestBody Ticket ticket){
		
		return new ResponseEntity<Ticket>(ticketService.generateTicket(ticket),HttpStatus.CREATED);
		
	}
	@PutMapping("/tickets/{ticketId}")
	public ResponseEntity<Ticket> updateTicket(@PathVariable Integer ticketId,@RequestBody Ticket ticket){
		
		return new ResponseEntity<Ticket>(ticketService.updateTicket(ticketId,ticket),HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping("/tickets/{ticketId}")
	public ResponseEntity<String> deleteTicket(@PathVariable Integer ticketId){
		ticketService.DeleteTicket(ticketId);
		return new ResponseEntity<String>("",HttpStatus.ACCEPTED);
	}
    
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> getTicket(){
		return new ResponseEntity<List<Ticket>>(ticketService.viewAllticket(),HttpStatus.OK);
	}
}
