package com.manutencao.manutencao.dto;

import java.time.Instant;

import com.manutencao.manutencao.entities.Ticket;
import com.manutencao.manutencao.entities.enums.Prioridade;
import com.manutencao.manutencao.entities.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {
	
	private Long id;
	private String solicitante;
	private String departamento;	
	private String StatusManutencao;
	private Status statusAtual;
	private Prioridade prioridade;
	private String discricaoDoProblema;
	private String tipoDeManutencao;
	private Instant dataDeCriacao;
	private Instant dataDeTermino;
	private Boolean active;
	

	public TicketDTO(Long id, String solicitante, String departamento, String statusManutencao, Status statusAtual,
			Prioridade prioridade, String discricaoDoProblema, String tipoDeManutencao, Instant dataDeCriacao, Instant dataDeTermino, Boolean active) {
		this.id = id;
		this.solicitante = solicitante;
		this.departamento = departamento;
		StatusManutencao = statusManutencao;
		this.statusAtual = statusAtual;
		this.prioridade = prioridade;
		this.discricaoDoProblema = discricaoDoProblema;
		this.tipoDeManutencao = tipoDeManutencao;
		this.dataDeCriacao = dataDeCriacao;
		this.dataDeTermino = dataDeTermino;
		this.active = true;
		
	}

	public TicketDTO(Ticket entity) {
		id = entity.getId();
		solicitante = entity.getSolicitante();
		departamento = entity.getDepartamento();
		StatusManutencao = entity.getStatusManutencao();
		statusAtual = entity.getStatusAtual();
		prioridade = entity.getPrioridade();
		discricaoDoProblema = entity.getDiscricaoDoProblema();
		tipoDeManutencao = entity.getTipoDeManutencao();
		dataDeCriacao = entity.getDataDeCriacao();
		dataDeTermino = entity.getDataDeTermino();
		active = entity.getActive();
	
	}
	
}
