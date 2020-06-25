package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.model.Arco;
import it.polito.tdp.crimes.model.Event;
import it.polito.tdp.crimes.model.Tempo;

public class EventsDao {

	public List<Event> listAllEvents() {
		String sql = "SELECT * FROM events";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			List<Event> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"), res.getInt("offense_code"),
							res.getInt("offense_code_extension"), res.getString("offense_type_id"),
							res.getString("offense_category_id"), res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"), res.getDouble("geo_lon"), res.getDouble("geo_lat"),
							res.getInt("district_id"), res.getInt("precinct_id"), res.getString("neighborhood_id"),
							res.getInt("is_crime"), res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<String> listAllCategories() {
		String sql = "SELECT DISTINCT offense_category_id FROM events";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			List<String> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(res.getString("offense_category_id"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Tempo> listAllDates() {
		String sql = "SELECT DISTINCT DATE_FORMAT(reported_date, '%Y %c %d') AS DATA\r\n" + 
				"FROM events";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			List<Tempo> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(new Tempo(res.getTimestamp("DATA").toLocalDateTime()));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> listAllOffenseType(String category, Tempo data) {
		String sql = "SELECT DISTINCT offense_type_id\r\n" + 
				"FROM EVENTS\r\n" + 
				"WHERE offense_category_id=? and DATE(reported_date)=?";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, category );
			st.setString(2, data.searchDB() );
			List<String> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(res.getString("offense_type_id"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Arco> listArchi(String category, Tempo data) {
		String sql = "SELECT e1.offense_type_id AS id1,e2.offense_type_id AS id2, COUNT( DISTINCT e1.precinct_id) AS peso\r\n" + 
				"	FROM `events` AS e1,`events` AS e2\r\n" + 
				"	WHERE e1.precinct_id=e2.precinct_id \r\n" + 
				"		AND DATE(e1.reported_date)=?\r\n" + 
				"		AND DATE(e2.reported_date)=?\r\n" + 
				"		AND e1.offense_category_id=?\r\n" + 
				"		AND e2.offense_category_id=?\r\n" + 
				"		AND e1.offense_type_id>e2.offense_type_id\r\n" + 
				"	GROUP BY e1.offense_type_id,e2.offense_type_id";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, data.searchDB() );
			st.setString(2, data.searchDB() );
			st.setString(3, category );
			st.setString(4, category );
			List<Arco> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(new Arco(res.getString("id1"),res.getString("id2"),res.getInt("peso")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
