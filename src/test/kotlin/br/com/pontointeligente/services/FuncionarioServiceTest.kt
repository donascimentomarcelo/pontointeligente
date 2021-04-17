package br.com.pontointeligente.services

import br.com.pontointeligente.documents.Funcionario
import br.com.pontointeligente.enums.PerfilEnum
import br.com.pontointeligente.repositories.FuncionarioRepository
import br.com.pontointeligente.utils.SenhaUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
class FuncionarioServiceTest {

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    @Autowired
    private val funcionarioService: FuncionarioService? = null

    private val email: String = "email@email.com"
    private val cpf: String = "123456789"
    private val id: String = "1"

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(funcionarioRepository?.save(Mockito.any(Funcionario::class.java)))
                .willReturn(funcionario())

        BDDMockito.given(funcionarioRepository?.findById(id))
                .willReturn(Optional.of(funcionario()))

        BDDMockito.given(funcionarioRepository?.findByEmail(email))
                .willReturn(funcionario())

        BDDMockito.given(funcionarioRepository?.findByCpf(cpf))
                .willReturn(funcionario())
    }

    @Test
    fun testSalvarFuncionario() {
        val funcionario: Funcionario? = this.funcionarioService?.salvar(funcionario())
        Assertions.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorCpf() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorCpf(cpf)
        Assertions.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorId() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorId(id)
        Assertions.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorEmail() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorEmail(email)
        Assertions.assertNotNull(funcionario)
    }

    @Test
    private fun funcionario(): Funcionario = Funcionario(
            "Nome",
            email,
            SenhaUtils().gerarBcrypt("123456"),
            cpf,
            PerfilEnum.ROLE_USUARIO,
            id
    )
}