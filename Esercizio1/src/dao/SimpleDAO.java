package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.DBConnect;
import bean.*;

public class SimpleDAO {

	public List<Item> getObjectList() {
		final String sql = "SELECT * FROM oggetti ORDER BY peso DESC LIMIT 80";
		List<Item> items = new ArrayList<Item>();
	
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Item o = new Item(rs.getInt("ID"), rs.getInt("peso"));
				items.add(o);
			}

			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return items;
	}
}
