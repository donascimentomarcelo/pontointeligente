package br.com.pontointeligente.dtos


data class FuncionarioDto (
    val name: String = "",
    val email: String = "",
    val senha: String? = null,
    val valorHora: String? = null,
    val qtdHorasTrabalhoDia: String? = null,
    val qtdHorasAlmoco: String? = null,
    val id: String? = null,
)