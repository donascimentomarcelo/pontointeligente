package br.com.pontointeligente.entities

import br.com.pontointeligente.enums.TipoEnum
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
data class Lancamento(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,
        val data: Date,
        val tipo: TipoEnum?,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "funcionario_id")
        @JsonIgnore
        val funcionario: Funcionario?,
        val descricao: String = "",
        val localizacao: String = ""
)