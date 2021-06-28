package br.com.minibank.boundary.conta;


import br.com.minibank.database.data.Conta;
import br.com.minibank.dto.Response;
import br.com.minibank.dto.conta.ContaDTO;
import br.com.minibank.exception.ContaNaoEncontradaException;
import br.com.minibank.service.ContaService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/mini-bank/v1/contas")
public class ContaController {

    ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {this.contaService = contaService;}

    @PostMapping
    public ResponseEntity<Response<ContaDTO>> criar(@Valid @RequestBody ContaDTO dto, BindingResult result) {

        Response<ContaDTO> response = new Response<>();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> response.addErrorMsgToResponse(objectError.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Conta conta = contaService.criar(dto.conversorDTOEmEntity());
        ContaDTO contaDTO = conta.conversorEntityEmDTO();

        response.setData(contaDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/buscarPorNumeroConta/{numero_conta}")
    @ApiOperation(value = "Rota para procurar a conta por numero_conta na API")
    public ResponseEntity<Response<List<ContaDTO>>> buscarContaPorNumeroConta(@PathVariable("numero_conta") String numeroConta) throws ContaNaoEncontradaException {

        Response<List<ContaDTO>> response = new Response<>();

        Optional<Conta> contas = contaService.buscarContaPorNumeroConta(numeroConta);

        if(contas.isEmpty()) {
            throw new ContaNaoEncontradaException("Não existe nenhuma conta bancaria com o numero:" + numeroConta);
        }

        List<ContaDTO> contaDTO = new ArrayList<>();
        contas.stream().forEach(c -> contaDTO.add(c.conversorEntityEmDTO()));

        response.setData(contaDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Rota para procurar a conta por id na API")
    public ResponseEntity<Response<ContaDTO>> buscarContaPorId(@PathVariable("id") Long idConta) throws ContaNaoEncontradaException {

        Response<ContaDTO> response = new Response<>();

        Optional<Conta> conta = contaService.buscarContaPorId(idConta);

        Conta contaEncontrada;
        if (conta.isPresent()) {
            contaEncontrada = conta.get();
        } else {
            throw new ContaNaoEncontradaException("Não existe nenhuma conta bancaria com o id:" + idConta);
        }

        ContaDTO contaDTO = contaEncontrada.conversorEntityEmDTO();

        response.setData(contaDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
