package garrio.prova.pratica.api.controller;

import garrio.prova.pratica.api.models.entity.ContatosPessoa;
import garrio.prova.pratica.api.models.entity.Pessoa;
import garrio.prova.pratica.api.repository.ContatosRepository;
import garrio.prova.pratica.api.repository.PessoaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/lista-contatos")
@RequiredArgsConstructor
public class ContatoPessoaController {

    private final PessoaRepository pessoaRepository;
    private final ContatosRepository contatosRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContatosPessoa salvar (@RequestBody  @Valid ContatosPessoa ct ){
        Integer idPessoa = ct.getId();

        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Contato inexistente."));

        ContatosPessoa contatosPessoa = new ContatosPessoa();
        contatosPessoa.setNome(ct.getNome());
        contatosPessoa.setEmail(ct.getEmail());
        contatosPessoa.setTelefone((ct.getTelefone()));
        contatosPessoa.setId(ct.getId());

        return contatosRepository.save(contatosPessoa);
    }

}
