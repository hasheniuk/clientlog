package org.clientlog.controller;

import org.clientlog.dto.ClientDTO;
import org.clientlog.dto.ClientRequest;
import org.clientlog.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientDTO> list(ClientRequest searchRequest) {
        return clientService.list(searchRequest);
    }

    @GetMapping("/{id}")
    public ClientDTO find(@PathVariable Long id) {
        return clientService.find(id);
    }

    @PostMapping
    public ClientDTO create(@RequestBody ClientDTO clientDTO) {
        return clientService.save(clientDTO);
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        return clientService.save(clientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
