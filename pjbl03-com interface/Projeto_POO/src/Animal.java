import java.util.ArrayList;


public class Animal{
    
    private Cliente dono;
    private ArrayList<Evento> eventos;
    private String raca;
    private String nome;
    private String porte;
    private String sexo;
    private String especie;
    private String dataDeNascimento;
    private int id;
    

    public Animal(Cliente dono, String raca, String nome, String porte, 
    String sexo, String especie, String dataDeNascimento, int id){

        this.dono = dono;
        this.raca = raca;
        this.nome = nome;
        this.porte = porte;
        this.sexo = sexo;
        this.especie = especie;
        this.dataDeNascimento = dataDeNascimento;
        this.id = id;
        this.eventos = new ArrayList<>();

    }


    public Pessoa getDono(){
        return dono;
    }


    public String getRaca(){
        return raca;
    }


    public String getNome(){
        return nome;
    }


    public String getPorte(){
        return porte;
    }


    public ArrayList<Evento> getEventos(){
        return eventos;
    }


    public String getSexo(){
        return sexo;
    }


    public String getEspecie(){
        return especie;
    }


    public String getDataDeNascimento(){
        return dataDeNascimento;
    }


    public int getId(){
        return id;
    }


    public void adicionarEvento(Evento e){
        eventos.add(e);
    }


    public int calcularIdade(){
       return DateUtil.calcularIdadeComTratamento(dataDeNascimento);
    }
    
}