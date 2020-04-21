package video.dao;

public class DaoRealisationFactory {
	private static DaoRealisation daoRealisation = null;

	public static DaoRealisation getInstance() {
		if (daoRealisation == null) {
			daoRealisation = new DaoRealisationVideoImpl();
		}
		return daoRealisation;
	}
}
