import java.util.ArrayList;


public class Cliente extends Pessoa {
    private Endereco enderecoCliente;
    private ArrayList<Animal> animais;


    public Cliente(String nome, String cpf, String dataDeNascimento, String telefone, Endereco enderecoCliente) {
        super(nome, cpf, dataDeNascimento, telefone);
        this.enderecoCliente = enderecoCliente;
        this.animais = new ArrayList<>();
    }


    public Endereco getEndereco(){
        return enderecoCliente;
    }


    public void visualizarEndereco() {
        imprimirLinha(50);
        imprimirCentralizado("Endereço", 50);
        imprimirLinha(50);
        imprimir("Estado: " + enderecoCliente.getEstado());
        imprimir("Cidade: " + enderecoCliente.getCidade());
        imprimir("Bairro: " + enderecoCliente.getBairro());
        imprimir("Rua: " + enderecoCliente.getRua());
        imprimir("Número: " + enderecoCliente.getNumero());
        imprimir("Complemento: " + enderecoCliente.getComplemento());
    }


    public void adicionarAnimal(Animal a) {
        animais.add(a);
    }


    public void mudarEndereco() {
        enderecoCliente = cadastrarEndereco("Atualizando Endereço","Endereço Atualizado com Sucesso!");
    }
    

    @Override
    public void visualizarAnimal(ArrayList<Animal> pet) {
        if (!animais.isEmpty()) {
            imprimirLinha(50);
            imprimirCentralizado("Pets", 50);
            imprimirLinha(50);
            for (Animal animal : animais) {
                imprimir("Nome: " + animal.getNome());
                imprimir("Espécie: " + animal.getEspecie());
                imprimir("Porte: " + animal.getPorte());
                imprimir("Raça: " + animal.getRaca());
                imprimir("Sexo: " + animal.getSexo());
                imprimir("Idade: " + animal.calcularIdade() + " anos");
                imprimir("Tratamentos realizados:");
                imprimirLinha(50);
            }
        } else {
            imprimir(getNome() + " ainda não tem pets cadastrados!");
        }
    } 
}