package jdbc.mapper;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Map;

import jdbc.map.MapSet;

/**
 * 封装数据库表的属性及值
 * 
 * @author gengzhy
 *
 */
public class ColumnMapRowMapper implements RowMapper<Map<String, Object>> {

	@Override
	public Map<String, Object> mapRow(ResultSet rs) throws Exception {
		Map<String, Object> map = new MapSet<String, Object>();
		
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int index = 1; index <= columnCount; index++) {
			String key = metaData.getColumnLabel(index);// 属性名
			Object value = getColumnValue(rs, index);// 属性值
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 根据属性值类型封装
	 * 
	 * @param rs
	 * @param index
	 * @return
	 * @throws Exception
	 */
	private Object getColumnValue(ResultSet rs, int index) throws Exception {
		Object obj = rs.getObject(index);
		String className = null;// 属性类型
		if (obj != null) {
			className = obj.getClass().getName();
		}
		if ((obj instanceof Blob)) {
			obj = rs.getBytes(index);
		} else if ((obj instanceof Clob)) {
			obj = rs.getString(index);
		} else if ((className != null)
				&& (("oracle.sql.TIMESTAMP".equals(className)) || ("oracle.sql.TIMESTAMPTZ".equals(className)))) {
			obj = rs.getTimestamp(index);
		} else if ((className != null) && (className.startsWith("oracle.sql.DATE"))) {
			String metaDataClassName = rs.getMetaData().getColumnClassName(index);
			if (("java.sql.Timestamp".equals(metaDataClassName))
					|| ("oracle.sql.TIMESTAMP".equals(metaDataClassName))) {
				obj = rs.getTimestamp(index);
			} else {
				obj = rs.getDate(index);
			}
		} else if ((obj != null) && ((obj instanceof java.sql.Date))
				&& ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index)))) {
			obj = rs.getTimestamp(index);
		}
		return obj;
	}

}
