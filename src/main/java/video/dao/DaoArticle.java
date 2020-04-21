package video.dao;

import java.util.Optional;

import video.model.Article;

public interface DaoArticle extends DaoGeneric<Article, Integer> {

		Optional<Article> findByAdherent(String prenom);
}
