package video.model;

public class Dvd extends Article {
	private Boolean bonus;

	public Dvd(Integer id, Integer nbDisques, Adherent emprunteur, Film film, Boolean bonus) {
		super(id, nbDisques, emprunteur, film);
		this.bonus = bonus;
	}

	public Dvd() {
		super();
	}

	public Boolean getBonus() {
		return bonus;
	}

	public void setBonus(Boolean bonus) {
		this.bonus = bonus;
	}

	public Dvd(Integer id, Integer nbDisques) {
		super(id, nbDisques);
	}

	public Dvd(Integer id) {
		super(id);
	}
}
