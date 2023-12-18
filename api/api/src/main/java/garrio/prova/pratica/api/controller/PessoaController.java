package garrio.prova.pratica.api.controller;

import garrio.prova.pratica.api.models.entity.Pessoa;
import garrio.prova.pratica.api.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin("http://localhost:4200")
public class PessoaController {


    private final PessoaRepository repository;

    @Autowired
    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pessoa> obterTodos(){
        return repository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar (@RequestBody @Valid Pessoa pessoa){
        return repository.save(pessoa);
    }

    @GetMapping("{id}")
    public Pessoa acharPorId(@PathVariable Integer id){
           return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(pessoa ->{
                 repository.delete(pessoa);
                 return Void.TYPE;})
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado"));

    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Pessoa pessoaAtualizado ){
        repository
                .findById(id)
                .map(pessoa ->
                {pessoa.setNome(pessoaAtualizado.getNome());
                    pessoa.setCpf(pessoaAtualizado.getCpf());
                    return repository.save(pessoaAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado"));
    }


}
