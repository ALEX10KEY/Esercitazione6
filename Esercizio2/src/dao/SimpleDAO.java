package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import db.DBConnect;

public class SimpleDAO {

	
	public List<String> getWordsFromDatabase() {
		
		String sql  = "select id,nome from parola order by id,nome " ;
		List<String> words = new ArrayList<String>();
		
		try {
			
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(sql) ;
			while (rs.next()) {
				words.add(rs.getString("nome"));
			}

			st.close();
			conn.close();
			return words;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo DAO per verificare se sono presenti nel dizionario parole che iniziano con il prefisso stabilito
	 * @param prefisso delle parole da cercare
	 * @return true se sono presenti parole che iniziano con il prefisso, false altrimenti
	 */
	public boolean trovaParoleCheInizianoConPrefisso(String prefisso) {

		String sql  = "select count(*) as numeroParole from parola where nome like '" + prefisso + "%' " ;
		boolean stato = false;
		
		try {
			
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(sql) ;
			if (rs.next()) {
			
				if ( rs.getLong("numeroParole")==0 )
					stato = false;
				else
					stato = true;
			}

			st.close();
			conn.close();
			return stato;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo DAO per verificare la presenza di una parola all'interno del vocabolario
	 * @param parola
	 * @return true se la parola è presente, false altrimenti
	 */
	public boolean contieneParola(String parola) {
		
		String sql  = "select nome from parola where nome = ?" ;
		boolean esito = false;
		
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, parola);
			
			ResultSet rs = st.executeQuery(sql) ;
			if (rs.next()) {
				esito = true ;
			}

			st.close();
			conn.close();
			return esito;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
