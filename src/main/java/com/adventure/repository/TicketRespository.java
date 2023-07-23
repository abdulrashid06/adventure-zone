package com.adventure.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.adventure.model.Ticket;

public interface TicketRespository extends JpaRepository<Ticket, Integer> {

}
