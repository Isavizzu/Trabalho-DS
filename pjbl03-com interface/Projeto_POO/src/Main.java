import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;


public class Main {

    //métodos para salvar os objetos
    public static void salvarEvento(ArrayList<Evento> eventos, String arquivo, String arquivo2){
        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;
        String caminhoDoarquivo2 = encontrarArquivo + "/" + arquivo2;

        try(PrintWriter apagar = new PrintWriter(arquivo)){}
        catch (IOException e) {e.printStackTrace();}

        try(PrintWriter apagar = new PrintWriter(arquivo2)){}
        catch (IOException e) {e.printStackTrace();}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoDoarquivo, true))) {
            
            writer.write("Nome,data,hora,preco,funcionario_cpf,animal_id");
            writer.newLine();

            for(Evento evento: eventos){
                if(evento.verificar() == 1){
                   
                    writer.write(evento.getNome() + "," +
                        evento.getData() + "," +
                        evento.getHora() + "," +
                        evento.getPreco() + "," +
                        evento.getFuncionario().getCpf() + "," +
                        evento.getAnimal().getId());
                    writer.newLine();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoDoarquivo2, true))) {
            
            writer.write("Nome,data,hora,preco,funcionario_cpf,animal_id,medicamento,descricao");
            writer.newLine();

            for(Evento evento: eventos){
                if(evento.verificar() == 2){
                
                    writer.write(evento.getNome() + "," +
                        evento.getData() + "," +
                        evento.getHora() + "," +
                        evento.getPreco() + "," +
                        evento.getFuncionario().getCpf() + "," +
                        evento.getAnimal().getId() + "," +
                        evento.obterDados().get(0) + "," +
                        evento.obterDados().get(1));
                    writer.newLine();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void salvarFuncionario(ArrayList<Funcionario> funcionarios, String arquivo){
        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;

        try(PrintWriter apagar = new PrintWriter(arquivo)){}
        catch (IOException e) {e.printStackTrace();}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoDoarquivo, true))) {
            // Escrever o cabeçalho 
            writer.write("Cpf,Nome,Data,telefone,cargo,Salario,CargaHoraria,Formacao,outro,id");
            writer.newLine();
            
            for(Funcionario funcionario: funcionarios){
                if(funcionario.verificar() == 1){
                    writer.write(funcionario.getCpf() + "," +
                        funcionario.getNome() + "," +
                        funcionario.getDataDeNacimento() + "," +
                        funcionario.getTelefone() + "," +
                        funcionario.getCargo() + "," +
                        funcionario.getSalario() + "," +
                        funcionario.getCargaHoraria() + "," +
                        funcionario.getFormacao() + "," +
                        funcionario.getOutro() + "," +
                        "1");
                    writer.newLine();
                }    
                else{
                    writer.write(funcionario.getCpf() + "," +
                        funcionario.getNome() + "," +
                        funcionario.getDataDeNacimento() + "," +
                        funcionario.getTelefone() + "," +
                        funcionario.getCargo() + "," +
                        funcionario.getSalario() + "," +
                        funcionario.getCargaHoraria() + "," +
                        funcionario.getFormacao() + "," +
                        funcionario.getOutro() + "," +
                        "2");
                    writer.newLine();

                    }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void salvarClientes(ArrayList<Cliente> clientes, String arquivo, String arquivo2) {

        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;

        String caminhoDoarquivo2 = encontrarArquivo + "/" + arquivo2;

        try(PrintWriter apagar = new PrintWriter(arquivo)){}
        catch (IOException e) {e.printStackTrace();}

        try(PrintWriter apagar = new PrintWriter(arquivo2)){}
        catch (IOException e) {e.printStackTrace();}

        try (BufferedWriter ar = new BufferedWriter(new FileWriter(caminhoDoarquivo2, true))) {
            ar.write("estado,cidade,bairro,rua,converter,complemento,cpf");
            ar.newLine();
        }catch (IOException e) {e.printStackTrace();}


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoDoarquivo, true))) {
            
            writer.write("Nome,cpf,data,telefone");
            writer.newLine();
            
                
            for(Cliente cliente: clientes){
                String cpf = cliente.getCpf();
                Endereco endereco = cliente.getEndereco();
                // Escreve uma nova linha com os dados do cliente
                writer.write(cliente.getNome() + "," +
                    cpf + "," +
                    cliente.getDataDeNacimento() + "," +
                    cliente.getTelefone());
                writer.newLine();

                salvarEndereco(endereco, cpf, caminhoDoarquivo2);
            } 
            }catch (IOException e) {
                e.printStackTrace();
            }
    }


    public static void salvarEndereco(Endereco endereco, String cpf,String arquivo){
        
        try (BufferedWriter ar = new BufferedWriter(new FileWriter(arquivo, true))) {
            
            // Escreve uma nova linha com os dados do endereço
            ar.write(endereco.getEstado() + "," +
                endereco.getCidade() + "," +
                endereco.getBairro() + "," +
                endereco.getRua() + "," +
                endereco.getNumero() + "," +
                endereco.getComplemento() + "," +
                cpf);
            ar.newLine();
 
            }catch (IOException e) {
                e.printStackTrace();
            }    
    }


    public static void salvarAnimal(ArrayList<Animal> animais, String arquivo) {

        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;

        try(PrintWriter apagar = new PrintWriter(arquivo)){

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoDoarquivo, true))) {
        
            writer.write("Id,Nome,DonoCpf,Especie,Porte,Raca,Sexo,DataDeNascimento");
            writer.newLine();
            
            for(Animal animal: animais){
                // Escreve uma nova linha com os dados do animal
                writer.write(animal.getId() + "," +
                    animal.getNome() + "," +
                    animal.getDono().getCpf() + "," +
                    animal.getEspecie() + "," +
                    animal.getPorte() + "," +
                    animal.getRaca() + "," +
                    animal.getSexo() + "," +
                    animal.getDataDeNascimento());
                writer.newLine();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    // Método para verificar a data do evento
    public static boolean verificarData(String data){
         
        // Formato do parse
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Converter a String para LocalDate
        LocalDate dataInformada = LocalDate.parse(data, formatter);
        
        // Data atual
        LocalDate hoje = LocalDate.now();
        
        // Início da semana atual (considerando domingo como primeiro dia da semana)
        LocalDate inicioSemana = hoje.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY));
        
        // Fim da semana atual 
        LocalDate fimSemana = hoje.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SATURDAY));
        
        // Verificar se a data informada está na semana atual
        boolean estaNaSemanaAtual = (dataInformada.isEqual(inicioSemana) || dataInformada.isAfter(inicioSemana)) &&
                                    (dataInformada.isEqual(fimSemana) || dataInformada.isBefore(fimSemana));
        
        if (estaNaSemanaAtual) {
            return true; // Se a data informada estiver na semana atual
        }
        return false; // Se a data informada não estiver na semana atual
    }
    

    // Métodos para ler e escrever no arquivo
    public static void lerFaturamento(String arquivo, InterfaceGrafica intGraf){
        String csvSeparador = ",";
        String line;

        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;

        // Variável de controle, para verificar se há eventos na semana atual
        boolean verificarEvento = false;

        // Variável para aculular os preços dos eventos
        double valorTotal = 0;

        JFrame frame = intGraf.criarJanela("Tabela de Faturamento", 1000, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = intGraf.criarPainel(frame);
        intGraf.adicionarLabel("Faturamento Semanal", panel, 450,20,100,20);


        // Cria um modelo de tabela com os dados e as colunas
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Data");
        modelo.addColumn("Serviço");
        modelo.addColumn("Preço");

        JTable tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);// Adiciona a tabela a um JScrollPane
        frame.add(scrollPane);// Adiciona o JScrollPane ao JFrame

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoarquivo))) {

            String header = br.readLine();

            while ((line = br.readLine()) != null) {
                // Dividir a linha em colunas usando o separador
                String[] columns = line.split(csvSeparador);

                if (verificarData(columns[1]) == true) {
                    verificarEvento = true; // Possui evento(s) na semana atual
                    modelo.addRow(new Object[]{columns[0], columns[1], columns[2], columns[3]});
                    double preco = Double.parseDouble(columns[3]);
                    valorTotal += preco;
                }
            }

            // Verificar se houve evento(s) na semana atual
            if(verificarEvento == false){
                JOptionPane.showMessageDialog(panel, "Ainda não há faturamento registrado para esta semana");

            }else{
                JOptionPane.showMessageDialog(panel, "Total: R$" + valorTotal);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }


    public static void salvarFaturamento(ArrayList<Evento> eventos, String arquivo){
        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;

        try(PrintWriter apagar = new PrintWriter(arquivo)){}
        catch (IOException e) {e.printStackTrace();}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoDoarquivo, true))) {
           
            writer.write("Nome,data,servico,preco");
            writer.newLine();
           
            for(Evento evento: eventos){
                writer.write(evento.getFuncionario().getNome() + "," +
                        evento.getData() + "," +
                        evento.getNome() + "," +
                        evento.getPreco());
                writer.newLine();
            }
        }    
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Métodos de recuperar os objetos
    public static void criarObjetosEvento(ArrayList<Funcionario> funcionarios, ArrayList<Animal> animais, ArrayList<Evento> eventos, 
    String arquivo, String arquivo2){
        String csvSeparador = ",";
        String line;
        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;
        String caminhoDoarquivo2 = encontrarArquivo + "/" + arquivo2;

        if(arquivo == "eventos.csv"){
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoarquivo))) {
                // Ler o cabeçalho (primeira linha) e ignorá-lo
                String header = br.readLine();
    
                // Ler cada linha do arquivo CSV
                while ((line = br.readLine()) != null) {
                    // Dividir a linha em colunas usando o separador
                    String[] columns = line.split(csvSeparador);

                    if (columns.length >= 5 ) {
                        String nome = columns[0];
                        String data = columns[1];
                        String hora = columns[2];
                        String preco = columns[3];
                        Double valorFloat = Double.parseDouble(preco);
                        String funcionario = columns[4];
                        String animal = columns[5];
                        int converter = Integer.parseInt(animal);

                        Funcionario fun = null;
                        for(Funcionario f: funcionarios){
                            if(f.getCpf().equals(funcionario)){
                                fun = f;
                                break;
                            }
                        }

                        Animal ani = null;
                        for(Animal a: animais){
                            if(a.getId() == converter){
                                ani = a;
                                break;
                            }
                        }

                        Evento evento = new Evento(nome, data, hora, valorFloat, fun, ani);
                        ani.adicionarEvento(evento);
                        fun.adicionarEvento(evento);
                        eventos.add(evento);

                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
                }
        }

        if(arquivo2 == "procedimentos.csv"){
            try (BufferedReader ar = new BufferedReader(new FileReader(caminhoDoarquivo2))) {
                // Ler o cabeçalho (primeira linha) e ignorá-lo
                String header = ar.readLine();
    
                // Ler cada linha do arquivo CSV
                while ((line = ar.readLine()) != null) {
                    // Dividir a linha em colunas usando o separador
                    String[] columns = line.split(csvSeparador);

                    if (columns.length >= 8 ) {
                        String nome = columns[0];
                        String data = columns[1];
                        String hora = columns[2];
                        String preco = columns[3];
                        Double valorFloat = Double.parseDouble(preco);
                        String funcionario = columns[4];
                        String animal = columns[5];
                        int converter = Integer.parseInt(animal);
                        String medicamento = columns[6];
                        String descricao = columns[7];

                        Funcionario fun = null;
                        for(Funcionario f: funcionarios){
                            if(f.getCpf().equals(funcionario)){
                                fun = f;
                                break;
                            }
                        }

                        Animal ani = null;
                        for(Animal a: animais){
                            if(a.getId() == converter){
                                ani = a;
                                break;
                            }
                        }

                        Evento evento = new Procedimento(nome, data, hora, valorFloat, fun, ani, medicamento, descricao);
                        ani.adicionarEvento(evento);
                        fun.adicionarEvento(evento);
                        eventos.add(evento);

                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
                }
        }
        
    }


    public static void criarObjetoFuncionario(ArrayList<Funcionario> funcionarios, String arquivo){
        String csvSeparador = ",";
        String line;
        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoarquivo))) {
            // Ler o cabeçalho (primeira linha) e ignorá-lo
            String header = br.readLine();

            // Ler cada linha do arquivo CSV
            while ((line = br.readLine()) != null) {
                // Dividir a linha em colunas usando o separador
                String[] columns = line.split(csvSeparador);
            
                if (columns.length >= 10 ) {
                    String cpf = columns[0];
                    String nome = columns[1];
                    String data = columns[2];
                    String telefone = columns[3];
                    String cargo = columns[4];
                    String salario = columns[5];
                    Float valorFloat = Float.valueOf(salario);
                    String cargaHoraria = columns[6];
                    int converter = Integer.parseInt(cargaHoraria);
                    String formacao = columns[7];
                    String outros = columns[8]; 
                    
                    if(columns[9].equals("1")){
                        Funcionario funcionario = new Veterinario(nome, cpf, data, telefone, cargo, valorFloat, converter, formacao, outros);
                        funcionarios.add(funcionario);
                    }
                    else{
                        if(columns[8].equals("true")){
                            Funcionario funcionario = new Ajudante(nome, cpf, data, telefone, cargo, valorFloat, converter, formacao, true);
                            funcionarios.add(funcionario);
                        }
                        else if(columns[8].equals("null")){
                            Funcionario funcionario = new Ajudante(nome, cpf, data, telefone, cargo, valorFloat, converter, formacao, null);
                            funcionarios.add(funcionario);
                        }
                        else{
                            Funcionario funcionario = new Ajudante(nome, cpf, data, telefone, cargo, valorFloat, converter, formacao, false);
                            funcionarios.add(funcionario);
                        }
                        
                    }
                }
            }

        }catch (IOException e) {
        e.printStackTrace();
        }
    }


    public static void criarObjetosAnimais(ArrayList<Animal> animais, ArrayList<Cliente> clientes,String arquivo){
        String csvSeparador = ",";
        String line;
        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoarquivo))) {
            // Ler o cabeçalho (primeira linha) e ignorá-lo
            String header = br.readLine();

            // Ler cada linha do arquivo CSV
            while ((line = br.readLine()) != null) {
                // Dividir a linha em colunas usando o separador
                String[] columns = line.split(csvSeparador);

                if (columns.length >= 8) {
                    // Extrair os dados da linha
                    int id = Integer.parseInt(columns[0]);
                    String nome = columns[1];
                    String donoCpf = columns[2];
                    String especie = columns[3];
                    String porte = columns[4];
                    String raca = columns[5];
                    String sexo = columns[6];
                    String dataDeNascimento = columns[7];

                    // Encontrar o dono correspondente pelo CPF
                    Cliente dono = null;
                    for (Cliente cliente : clientes) {
                        if (cliente.getCpf().equals(donoCpf)) {
                            dono = cliente;
                            break;
                        }
                    }

                    // Se o dono for encontrado, criar o objeto Animal e adicionar à lista do dono
                    if (dono != null) {
                        Animal animal = new Animal(dono, raca, nome, porte, sexo, especie, dataDeNascimento, id);
                        animais.add(animal);
                        dono.adicionarAnimal(animal);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void criarObjetosClientes(ArrayList<Cliente> clientes, String arquivo){
        String csvSeparador = ",";
        String line;
        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoarquivo))) {
            // Ler o cabeçalho (primeira linha) e ignorá-lo
            String header = br.readLine();

             // Ler cada linha do arquivo CSV
             while ((line = br.readLine()) != null) {
                // Dividir a linha em colunas usando o separador
                String[] columns = line.split(csvSeparador);
                // Verificar se a coluna do nome (assumimos que é a segunda coluna) corresponde ao nome que procuramos
                if (columns.length >= 3) {
                    String nome = columns[0];
                    String cpf = columns[1];
                    String data = columns[2];
                    String telefone = columns[3];
                    Endereco endereco  = pegarEndereco(cpf, "endereco.csv");
                    Cliente cliente = new Cliente(nome, cpf, data, telefone, endereco);
                    clientes.add(cliente);
                }

             }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Endereco pegarEndereco(String cpf, String arquivo){
        String csvSeparador = ",";
        String line;
        String encontrarArquivo = System.getProperty("user.dir");
        String caminhoDoarquivo = encontrarArquivo + "/" + arquivo;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoarquivo))) {
            // Ler o cabeçalho (primeira linha) e ignorá-lo
            String header = br.readLine();

             // Ler cada linha do arquivo CSV
             while ((line = br.readLine()) != null) {
                // Dividir a linha em colunas usando o separador
                String[] columns = line.split(csvSeparador);
                // Verificar se a coluna do nome (assumimos que é a segunda coluna) corresponde ao nome que procuramos
                if (columns.length > 1 && columns[6].equals(cpf)) {
                    String estado = columns[0];
                    String cidade = columns[1];
                    String bairro = columns[2];
                    String rua = columns[3];
                    String numero = columns[4];
                    int converter = Integer.parseInt(numero);
                    String complemento = columns[5];
                    Endereco endereco = new Endereco(estado, cidade, bairro, rua, converter, complemento);
                    return endereco;
                }
             }
        }catch (IOException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    
    //métodos de impressão
    public static void imprimirLinha(int quantidade){
        String linha = "";
        for (int i = 0; i < quantidade; i++){
            linha += "-";
        }
        imprimir(linha);
    }


    public static void imprimir(String conteudo){
        System.out.println(conteudo);
    }


    public static void imprimirCentralizado(String texto, int quant){
        int espaco = (quant - texto.length()) /2;
        if (espaco > 0){
                String esp = " ".repeat(espaco);
                imprimir(esp + texto + esp);
        }
        else{
            imprimir(texto);
        }
    }


    //métodos de solicitação de dados
    public static String solicitarString(String pergunta){
        Scanner scaneer = new Scanner(System.in);
        while (true){
            imprimir(pergunta);
            try {
                return scaneer.nextLine();
            } catch (InputMismatchException e) {
                imprimir("Entrada Inválida!\nTente Novamente!");
                imprimirLinha(50);
                scaneer.next();
            }
        }
    }


    public static int solicitarInt(String pergunta){
        Scanner scaneer = new Scanner(System.in);
        while (true){
            imprimir(pergunta);
            try {
                return scaneer.nextInt();
            } catch (InputMismatchException e) {
                imprimir("Entrada Inválida!\nTente Novamente!");
                imprimirLinha(50);
                scaneer.next();
            }
        }
    }


    public static void visualizarCliente(Cliente cliente, ArrayList<Animal> pets){
        while (true) {
            imprimirLinha(50);
            imprimirCentralizado("Opcões de Cliente", 50);
            imprimirLinha(50);
            imprimir("1-Sair");
            imprimir("2-Visualizar Pets do(a): " + cliente.getNome());
            imprimir("3-Visualizar Endereço");
            imprimir("4-Mudar Endereço");
            imprimirLinha(50);
            switch (solicitarInt("Digite o número da opção que quer acessar: ")) {
                case 1:
                    return;
                case 2:
                    cliente.visualizarAnimal(pets);
                    break;
                case 3:
                    cliente.visualizarEndereco();
                    break;
                case 4:
                    cliente.mudarEndereco();
                    break;
                default:
                    imprimir("Opcão não encontrada!\nTente Novamente!");
                    break;
            }
        }
    }


    public static Funcionario autenticar(ArrayList<Funcionario> funcionarios){
        while (true) {
            String cpf = solicitarString("Digite seu Cpf: ");
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getCpf().equals(cpf)) {
                    return funcionario;
                }
            }
            imprimir("Cpf não identificado!\nTente Novamente!");
            imprimirLinha(50);
        }
    }


    public static void main(String[] args) throws FileNotFoundException, IOException, InformacaoInvalida, InterruptedException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        criarObjetosClientes(clientes, "clientes.csv");
        ArrayList<Animal> pets = new ArrayList<>();
        criarObjetosAnimais(pets, clientes, "animais.csv");
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        criarObjetoFuncionario(funcionarios, "funcionarios.csv");
        ArrayList<Evento> eventos = new ArrayList<>();
        criarObjetosEvento(funcionarios, pets, eventos, "eventos.csv", "procedimentos.csv");
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        pessoas.addAll(clientes);
        pessoas.addAll(funcionarios);

        InterfaceGrafica intGraf = new InterfaceGrafica();

        menuu: while (true) {
            Funcionario funcionario = intGraf.iniciarTelaDeEntrada(funcionarios);

            menu: while (true) {
                JPanel panel = new JPanel(); // Cria um novo JPanel para cada menu
                String comando = intGraf.iniciarTelaDeMenu(panel);

                switch (comando) {
                    case "Cadastrar Cliente":
                        // Implementar lógica para cadastrar cliente
                        break;
                    case "Cadastrar Pet":
                        // Implementar lógica para cadastrar pet
                        break;
                    case "Visualizar Registro do Cliente":
                        // Implementar lógica para visualizar registro do cliente
                        break;
                    case "Visualizar Registro do Pet":
                        // Implementar lógica para visualizar registro do pet
                        break;
                    case "Calcular Salário Semanal":
                        // Implementar lógica para calcular salário semanal
                        break;
                    case "Registrar Serviço":
                        // Implementar lógica para registrar serviço
                        break;
                    case "Visualizar Serviços Registrados":
                        // Implementar lógica para visualizar serviços registrados
                        break;
                    case "Cadastrar Veterinário":
                        // Implementar lógica para cadastrar veterinário
                        break;
                    case "Cadastrar Ajudante":
                        // Implementar lógica para cadastrar ajudante
                        break;
                    case "Visualizar Faturamento Semanal":
                        lerFaturamento("faturamento.csv", intGraf);
                        break;
                    case "Sair":
                        JFrame frame = new JFrame("Menu de Saída");
                        JPanel panelSaida = new JPanel();
                        frame.setContentPane(panelSaida);
                        frame.setSize(400, 200);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setVisible(true);

                        if ("Sair do Sistema".equals(intGraf.iniciarTelaDeMenuDeSaida(panelSaida))) {
                            JOptionPane.showMessageDialog(panelSaida, "Salvando os dados...");
                            salvarAnimal(pets, "animais.csv");
                            salvarClientes(clientes, "clientes.csv", "endereco.csv");
                            salvarFuncionario(funcionarios, "funcionarios.csv");
                            salvarEvento(eventos, "eventos.csv", "procedimentos.csv");
                            JOptionPane.showMessageDialog(panelSaida, "Dados salvos com Sucesso!\nObrigado por usar o Sistema!");
                            Thread.sleep(1000);
                            frame.dispose();
                            break menuu; // Encerra o programa
                        }
                        else {
                            break menu;
                        }
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                }
            }
        }
    }


}
