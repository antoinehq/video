package video.dao;

public class DaoFilmFactory {
	private static DaoFilm singleton = null;

	public static DaoFilm getInstance() {
		if (singleton == null) {
			singleton = new DaoFilmImpl();
		}
		return singleton;
	}
}
