package jdbc.util;

import jdbc.template.JdbcTemplate;

/**jdbcTemplate操作类
 * @author gengzhy
 *
 */
public class GeneralOperatUtils {
	/**取jdbcTemplate模板
	 * @return
	 */
	public static JdbcTemplate getJdbcTemplate() {
		return JdbcTemplate.getJdbcTemplate();
	}

}
