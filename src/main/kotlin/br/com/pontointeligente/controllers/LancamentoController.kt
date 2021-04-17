package br.com.pontointeligente.controllers

import br.com.pontointeligente.documents.Lancamento
import br.com.pontointeligente.dtos.LancamentoDto
import br.com.pontointeligente.services.FuncionarioService
import br.com.pontointeligente.services.LancamentoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/lancamentos")
class LancamentoController(
        val lancamentoService: LancamentoService,
        val funcionarioService: FuncionarioService) {

    @Value("\${paginacao.qtdPorPagina}")
    val qtdPorPagina: Int = 15

    @PostMapping
    fun salvar(@Valid @RequestBody lancamentoDto: LancamentoDto): ResponseEntity<Lancamento> {

        val lancamento: Lancamento = lancamentoService.salvar(lancamentoDto.fromEntity())
        return ResponseEntity.ok(lancamento)
    }
}