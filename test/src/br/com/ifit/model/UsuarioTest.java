package br.com.ifit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ifit.business.UsuarioBusiness;
import br.com.ifit.exception.BusinessException;
import br.com.ifit.io.dao.ExceptionsType;

public class UsuarioTest {

	private static Usuario user;	
	private static UsuarioBusiness usuarioBusiness;

	@BeforeClass
	public static void setUp(){
		usuarioBusiness = new UsuarioBusiness();
		
		user = new Usuario();
		user.setCpf("000.000.000-00");
		user.setNome("Junior");	
		user.setEmail("junior@gmail.com");
		user.setSenha("12345");	
		user.setDiaPagamento(1);
		user.setEstadoCivil("Solteiro");
		user.setRg("111111");
		user.setOrgaoEmissor("SSP");
		user.setSexo("Masculino");
		user.setTipo("Aluno");
		user.setSenha("12345");
		user.setDataNascimento(new Date());
		Endereco end = new Endereco();
		end.setCep("58297000");
		end.setRua("Rua");
		end.setBairro("Centro");
		end.setCidade("Rio Tinto");
		end.setComplemento("Casa");
		end.setEstado("Paraiba");
		end.setNumero("1");
		user.setEndereco(end);
	}
	
	@Test
	public void cadastrarUsuario() {	
		try {
			usuarioBusiness.adicionar(user);
		} catch (BusinessException e) {
			assertEquals("Erro ao tentar inserir objeto no banco de dados.\nVerifique se todas as informações está corretas ou reinicie a aplicação.", e.getMessage());
		}		
	}
	
	
	@Test 
	public void atualizarUsuario() throws BusinessException{
		Usuario usuario = usuarioBusiness.getPorCpf(user.getCpf());
		usuario.setNome("Galdino");		
		Usuario usuarioTeste = usuarioBusiness.atualizar(usuario);
		assertEquals("Galdino",usuarioTeste.getNome());		
	}
	
	@Test
	public void removerUsuario() {
		try {
			usuarioBusiness.remover(user);
			assertNull(usuarioBusiness.getPorCpf(user.getCpf()));
		} catch (BusinessException e) {
			assertEquals(ExceptionsType.REMOVE_ERROR, e.getMessage());
		}
	}
	

}
