package video.dao;

import java.util.List;

import video.model.Article;

public interface DaoArticle extends DaoGeneric<Article, Integer> {

		List<Article> findByAdherent(String prenom);
}
