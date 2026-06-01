package br.com.lanchonete.dao;
import br.com.lanchonete.util.JPAUtil;
import br.com.lanchonete.model.Produto;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ProdutoDAO {
    public void salvar(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void atualizar(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void excluir(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Produto produto = em.find(Produto.class, id);

            if (produto != null) {
                em.remove(produto);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Produto buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public List<Produto> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Produto p",
                    Produto.class
            ).getResultList();
        } finally {
            em.close();
        }
    }    
}
