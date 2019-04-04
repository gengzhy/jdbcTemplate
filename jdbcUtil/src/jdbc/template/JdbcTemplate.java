package jdbc.template;

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

	public static JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate();
		}
		return jdbcTemplate;
	}

	@Override
	public List<Map<String, Object>> queryForList(String sql, Object... args) throws Exception {
		return queryCore(sql, new ColumnMapRowMapper(), args);
	}

	@Override
	public List<Map<String, Object>> queryForList(String sql) throws Exception {
		return queryCore(sql, new ColumnMapRowMapper());
	}

	@Override
	public <T> int insert(String sql, Object... args) {
		return insertPreparedStatement(sql, args);
	}
}
