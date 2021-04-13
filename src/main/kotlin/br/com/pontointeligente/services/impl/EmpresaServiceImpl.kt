package br.com.pontointeligente.services.impl

import br.com.pontointeligente.documents.Empresa
import br.com.pontointeligente.repositories.EmpresaRepository
import br.com.pontointeligente.services.EmpresaService
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService {

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun salvar(empresa: Empresa): Empresa = empresaRepository.save(empresa)
}