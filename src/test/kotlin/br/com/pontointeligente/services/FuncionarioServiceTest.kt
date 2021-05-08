package br.com.pontointeligente.services

import br.com.pontointeligente.entities.Funcionario
import br.com.pontointeligente.enums.PerfilEnum
import br.com.pontointeligente.repositories.FuncionarioRepository
import br.com.pontointeligente.services.impl.FuncionarioServiceImpl
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
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
        private val CPF_ALREADY_EXISTS: String = "cpf already exists"
    }

    @Mock
    private val funcionarioRepositoryMock: FuncionarioRepository? = null

    @Autowired
    private var funcionarioService: FuncionarioService? = null

    private var funcionario: Funcionario? = null

    @BeforeEach
    fun setUp() {
        funcionarioService = FuncionarioServiceImpl(funcionarioRepositoryMock)

        this.funcionario = Funcionario(ID, NOME, EMAIL, SENHA, CPF, PERFIL, EMPRESAID)

        whenever(funcionarioRepositoryMock?.save(Mockito.any(Funcionario::class.java)))
                .thenReturn(this.funcionario)

        whenever(funcionarioRepositoryMock?.findById(ID.toString()))
                .thenReturn(Optional.of(this.funcionario!!))

        whenever(funcionarioRepositoryMock?.findByEmail(EMAIL))
                .thenReturn(this.funcionario)

        whenever(funcionarioRepositoryMock?.findByCpf(CPF))
                .thenReturn(this.funcionario)
    }

    @Test
    fun `it should create a user with successfully`() {
        whenever(funcionarioRepositoryMock?.findByCpf(CPF))
                .thenReturn(null)

        val funcionario: Funcionario? = this.funcionarioService?.salvar(this.funcionario!!)

        Assertions.assertNotNull(funcionario)

        verify(funcionarioRepositoryMock, times(1))
                ?.save(this.funcionario!!)

        verify(funcionarioRepositoryMock, times(1))
                ?.findByCpf(CPF)

        verify(funcionarioRepositoryMock, never())
                ?.deleteById(ID.toString())
    }

    @Test
    fun `it should throws IOException for cpf duplicated`() {

        val ex = assertThrows(IOException::class.java,
                {this.funcionarioService?.salvar(this.funcionario!!)}
        )

        assertEquals(CPF_ALREADY_EXISTS, ex.message)

        verify(funcionarioRepositoryMock, times(1))
                ?.findByCpf(CPF)

        verify(funcionarioRepositoryMock, never())
                ?.deleteById(ID.toString())
    }

    @Test
    fun `it should find employee by cpf`() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorCpf(CPF)
        Assertions.assertNotNull(funcionario)

        verify(funcionarioRepositoryMock, times(1))
                ?.findByCpf(CPF)

        verify(funcionarioRepositoryMock, never())
                ?.save(this.funcionario!!)

        verify(funcionarioRepositoryMock, never())
                ?.deleteById(ID.toString())
    }

    @Test
    fun `it should find employee by Id`() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorId(ID.toString())
        Assertions.assertNotNull(funcionario)

        verify(funcionarioRepositoryMock, times(1))
                ?.findById(ID.toString())

        verify(funcionarioRepositoryMock, never())
                ?.save(this.funcionario!!)

        verify(funcionarioRepositoryMock, never())
                ?.deleteById(ID.toString())
    }

    @Test
    fun `it should find employee by Email`() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorEmail(EMAIL)
        Assertions.assertNotNull(funcionario)

        verify(funcionarioRepositoryMock, times(1))
                ?.findByEmail(EMAIL)

        verify(funcionarioRepositoryMock, never())
                ?.save(this.funcionario!!)

        verify(funcionarioRepositoryMock, never())
                ?.deleteById(ID.toString())
    }

    @Test
    fun `it should delete employee`() {
        this.funcionarioService?.remover(ID)

        verify(funcionarioRepositoryMock, times(1))
                ?.deleteById(ID.toString())
    }
}