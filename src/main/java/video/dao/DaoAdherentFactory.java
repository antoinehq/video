package video.dao;

public class DaoAdherentFactory {
	private static DaoAdherent singleton = null;

	public static DaoAdherent getInstance() {
		if (singleton == null) {
			singleton = new DaoAdherentVideoImpl();
		}
		return singleton;
	}
}