package com.manutencao.manutencao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manutencao.manutencao.entities.Ticket;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	@Query("SELECT t FROM Ticket t WHERE t.active = true")
	List<Ticket> findActiveTicket();
}
