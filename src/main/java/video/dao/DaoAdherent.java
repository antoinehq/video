package video.dao;

import java.util.List;

import video.model.Adherent;

public interface DaoAdherent extends DaoGeneric<Adherent, Integer> {
	List<Adherent> findByPrenom(String prenom);
}
