package projeto.spring;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.dao.InterfaceSpringDataUser;
import projeto.spring.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Test
	public void testeInsert() throws Exception {
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("matheus_godoyland@hotmail.com");
		usuarioSpringData.setIdade(24);
		usuarioSpringData.setLogin("smookew");
		usuarioSpringData.setNome("Matheus Godoy Landgraf");
		usuarioSpringData.setSenha("123teste123");
		interfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuários cadastrados -> "+ interfaceSpringDataUser.count());
		System.out.println("Iniciou Spring com sucesso");
	}
	
	@Test
	public void testeConsulta() throws Exception{
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		System.out.println(usuarioSpringData.get().getId());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getEmail());
	}
	
	@Test
	public void testeConsultaTodos() {
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println("--------------------------");
		}
	}
	
	@Test
	public void testeUpdate() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		UsuarioSpringData data = usuarioSpringData.get();
		data.setNome("Larine Godoy Landgraf");
		interfaceSpringDataUser.save(data);
	}
}
