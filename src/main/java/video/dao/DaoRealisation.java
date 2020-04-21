package video.dao;

import java.util.List;

import video.model.Realisation;

public interface DaoRealisation  extends DaoGeneric<Realisation,Integer>{
	List<Realisation> findById(Integer id);
}
