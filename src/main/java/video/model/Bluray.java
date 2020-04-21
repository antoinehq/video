package video.model;

public class Bluray extends Article {
	private Boolean troisD;

	public Bluray(Integer id, Integer nbDisques, Adherent emprunteur, Film film, Boolean troisD) {
		super(id, nbDisques, emprunteur, film);
		this.troisD = troisD;
	}

	public Bluray() {
		super();
	}

	public Bluray(Integer id, Integer nbDisques) {
		super(id, nbDisques);

	}

	public Bluray(Integer id) {
		super(id);
	}

	public Boolean getTroisD() {
		return troisD;
	}

	public void setTroisD(Boolean troisD) {
		this.troisD = troisD;
	}

}
