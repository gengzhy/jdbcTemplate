package jdbc.map;

import java.util.HashMap;

/**自定义Map
 * 将 key 全部转化为大写
 * @author gengzhy
 *
 */
public class MapSet<K,V> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Object get(Object key) {
		key = String.valueOf(key).toUpperCase();
		return super.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		key = String.valueOf(key).toUpperCase();
		return super.put(key, value);
	}
	
	
}
