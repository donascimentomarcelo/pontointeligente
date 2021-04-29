package br.com.pontointeligente.services

import br.com.pontointeligente.entities.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface LancamentoService {

    fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest): Page<Lancamento>

    fun buscarPorId(id: Long): Lancamento?

    fun salvar(lancamento: Lancamento): Lancamento

    fun remover(id: Long)
}