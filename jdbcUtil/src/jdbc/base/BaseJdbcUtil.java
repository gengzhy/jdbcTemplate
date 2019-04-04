package jdbc.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import jdbc.mapper.ColumnMapRowMapper;

public class BaseJdbcUtil {
	
	private static Connection conn = null;
	
	private static Connection getConnection() {
		if(conn == null) {
			ResourceBundle resource = ResourceBundle.getBundle("jdbc");
			String driver = resource.getString("driver");
			String url = resource.getString("url");
			String username = resource.getString("username");
			String password = resource.getString("password");
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	protected static void close() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected static ResultSet getResultSet(String sql, Object...args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement(sql);
			if(args.length>0) {
				for (int i = 0; i < args.length; i++) {
					Object o = args[i];
					if(o instanceof String) {
						ps.setString(i+1, (String) o);
					}else if(o instanceof Integer) {
						ps.setInt(i+1, (Integer) o);
					}else if(o instanceof Double) {
						ps.setDouble(i+1, (Double) o);
					}else if(o instanceof Date) {
						ps.setDate(i+1, (java.sql.Date)o);
					}
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 核心查询结果类
	 * 
	 * @param sql
	 * @param rowMapper
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected List<Map<String, Object>> queryCore(String sql, ColumnMapRowMapper rowMapper, Object... args) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet rs = getResultSet(sql, args);
		try {
			while (rs.next()) {
				list.add(rowMapper.mapRow(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	protected static int insertPreparedStatement(String sql, Object...args) {
		PreparedStatement ps = null;
		int rs = -1;
		try {
			ps = getConnection().prepareStatement(sql);
			if(args.length>0) {
				for (int i = 0; i < args.length; i++) {
					Object o = args[i];
					if(o instanceof String) {
						ps.setString(i+1, (String) o);
					}else if(o instanceof Integer) {
						ps.setInt(i+1, (Integer) o);
					}else if(o instanceof Double) {
						ps.setDouble(i+1, (Double) o);
					}else if(o instanceof Date) {
						ps.setDate(i+1, (java.sql.Date)o);
					}
				}
				rs = ps.executeUpdate();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return rs;
	}

}
