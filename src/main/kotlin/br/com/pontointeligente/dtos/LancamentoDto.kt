package br.com.pontointeligente.dtos

import br.com.pontointeligente.entities.Lancamento
import br.com.pontointeligente.enums.TipoEnum
import java.text.SimpleDateFormat

data class LancamentoDto (
    val id: Long? = 0,
    val data: String? = "",
    val tipo: String? = "",
    val descricao: String = "",
    val localizacao: String = "",
    val funcionarioId: String = "",
) {
    fun fromEntity(): Lancamento =
        Lancamento(
                id,
                SimpleDateFormat("MM/dd/yy").parse(data),
                TipoEnum.valueOf(tipo!!),
                funcionarioId,
                localizacao,
                descricao
        )
}