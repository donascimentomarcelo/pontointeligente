package br.com.pontointeligente.services

import br.com.pontointeligente.entities.Funcionario
import br.com.pontointeligente.enums.PerfilEnum
import br.com.pontointeligente.repositories.FuncionarioRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.io.IOException
import java.util.*

@SpringBootTest
class FuncionarioServiceTest {

    companion object {
        private val ID: Long = 1L
        private val EMAIL: String = "EMAIL@EMAIL.com"
        private val CPF: String = "123456789"
        private val SENHA: String = "123456789"
        private val NOME: String = "crane"
        private val PERFIL: PerfilEnum = PerfilEnum.ROLE_USUARIO
        private val EMPRESAID: String = "1"
    }

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    @Autowired
    private val funcionarioService: FuncionarioService? = null

    private var funcionario: Funcionario? = null

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        this.funcionario = Funcionario(ID, NOME, EMAIL, SENHA, CPF, PERFIL, EMPRESAID)

        `when`(funcionarioRepository?.save(Mockito.any(Funcionario::class.java)))
                .thenReturn(this.funcionario)

        `when`(funcionarioRepository?.findById(ID.toString()))
                .thenReturn(Optional.of(this.funcionario!!))

        `when`(funcionarioRepository?.findByEmail(EMAIL))
                .thenReturn(this.funcionario)

        `when`(funcionarioRepository?.findByCpf(CPF))
                .thenReturn(this.funcionario)
    }

    @Test
    fun testSalvarFuncionario() {
        `when`(funcionarioRepository?.findByCpf(CPF))
                .thenReturn(null)

        val funcionario: Funcionario? = this.funcionarioService?.salvar(this.funcionario!!)

        Assertions.assertNotNull(funcionario)

        verify(funcionarioRepository, times(1))
                ?.save(this.funcionario!!)

        verify(funcionarioRepository, times(1))
                ?.findByCpf(CPF)

        verify(funcionarioRepository, never())
                ?.delete(this.funcionario!!)
    }

    @Test
    fun testBuscarFuncionarioPorCpf() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorCpf(CPF)
        Assertions.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorId() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorId(ID.toString())
        Assertions.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorEmail() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorEmail(EMAIL)
        Assertions.assertNotNull(funcionario)
    }
}