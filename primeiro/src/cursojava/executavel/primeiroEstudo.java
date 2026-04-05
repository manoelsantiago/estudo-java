package cursojava.executavel;


public class primeiroEstudo {
	
	public static void main(String[] args) {
		//Exemplo de operador ternario IF Else
		int nota1 = 90;
		int nota2 = 70;
		int nota3 = 50;
		int nota4 = 20;
		
		int media = (nota1 + nota2 + nota3 + nota4) / 4;
		
		String saidaResultado = "";
		
		saidaResultado = media >= 70 ? "Aluno aprovado!" : "Aluno Reprovado! :( ";
		
		System.out.println("Situação: " + saidaResultado);
		
		
	}


}
