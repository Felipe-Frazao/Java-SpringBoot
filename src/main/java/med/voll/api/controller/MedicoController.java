package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.Medico;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    MedicoRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);
            //.path() onde se passa o caminho da url
            //.buildAndExpand() pega a referencia do id
            //.toUri monta a url
        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity Atualizar(@RequestBody @Valid DadosAtualizarMedico dados){
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarDados(dados);
        return ResponseEntity.ok(new DetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}") //parametro passado pela url
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") //parametro passado pela url
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoMedico(medico));
    }

//    public void excluir(@PathVariable Long id){ //essa anotacao indica que o parametro sera recebido pela url
//        repository.deleteById(id); //Esse delete, exclui os dados permanentes do banco
//    }

}
