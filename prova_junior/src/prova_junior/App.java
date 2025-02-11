package prova_junior;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import prova_junior.model.Funcionario;

public class App {

	public static void main(String[] args) {
		
		
		//3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima. 
		List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
				new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("Joao", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Heloisa", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Caio", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
				));
	
		//3.2 – Remover o funcionário “João” da lista. 
		funcionarios.removeIf(funcionario -> (funcionario.getNome().equals("Joao")));
		System.out.println("Joao removido ----------\n");
		
		//3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
		//informação de data deve ser exibido no formato dd/mm/aaaa; 
		//informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto
		//e decimal como vírgula. 
		for(Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.toString());
		}
		
		//3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor. 
		funcionarios.forEach(f -> {
			f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")));
		});
		
		//3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”. 
		Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao));
		
		
		//3.6 – Imprimir os funcionários, agrupados por função. 
		System.out.println("\nFuncionarios agrupados por funcao --------------\n");

		funcionariosPorFuncao.forEach((funcao, lista) -> {
			String nomes = lista.stream()
				.map(Funcionario::getNome)
				.collect(Collectors.joining(", "));
			
		System.out.println(funcao + ":" + nomes);
		
	});
		
		//3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12. 

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		final LocalDate dataAtual = LocalDate.now();
		
		
		
		System.out.println("\nAniversariantes do mes de outubro e dezembro ----------\n");
		Set<Integer> mesesAniversario = Set.of(10,12); 
				
		funcionarios.stream()
			.filter(f -> mesesAniversario.contains(f.getDataNasc().getMonthValue()))
			.forEach(f -> System.out.println(f.getNome() + " - " + dateFormatter.format(f.getDataNasc())));
				
				
		//3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade. 		
		System.out.println("\nFuncionario de maior idade ----------\n");
		
		Optional<Funcionario> funcionarioMaisVelho = funcionarios.stream()
		.min((f1, f2) -> f1.getDataNasc().compareTo(f2.getDataNasc()));
		
		System.out.println(funcionarioMaisVelho.get().getNome() + " : " + (Period.between(funcionarioMaisVelho.get().getDataNasc(), dataAtual).getYears()));

		//3.10 – Imprimir a lista de funcionários por ordem alfabética. 
		System.out.println("\nLista dos funcionarios ordenada alfabeticamente ---------\n");
		
		funcionarios.stream()
			.sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
			.forEach(System.out::println);

		
		//3.11 – Imprimir o total dos salários dos funcionários. 
		BigDecimal totalSalario = new BigDecimal(0);
		DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

		
		for (Funcionario funcionario : funcionarios) {
			totalSalario = totalSalario.add(funcionario.getSalario());			
		}
		
		System.out.println("\nSalario total de todos os funcionarios da lista: R$ " + decimalFormat.format(totalSalario));
		
		
		//3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00
		final BigDecimal salarioMinimo = new BigDecimal("1212");
		
		System.out.println("\nQuantidade de salarios minimos por funcionario --------------\n");		
		for(Funcionario funcionario : funcionarios) {
			System.out.println("Funcionario " + funcionario.getNome() + " recebe " + funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_EVEN) + " salarios minimos.");		
		};
		
	}
}
