package br.dev.estudos.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String nome;
    @Column(nullable = false, length = 180)
    private String email;

    protected Cliente() { } // Exigido pelo provedor JPA.
    public Cliente(String nome, String email) {
        if (nome == null || nome.isBlank() || email == null || email.isBlank())
            throw new IllegalArgumentException("Nome e e-mail obrigatórios");
        this.nome = nome;
        this.email = email;
    }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}
