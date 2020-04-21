package video.dao;

public class DaoArticleFactory {

	private static DaoArticle daoArticle = null;
	
	public static DaoArticle getInstance() {
		if (daoArticle == null) {
			daoArticle = new DaoArticleVideoImpl();
		}
		return daoArticle;
	}
}
