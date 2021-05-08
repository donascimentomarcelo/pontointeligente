package br.com.pontointeligente.entities

import br.com.pontointeligente.enums.TipoEnum
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Lancamento(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,
        val data: Date,
        val tipo: TipoEnum?,
        val funcionarioId: Long?,
        val descricao: String = "",
        val localizacao: String = ""
)