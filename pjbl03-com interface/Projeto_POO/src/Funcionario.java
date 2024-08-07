import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Funcionario extends Pessoa {
    private String cargo;
    private double salarioPorHora;
    private int cargaHoraria;
    private String formacao;
    protected ArrayList<Evento> eventos;

    public Funcionario(String nome, String cpf, String dataNascimento, String telefone,
                       String cargo, double salarioPorHora, int cargaHoraria, String formacao) {
        super(nome, cpf, dataNascimento, telefone);
        this.cargo = cargo;
        this.salarioPorHora = salarioPorHora;
        this.cargaHoraria = cargaHoraria;
        this.formacao = formacao;
        this.eventos = new ArrayList<>();
    }


    public abstract String getOutro();


    public abstract int verificar();


    public String getCargo() {
        return cargo;
    }


    public double getSalario() {
        return salarioPorHora;
    }


    public int getCargaHoraria() {
        return cargaHoraria;
    }


    public String getFormacao() {
        return formacao;
    }


    public void adicionarEvento(Evento e) {
        eventos.add(e);
    }


    public void calcularSalario(int horasFaltadas, int horasExtras) {
        imprimirLinha(50);
        imprimirCentralizado("Salário Semanal", 50);
        imprimirLinha(50);
        imprimir("Nome: " + getNome());
        imprimir("Cargo: " + cargo);
        imprimir("Total do salário semanal: R$" + ((cargaHoraria + horasExtras) - horasFaltadas) * salarioPorHora);
    }


    public abstract void visualizarEventos();


    public Cliente visualizarCliente(ArrayList<Cliente> clientes) throws InformacaoInvalida{
        Cliente cliente = encontrarPessoa(clientes);
        cliente.visualizarCliente();
        return cliente;
    }

    @Override
    public void visualizarAnimal(ArrayList<Animal> animais) throws InformacaoInvalida {
        Animal animal = encontrarPet(animais, "Digite o id do pet que quer visualizar: ");
        imprimirLinha(50);
        imprimirCentralizado("Pet:", 50);
        imprimirLinha(50);
        imprimir("Nome: " + animal.getNome());
        imprimir("Dono: " + animal.getDono().getNome());
        imprimir("Espécie: " + animal.getEspecie());
        imprimir("Porte: " + animal.getPorte());
        imprimir("Raça: " + animal.getRaca());
        imprimir("Sexo: " + animal.getSexo());
        imprimir("Idade: " + animal.calcularIdade() + " anos");
        imprimir("Tratamentos realizados:");
        int i = 0;
        if (animal.getEventos().isEmpty()) {
            imprimir("Ainda não realizou nenhum tratamento!");
            return;
        }
        for (Evento evento : animal.getEventos()) {
            i++;
            imprimir(i + "- " + evento.imprimirDados());
        }
    }





    public void cadastrarCliente(ArrayList<Pessoa> pessoas, ArrayList<Cliente> clientes) throws InformacaoInvalida {
        imprimirLinha(50);
        imprimirCentralizado("Cadastro de Cliente", 50);
        imprimirLinha(50);
        String nome = solicitarString("Digite o nome do cliente: ");
        String data = solicitarEntradaValida("Digite a data de Nascimento do cliente (formato dd/MM/yyyy): ", "data");
        String telefone = solicitarString("Digite o telefone do cliente: ");
        Cliente cliente = new Cliente(nome, solicitarCpf(pessoas), data, telefone, cadastrarEndereco("Cadastro do Endereço do Cliente", "Endereço cadastrado com Sucesso!"));
        pessoas.add(cliente);
        clientes.add(cliente);
        imprimirLinha(50);
        imprimir("Cadastro de Cliente realizado com Sucesso!");
    }


    public void cadastrarVeterinario(ArrayList<Pessoa> pessoas, ArrayList<Funcionario> funcionarios) throws InformacaoInvalida{
        imprimirLinha(50);
        imprimirCentralizado("Cadastro de Novo Veterinário", 50);
        imprimirLinha(50);
        String especializacao = solicitarString("Digite a especialização: ");
        Funcionario funcionario = cadastrarFuncionario(pessoas, especializacao, null, 2);
        funcionarios.add(funcionario);
        pessoas.add(funcionario);
        imprimirLinha(50);
        imprimir("Cadastro realizado com Sucesso!");
    }


    public void cadastrarAjudante(ArrayList<Pessoa> pessoas, ArrayList<Funcionario> funcionarios) throws InformacaoInvalida {
        imprimirLinha(50);
        imprimirCentralizado("Cadastro de Novo Ajudante", 50);
        imprimirLinha(50);
        Boolean temporario = solicitarBoolean("O funcionário é temporário (true/false)? ");
        Funcionario funcionario = cadastrarFuncionario(pessoas, null, temporario, 1);
        funcionarios.add(funcionario);
        pessoas.add(funcionario);
        imprimirLinha(50);
        imprimir("Cadastro realizado com Sucesso!");
    }


    public Funcionario cadastrarFuncionario(ArrayList<Pessoa> pessoas, String especializacao, Boolean temporario, int escolha)throws InformacaoInvalida {
        String nome = solicitarString("Digite o nome do novo funcionário: ");
        String data = solicitarEntradaValida("Digite a data de Nascimento do funcionário (formato dd/MM/yyyy): ", "data");
        String telefone = solicitarString("Digite o telefone do funcionário: ");
        String cargo = solicitarString("Digite o cargo do funcionário: ");
        int carga = solicitarInt("Digite a carga horária semanal: ");
        double salario = solicitarDouble("Digite o salário por hora: ");
        String formacao = solicitarString("Digite o nível de escolaridade: ");
        if (escolha == 1) {
            return new Ajudante(nome, solicitarCpf(pessoas), data, telefone, cargo, salario, carga, formacao, temporario);
        }
        return new Veterinario(nome, solicitarCpf(pessoas), data, telefone, cargo, salario, carga, formacao, especializacao);
    }


    public void cadastrarAnimal(ArrayList<Cliente> clientes, ArrayList<Animal> pets) throws InformacaoInvalida {
        imprimirLinha(50);
        imprimirCentralizado("Cadastro do animal:", 50);
        imprimirLinha(50);
        int id = solicitarInt("Digite o Id do animal: ");
        String raca = solicitarString("Digite a raça do animal: ");
        String nome = solicitarString("Digite o nome do animal: ");
        String porte = solicitarString("Digite o porte do animal: ");
        String sexo = solicitarString("Digite o sexo do animal: ");
        String dataDeNascimento = solicitarEntradaValida("Digite a data de nascimento do animal (formato dd/MM/yyyy): ", "data");
        String especie = solicitarString("Digite a espécie do animal: ");
        Cliente pessoa = encontrarPessoa(clientes);
        Animal animal = new Animal(pessoa, raca, nome, porte, sexo, especie, dataDeNascimento, id);
        imprimirLinha(50);
        imprimir("Animal cadastrado com sucesso!");
        pets.add(animal);
        pessoa.adicionarAnimal(animal);
    }


    private boolean validarDataDeNascimento(String dataDeNascimento) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataDeNascimento, formatter);
            LocalDate hoje = LocalDate.now();
            return !data.isAfter(hoje);
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    private boolean validarEntrada(String entrada, String tipo) {
        if ("data".equals(tipo)) {
            return validarDataDeNascimento(entrada);
        }
        return true;
    }


    public String solicitarEntradaValida(String mensagem, String tipo)throws InformacaoInvalida {
        String entrada;
        while (true) {
            entrada = solicitarString(mensagem);
            if (validarEntrada(entrada, tipo)) {
                break;
            }
            System.out.println("Entrada inválida. Por favor, insira novamente.");
        }
        return entrada;
    }


    public abstract Evento criarEvento(ArrayList<Funcionario> funcionarios, ArrayList<Animal> animais) throws InformacaoInvalida;


    public String solicitarCpf(ArrayList<Pessoa> pessoas) throws InformacaoInvalida{
        String cpf;
        while (true) {
            cpf = solicitarString("Digite o cpf: ");
            boolean id = false;
            for (Pessoa pessoa : pessoas) {
                if (pessoa.getCpf().equals(cpf)) {
                    id = true;
                    break;
                }
            }
            if (!id) {
                break;
            }
            imprimir("Já existe um funcionário ou cliente cadastrado com esse CPF!");
            imprimirLinha(26);
        }
        return cpf;
    }


    public Animal encontrarPet(ArrayList<Animal> animais, String pergunta) throws InformacaoInvalida{
        while (true) {
            int id = solicitarInt(pergunta);
            for (Animal animal : animais) {
                if (animal.getId() == id) {
                    return animal;
                }
            }
            imprimir("Não encontramos nenhum pet com esse ID: " + id + "\nTente novamente!");
        }
    }


    public Cliente encontrarPessoa(ArrayList<Cliente> clientes)throws InformacaoInvalida {
        while (true) {
            String cpf = solicitarString("Digite o CPF do Cliente: ");
            for (Cliente cliente : clientes) {
                if (cliente.getCpf().equals(cpf)) {
                    return cliente;
                }
            }
            imprimir("Não encontramos nenhum Cliente com esse CPF: " + cpf + ".\nTente Novamente!");
            imprimirLinha(50);
        }
    }

}