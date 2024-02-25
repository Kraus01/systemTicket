package com.manutencao.manutencao.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manutencao.manutencao.dto.TicketDTO;
import com.manutencao.manutencao.entities.Ticket;
import com.manutencao.manutencao.repositories.TicketRepository;
import com.manutencao.manutencao.repositories.manutentorRepository;

@Service
public class TicketService {
	
	@Autowired
	TicketRepository repository;
	@Autowired
	manutentorRepository manutentorRepository;
	@Autowired 
	EmailNotificationService emailNotificationService;
	
	
	
	/* busca um ticket pela pelo id, e preciso trata a exception*/
	@Transactional(readOnly = true)
	public TicketDTO findById(Long id) {
		Ticket ticket = repository.findById(id).orElseThrow();;
		return new TicketDTO(ticket);
	}
	/*Busca apenas os tickets ativos*/
	@Transactional(readOnly = true)
	public List<TicketDTO> findByActiveTicket() {
		List<Ticket> listTicket = repository.findallActiveTicket();
		return listTicket.stream().map(x -> new TicketDTO(x)).toList();
	}
	
	/* metodo para retorna uma lista paginada dos tickts*/
	@Transactional(readOnly = true)
	public Page<TicketDTO> findByAll (Pageable pageable){
		Page <Ticket> result = repository.findAll(pageable);
		return result.map(x -> new TicketDTO(x));
	}
	
	/* esse motodo desativa o ticket no luga de deleta direto*/
	@Transactional
	public void softDelete (Long id) {
		Optional<Ticket> entity = repository.findById(id);
		if (entity.isPresent()) {
			Ticket ticket = entity.get();
			ticket.setActive(false);
		}
	}
	
	@Transactional
	public TicketDTO insert(TicketDTO dto) {
		Ticket entity = new Ticket();
		CopyTicketDTO(dto, entity);
		
		return new TicketDTO(entity);
	}
	
	//Metodo para receber os dados do DTO e grava na entidade
	private void CopyTicketDTO (TicketDTO dto, Ticket entity) {
		entity.setSolicitante(dto.getSolicitante());
		entity.setDepartamento(dto.getDepartamento());
		entity.setPrioridade(dto.getPrioridade());
		entity.setDiscricaoDoProblema(dto.getDiscricaoDoProblema());
		entity.setDataDeCriacao(Instant.now());
		entity.setActive(true);
		entity = repository.save(entity);
		emailNotificationService.notifyNewTicketCreated(entity);
	}
	
}	