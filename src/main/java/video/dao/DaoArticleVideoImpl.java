package video.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import video.context.Context;
import video.model.Adherent;
import video.model.Article;
import video.model.Bluray;
import video.model.Dvd;
import video.model.Film;

class DaoArticleVideoImpl implements DaoArticle {


	public void insert(Article obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into article(id_article, nb_disques, type, bonus, trois_d, id_film, emprunteur) values (nextval('seq_article'),?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {			
			if (obj.getNbDisques() != null) {
				ps.setInt(1, obj.getNbDisques());
			} else {
				ps.setNull(1, Types.INTEGER);
			}
			if (obj instanceof Dvd) {
				ps.setString(2, "d");
				ps.setBoolean(3, true);
				ps.setBoolean(4, false);
			} else if (obj instanceof Bluray) {
				ps.setString(2, "b");
				ps.setBoolean(3, false);
				ps.setBoolean(4, true);
			}
			if (obj.getFilm() != null) {
				ps.setInt(5, obj.getFilm().getId());
			} else {
				ps.setNull(5, Types.INTEGER);
			}
			if (obj.getEmprunteur() != null) {
				ps.setInt(6, obj.getFilm().getId());
			} else {
				ps.setNull(6, Types.INTEGER);
			}
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt(1));
			}
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

	public Article update(Article obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update article set nb_disques=?, type=?, bonus=?, trois_d=?, id_film=?, emprunteur=? where id_article=?")) {
			ps.setInt(1, obj.getNbDisques());
			if (obj instanceof Dvd) {
				ps.setString(2, "d");
				ps.setBoolean(3, true);
				ps.setBoolean(4, false);
			} else if (obj instanceof Bluray) {
				ps.setString(2, "b");
				ps.setBoolean(3, false);
				ps.setBoolean(4, true);
			}
			if (obj.getFilm() != null) {
				ps.setInt(5, obj.getFilm().getId());
			} else {
				ps.setNull(5, Types.INTEGER);
			}
			if (obj.getEmprunteur() != null) {
				ps.setInt(6, obj.getEmprunteur().getId());
			} else {
				ps.setNull(6, Types.INTEGER);
			}
			ps.setInt(7, obj.getId());
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

	public void delete(Article obj) {
		deleteById(obj.getId());	
	}

	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"delete from article where id_article=?")) {
			ps.setInt(1, key);
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		} catch(SQLException e) {
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
	public List<Article> findAll() {
		List<Article> articles = new ArrayList<Article>();
		Article a = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery(
					"select a.id_article, a.nb_disques, a.emprunteur, a.id_film, a.type, id, prenom, nom, b.id_film, b.titre, b.date_sortie from adherent right join article a on id=a.emprunteur left join film b on a.id_film=b.id_film");
			while (rs.next()) {
				if (rs.getString("type").equals("d")) {
					a = new Dvd(rs.getInt("id_article"), rs.getInt("nb_disques"));
				} else if (rs.getString("type").equals("b")) {
					a = new Bluray(rs.getInt("id_article"), rs.getInt("nb_disques"));
				}
				if (rs.getInt("id") != 0) {
					a.setEmprunteur(new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom")));
				}
				if (rs.getInt("id_film") != 0) {
					a.setFilm(new Film(rs.getInt("id_film"), rs.getString("titre"), rs.getDate("date_sortie")));
				}
				articles.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return articles;
	}

	public Optional<Article> findByKey(Integer key) {
		Article a = null;
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"select a.id_article, a.nb_disques, a.emprunteur, a.id_film, a.type, id, prenom, nom, b.id_film, b.titre, b.date_sortie from adherent right join article a on id=a.emprunteur left join film b on a.id_film=b.id_film where a.id_article=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("type").equals("d")) {
					a = new Dvd(rs.getInt("id_article"), rs.getInt("nb_disques"));
				} else if (rs.getString("type").equals("b")) {
					a = new Bluray(rs.getInt("id_article"), rs.getInt("nb_disques"));
				}
				if (rs.getInt("id") != 0) {
					a.setEmprunteur(new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom")));
				}
				if (rs.getInt("id_film") != 0) {
					a.setFilm(new Film(rs.getInt("id_film"), rs.getString("titre"), rs.getDate("date_sortie")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(a);
	}
	
	public Optional<Article> findByAdherent(String prenom) {
		Article a = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select a.id_article, a.nb_disques, a.emprunteur, a.id_film, a.type, id, prenom, nom, b.id_film, b.titre, b.date_sortie from adherent right join article a on id=a.emprunteur left join film b on a.id_film=b.id_film where prenom=?")) {
			ps.setString(1, prenom);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("type").equals("d")) {
					a = new Dvd(rs.getInt("id_article"), rs.getInt("nb_disques"));
				} else if (rs.getString("type").equals("b")) {
					a = new Bluray(rs.getInt("id_article"), rs.getInt("nb_disques"));
				}
				a.setEmprunteur(new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom")));
				if (rs.getInt("id_film") != 0) {
					a.setFilm(new Film(rs.getInt("id_film"), rs.getString("titre"), rs.getDate("date_sortie")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(a);
	}

}
