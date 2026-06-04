package br.com.lanchonete;

import br.com.lanchonete.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class Lanchonete {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        
        System.out.print("Conectado com sucesso!");
        em.close();
    }
}
