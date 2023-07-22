package com.adventure.service;

import java.util.List;


import com.adventure.model.Ticket;

public interface TicketServiceInterface {

	public Ticket generateTicket(Ticket ticket);
	public Ticket updateTicket(Integer ticketId,Ticket ticket);
	public void DeleteTicket(Integer ticketId);
	public List<Ticket> viewAllticket();
	public double calculateBill(Integer custmerId);
	
}
