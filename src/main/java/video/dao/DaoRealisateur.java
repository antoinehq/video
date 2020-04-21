package video.dao;

import java.util.List;

import video.model.Realisateur;

public interface DaoRealisateur extends DaoGeneric<Realisateur,Integer>{
	List<Realisateur> findByNom(String nom);
}
