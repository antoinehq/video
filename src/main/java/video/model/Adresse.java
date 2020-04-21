package video.model;

public class Adresse {
	private Integer numero;
	private String rue;
	private String codePostal;
	private String ville;
	private Article articles;

	public Adresse() {
	}

	public Adresse(Integer numero, String rue, String codePostal, String ville, Article articles) {
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.articles = articles;
	}

	public Adresse(Integer numero, String rue, String codePostal, String ville) {
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Adresse(String ville) {
		this.ville = ville;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Article getArticles() {
		return articles;
	}

	public void setArticles(Article articles) {
		this.articles = articles;
	}

}
