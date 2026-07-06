/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lanchonete.dao;
import br.com.lanchonete.util.JPAUtil;
import br.com.lanchonete.model.Cliente;
import jakarta.persistence.EntityManager;
import java.util.List;
import jakarta.persistence.NoResultException;
/**
 *
 * @author joao
 */
public class ClienteDAO {
    
    public void salvar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void atualizar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void excluir(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            
            Cliente cliente = em.find(Cliente.class, id);
            
            if (cliente != null) {
                em.remove(cliente);
            }
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public Cliente buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Cliente> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
           return em.createQuery(
                   "SELECT c FROM Cliente c",
                   Cliente.class
           ).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Cliente buscarPorNome(String nome) {

    EntityManager em = JPAUtil.getEntityManager();

    try {

        return em.createQuery(
                "SELECT c FROM Cliente c WHERE LOWER(c.nome) = LOWER(:nome)",
                Cliente.class
        )
        .setParameter("nome", nome)
        .getSingleResult();

    } catch (NoResultException e) {
        return null;
    } finally {
        em.close();
    }
    }
}
