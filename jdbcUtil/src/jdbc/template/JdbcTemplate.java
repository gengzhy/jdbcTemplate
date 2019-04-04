package jdbc.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.base.BaseJdbcTemplate;
import jdbc.base.BaseJdbcUtil;
import jdbc.mapper.ColumnMapRowMapper;

/**
 * 自定义jdbcTemplate模板
 * 
 * @author gengzhy
 *
 */
@SuppressWarnings("unchecked")
public class JdbcTemplate extends BaseJdbcUtil implements BaseJdbcTemplate {

	private static JdbcTemplate jdbcTemplate;

	public static synchronized JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate();
		}
		return jdbcTemplate;
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
	private List<Map<String, Object>> queryCore(String sql, ColumnMapRowMapper rowMapper, Object... args) throws Exception {
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

	@Override
	public List<Map<String, Object>> queryForList(String sql, Object... args) throws Exception {
		return queryCore(sql, new ColumnMapRowMapper(), args);
	}

	@Override
	public List<Map<String, Object>> queryForList(String sql) throws Exception {
		return queryCore(sql, new ColumnMapRowMapper());
	}
}
