package br.com.pontointeligente.entities

import javax.persistence.*

@Entity
data class Empresa (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,
        val razaoSocial: String,
        val cnpj: String,
) {
        @OneToMany(mappedBy = "empresa", cascade = [CascadeType.ALL])
        val funcionario: List<Funcionario>? = mutableListOf()
}