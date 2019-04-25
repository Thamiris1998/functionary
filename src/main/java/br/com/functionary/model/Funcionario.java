package br.com.functionary.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="FUNCIONARIO_SEQ")
    @SequenceGenerator(name="FUNCIONARIO_SEQ", sequenceName="FUNCIONARIO_SEQ", allocationSize=1)
    private long id;
    @NotNull
    private Integer idade;
    @NotNull
    private String nome;
    @NotNull
    private String cargo;
}
