package br.com.pontointeligente.services

import br.com.pontointeligente.entities.Lancamento
import br.com.pontointeligente.enums.TipoEnum
import br.com.pontointeligente.repositories.LancamentoRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.*
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

@SpringBootTest
class LancamentoServiceTest {

    @MockBean
    private val lancamentoRepository: LancamentoRepository? = null

    @Autowired
    private val lancamentoService: LancamentoService? = null

    companion object {
        private val ID: Long = 1L
        private val FUNCIONARIO_ID: Long = 1L
        private val DESCRICAO: String = "123456789"
        private val LOCALIZACAO: String = "RJ"
        private val TIPO: TipoEnum = TipoEnum.INICIO_TRABALHO
        private val DATA: Date = Date()
        private val PAGE: Int = 0
        private val SIZE: Int = 10
    }

    @BeforeEach
    fun setUp() {
        whenever(lancamentoRepository?.findByFuncionarioId(ID.toString(), PageRequest.of(PAGE, SIZE)))
                .thenReturn(PageImpl(ArrayList<Lancamento>()))

        whenever(lancamentoRepository?.findById(ID))
                .thenReturn(Optional.of(lancamento()))

        whenever(lancamentoRepository?.save(Mockito.any(Lancamento::class.java)))
                .thenReturn(lancamento())
    }

    @Test
    fun testBuscarPorId() {
        val lanc = assertDoesNotThrow {
            lancamentoRepository
                    ?.findById(ID)
        }

        Assertions.assertEquals(lanc?.get()?.id, ID)

        verify(lancamentoRepository)
                ?.findById(ID)
    }

    @Test
    fun testSalvarLancamento() {
        val lancamento: Lancamento? = lancamentoService?.salvar(lancamento())
        Assertions.assertNotNull(lancamento)
    }

    @Test
    fun testBuscarLancamentoPorFuncionarioId() {
        val lancamento: Page<Lancamento>? = lancamentoService?.buscarPorFuncionarioId(ID.toString(), PageRequest.of(PAGE, SIZE))
        Assertions.assertNotNull(lancamento)
    }

    private fun lancamento(): Lancamento = Lancamento(ID, DATA, TIPO, FUNCIONARIO_ID, DESCRICAO, LOCALIZACAO)
}