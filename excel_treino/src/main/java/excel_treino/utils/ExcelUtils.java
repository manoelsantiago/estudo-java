package excel_treino.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excel_treino.model.Emprestimo;
import excel_treino.model.Livro;

public class ExcelUtils {
	
	String endereco = "C:\\Users\\pmano\\git\\estudo-java\\excel_treino\\src\\main\\java";
	String planilha = "C:\\Users\\pmano\\git\\estudo-java\\excel_treino\\src\\main\\resources\\biblioteca.xlsx";
	
	
	public void salvarNoExcel(Livro livro) throws IOException{
		File file = new File(endereco);
		String novoId = "0";
		//cria nova planilha
		if(!file.exists()) {
			novoId = "1";
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
			XSSFSheet planilha1 = xssfWorkbook.createSheet("livro");
			XSSFSheet planilha2 = xssfWorkbook.createSheet("emprestimo");
			file.createNewFile();
			
			List<String> cabecalho = new ArrayList<>();
			cabecalho.add("id");
			cabecalho.add("titulo");
			cabecalho.add("autor");
			cabecalho.add("ano_publicacao");
			cabecalho.add("disponivel");
			
			Row linha = planilha1.createRow(0);
			int i = 0;
			for(String texto : cabecalho) {
				Cell cell = linha.createCell(i);
				cell.setCellValue(texto);
				i++;
			}
			
			List<String> cabecalho2 = new ArrayList<>();
			cabecalho2.add("id");
			cabecalho2.add("id_livro");
			cabecalho2.add("nome_cliente");
			cabecalho2.add("data_emprestimo");
			cabecalho2.add("data_devolucao");
			
			Row linha2 = planilha2.createRow(0);
			i = 0;
			for (String texto : cabecalho2) {
				Cell cell = linha2.createCell(i);
				cell.setCellValue(texto);
				i++;
			}
			
			
			FileOutputStream saida = new FileOutputStream(file);
			
			xssfWorkbook.write(saida);
			
			saida.flush();
			
			saida.close();
			xssfWorkbook.close();
			System.out.println("Rotina de primeira execução.\nCriação do arquivo de dados executada com sucesso.\n");
			System.out.print("Pressione Enter para continuar...");
			
			Scanner scanner = new Scanner(System.in);
			String aux = scanner.nextLine();
		}//if
		
		//se não houver livro sai do programa
		if(livro.equals(null)) {
			return;
		}
		

		//salva o novo livro
		FileInputStream entrada = new FileInputStream(file);
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(entrada);		
		XSSFSheet planilha = xssfWorkbook.getSheetAt(0);
		
		int ultimaLinha = planilha.getLastRowNum();
		
		Row rowUltima = planilha.getRow(ultimaLinha);
		Cell cell = rowUltima.getCell(0);

		if(!novoId.equalsIgnoreCase("1")) {
			String idMaior = cell.getStringCellValue();
			int idInt = Integer.parseInt(idMaior);
			int x = idInt + 1;
			novoId = Integer.toString(x);
		}
			
		XSSFRow linhaNova = planilha.createRow(++ultimaLinha);
		
		linhaNova.createCell(0).setCellValue(novoId);
		linhaNova.createCell(1).setCellValue(livro.getTitulo());
		linhaNova.createCell(2).setCellValue(livro.getAutor());
		linhaNova.createCell(3).setCellValue(livro.getAnoPublicacao());
		linhaNova.createCell(4).setCellValue("Sim");
		
		entrada.close();
		
		FileOutputStream saida = new FileOutputStream(file);
		
		xssfWorkbook.write(saida);
		
		saida.flush();
		
		xssfWorkbook.close();
		saida.close();
		System.out.println("Planilha salva com sucesso.\n");
	}//salvarNoExcel
	
	
	public void listarLivros() throws IOException{
		
		File file = new File(planilha);
		
		if(!file.exists()) {
			System.out.println("\nExecução interrompida. Arquivo não localizado.\n");
			return;
		}
		
		FileInputStream entrada = new FileInputStream(file);
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(entrada);
		
		XSSFSheet planilha = xssfWorkbook.getSheetAt(0);
		
		int qtdDisponiveis = 0;
		
		for(Row linha : planilha) {
			int i = 0;
			for(Cell cell : linha) {
				System.out.print(cell.getStringCellValue() + "||");
				if(i == 4) {
					if(cell.getStringCellValue().equalsIgnoreCase("Sim")) {
						qtdDisponiveis += 1;
					}
				}
				++i;
			}
			System.out.println();
		}
		System.out.println("\nFim da lista.\n");
		System.out.println("\nResumo:");
		System.out.println("Total de livros: " + (planilha.getLastRowNum()));
		System.out.println("Disponíveis: " + qtdDisponiveis);
		System.out.println("Emprestados: " + ((planilha.getLastRowNum()) - qtdDisponiveis));
		
		xssfWorkbook.close();
		
	}//listarLivros
	
	
	public Livro buscarLivro(String nome) throws IOException {
		
		
		File file = new File(planilha);
		
		if(!file.exists()) {
			return null;
		}
		
		FileInputStream entrada = new FileInputStream(file);
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(entrada);
		
		XSSFSheet planilhaLivro = xssfWorkbook.getSheet("livro"); 
		
		Livro livro = new Livro();
		for(Row linha : planilhaLivro) {
			for(Cell cell : linha) {
				int i = cell.getColumnIndex();
				switch(i) {
				case 0:
					livro.setId(cell.getStringCellValue());
					break;
				case 1:
					livro.setTitulo(cell.getStringCellValue());
					break;
				case 2:
					livro.setAutor(cell.getStringCellValue());
					break;
				case 3:
					livro.setAnoPublicacao(cell.getStringCellValue());
					break;
				case 4:
					livro.setDisponivel(cell.getStringCellValue());
					break;
				}
				
				if(i == 4) {
					if(livro.getTitulo().toLowerCase().contains(nome.toLowerCase())) {
						System.out.println("\nLivro encontrato.\n");
						xssfWorkbook.close();
						System.out.println("Id: " + livro.getId());
						System.out.println("Titulo: " + livro.getTitulo());
						System.out.println("Autor: " + livro.getAutor());
						System.out.println("Ano Publicação: " + livro.getAnoPublicacao());
						System.out.println("Disponível: " + livro.getDisponivel());
						System.out.println();
						return livro;
					}
				}
				i++;
				}
			}
		xssfWorkbook.close();
		System.out.println("\nLivro não localizado.\n");
		return null;
		}//buscarLivro
	
	public void emprestarLivro(Emprestimo emprestimo) throws IOException{
		
		if(emprestimo.getLivro() == null) {
			System.out.println("Empréstimo não efetivado. Livro não localizado.");
			return;
		}
		
		File file = new File(planilha);
		
		if(!file.exists()) {
			System.out.println("\nExecução interrompida! Arquivo com os dados não localizado.\n");
		}
		
		Livro livro = emprestimo.getLivro();
		
		if(livro.getDisponivel().equalsIgnoreCase("nao") || livro.getDisponivel().equalsIgnoreCase("não")) {
			System.out.println("\nFalha no empréstimo. Livro indisponível.\n");
			return;
		}
		
		FileInputStream entrada = new FileInputStream(file);
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(entrada);
		
		XSSFSheet sheetLivro = xssfWorkbook.getSheet("livro");
		XSSFSheet sheetEmprestimo = xssfWorkbook.getSheet("emprestimo");
		
		

		//preenche o emprestimo na planilha emprestimo
		
		
		int linhasEmprestimo = sheetEmprestimo.getLastRowNum();
		
		Row novaLinha = sheetEmprestimo.createRow(++linhasEmprestimo);
		
		novaLinha.createCell(0).setCellValue(++linhasEmprestimo);
		novaLinha.createCell(1).setCellValue(livro.getId());
		novaLinha.createCell(2).setCellValue(emprestimo.getNomeCliente());
		novaLinha.createCell(3).setCellValue(emprestimo.getDataEmprestimo());
		novaLinha.createCell(4).setCellValue(emprestimo.getDataDevolucao());
		
		livro.setDisponivel("Não");
		
		//alterar o status na planilha livro
		boolean flag = false;
		
		for(Row linha : sheetLivro) {
			int i = 0;
			for(Cell cell : linha) {
				switch(i) {
				case 0:
					if(livro.getId().equalsIgnoreCase(cell.getStringCellValue())) {
						flag = true;
					}
					break;
				case 4:
					if(flag) {
						cell.setCellValue(livro.getDisponivel());
						flag = false;
					}
					break;
				}
				i++;
			}
		}
		
		
		FileOutputStream saida = new FileOutputStream(file);
		
		xssfWorkbook.write(saida);
		
		saida.flush();
		
		//todos os fechamentos
		saida.close();
		entrada.close();
		xssfWorkbook.close();
		
		System.out.println("\nEmpréstimo registrado com sucesso.\n");
		
		
	}//emprestarLivro
	
	
	public void devolverLivro(Livro livro, String dataDevolucao) throws IOException {
		
		File file = new File(planilha);
		
		if(!file.exists()) {
			System.out.println("\nExecução interrompida. Arquivo de dados não localizado.\n");
			return;
		}
		
		if(livro == null) {
			System.out.println("\nExecução interrompida.Livro não localizado.\n");
			return;
		}
		
		FileInputStream entrada = new FileInputStream(file);
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(entrada);
		
		XSSFSheet planilhaLivro = xssfWorkbook.getSheet("livro");
		XSSFSheet planilhaEmprestimo = xssfWorkbook.getSheet("emprestimo");
		
		//atualiza planilha emprestimo
		for(Row linha : planilhaEmprestimo) {
		boolean flag = false;
		int i = 0;
			for(Cell cell : linha) {
				switch(i) {
				case 1:
					if(livro.getId().equalsIgnoreCase(cell.getStringCellValue())) {
						flag = true;
					}
					break;
				case 4:
					if(flag) {
						if(cell.getStringCellValue() == null || cell.getStringCellValue() == "") {
							cell.setCellValue(dataDevolucao);
						}
					}
					break;
				}
				i++;
			}
		}
		
		//atualiza planilha livro
		for (Row linha : planilhaLivro) {
			boolean flag = false;
			int i = 0;
			for(Cell cell : linha) {
				switch (i) {
				case 0:
					if(livro.getId().equalsIgnoreCase(cell.getStringCellValue())) {
						flag = true;
					}
					break;
				case 4:
					if(flag) {
						cell.setCellValue("Sim");
					}
					break;
				}
				i++;
			}
		}
		
		
		entrada.close();
		
		FileOutputStream saida = new FileOutputStream(file);
		
		xssfWorkbook.write(saida);
		
		saida.flush();
		
		xssfWorkbook.close();
		saida.close();
		
		System.out.println("Livro "+livro.getTitulo()+" devolvido com sucesso.");
		
		
		
	}//devolverLivro
	
	
	public void listarLivrosDisponiveis() throws IOException{
		
		File file = new File(planilha);
		
		if(!file.exists()) {
			System.out.println("\nExecução interrompida. Arquivo não localizado.\n");
			return;
		}
		
		FileInputStream entrada = new FileInputStream(file);
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(entrada);
		
		XSSFSheet planilha = xssfWorkbook.getSheetAt(0);
		
		int qtdDisponiveis = 0;
		
		List<Livro> livros = new ArrayList<>();
		
		for(Row linha : planilha) {
			Livro livro = new Livro();
			int i = 0;
			
			for(Cell cell : linha) {
				switch (i) {
				case 0:
					livro.setId(cell.getStringCellValue());
					break;
				case 1:
					livro.setTitulo(cell.getStringCellValue());
					break;
				case 2:
					livro.setAutor(cell.getStringCellValue());
					break;
				case 3:
					livro.setAnoPublicacao(cell.getStringCellValue());
					break;
				case 4:
					livro.setDisponivel(cell.getStringCellValue());
					if(cell.getStringCellValue().equalsIgnoreCase("Sim")) {
						livros.add(livro);
					}
					break;
				}
				++i;
			}//for
			}//for
		
			System.out.println();
			System.out.println("Total de livros: " + planilha.getLastRowNum());
			System.out.println("Total de livros disponíveis: " + livros.size());
			xssfWorkbook.close();
			
			System.out.println("Relação:\n");
			for(Livro livro : livros) {
				System.out.println("Id: " + livro.getId());
				System.out.println("Título: " + livro.getTitulo());
				System.out.println("Autor: " + livro.getAutor());
				System.out.println("Ano publicação: " + livro.getAnoPublicacao());
				System.out.println("Disponível: " + livro.getDisponivel());
				System.out.println("------------------------------------------------");
			}
	}//listarLivrosDisponíveis
	
	public void excluirLivro(String id) throws IOException {
		
		File file = new File(planilha);
		
		if(!file.exists()) {
			System.out.println("Execução interrompida, arquivo de dados não localizado.");
			return;
		}
		
		if(id == null || id.isBlank()) {
			System.out.println("Execução interrompida. Id não localizado.");
			return;
		}
		
		FileInputStream entrada = new FileInputStream(file);
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(entrada);
		
		XSSFSheet planilha = xssfWorkbook.getSheet("livro");
		
		boolean flag = false;
		
		Row rowExcluir = null;
		int rowIndex = 0;
		
		for(Row row : planilha) {
			
			Cell cellId = row.getCell(0);
			
			if(cellId != null && cellId.getStringCellValue().equals(id)) {
				rowExcluir = row;
				rowIndex = row.getRowNum();
			}
			
		}//for
		
		if (rowExcluir != null) {
			planilha.removeRow(rowExcluir);
			flag = true;
			
			//se a linha excluída estiver no meio vai subir as linhas
			if(rowIndex < planilha.getLastRowNum()) {
				planilha.shiftRows(rowIndex+1, planilha.getLastRowNum(), -1);
			}
		}
		
		entrada.close();
		
		FileOutputStream saida = new FileOutputStream(file);
		xssfWorkbook.write(saida);
		saida.flush();
		saida.close();
		xssfWorkbook.close();
		
		if(flag) {
			System.out.println("\nLivro código: " + id + " - excluído com sucesso.\n");
		}else System.out.println("\nLivro código: "+id+" - NÃO foi localizado para exclusão.");
		
	}//excluirLivro
	
	public void editarLivro(Livro livro) throws IOException {
		File file = new File(planilha);
		
		if(!file.exists()) {
			System.out.println("\nExecução interrompida. Arquivo de dados não localizado.\n");
			return;
		}
		
		if(livro == null) {
			System.out.println("\nExecução interrompida. Código id do livro não localizado.\n");
			return;
		}
		
		FileInputStream entrada = new FileInputStream(file);
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(entrada);
		
		entrada.close();
		
		XSSFSheet planilha = xssfWorkbook.getSheet("livro");
		
		Row rowEditar = null;
		
		for(Row row : planilha) {
			Cell celulaId = row.getCell(0);
			if(celulaId.getStringCellValue().equalsIgnoreCase(livro.getId())) {
				rowEditar = row;
			}
		}
		
		int i = 0;
		for(Cell cell : rowEditar) {
			switch(i) {
				case 0:
					cell.setCellValue(livro.getId());
					break;
				case 1:
					cell.setCellValue(livro.getTitulo());
					break;
				case 2:
					cell.setCellValue(livro.getAutor());
					break;
				case 3:
					cell.setCellValue(livro.getAnoPublicacao());
					break;
				case 4:
					cell.setCellValue(livro.getDisponivel());
					break;
			}
			i++;
		}
		
		FileOutputStream saida = new FileOutputStream(file);
		
		xssfWorkbook.write(saida);
		
		saida.flush();
		
		saida.close();
		xssfWorkbook.close();
		System.out.println("\nLivro Editado com Sucesso.\n");
		
	}//editarLivro
		
	}//class