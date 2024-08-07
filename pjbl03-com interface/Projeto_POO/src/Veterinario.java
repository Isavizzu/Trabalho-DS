import java.sql.Array;
import java.util.ArrayList;

public class Veterinario extends Funcionario{
    String especializacao;


    public Veterinario(String nome, String cpf, String dataNascimento, String telefone,
                    String cargo, double salarioPorHora, int cargaHoraria, String formacao,
                    String especializacao){
        super(nome,cpf,dataNascimento,telefone,cargo,salarioPorHora,cargaHoraria,formacao);
        this.especializacao = especializacao;
    }

    @Override
    public String getOutro(){
        return especializacao;
    }

    @Override
    public int verificar(){
        return 1;
    }

    @Override
    public Evento criarEvento(ArrayList<Funcionario> funcionarios, ArrayList<Animal> animais)throws InformacaoInvalida{
        imprimirLinha(50);
        imprimirCentralizado("Registrando Procedimento:", 50);
        imprimirLinha(50);
        String nome = solicitarString("Digite o nome do procedimento: ");
        String data = solicitarEntradaValida("Digite a data do procedimento (formato dd/MM/yyyy): ", "data");
        String hora = solicitarString("Digite a hora do procedimento: ");
        double preco = solicitarDouble("Digite o preço do procedimento: ");
        String medicamento = solicitarString("Digite o medicamento usado no procedimento: ");
        String descricao = solicitarString("Digite a descrição do procedimento: ");
        Animal pet = encontrarPet(animais, "Digite o id do pet que realizará o serviço: ");
        Procedimento procedimento = new Procedimento(nome, data, hora, preco, encontrarVoce(funcionarios), pet, medicamento, descricao);
        eventos.add(procedimento);
        pet.adicionarEvento(procedimento);
        imprimirLinha(50);
        imprimir("Procedimento cadastrado com sucesso!");
        return procedimento;
    }

    @Override
    public void visualizarEventos(){
        imprimirLinha(50);
        imprimirCentralizado("Procedimentos Cadastrados", 50);
        imprimirLinha(50);
        int i = 0;
        if (!eventos.isEmpty()) {
            for (Evento evento : eventos) {
                i++;
                imprimir(i + "-" + evento.getNome() + " -- Data: " + evento.getData() + " -- R$" + evento.getPreco());
            }
        }
        else{
            imprimir("Você ainda não cadastrou nenhum serviço!");
        }
    }

}
