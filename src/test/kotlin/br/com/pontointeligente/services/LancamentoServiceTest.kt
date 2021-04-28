package br.com.pontointeligente.services

import br.com.pontointeligente.entities.Lancamento
import br.com.pontointeligente.enums.TipoEnum
import br.com.pontointeligente.repositories.LancamentoRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.collections.ArrayList

@SpringBootTest
class LancamentoServiceTest {

    @MockBean
    private val lancamentoRepository: LancamentoRepository? = null

    @Autowired
    private val lancamentoService: LancamentoService? = null

    private val id: Long = 1L

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given<Page<Lancamento>>(lancamentoRepository?.findByFuncionarioId(id.toString(), PageRequest.of(0, 10)))
                .willReturn(PageImpl(ArrayList<Lancamento>()))

        BDDMockito.given(lancamentoRepository?.findById(id.toString()))
                .willReturn(Optional.of(lancamento()))

        BDDMockito.given(lancamentoRepository?.save(Mockito.any(Lancamento::class.java)))
                .willReturn(lancamento())
    }

    @Test
    fun testBuscarPorId() {
        val lancamento: Lancamento? = lancamentoService?.buscarPorId(id.toString())
        Assertions.assertNotNull(lancamento)
    }

    @Test
    fun testSalvarLancamento() {
        val lancamento: Lancamento? = lancamentoService?.salvar(lancamento())
        Assertions.assertNotNull(lancamento)
    }

    @Test
    fun testBuscarLancamentoPorFuncionarioId() {
        val lancamento: Page<Lancamento>? = lancamentoService?.buscarPorFuncionarioId(id.toString(), PageRequest.of(0, 10))
        Assertions.assertNotNull(lancamento)
    }

    private fun lancamento(): Lancamento = Lancamento(id, Date(), TipoEnum.INICIO_TRABALHO, null!!)
}