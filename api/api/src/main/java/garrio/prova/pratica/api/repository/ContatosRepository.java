package garrio.prova.pratica.api.repository;


import garrio.prova.pratica.api.models.entity.ContatosPessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatosRepository  extends JpaRepository<ContatosPessoa, Integer> {

}
