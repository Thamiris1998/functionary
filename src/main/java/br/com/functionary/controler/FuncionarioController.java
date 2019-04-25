package br.com.functionary.controler;

import br.com.functionary.model.Funcionario;
import br.com.functionary.model.FuncionarioHistory;
import br.com.functionary.repository.FuncionarioHistoryRepository;
import br.com.functionary.utils.Events;
import br.com.functionary.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.functionary.repository.FuncionarioRepository;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FuncionarioHistoryRepository funcionarioHistoryRepository;

    @PostMapping("save")
    public ResponseEntity<Object> save(@Valid @RequestBody Funcionario funcionario) {
        try {

            Funcionario save = funcionarioRepository.save(funcionario);
            //SALVANDO LOG DA INSERÇAO
            saveFuncionarioHistory(save, Events.CREATE.name());
            return new ResponseEntity<>(new Message("Funcionario salvo com sucesso!", true, save.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Funcionario>> findAll() {
        try {
            List<Funcionario> funcionarioList = (List<Funcionario>) funcionarioRepository.findAll();
            return ResponseEntity.ok(funcionarioList);
        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable("id") Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).get();
        return ResponseEntity.ok(funcionario);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody Funcionario funcionario, @PathVariable("id") Long id) {
        try {
            Optional<Funcionario> findFuncionario = funcionarioRepository.findById(id);
            if (findFuncionario.isPresent()) {
                funcionario.setId(id);
                Funcionario save = funcionarioRepository.save(funcionario);
                //SALVANDO LOG DA ALTERACAO
                saveFuncionarioHistory(save,Events.UPDATE.name());

                return new ResponseEntity<>(new Message("Funcionario alterado com sucesso!", true, id), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Message("Funcionario não encontrado.", true, id), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable("id") Long id) {
        try {
            Optional<Funcionario> findFuncionario = funcionarioRepository.findById(id);
            if (findFuncionario.isPresent()) {
                funcionarioRepository.deleteById(id);
                //SALVANDO LOG DA EXCLUSAO
                saveFuncionarioHistory(findFuncionario.orElse(null), Events.DELETE.name());
            }
            return new ResponseEntity<>(new Message("Funcionario deletado com sucesso!", true, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void saveFuncionarioHistory(Funcionario funcionario, String evento) {

        FuncionarioHistory funcionarioHistory = new FuncionarioHistory();
        funcionarioHistory.setFuncionarioId(funcionario.getId());
        funcionarioHistory.setIdade(funcionario.getIdade());
        funcionarioHistory.setNome(funcionario.getNome());
        funcionarioHistory.setCargo(funcionario.getCargo());
        funcionarioHistory.setDataAtualizacao(new Date());
        funcionarioHistory.setEvento(evento);
        funcionarioHistoryRepository.save(funcionarioHistory);

    }
}
