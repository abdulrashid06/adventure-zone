package com.adventure.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventure.exception.AdminException;
import com.adventure.exception.CustomerException;
import com.adventure.exception.NoRecordFoundException;
import com.adventure.model.Activity;
import com.adventure.model.Ticket;
import com.adventure.repository.TicketRespository;


@Service
public class TicketServiceImplements implements TicketServiceInterface {

	@Autowired
	private TicketRespository ticketRepositry;
	
	@Override
	public Ticket generateTicket(Ticket ticket) {
		if(ticket==null) throw new CustomerException("The ticket you have provided is null");
		Optional<Ticket> act = ticketRepositry.findById(ticket.getTicketId());
		if(act.isPresent()) throw new CustomerException("Ticket already exists");
		return ticketRepositry.save(ticket);
	}

	@Override
	public Ticket updateTicket(Integer ticketId, Ticket ticket) {
		Ticket tic = ticketRepositry.findById(ticketId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+ticketId));
		if(tic.isExpired()==true) throw new CustomerException("Ticket is deleted");

		tic.setCategory(ticket.getCategory());
		tic.setPrice(ticket.getPrice());
		return ticketRepositry.save(tic);
	}

	@Override
	public void DeleteTicket(Integer ticketId) {
		Ticket ticket = ticketRepositry.findById(ticketId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+ticketId));
		if(ticket.isExpired()==true) throw new AdminException("Ticket is already deleted");
		ticket.setExpired(true);

	}

	@Override
	public List<Ticket> viewAllticket() {
		List<Ticket> ticket = ticketRepositry.findAll();
		if(ticket.isEmpty()) throw new NoRecordFoundException("Ticket list is empty");
		return ticket;
	}

	@Override
	public double calculateBill(Integer custmerId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
