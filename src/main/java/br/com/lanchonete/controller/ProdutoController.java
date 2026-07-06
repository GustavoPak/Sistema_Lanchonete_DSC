package br.com.lanchonete.controller;

import br.com.lanchonete.dao.CategoriaDAO;
import br.com.lanchonete.dao.ProdutoDAO;
import br.com.lanchonete.model.Categoria;
import br.com.lanchonete.model.Produto;
import java.util.List;

public class ProdutoController {

    private final ProdutoDAO produtoDAO;
    private final CategoriaDAO categoriaDAO;

    public ProdutoController() {
        produtoDAO = new ProdutoDAO();
        categoriaDAO = new CategoriaDAO();
    }

    public void salvar(String nome,Double preco,String descricao,Boolean ativo,Integer categoriaId) {

        Categoria categoria =
                categoriaDAO.buscarPorId(categoriaId);

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "Categoria não encontrada.");
        }

        Produto produto = new Produto();

        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setDescricao(descricao);
        produto.setAtivo(ativo);
        produto.setCategoria(categoria);

        produtoDAO.salvar(produto);
    }

    public void atualizar(Integer id,String nome,Double preco,String descricao,Boolean ativo,Integer categoriaId) {

        Produto produto =
                produtoDAO.buscarPorId(id);

        if (produto == null) {
            throw new IllegalArgumentException(
                    "Produto não encontrado.");
        }

        Categoria categoria =
                categoriaDAO.buscarPorId(categoriaId);

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "Categoria não encontrada.");
        }

        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setDescricao(descricao);
        produto.setAtivo(ativo);
        produto.setCategoria(categoria);

        produtoDAO.atualizar(produto);
    }

    public void excluir(Integer id) {
        produtoDAO.excluir(id);
    }

    public Produto buscarPorId(Integer id) {
        return produtoDAO.buscarPorId(id);
    }

    public List<Produto> listar() {
        return produtoDAO.listarTodos();
    }
    
    public List<Produto> buscarPorNome(String nome) {
        return produtoDAO.buscarPorNome(nome);
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        return produtoDAO.buscarPorCategoria(categoria);
    }
    
    public Produto buscarPorNomeExato(String nome) {
    return produtoDAO.buscarPorNomeExato(nome);
}
}