package br.com.pontointeligente.services.impl

import br.com.pontointeligente.entities.Lancamento
import br.com.pontointeligente.repositories.LancamentoRepository
import br.com.pontointeligente.services.LancamentoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class LancamentoServiceImpl(val lancamentoRepository: LancamentoRepository): LancamentoService {
    override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest): Page<Lancamento> =
            lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest)

    override fun buscarPorId(id: Long): Lancamento? {
        return lancamentoRepository.findById(id)
                .orElseThrow({IOException("not found")})
    }

    override fun salvar(lancamento: Lancamento): Lancamento {

        return lancamentoRepository.save(lancamento)
    }

    override fun remover(id: Long) = lancamentoRepository.deleteById(id)
}