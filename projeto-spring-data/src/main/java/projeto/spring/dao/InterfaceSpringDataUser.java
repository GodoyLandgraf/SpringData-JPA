package projeto.spring.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long>{

	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> burscaPorNome (String nome);

	@Modifying
	@Transactional
	@Query(value = "delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNome(String nome);
	
	@Modifying
	@Transactional
	@Query(value = "update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);
	
	
	@Lock(LockModeType.READ)   //Controle de transações - quando um usuario ta consultando o objeto, o outro nao pode atualizar.
	@Transactional(readOnly = true)  //APENAS LEITURA ( NÃO FAZ MODIFICAÇÕES NA DB)
	@Query(value = "select p from UsuarioSpringData p where p.nome  =:paramnome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);
}
