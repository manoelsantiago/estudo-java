package cursojava.executavel;

import javax.swing.JOptionPane;

public class ExemploCarros {
	
	public static void main(String[] args) {
		int qtdCarros = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de carros:"));
		int qtdPessoas = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de pessoas:"));
		
		double carroPorPessoa = (double)qtdCarros/qtdPessoas;
		
		JOptionPane.showMessageDialog(null, "Carros: "+qtdCarros+". Pessoas: "+qtdPessoas+". Carro por pessoa: "+carroPorPessoa);
		
	}

}
