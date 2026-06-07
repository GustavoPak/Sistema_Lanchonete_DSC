/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lanchonete.controller;

import br.com.lanchonete.dao.ItemPedidoDAO;
import br.com.lanchonete.dao.PedidoDAO;
import br.com.lanchonete.dao.ProdutoDAO;

import br.com.lanchonete.model.ItemPedido;
import br.com.lanchonete.model.Pedido;
import br.com.lanchonete.model.Produto;

import java.util.List;


/**
 *
 * @author joao
 */

public class ItemPedidoController {

    private final ItemPedidoDAO itemDAO;
    private final PedidoDAO pedidoDAO;
    private final ProdutoDAO produtoDAO;

    public ItemPedidoController() {
        itemDAO = new ItemPedidoDAO();
        pedidoDAO = new PedidoDAO();
        produtoDAO = new ProdutoDAO();
    }

    public void salvar(
            Integer pedidoId,
            Integer produtoId,
            Integer quantidade) {

        Pedido pedido =
                pedidoDAO.buscarPorId(pedidoId);

        if (pedido == null) {
            throw new IllegalArgumentException(
                    "Pedido não encontrado.");
        }

        Produto produto =
                produtoDAO.buscarPorId(produtoId);

        if (produto == null) {
            throw new IllegalArgumentException(
                    "Produto não encontrado.");
        }

        ItemPedido item = new ItemPedido();

        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(quantidade);

        itemDAO.salvar(item);
    }

    public void atualizar(
            Integer id,
            Integer pedidoId,
            Integer produtoId,
            Integer quantidade) {

        ItemPedido item =
                itemDAO.buscarPorId(id);

        if (item == null) {
            throw new IllegalArgumentException(
                    "Item não encontrado.");
        }

        Pedido pedido =
                pedidoDAO.buscarPorId(pedidoId);

        Produto produto =
                produtoDAO.buscarPorId(produtoId);

        if (pedido == null || produto == null) {
            throw new IllegalArgumentException(
                    "Pedido ou Produto inválido.");
        }

        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(quantidade);

        itemDAO.atualizar(item);
    }

    public void excluir(Integer id) {
        itemDAO.excluir(id);
    }

    public ItemPedido buscarPorId(Integer id) {
        return itemDAO.buscarPorId(id);
    }

    public List<ItemPedido> listar() {
        return itemDAO.listarTodos();
    }
}
