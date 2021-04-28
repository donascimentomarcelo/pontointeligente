package br.com.pontointeligente.repositories

import br.com.pontointeligente.entities.Empresa
import org.springframework.data.jpa.repository.JpaRepository

interface EmpresaRepository : JpaRepository<Empresa, String> {

    fun findByCnpj(cnpj: String): Empresa?
}