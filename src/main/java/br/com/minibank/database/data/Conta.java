package br.com.minibank.database.data;

import br.com.minibank.dto.conta.ContaDTO;
import br.com.minibank.util.enums.TipoContaEnum;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contas")
public class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numero_conta")
    private String numeroConta;

    @NotNull
    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "ultima_transacao")
    private Double ultimaTransacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta")
    private TipoContaEnum tipoContaEnum;

    @NotNull
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    public Conta(Long id) {this.id = id;}

    public ContaDTO conversorEntityEmDTO() { return new ModelMapper().map(this, ContaDTO.class);}
}
