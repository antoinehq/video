package video.dao;

import java.util.List;

import video.model.Film;

public interface DaoFilm extends DaoGeneric<Film, Integer> {
	
	List<Film> findByArticle(String titre);

}
