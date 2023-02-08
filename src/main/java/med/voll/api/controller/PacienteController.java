package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.Paciente;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes") //atalhor para a url buscar
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping //aqui esta dizendo qual o tipo de requisicao, nesse caso e o Post
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){ //@Valid como o nome diz é pra validar
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivosTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarPaciente dados){
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

}
