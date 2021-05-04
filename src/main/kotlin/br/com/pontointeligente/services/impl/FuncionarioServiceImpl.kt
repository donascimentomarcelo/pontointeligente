package br.com.pontointeligente.services.impl

import br.com.pontointeligente.entities.Funcionario
import br.com.pontointeligente.repositories.FuncionarioRepository
import br.com.pontointeligente.services.FuncionarioService
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class FuncionarioServiceImpl(val funcionarioRepository: FuncionarioRepository?) : FuncionarioService {

    companion object {
        private val CPF_ALREADY_EXISTS: String = "cpf already exists"
        private val USER_NOT_FOUND: String = "user not found"
    }

    override fun salvar(funcionario: Funcionario): Funcionario? {
        if(buscarPorCpf(funcionario.cpf) != null) {
            throw IOException(CPF_ALREADY_EXISTS)
        }
        return funcionarioRepository?.save(funcionario)
    }

    override fun buscarPorCpf(cpf: String): Funcionario? {
        return funcionarioRepository?.findByCpf(cpf)
    }

    override fun buscarPorEmail(email: String): Funcionario? =
            funcionarioRepository?.findByEmail(email)

    override fun buscarPorId(id: String): Funcionario? =
            funcionarioRepository?.findById(id)
                    ?.orElseThrow({ IOException(USER_NOT_FOUND) })

    override fun remover(id: Long) {
        funcionarioRepository?.deleteById(id.toString())
    }

}