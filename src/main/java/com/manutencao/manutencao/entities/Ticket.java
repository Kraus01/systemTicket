package com.manutencao.manutencao.entities;

import java.io.Serializable;
import java.time.Instant;

import com.manutencao.manutencao.entities.enums.Prioridade;
import com.manutencao.manutencao.entities.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "ticket")
public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (unique = true)
	private String solicitante;
	@Column (nullable = false)
	private String departamento;	
	private String StatusManutencao;
	private Status statusAtual;
	private Prioridade prioridade;
	private String tipoDeManutencao;
	private Boolean active;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataDeCriacao;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataDeTermino;
	
	@Column (columnDefinition = "TEXT")
	private String discricaoDoProblema;
	@ManyToOne
	@JoinColumn (name = "manutentor_id")
	private Manutentor manutentor;
		
	public Ticket () {}

	public Ticket(Long id, String solicitante, String departamento, String statusManutencao, Status statusAtual,
			Prioridade prioridade, String tipoDeManutencao, Instant dataDeCriacao, Instant dataDeTermino,
			Manutentor manutentor, Boolean active) {
		this.id = id;
		this.solicitante = solicitante;
		this.departamento = departamento;
		StatusManutencao = statusManutencao;
		this.statusAtual = statusAtual;
		this.prioridade = prioridade;
		this.tipoDeManutencao = tipoDeManutencao;
		this.dataDeCriacao = dataDeCriacao;
		this.dataDeTermino = dataDeTermino;
		this.manutentor = manutentor;
		this.active = active;
	}
}