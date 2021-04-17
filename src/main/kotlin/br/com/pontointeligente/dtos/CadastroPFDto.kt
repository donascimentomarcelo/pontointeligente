package br.com.pontointeligente.dtos

data class CadastroPFDto (
    val nome: String = "",
    val email: String = "",
    val senha: String = "",
    val cpf: String = "",
    val cnpj: String = "",
    val empresaId: String = "",
    val valorHora: String? = "",
    val qtdHorasTrabalhoDia: String? = "",
    val qtdhorasAlmoco: String? = "",
    val id: String? = null,
)