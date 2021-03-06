package projeto.spring;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.dao.InterfaceSpringDataUser;
import projeto.spring.dao.InterfaceTelefone;
import projeto.spring.model.Telefone;
import projeto.spring.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	@Test
	public void testeInsert() throws Exception {
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("matheus_godoyland@hotmail.com");
		usuarioSpringData.setIdade(24);
		usuarioSpringData.setLogin("smookew");
		usuarioSpringData.setNome("Junior");
		usuarioSpringData.setSenha("123teste123");
		interfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuários cadastrados -> "+ interfaceSpringDataUser.count());
		System.out.println("Iniciou Spring com sucesso");
	}
	
	@Test
	public void testeConsulta() throws Exception{
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		System.out.println(usuarioSpringData.get().getId());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getEmail());
		
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getNumero());	
		}
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
	
	@Test
	public void testeDelete() {
		interfaceSpringDataUser.deleteById(2L);
	}
	
	@Test
	public void testeConsultaNome() {
		List<UsuarioSpringData> list = interfaceSpringDataUser.burscaPorNome("Matheus");
		
		for (UsuarioSpringData usuarioSpringData : list) {
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
	public void testeConsultaNomeParam() {
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Egidio");
		
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println("--------------------------");
	
}
	@Test
	public void testeDeletePorNome() {
		interfaceSpringDataUser.deletePorNome("Alex");
	}
	
	@Test
	public void testeUpdateEmailPorNome() {
		interfaceSpringDataUser.updateEmailPorNome("egidio@hotmail.com", "Egidio");
	}
	
	@Test
	public void testeInsertTelefone() {
	Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
	Telefone telefone = new Telefone();
	telefone.setTipo("casa");
	telefone.setNumero("35245774");
	telefone.setUsuarioSpringData(usuarioSpringData.get());
	interfaceTelefone.save(telefone);
	}
	
}