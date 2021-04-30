package br.com.pontointeligente.services.impl

import br.com.pontointeligente.entities.Funcionario
import br.com.pontointeligente.repositories.FuncionarioRepository
import br.com.pontointeligente.services.FuncionarioService
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class FuncionarioServiceImpl(val funcionarioRepository: FuncionarioRepository) : FuncionarioService {

    override fun salvar(funcionario: Funcionario): Funcionario {
        val retorno = buscarPorCpf(funcionario.cpf)

        if (retorno != null) {
            throw IOException("cpf alredy exists")
        }

        return funcionarioRepository.save(funcionario)
    }

    override fun buscarPorCpf(cpf: String): Funcionario? = funcionarioRepository.findByCpf(cpf)

    override fun buscarPorEmail(email: String): Funcionario? = funcionarioRepository.findByEmail(email)

    override fun buscarPorId(id: String): Funcionario? = funcionarioRepository.findById(id).get()

}