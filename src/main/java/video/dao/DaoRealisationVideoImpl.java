package video.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import video.context.Context;
import video.model.Realisation;

public class DaoRealisationVideoImpl implements DaoRealisation {

	@Override
	public void insert(Realisation obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into realisation(id_film, id_realisateur) values(?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, obj.getId().getFilm().getId());
			ps.setInt(2, obj.getId().getRealisateur().getId());
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public Realisation update(Realisation obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update realisation set  id_film=?, id_realisateur=? where id_film=?")) {
			ps.setInt(1, obj.getId().getFilm().getId());
			ps.setInt(2, obj.getId().getRealisateur().getId());
			ps.setInt(3, obj.getId().getFilm().getId());
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		Context.destroy();
		return obj;
	}
	
	@Override
	public void delete(Realisation obj) {		
	}

	@Override
	public void deleteById(Integer key) {		
	}
	
	public void deleteFilm(Realisation obj) {
		deleteById(obj.getId().getFilm().getId());
	}
	
	public void deleteByFilm(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from realisation where id_film=?")) {
			ps.setInt(1, key);
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
	}
	
	public void deleteRealisateur(Realisation obj) {
		deleteById(obj.getId().getRealisateur().getId());
	}
	
	public void deleteByRealisateur(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from realisation where id_realisateur=?")) {
			ps.setInt(1, key);
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
	}


	
	@Override
	public List<Realisation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Realisation> findByKey(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Realisation> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
