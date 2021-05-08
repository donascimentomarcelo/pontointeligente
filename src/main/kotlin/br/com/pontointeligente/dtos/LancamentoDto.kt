package br.com.pontointeligente.dtos

import br.com.pontointeligente.entities.Lancamento
import br.com.pontointeligente.enums.TipoEnum
import java.text.SimpleDateFormat
import java.util.*

data class LancamentoDto (
    val id: Long? = 0,
    val data: Date? = Date(),
    val tipo: String? = "",
    val descricao: String = "",
    val localizacao: String = "",
    val funcionarioId: Long? = 0,
) {
    fun fromEntity(): Lancamento =
        Lancamento(
                id,
                data!!,
                TipoEnum.valueOf(tipo!!),
                funcionarioId,
                localizacao,
                descricao
        )
}