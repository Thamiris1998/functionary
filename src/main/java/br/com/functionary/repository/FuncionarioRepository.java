package br.com.functionary.repository;

import br.com.functionary.model.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service("funcionario")
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {


}
