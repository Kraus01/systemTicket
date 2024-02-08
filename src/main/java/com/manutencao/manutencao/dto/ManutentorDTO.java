package com.manutencao.manutencao.dto;

import java.util.ArrayList;
import java.util.List;

import com.manutencao.manutencao.entities.Manutentor;
import com.manutencao.manutencao.entities.Ticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManutentorDTO {
	
	private Long id;
	private String nome;
	private String cargo;
	private List <Ticket> ticket = new ArrayList<>();
	
	public ManutentorDTO(Long id, String nome, String cargo, List<Ticket> ticket) {
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.ticket = ticket;
	}
	
	public ManutentorDTO (Manutentor entity) {
		id = entity.getId();
		nome = entity.getNome();
		cargo = entity.getCargo();
		ticket = entity.getTicket();
		
	}
	
}
