package video.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import video.context.Context;
import video.model.Realisateur;

public class DaoRealisateurVideoImpl implements DaoRealisateur {

	@Override
	public void insert(Realisateur obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into realisateur(id_realisateur,prenom_real,nom_real) values(nextval('seq_realisateur'),?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
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
	}

	@Override
	public Realisateur update(Realisateur obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update realisateur set  prenom_real=?, nom_real=? where id_realisateur=?")) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
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
	public void delete(Realisateur obj) {
		deleteById(obj.getId());
	}

	@Override
	public void deleteById(Integer key) {
			DaoRealisationVideoImpl daoRealisation=new DaoRealisationVideoImpl();
			daoRealisation.deleteByRealisateur(key);
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from realisateur where id_realisateur=?")) {
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
	public List<Realisateur> findAll() {
		List<Realisateur> realisateurs = new ArrayList<Realisateur>();
		Realisateur r = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select * from realisateur");
			while (rs.next()) {
				r = new Realisateur(rs.getInt("id_realisateur"), rs.getString("prenom_real"), rs.getString("nom_real"));
				realisateurs.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return realisateurs;
	}

	@Override
	public Optional<Realisateur> findByKey(Integer key) {
		Realisateur realisateur = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select * from realisateur where id_realisateur=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				realisateur = new Realisateur(rs.getInt("id_realisateur"), rs.getString("prenom_real"), rs.getString("nom_real"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(realisateur);
	}

	@Override
	public List<Realisateur> findByNom(String nom) {
		return null;
	}

}
