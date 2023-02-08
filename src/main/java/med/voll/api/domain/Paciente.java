package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.Endereco;
import med.voll.api.paciente.DadosAtualizarPaciente;
import med.voll.api.paciente.DadosCadastroPaciente;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativos;

    public Paciente(DadosCadastroPaciente dados){
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarPaciente(DadosAtualizarPaciente dados) {
       if(dados.nome() != null){
           this.nome = dados.nome();
       }
       if(dados.telefone() != null){
           this.telefone = dados.telefone();
       }
       if(dados.endereco() != null) {
           this.endereco.atualizarEndereco(dados.endereco());
       }
    }

    public void excluir() {
        this.ativos = false;
    }
}
