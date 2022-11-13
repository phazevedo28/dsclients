package com.clients.dsclients.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clients.dsclients.dto.ClientDTO;
import com.clients.dsclients.entities.Client;
import com.clients.dsclients.repositories.ClientRepository;
import com.clients.dsclients.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(PageRequest pageRequest) {
		Page<Client> clients = repository.findAll(pageRequest);
		return clients.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> clients = repository.findById(id);
		Client entity = clients.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);

	}

}
