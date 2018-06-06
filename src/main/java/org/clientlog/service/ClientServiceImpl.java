package org.clientlog.service;

import org.clientlog.component.PageableHelper;
import org.clientlog.domain.Client;
import org.clientlog.dto.ClientDTO;
import org.clientlog.dto.ClientRequest;
import org.clientlog.exception.UniqueConstraintViolationException;
import org.clientlog.repository.ClientRepository;
import org.clientlog.search.specification.ClientSearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private PageableHelper pageableHelper;

    @Override
    public List<ClientDTO> list(ClientRequest searchRequest) {
        Specification<Client> search = new ClientSearchSpecification(searchRequest);
        Pageable pageable = pageableHelper.asPageable(searchRequest);
        Page<Client> clients = repository.findAll(search, pageable);
        return clients.stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClientDTO find(Long id) {
        Optional<Client> client = repository.findById(id);
        return client.map(ClientDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public ClientDTO save(ClientDTO clientDTO) {
        Assert.hasLength(clientDTO.getEmail(), "Email required");
        Client byEmail = repository.findByEmail(clientDTO.getEmail());
        if (byEmail != null && !byEmail.getId().equals(clientDTO.getId())) {
            throw new UniqueConstraintViolationException();
        }
        Client savedClient = repository.save(new Client(clientDTO));
        return new ClientDTO(savedClient);
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }
}
