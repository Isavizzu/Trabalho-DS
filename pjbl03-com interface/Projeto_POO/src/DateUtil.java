import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

 
public class DateUtil {

    //Método para calcular a idade com tratamento de exceção
    public static int calcularIdadeComTratamento(String dataDeNascimento){
        //Define o formato da data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            //Tetna analisar a data de nascimento usando o formato especifico
            LocalDate nascimento = LocalDate.parse(dataDeNascimento, formatter);
            //Obtém a data atual
            LocalDate hoje = LocalDate.now();
            //Calcula a diferença de anos entre a data de nascimento e a data atual
            return Period.between(nascimento, hoje).getYears();
        } catch (DateTimeParseException e){
            //Se ocorrer uma exceção ao analisar  a data de nascimento imprime uma mensagem de erro
            System.err.println("Erro ao calcular a idade: " + e.getMessage());
            //Relança a exceção para que quem chamou o método
            throw e;
        }
    }
    
}
