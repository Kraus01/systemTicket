package com.manutencao.manutencao.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	/*@GetMapping
	public ResponseEntity<Page<TicketDTO>> findByAll(Pageable pageable){
		Page<TicketDTO> dto = ticketService.findByAll(pageable);
		return ResponseEntity.ok(dto);
	}*/
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TicketDTO>finById(@PathVariable Long id){
		TicketDTO dto = ticketService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<TicketDTO>>findByActive(){
		List<TicketDTO> dto = ticketService.findByActiveTicket();
		return ResponseEntity.ok(dto);
	}
	@PostMapping
	public ResponseEntity<TicketDTO> insert(@RequestBody TicketDTO dto){
		dto = ticketService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	@DeleteMapping (value = "/{id}") 
	public ResponseEntity<Void> softDelete (@PathVariable Long id){
		 ticketService.softDelete(id);
		 return ResponseEntity.noContent().build();
	}
}