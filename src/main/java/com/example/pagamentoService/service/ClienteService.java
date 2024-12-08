package com.example.pagamentoService.service;

import com.example.pagamentoService.dao.ClienteDAO;
import com.example.pagamentoService.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteDAO clienteDao;

    @Autowired
    public ClienteService(ClienteDAO clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<ClienteDTO> listarClientes() {
        return clienteDao.listarClientes().stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
    }
}
