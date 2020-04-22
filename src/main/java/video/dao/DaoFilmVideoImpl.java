package video.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import video.context.Context;
import video.model.Film;

class DaoFilmVideoImpl implements DaoFilm {
	
	@Override
	public void insert(Film obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into film(id_film,titre,date_sortie) values(nextval('seq_film'),?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getTitre());
			if (obj.getDateDeSortie() != null) {
				ps.setDate(2, new Date(obj.getDateDeSortie().getTime()));
			} else {
				ps.setNull(2, Types.DATE);
			}

			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		Context.destroy();
	}

	@Override
	public Film update(Film obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update Film set titre=?, date_sortie=? where id_film=?")) {
			ps.setString(1, obj.getTitre());
			if (obj.getDateDeSortie() != null) {
				ps.setDate(2, new Date(obj.getDateDeSortie().getTime()));
			} else {
				ps.setNull(2, Types.DATE);
			}
			ps.setInt(3, obj.getId());
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
	public void delete(Film obj) {
		deleteById(obj.getId());
	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from film where id=?")) {
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
		Context.destroy();
	}

	@Override
	public Optional<Film> findByKey(Integer key) {
		Film film = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select * from film where id_film=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				film = new Film(rs.getInt("id_film"), rs.getString("titre"), rs.getDate("date_sortie"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(film);
	}

	@Override
	public List<Film> findAll() {
		List<Film> films = new ArrayList<Film>();
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select * from film");
			while (rs.next()) {
				films.add(new Film(rs.getInt("id_film"), rs.getString("titre"), rs.getDate("date_sortie")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Context.destroy();
		return films;
	}

	@Override
	public List<Film> findByArticle(String titre) {
		// TODO Auto-generated method stub
		return null;
	}

}
