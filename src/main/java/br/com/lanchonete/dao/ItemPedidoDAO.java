/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lanchonete.dao;
import br.com.lanchonete.util.JPAUtil;
import br.com.lanchonete.model.ItemPedido;
import jakarta.persistence.EntityManager;
import java.util.List;
/**
 *
 * @author joao
 */
public class ItemPedidoDAO {
    
    public void salvar(ItemPedido itemPedido) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(itemPedido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void atualizar(ItemPedido itemPedido){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(itemPedido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void excluir(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
           em.getTransaction().begin();
           
      
            ItemPedido itemPedido = em.find(ItemPedido.class, id);
            
            if (itemPedido != null) {
                em.remove(itemPedido);
            }
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public ItemPedido  buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.find(ItemPedido.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<ItemPedido> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.createQuery(
                 "SELECT i.p FROM ItemPedido i.p",
                    ItemPedido.class 
            ).getResultList();
        } finally {
            em.close();
        }
    }
}
