package com.manutencao.manutencao.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.manutencao.manutencao.dto.TicketDTO;
import com.manutencao.manutencao.services.TicketService;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	
	@GetMapping
	public ResponseEntity<Page<TicketDTO>> findByAll(Pageable pageable){
		Page<TicketDTO> dto = ticketService.findByAll(pageable);
		return ResponseEntity.ok(dto);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<TicketDTO>finById(@PathVariable Long id){
		TicketDTO dto = ticketService.findById(id);
		return ResponseEntity.ok(dto);
	}
	@PostMapping
	public ResponseEntity<TicketDTO> insert(@RequestBody TicketDTO dto){
		dto = ticketService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	@PutMapping (value = "/{id}") 
	public ResponseEntity<TicketDTO> softDelete (@PathVariable Long id, @RequestBody TicketDTO dto){
		 dto = ticketService.softDelete(id, dto);
		return ResponseEntity.ok(dto);
	}

}
