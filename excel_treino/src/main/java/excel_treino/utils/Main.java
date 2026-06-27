package excel_treino.utils;

import java.time.LocalDate;
import java.util.Scanner;

import excel_treino.model.Emprestimo;
import excel_treino.model.Livro;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		boolean encerrar = false;
		ExcelUtils excel = new ExcelUtils();
		while(!encerrar) {
		System.out.println("..:: Sistema Biblioteca Acreana ::..\n");
			System.out.println("Escolha a opção: ");
			System.out.println("1 - Cadastrar livro.");
			System.out.println("2 - Listar Livros.");
			System.out.println("3 - Pesquisar livro.");
			System.out.println("4 - Emprestar livro.");
			System.out.println("5 - Devolver livro.");
			System.out.println("6 - Editar livro.");
			System.out.println("7 - Excluir livro.");
			System.out.println("8 - Livros disponíveis.");
			System.out.println("9 - Relatório geral.");
			System.out.println("0 - Sair.");
			System.out.println();
			Scanner scanner = new Scanner(System.in);
			System.out.print("Digite a opção: ");
			
			int opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			case 1:
				Livro livro = new Livro();
				System.out.println("\nCADASTRO DE LIVRO");
				System.out.print("Informe o título do livro: ");
				livro.setTitulo(scanner.nextLine());
				System.out.println();
				System.out.print("Informe o autor do livro: ");
				livro.setAutor(scanner.nextLine());
				System.out.println();
				System.out.print("Informe o ano de publicação: ");
				livro.setAnoPublicacao(scanner.nextLine());
				System.out.println();
				
				excel.salvarNoExcel(livro);
				System.out.println("-----------------------------------------");
				break;
			case 2:
				excel.listarLivros();
				System.out.println("-----------------------------------------");
				System.out.print("Pressione enter para continuar...");
				String aux = scanner.nextLine();
				break;
			case 3:
				System.out.println("\nPESQUISAR LIVRO");
				System.out.print("Informe o nome ou parte: ");
				String nome = scanner.nextLine();
				excel.buscarLivro(nome);
				System.out.println("-----------------------------------------");
				System.out.print("Pressione enter para continuar...");
				aux = scanner.nextLine();
				break;
			case 4:
				System.out.println("\nEMPRESTIMO DE LIVRO");
				
				System.out.print("Informe o nome do livro: ");
				String nmeLivroempretimo = scanner.nextLine();
				Livro livroEmprestimo = excel.buscarLivro(nmeLivroempretimo);
				
				System.out.print("\nInforme agora a data da devolução: ");
				String dataDevolucao = scanner.nextLine();
				
				System.out.print("Informe o nome do cliente: ");
				String nomeCliente = scanner.nextLine();
				
				LocalDate dataAtual = LocalDate.now();
				String dataString = (dataAtual.getDayOfMonth()+"/"+dataAtual.getMonthValue()+"/"+dataAtual.getYear());
				
				Emprestimo emprestimo = new Emprestimo();
				
				emprestimo.setDataDevolucao(dataDevolucao);
				emprestimo.setDataEmprestimo(dataString);
				emprestimo.setLivro(livroEmprestimo);
				emprestimo.setNomeCliente(nomeCliente);
				
				excel.emprestarLivro(emprestimo);
				System.out.print("Pressione Enter para continuar...");
				aux = scanner.nextLine();
				break;
				
			case 5:
				System.out.println("DEVOLVER LIVRO");
				System.out.print("Informe o nome do livro: ");
				String nmeLivroDevolucao = scanner.nextLine();
				Livro livroDevolucao = excel.buscarLivro(nmeLivroDevolucao);
				System.out.print("Informe a data da devolução: ");
				String dataDevolucaoEfetiva = scanner.nextLine();
				excel.devolverLivro(livroDevolucao, dataDevolucaoEfetiva);
				System.out.print("Pressione Enter para continuar...");
				aux = scanner.nextLine();
				break;
			
			case 6:
				System.out.println("EDITAR LIVRO");
				System.out.print("Informe o nome do livro: ");
				String nomeEditar = scanner.nextLine();
				Livro livroEditar = excel.buscarLivro(nomeEditar);
				System.out.println("Informe os dados para alterar ou deixe em branco...");
				System.out.print("Informe o título correto: ");
				String tituloNovo = scanner.nextLine();
				System.out.print("Informe o autor correto: ");
				String autorNovo = scanner.nextLine();
				System.out.print("Informe o ano correto: ");
				String anoNovo = scanner.nextLine();
				
				if(!tituloNovo.isBlank()) {
					livroEditar.setTitulo(tituloNovo);
				}
				if(!autorNovo.isBlank()) {
					livroEditar.setAutor(autorNovo);
				}
				if(!anoNovo.isBlank()) {
					livroEditar.setAnoPublicacao(anoNovo);
				}
				
				excel.editarLivro(livroEditar);
				
				System.out.println("Pressione Enter para continuar...");
				aux = scanner.nextLine();
				break;
				
			case 7:
				System.out.println("EXCLUIR LIVRO");
				System.out.print("Informe o ID do livro para exclusão: ");
				String idExcluir = scanner.nextLine();
				
				try {
					Integer.parseInt(idExcluir);
					excel.excluirLivro(idExcluir);
					System.out.println("Pressione Enter para continuar...");
					aux = scanner.nextLine();
				}catch(NumberFormatException e) {
					System.out.println("ID Informado inválido.");
				}
				break;
			
			case 8:
				excel.listarLivrosDisponiveis();
				System.out.println("Pressione Enter para continuar...");
			    aux = scanner.nextLine();
				break;
				
			case 9:
				excel.listarLivros();
				System.out.println("Pressione Enter para continuar...");
				aux = scanner.nextLine();
				break;
				
			case 0:
				System.out.println("SAIR DO SISTEMA...");
				System.out.print("Tem certeza que deseja encerrar? Escolha S ou N: ");
				String op = scanner.nextLine();
				if(op.equalsIgnoreCase("S")) {
					encerrar = true;
					System.out.println("\nSISTEMA ENCERRADO COM SUCESSO");
				}else {
					System.out.println("Sistema não foi encerrado.");
				}
				System.out.print("Pressione Enter para continuar...");
				aux = scanner.nextLine();
			}
			
		}//while
		
	}

}
