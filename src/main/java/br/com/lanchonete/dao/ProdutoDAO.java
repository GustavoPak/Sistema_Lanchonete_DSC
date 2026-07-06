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

    public List<Produto> buscarPorNome(String nome) {
    EntityManager em = JPAUtil.getEntityManager();

     try {
        return em.createQuery(
                "SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE LOWER(:nome)",
                Produto.class
        )
        .setParameter("nome", "%" + nome + "%")
        .getResultList();
        } finally {
        em.close();
        }
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                "SELECT p FROM Produto p WHERE LOWER(p.categoria.nome) LIKE LOWER(:categoria)",
                Produto.class
        )
        .setParameter("categoria", "%" + categoria + "%")
        .getResultList();
        } finally {
        em.close();
        }
    }
    
    public Produto buscarPorNomeExato(String nome) {
    EntityManager em = JPAUtil.getEntityManager();

    try {
        return em.createQuery(
                "SELECT p FROM Produto p WHERE LOWER(p.nome) = LOWER(:nome)",
                Produto.class
        )
        .setParameter("nome", nome)
        .getSingleResult();

    } catch (jakarta.persistence.NoResultException e) {
        return null;
    } finally {
        em.close();
    }
    }
}
