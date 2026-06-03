/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lanchonete.dao;
import br.com.lanchonete.util.JPAUtil;
import br.com.lanchonete.model.Pedido;
import jakarta.persistence.EntityManager;
import java.util.List;
/**
 *
 * @author joao
 */
public class PedidoDAO {
    
    public void salvar(Pedido pedido) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void atualizar(Pedido pedido){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(pedido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void excluir(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
           em.getTransaction().begin();
           
      
            Pedido pedido = em.find(Pedido.class, id);
            
            if (pedido != null) {
                em.remove(pedido);
            }
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public Pedido  buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Pedido> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.createQuery(
                 "SELECT p FROM Pedido p",
                    Pedido.class 
            ).getResultList();
        } finally {
            em.close();
        }
    }
    
}
