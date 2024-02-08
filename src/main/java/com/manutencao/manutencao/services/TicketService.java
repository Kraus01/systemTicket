package com.manutencao.manutencao.services;

import java.util.List;

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
	
	
	/* busca um ticket pela pelo id, e preciso trata a exception*/
	@Transactional(readOnly = true)
	public TicketDTO findById(Long id) {
		Ticket ticket = repository.findById(id).orElseThrow();;
		return new TicketDTO(ticket);
	}
	/*Busca apenas os tickets ativos*/
	@Transactional(readOnly = true)
	public List<TicketDTO> findByActiveTicket (TicketDTO dto) {
		List<Ticket> listTicket = repository.findActiveTicket();
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
	public TicketDTO softDelete (Long id, TicketDTO dto) {
		Ticket entity = repository.getReferenceById(id);
		entity.setActive(dto.getActive());
		entity = repository.save(entity);
		return new TicketDTO(entity);
		
	}
	
	
	@Transactional
	public TicketDTO insert(TicketDTO dto) {
		Ticket entity = new Ticket();
		CopiaTicketDTO(dto, entity);
		return new TicketDTO(entity);
	}
	
	//Metodo para receber os dados do DTO e grava na entidade
	private void CopiaTicketDTO (TicketDTO dto, Ticket entity) {
		entity.setSolicitante(dto.getSolicitante());
		entity.setDepartamento(dto.getDepartamento());
		entity.setPrioridade(dto.getPrioridade());
		entity.setDiscricaoDoProblema(dto.getDiscricaoDoProblema());
		entity.setDataDeCriacao(dto.getDataDeCriacao());
		entity = repository.save(entity);
		
	}
}
	