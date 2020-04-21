package video.model;

public class Article {
	private Integer id;
	private Integer nbDisques;
	private Adherent emprunteur;
	private Film film;
	
	public Article(Integer id, Integer nbDisques, Adherent emprunteur, Film film) {
		super();
		this.id = id;
		this.nbDisques = nbDisques;
		this.emprunteur = emprunteur;
		this.film = film;
	}

	public Article(Integer id, Integer nbDisques) {
		super();
		this.id = id;
		this.nbDisques = nbDisques;
	}

	public Article(Integer id) {
		super();
		this.id = id;
	}

	public Article() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNbDisques() {
		return nbDisques;
	}

	public void setNbDisques(Integer nbDisques) {
		this.nbDisques = nbDisques;
	}

	public Adherent getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Adherent emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Film getFilm() {
		return film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
