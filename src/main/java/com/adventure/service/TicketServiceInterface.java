package com.adventure.service;

import java.util.List;


import com.adventure.model.Ticket;

public interface TicketServiceInterface {

//	public Ticket generateTicket(Ticket ticket, Integer cusId, String catName);
//	public Ticket updateTicket(Integer ticketId,Ticket ticket);
	public void DeleteTicket(Integer ticketId);
	public List<Ticket> viewAllticket();
	public double calculateBill(Integer custmerId);
	Ticket generateTicket(Ticket ticket, Integer cusId, List<String> catNames);
	Ticket updateTicket(Integer ticketId, List<String> catNames);
	
	
}
