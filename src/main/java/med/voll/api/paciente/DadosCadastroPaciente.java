package med.voll.api.paciente;

import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public record DadosCadastroPaciente(
        String nome,
        String email,
        String cpf,
        String telefone,
        DadosEndereco endereco){
}
