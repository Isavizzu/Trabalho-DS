import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String dataDeNacimento;
    private String telefone;

    public Pessoa(String nome, String cpf, String dataDeNascimento, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNacimento = dataDeNascimento;
        this.telefone = telefone;
    }

    public Endereco cadastrarEndereco(String mensagem, String mensagem2) {
        imprimirLinha(50);
        imprimirCentralizado(mensagem, 50);
        imprimirLinha(50);
        try {
            String estado = solicitarString("Digite o nome do estado: ");
            String cidade = solicitarString("Digite o nome da cidade: ");
            String bairro = solicitarString("Digite o nome do bairro: ");
            String rua = solicitarString("Digite o nome da rua: ");
            int numero = solicitarInt("Digite o número da casa: ");
            String complemento = solicitarString("Digite o complemento: ");
            imprimirLinha(50);
            imprimir(mensagem2);
            return new Endereco(estado, cidade, bairro, rua, numero, complemento);
        } catch (InformacaoInvalida e) {
            imprimir("Erro ao cadastrar endereço: " + e.getMessage());
            return null;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDataDeNacimento() {
        return dataDeNacimento;
    }

    public Funcionario encontrarVoce(ArrayList<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }
        return null;
    }

    public abstract void visualizarAnimal(ArrayList<Animal> animais) throws InformacaoInvalida;

    public void visualizarCliente() {
        imprimirLinha(50);
        imprimirCentralizado("Cliente:", 50);
        imprimirLinha(50);
        imprimir("Nome: " + nome);
        imprimir("Cpf: " + cpf);
        imprimir("Data de Nascimento: " + dataDeNacimento);
        imprimir("Telefone: " + telefone);
    }

    // Métodos de impressão
    public void imprimirLinha(int quantidade) {
        String linha = "";
        for (int i = 0; i < quantidade; i++) {
            linha += "-";
        }
        imprimir(linha);
    }

    public void imprimir(String conteudo) {
        System.out.println(conteudo);
    }

    public void imprimirCentralizado(String texto, int quant) {
        int espaco = (quant - texto.length()) / 2;
        if (espaco > 0) {
            String esp = " ".repeat(espaco);
            imprimir(esp + texto + esp);
        } else {
            imprimir(texto);
        }
    }

    // Métodos de solicitar informação
    public String solicitarString(String pergunta) throws InformacaoInvalida {
        Scanner scanner = new Scanner(System.in);
        imprimir(pergunta);
        try {
            return scanner.nextLine();
        } catch (InputMismatchException e) {
            throw new InformacaoInvalida("Entrada Inválida! Tente novamente.");
        }
    }

    public int solicitarInt(String pergunta) throws InformacaoInvalida {
        Scanner scanner = new Scanner(System.in);
        imprimir(pergunta);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); 
            throw new InformacaoInvalida("Entrada Inválida! Tente novamente.");
        }
    }

    public double solicitarDouble(String pergunta) throws InformacaoInvalida{
        Scanner scanner = new Scanner(System.in);
        imprimir(pergunta);
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.next();
            throw new InformacaoInvalida("Entrada Inválida! Tente novamente.");
        }
    }

    public Boolean solicitarBoolean(String pergunta) throws InformacaoInvalida {
        Scanner scanner = new Scanner(System.in);
        imprimir(pergunta);
        try {
            return scanner.nextBoolean();
        } catch (InputMismatchException e) {
            scanner.next();
            throw new InformacaoInvalida("Entrada Inválida! Tente novamente.");
        }
    }
}