package video.model;

import java.util.Date;

public class Film {
	
	private Integer id;
	private String titre;
	private Date dateDeSortie;

	public Film(Integer id, String titre, Date dateDeSortie) {
		this.id = id;
		this.titre = titre;
		this.dateDeSortie = dateDeSortie;
	}
	
	public Film(String titre, Date dateDeSortie) {
		this.titre = titre;
		this.dateDeSortie = dateDeSortie;
	}
	
	public Film() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateDeSortie() {
		return dateDeSortie;
	}

	public void setDateDeSortie(Date dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDeSortie == null) ? 0 : dateDeSortie.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		Film other = (Film) obj;
		if (dateDeSortie == null) {
			if (other.dateDeSortie != null)
				return false;
		} else if (!dateDeSortie.equals(other.dateDeSortie))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
}
