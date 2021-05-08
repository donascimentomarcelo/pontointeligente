package br.com.pontointeligente.entities

import br.com.pontointeligente.enums.PerfilEnum
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Funcionario(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,
        val nome: String,
        val email: String,
        val senha: String,
        val cpf: String,
        val perfil: PerfilEnum,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "empresa_id")
        @JsonIgnore
        val empresa: Empresa?,
        val valorHora: Double? = 0.0,
        val qtdHorasTrabalhoDia: Float? = 0.0f,
        val qtdHorasAlmoco: Float? = 0.0f,
) {
        @OneToMany(mappedBy = "funcionario", cascade = [CascadeType.ALL])
        val lancamento: List<Lancamento>? = mutableListOf()
}