package br.com.pontointeligente.controllers

import br.com.pontointeligente.entities.Lancamento
import br.com.pontointeligente.dtos.LancamentoDto
import br.com.pontointeligente.services.FuncionarioService
import br.com.pontointeligente.services.LancamentoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @GetMapping("/{id}")
    fun listarPorId(@PathVariable("id") id: String): ResponseEntity<Lancamento> {

        val lancamento: Lancamento? = lancamentoService.buscarPorId(id)
        return ResponseEntity.ok(lancamento)
    }

    @GetMapping("/funcionario/{funcionarioId}")
    fun listarPorFuncionarioId(@PathVariable("funcionarioId") funcionarioId: String,
                                @RequestParam("page", defaultValue = "0") page: Int,
                                @RequestParam("order", defaultValue = "id") order: String,
                                @RequestParam("direction", defaultValue = "DESC") direction: String): ResponseEntity<Page<Lancamento>> {

        val pageRequest: PageRequest = PageRequest.of(page, qtdPorPagina, Sort.Direction.valueOf(direction), order)
        val lancamento: Page<Lancamento>? = lancamentoService.buscarPorFuncionarioId(funcionarioId, pageRequest)
        return ResponseEntity.ok(lancamento)
    }
}