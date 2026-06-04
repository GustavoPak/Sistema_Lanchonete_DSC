package br.com.lanchonete.controller;

import br.com.lanchonete.dao.CategoriaDAO;
import br.com.lanchonete.model.Categoria;
import java.util.List;

public class CategoriaController {
    
    private final CategoriaDAO dao;

    public CategoriaController() {
        this.dao = new CategoriaDAO();
    }
    
    public void salvar(String nome) {

        Categoria categoria = new Categoria();

        categoria.setNome(nome);

        dao.salvar(categoria);
    }
    
    public List<Categoria> listar() {
        return dao.listarTodos();
    }
    
    public void excluir(Integer id) {
        dao.excluir(id);
    }
    
    public void atualizar(Integer id, String nome) {

        Categoria categoria =
                dao.buscarPorId(id);

        if (categoria != null) {

            categoria.setNome(nome);

            dao.atualizar(categoria);
        }
    }
   
}
