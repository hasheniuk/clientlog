package org.clientlog.service;

import org.clientlog.dto.ClientDTO;
import org.clientlog.dto.ClientRequest;

import java.util.List;

public interface ClientService {

    List<ClientDTO> list(ClientRequest searchRequest);
    ClientDTO find(Long id);
    ClientDTO save(ClientDTO clientDTO);
    void delete(long id);
}
