package jdbc;

import java.util.List;
import java.util.Map;

import jdbc.util.GeneralOperatUtils;

/**测试类
 * @author gengzhy
 *
 */
public class JdbcTemplateTest extends GeneralOperatUtils {
	
	public static void main(String[] args) throws Exception {// 测试

		List<Map<String, Object>> list = getJdbcTemplate()
				.queryForList("select * from sm_user sm");
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			System.out.println(map.get("user_name")+"\t"+map.get("user_code")+"\t"+map.get("user_password"));
		}
	}

}
