/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lanchonete.controller;

import br.com.lanchonete.dao.ClienteDAO;
import br.com.lanchonete.model.Cliente;
import java.util.List;

/**
 *
 * @author joao
 */
public class ClienteController {
    
    private final ClienteDAO dao;
    
    public ClienteController() {
        dao = new ClienteDAO();
    }
    
    public void salvar(String nome) {

        Cliente cliente = new Cliente();

        cliente.setNome(nome);

        dao.salvar(cliente);
    }

    public void atualizar(Integer id, String nome) {

        Cliente cliente = dao.buscarPorId(id);

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Cliente não encontrado.");
        }

        cliente.setNome(nome);

        dao.atualizar(cliente);
    }

    public void excluir(Integer id) {
        dao.excluir(id);
    }

    public Cliente buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public List<Cliente> listar() {
        return dao.listarTodos();
    }
    
    public Cliente buscarPorNome(String nome) {
    return dao.buscarPorNome(nome);
    }
}
