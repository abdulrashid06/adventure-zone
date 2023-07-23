package com.adventure.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventure.exception.AdminException;
import com.adventure.exception.CustomerException;
import com.adventure.exception.NoRecordFoundException;
import com.adventure.model.Activity;
import com.adventure.model.Category;
import com.adventure.model.Customer;
import com.adventure.model.Ticket;
import com.adventure.repository.TicketRespository;


@Service
public class TicketServiceImplements implements TicketServiceInterface {

	@Autowired
	private TicketRespository ticketRepositry;
	
	private CategoryServiceImplements csi;
	
	private CustomerServiceImplements cusi;
	
	@Override
	public Ticket generateTicket(Ticket ticket, Integer cusId, List<String> catNames) {
	    if (ticket == null) throw new CustomerException("The ticket you have provided is null");
	    Optional<Ticket> act = ticketRepositry.findById(ticket.getTicketId());
	    if (act.isPresent()) throw new CustomerException("Ticket already exists");
	    Customer currCus = cusi.viewCustomerById(cusId);

	    // Loop through the list of category names and add each category to the ticket
	    for (String catName : catNames) {
	        Category cat = csi.viewAllcategory()
	                .stream()
	                .filter(a -> a.getCatName().equals(catName))
	                .findFirst()
	                .orElseThrow(() -> new CustomerException("Category not found: " + catName));

	        ticket.setCustomer(currCus);
	        currCus.getTicketList().add(ticket);
	        cat.getTicket().add(ticket);
	        ticket.getCategory().add(cat);
	    }

	    return ticketRepositry.save(ticket);
	}

	@Override
	public Ticket updateTicket(Integer ticketId , List<String> catNames) {
		
		Ticket ticket = ticketRepositry.findById(ticketId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+ticketId));
		
		
		if(ticket.isExpired()==true) throw new CustomerException("Ticket is deleted");

		double sum=0;
		  for (String catName : catNames) {
			 
		        Category cat = csi.viewAllcategory()
		                .stream()
		                .filter(a -> a.getCatName().equals(catName))
		                .findFirst()
		                .orElseThrow(() -> new CustomerException("Category not found: " + catName));
		        
		        double categoryPriceSum = cat.getActivities()
		                .stream()
		                .mapToDouble(Activity::getCharges)
		                .sum();
		        
		       sum +=categoryPriceSum ;
		        cat.getTicket().add(ticket);
		        ticket.getCategory().add(cat);
		        
		    }
		  
		ticket.setPrice(sum);
		
		return ticketRepositry.save(ticket);
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
