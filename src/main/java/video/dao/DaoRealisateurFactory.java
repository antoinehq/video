package video.dao;

public class DaoRealisateurFactory {
	private static DaoRealisateur daoRealisateur = null;

	public static DaoRealisateur getInstance() {
		if (daoRealisateur == null) {
			daoRealisateur = new DaoRealisateurVideoImpl();
		}
		return daoRealisateur;
	}
}
