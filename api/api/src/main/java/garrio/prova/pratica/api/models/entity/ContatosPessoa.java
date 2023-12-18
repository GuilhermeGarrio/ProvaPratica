package garrio.prova.pratica.api.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data

//criar a tabela desse fdp
public class ContatosPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false, length = 12)
    private Integer telefone;

    @Column(nullable = false, length = 150)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa contato;



}
