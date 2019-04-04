package jdbc.mapper;

import java.sql.ResultSet;

/**
 * 封装Table字段及其值
 * @author gengzhy
 *
 * @param <T> 泛型（主要用到Map<String,Object>）
 */
public interface RowMapper<T> {
	
	T mapRow(ResultSet resultSet) throws Exception;

}
