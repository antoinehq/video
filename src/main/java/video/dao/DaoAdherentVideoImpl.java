package video.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import video.model.Civilite;
import video.context.Context;
import video.model.Adherent;

public class DaoAdherentVideoImpl implements DaoAdherent {

	@Override
	public void insert(Adherent obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into adherent(id,prenom,nom,civilite,adresse) values(nextval('seq_adherent'),?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			if (obj.getCivilite() != null) {
				ps.setString(3, obj.getCivilite().toString());
			} else {
				ps.setNull(3, Types.VARCHAR);
			}
			if (obj.getAdresse() != null) {
				ps.setString(4, obj.getAdresse().donnerAdresse());
			} else {
				ps.setNull(4, Types.VARCHAR);
			}
			ps.setInt(5, obj.getId());
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
	public Adherent update(Adherent obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update adherent set prenom=?,nom=?,civilite=?,adresse=? where id=?")) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			if (obj.getCivilite() != null) {
				ps.setString(3, obj.getCivilite().toString());
			} else {
				ps.setNull(3, Types.VARCHAR);
			}
			if (obj.getAdresse() != null) {
				ps.setString(4, obj.getAdresse().donnerAdresse());
			} else {
				ps.setNull(4, Types.VARCHAR);
			}
			ps.setInt(5, obj.getId());
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
	public void delete(Adherent obj) {
		deleteById(obj.getId());

	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from adherent where id=?")) {
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
	public List<Adherent> findAll() {
		List<Adherent> adherents = new ArrayList<Adherent>();
		Adherent ad = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select * from adherent where id=?");
			while (rs.next()) {
				ad = new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
				if (rs.getString("civilite") != null) {
					ad.setCivilite(Civilite.valueOf(rs.getString("civilite")));
				}
				if (rs.getString("adresse") != null) {
//					ad.setAdresse().donnerAdresse();
				}
				adherents.add(ad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return adherents;
	}

	@Override
	public Optional<Adherent> findByKey(Integer key) {
		Adherent adherent = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select * from adherent where id=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				adherent = new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(adherent);
	}

	@Override
	public List<Adherent> findByPrenom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

}
