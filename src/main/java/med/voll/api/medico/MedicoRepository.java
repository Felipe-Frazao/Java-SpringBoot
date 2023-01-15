package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    //JpaRepository<Qual o tipo da entidade que iremos trabalhar, e tipo de atributo da chave primaria>


}
