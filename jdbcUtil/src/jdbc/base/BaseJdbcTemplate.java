package jdbc.base;

import java.util.List;

/**JdbcTemplate模板基础类
 * @author gengzhy
 *
 */
public interface BaseJdbcTemplate {
	
	
	/**无参查询
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	<T> List<T> queryForList(String sql) throws Exception;
	
	/**有参查询
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	<T> List<T> queryForList(String sql,Object...args) throws Exception;
	
	/**插入或更新数据
	 * @param sql
	 * @param args
	 * @return
	 */
	<T> int insert(String sql, Object...args);

}
