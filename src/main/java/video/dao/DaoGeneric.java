package video.dao;

import java.util.List;

public interface DaoGeneric<T, K> {
	
	void insert(T obj);
	
	T update(T obj);
	
	void delete(T obj);
	
	void deleteById(K key);
	
	List<T> findAll();
	
	T findByKey(K key);
	
}