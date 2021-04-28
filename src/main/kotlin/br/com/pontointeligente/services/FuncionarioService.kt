package br.com.pontointeligente.services

import br.com.pontointeligente.entities.Funcionario

interface FuncionarioService {

    fun salvar(funcionario: Funcionario): Funcionario

    fun buscarPorCpf(cpf: String): Funcionario?

    fun buscarPorEmail(email: String): Funcionario?

    fun buscarPorId(id: String): Funcionario?

}