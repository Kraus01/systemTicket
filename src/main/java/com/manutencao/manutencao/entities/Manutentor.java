package com.manutencao.manutencao.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "manutentor")
@Getter
@Setter
public class Manutentor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	@Column (unique = true)
	private String cargo;
	
	@OneToMany(mappedBy = "manutentor", cascade = CascadeType.ALL)
	private List <Ticket> ticket = new ArrayList<>();
	
	public Manutentor () {}

	public Manutentor(Long id, String nome, String cargo) {
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
	}
	
}