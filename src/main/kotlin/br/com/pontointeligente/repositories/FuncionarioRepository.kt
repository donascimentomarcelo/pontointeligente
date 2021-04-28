package br.com.pontointeligente.repositories

import br.com.pontointeligente.entities.Funcionario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FuncionarioRepository : JpaRepository<Funcionario, String> {

    fun findByEmail(email: String): Funcionario?

    fun findByCpf(cpf: String): Funcionario?
}