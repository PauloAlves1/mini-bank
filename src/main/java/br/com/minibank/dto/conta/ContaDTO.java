package br.com.minibank.dto.conta;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.minibank.database.data.Conta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ContaDTO extends RepresentationModel<ContaDTO> {

    private Long id;

    @NotNull(message = "O numero da conta não pode ser null.")
    @Length(min=6, message="O numero da conta deve conter no minimo 6 numeros.")
    private String numeroConta;

    @NotNull(message = "O saldo inicial da conta não pode ser null.")
    private Double saldo;

    private Double ultimaTransacao;

    @NotNull(message = "O tipo da conta não pode ser null.")
    @Pattern(regexp = "^(FREE|BASIC|PREMIUM)$",
    message="Valores permitidos: FREE, BASIC E PREMIUM.")
    private String tipoConta;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Brazil/East")
    private LocalDate dataCriacao;

    public Conta conversorDTOEmEntity() { return new ModelMapper().map(this, Conta.class);}

}
