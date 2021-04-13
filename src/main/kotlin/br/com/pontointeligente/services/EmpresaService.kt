package br.com.pontointeligente.services

import br.com.pontointeligente.documents.Empresa

interface EmpresaService {

    fun buscarPorCnpj(cnpj: String): Empresa?

    fun salvar(empresa: Empresa): Empresa
}