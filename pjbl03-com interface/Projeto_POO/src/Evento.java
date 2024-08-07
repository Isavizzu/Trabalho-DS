import java.util.ArrayList;


public class Evento {
    protected String nome;
    protected String data;
    protected String hora;
    protected double preco;
    protected Pessoa funcionario;
    protected Animal animal;


    public Evento(String nome, String data, String hora, double preco, Pessoa funcionario, Animal animal){
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.preco = preco;
        this.funcionario = funcionario;
        this.animal = animal;
    }


    public int verificar(){
        return 1;
    }


    public ArrayList<String> obterDados(){
        ArrayList<String> dados = new ArrayList<>();
        return dados;
    }


    public String getHora(){
        return hora;
    }


    public Pessoa getFuncionario(){
        return funcionario;
    }


    public Animal getAnimal(){
        return animal;
    }


    public String imprimirDados(){
        return "Serviço: " + nome + " - Data: " + data;
    }


    public String getNome(){
        return nome;
    }


    public String getData(){
        return data;
    }


    public Double getPreco(){
        return preco;
    }


    public String getTratamento(){
        return "Tratamento";
    }
    
}
