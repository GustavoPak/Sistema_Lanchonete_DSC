package br.com.lanchonete.dao;

import br.com.lanchonete.model.Categoria;
import br.com.lanchonete.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CategoriaDAO {
    
    public void Salvar(Categoria categoria){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        
        em.persist(categoria);
        em.getTransaction().commit();
        
        em.close();
    }
    
    public void atualizar(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(categoria);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
     
    public void excluir(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Categoria categoria = em.find(Categoria.class, id);

            if (categoria != null) {
               em.createQuery("DELETE FROM Produto p WHERE p.categoria = :categoria")
                    .setParameter("categoria", categoria)
                    .executeUpdate();

               em.remove(categoria);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public Categoria buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Categoria> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery("SELECT c FROM Categoria c", Categoria.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
