package br.dev.estudos.jpa;

import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;

public final class AssinaturaService {
    private final EntityManagerFactory fabrica;
    public AssinaturaService(EntityManagerFactory fabrica) { this.fabrica = fabrica; }

    public long contratar(String nome, String email, BigDecimal mensalidade) {
        try (var em = fabrica.createEntityManager()) {
            var transacao = em.getTransaction();
            transacao.begin();
            try {
                var cliente = new Cliente(nome, email);
                em.persist(cliente);
                var assinatura = new Assinatura(cliente, mensalidade);
                em.persist(assinatura);
                transacao.commit();
                return assinatura.getId();
            } catch (RuntimeException falha) {
                if (transacao.isActive()) transacao.rollback();
                throw falha;
            }
        }
    }
    public List<Assinatura> listarAssinaturas() {
        try (var em = fabrica.createEntityManager()) {
            // JOIN FETCH acessa cliente ainda dentro do contexto de persistência.
            return em.createQuery("select a from Assinatura a join fetch a.cliente", Assinatura.class).getResultList();
        }
    }
}
