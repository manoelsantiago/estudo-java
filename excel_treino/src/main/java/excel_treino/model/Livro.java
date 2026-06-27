package excel_treino.model;

public class Livro {

	private String id;
	private String titulo;
	private String autor;
	private String anoPublicacao;
	private String disponivel;//sim ou nao
	private int qtdAtributos = 5;
	
		
	

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anoPublicacao=" + anoPublicacao
				+ ", disponivel=" + disponivel + ", qtdAtributos=" + qtdAtributos + "]";
	}
	
	public String getId() {
		return id;
	}

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public int getQtdAtributos() {
		return qtdAtributos;
	}

	public void setQtdAtributos(int qtdAtributos) {
		this.qtdAtributos = qtdAtributos;
	}
	
	
	
	
	
	
}
