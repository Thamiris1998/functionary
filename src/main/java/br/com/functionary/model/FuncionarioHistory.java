package br.com.functionary.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "funcionarioHistory")
public class FuncionarioHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FUNCIONARIO_HISTORY_SEQ")
    @SequenceGenerator(name="FUNCIONARIO_HISTORY_SEQ", sequenceName="FUNCIONARIO_HISTORY_SEQ", allocationSize=1)
    private long id;
    private long funcionarioId;
    private Integer idade;
    private String nome;
    private String cargo;
    private Date dataAtualizacao;
    private String evento;
}



