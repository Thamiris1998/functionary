package br.com.functionary.repository;

import br.com.functionary.model.FuncionarioHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service("funcionarioHistory")
public interface FuncionarioHistoryRepository extends CrudRepository<FuncionarioHistory, Long> {
}
