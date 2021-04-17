package br.com.pontointeligente.dtos

import br.com.pontointeligente.documents.Lancamento
import br.com.pontointeligente.enums.TipoEnum
import java.text.SimpleDateFormat

data class LancamentoDto (
    val data: String? = "",
    val tipo: String? = "",
    val descricao: String = "",
    val localizacao: String = "",
    val funcionarioId: String = "",
    val id: String? = "",
) {
    fun fromEntity(): Lancamento =
        Lancamento(
                SimpleDateFormat("MM/dd/yy").parse(data),
                TipoEnum.valueOf(tipo!!),
                funcionarioId!!,
                localizacao,
                descricao,
                id)
}