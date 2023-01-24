package med.voll.api.medico;

import med.voll.api.paciente.DadosListagemPaciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
    //JpaRepository<Qual o tipo da entidade que iremos trabalhar, e tipo de atributo da chave primaria>


}
