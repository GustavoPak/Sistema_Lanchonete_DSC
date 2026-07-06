/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lanchonete.controller;

import br.com.lanchonete.dao.ClienteDAO;
import br.com.lanchonete.dao.PedidoDAO;
import br.com.lanchonete.model.Cliente;
import br.com.lanchonete.model.Pedido;

/**
 *
 * @author joao
 */

import java.time.LocalDateTime;
import java.util.List;

public class PedidoController {

    private final PedidoDAO pedidoDAO;
    private final ClienteDAO clienteDAO;

    public PedidoController() {
        pedidoDAO = new PedidoDAO();
        clienteDAO = new ClienteDAO();
    }

    public void salvar(
            Integer clienteId,
            String status,
            Double valorTotal) {

        Cliente cliente =
                clienteDAO.buscarPorId(clienteId);

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Cliente não encontrado.");
        }

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setStatus(status);
        pedido.setValorTotal(valorTotal);
        pedido.setDataHora(LocalDateTime.now());

        pedidoDAO.salvar(pedido);
    }

    public void atualizar(
            Integer id,
            Integer clienteId,
            String status,
            Double valorTotal) {

        Pedido pedido =
                pedidoDAO.buscarPorId(id);

        if (pedido == null) {
            throw new IllegalArgumentException(
                    "Pedido não encontrado.");
        }

        Cliente cliente =
                clienteDAO.buscarPorId(clienteId);

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Cliente não encontrado.");
        }

        pedido.setCliente(cliente);
        pedido.setStatus(status);
        pedido.setValorTotal(valorTotal);

        pedidoDAO.atualizar(pedido);
    }

    public void excluir(Integer id) {
        pedidoDAO.excluir(id);
    }

    public Pedido buscarPorId(Integer id) {
        return pedidoDAO.buscarPorId(id);
    }

    public List<Pedido> listar() {
        return pedidoDAO.listarTodos();
    }
    
    public Pedido buscarPorClienteEStatus(Integer clienteId, String status) {
    return pedidoDAO.buscarPorClienteEStatus(clienteId, status);
}
}