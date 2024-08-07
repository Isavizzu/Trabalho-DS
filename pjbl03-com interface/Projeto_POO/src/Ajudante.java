import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Ajudante extends Funcionario {
    public Boolean temporario;

    public Ajudante(String nome, String cpf, String dataNascimento, String telefone,
                    String cargo, double salarioPorHora, int cargaHoraria, String formacao,
                    Boolean temporario){
        super(nome,cpf,dataNascimento,telefone,cargo,salarioPorHora,cargaHoraria,formacao);
        this.temporario = temporario;
    }

    @Override
    public String getOutro(){
        return temporario.toString();
    }


    @Override
    public int verificar(){
        return 2;
    }

    @Override
    public Evento criarEvento(ArrayList<Funcionario> funcionarios, ArrayList<Animal> animais) throws InformacaoInvalida{
        imprimirLinha(50);
        imprimirCentralizado("Registrando Serviço:", 50);
        imprimirLinha(50);
        String nome = solicitarString("Digite o nome do serviço: ");
        String data = solicitarEntradaValida("Digite a data do serviço (formato dd/MM/yyyy): ", "data");
        String hora = solicitarString("Digite a hora do serviço: ");
        double preco = solicitarDouble("Digite o preço do serviço: ");
        Animal pet = encontrarPet(animais, "Digite o id do pet que realizará o serviço: ");
        Evento evento = new Evento(nome, data, hora, preco, encontrarVoce(funcionarios), pet);
        eventos.add(evento);
        pet.adicionarEvento(evento);
        imprimirLinha(50);
        imprimir("Serviço cadastrado com sucesso!");
        return evento;
    }

    @Override
    public void visualizarEventos(){
        imprimirLinha(50);
        imprimirCentralizado("Serviços Cadastrados", 50);
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
