import java.util.ArrayList;


public class Procedimento extends Evento{
    private String medicamento;
    private String descricao;


    public Procedimento(String nome, String data, String hora, double preco, Pessoa funcionario,
                        Animal animal, String medicamento, String descricao){
        super(nome, data, hora, preco, funcionario, animal);
        this.medicamento = medicamento;
        this.descricao = descricao;
    }


    @Override
    public int verificar(){
        return 2;
    }


    public String getMedicamento(){
        return medicamento;
    }


    public String getDescricao(){
        return descricao; 
    }


    @Override
    public ArrayList<String> obterDados(){
        ArrayList<String> dados = new ArrayList<>();
        dados.add(medicamento);
        dados.add(descricao);
        return dados;
    }
    
}