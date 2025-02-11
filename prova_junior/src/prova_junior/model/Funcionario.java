package prova_junior.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
	
	DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

		
	private BigDecimal salario;
	private String funcao;
	
	public Funcionario(String nome, LocalDate dataNasc) {
		super(nome, dataNasc);
	}
	
	public Funcionario(String nome, LocalDate dataNasc, BigDecimal salario, String funcao) {
		super(nome, dataNasc);
		this.salario = salario;
		this.funcao = funcao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "nome= " + super.getNome() + ", data nascimento= "+ super.getDataNasc().format(dateFormater) + ", salario=" + decimalFormat.format(salario) + ", funcao=" + funcao;
	}
	
	

}
