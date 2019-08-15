package com.utils.json;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.utils.ClassUtil;
import com.utils.date.DateUtil;
import com.utils.string.StrUtil;

public class JsonUtil {

	private static ObjectMapper initObjectMapper() {
		return JsonUtil.initObjectMapper(true);
	}

	/**
	 * 
	 * @param isFormatTimestamps
	 *            true 设置timestamps转字符串的格式<br>
	 *            false 不设置timestamps转字符串的格式
	 * @return
	 */
	private static ObjectMapper initObjectMapper(boolean isFormatTimestamps) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);// 设置允许使用不被双引号或单引号包裹在key
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, false);// 不包装异常，把jackson的异常抛出来
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);// 设置json字符串中有key，对象中无key时不报错
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);// 设置json字符串中value为空字符串时转换为对象的null
		if (isFormatTimestamps) {
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mapper.setDateFormat(d);
		}

		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
				arg1.writeString("");
			}
		});
		return mapper;
	}

	/**
	 * 
	 * @param map
	 *            存json对象的map
	 * @param key
	 *            request中的key
	 * @param value
	 *            request中的值
	 * @return
	 */
	public static void initJsonObject(Map<String, Object> map, String key, String value) {
		if (map == null)
			return;
		key = key.replaceAll("]", "").replace("[", "#");
		String[] keyArray = key.split("#");
		if (keyArray.length < 2)
			return;
		// date结尾，转换为日期类型
		if (keyArray[1].toLowerCase().endsWith("date") && !StrUtil.isNull(value) && DateUtil.isValidDate(value))
			map.put(keyArray[1], DateUtil.string2Date(value));
		else
			map.put(keyArray[1], value);
	}

	/**
	 * json数组转list
	 * 
	 * @param jsonArr
	 * @return
	 * @throws Exception
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static List<Map<String, Object>> json2List(String jsonObj) {
		ObjectMapper mapper = JsonUtil.initObjectMapper();
		try {
			return mapper.readValue(jsonObj, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Map.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static <T> List<T> json2List(Object obj, Class<T> requiredType) {
		if (!(obj instanceof List))
			throw new RuntimeException("入参必须为List类型");
		try {
			String jsonObj = JsonUtil.formatObject(obj);
			if (StrUtil.isNull(jsonObj))
				return new ArrayList<T>();
			ObjectMapper mapper = JsonUtil.initObjectMapper();
			return mapper.readValue(jsonObj, mapper.getTypeFactory().constructCollectionType(ArrayList.class, requiredType));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * json对象转map
	 * 
	 * @param jsonObj
	 * @return
	 * @throws Exception
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static Map<String, Object> json2Map(String jsonObj) {
		if (StrUtil.isNull(jsonObj))
			return new HashMap<String, Object>();
		try {
			ObjectMapper mapper = JsonUtil.initObjectMapper();
			return mapper.readValue(jsonObj, mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static <T> T json2Object(String jsonObj, Class<T> requiredType) {
		return JsonUtil.json2Object(jsonObj, requiredType, true);
	}

	/**
	 * 是否格式化Timestamps时间格式
	 * 
	 * @param jsonObj
	 * @param requiredType
	 * @param isFormatDate
	 * @return
	 */
	public static <T> T json2Object(String jsonObj, Class<T> requiredType, boolean isFormatTimestamps) {
		try {
			ObjectMapper mapper = JsonUtil.initObjectMapper(isFormatTimestamps);
			return mapper.readValue(jsonObj, requiredType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * list转json数组
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String formatJSONArray(List<?> list) {
		try {
			ObjectMapper mapper = JsonUtil.initObjectMapper();
			return mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * map转json对象
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String formatJSONObject(Map<?, ?> map) {
		try {
			ObjectMapper mapper = JsonUtil.initObjectMapper();
			return mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Object转json对象
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String formatObject(Object object) {
		if (object == null)
			return "";
		try {
			ObjectMapper mapper = JsonUtil.initObjectMapper();
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		String a="{\"Q1\":[\"Y\"],\"Q10\":[\"N\"],\"Q11\":[\"N\"],\"Q12\":[\"N\"]}";
		Map<String,Object> map=JsonUtil.json2Map(a);
		System.out.println(map.get("name"));
		
	}

	public static Map<String, Object> toLowerCaseMap(Map<String, Object> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			param.put(entry.getKey().toLowerCase(), entry.getValue());
		}
		return param;
	}

	/**
	 * 将map转换成对应的对象
	 * 
	 * @param map
	 *            需要转换的map
	 * @param cla
	 *            需要转换的对象的类型
	 * @return 转换后的类的对象
	 */
	public static <T> T mapToObject(Map<String, Object> map, Class<T> cla) {
		try {
			T t = cla.newInstance();
			List<Field> fields = ClassUtil.getClassFields(cla);
			for (Field field : fields) {
				Object obj = map.get(field.getName());
				// 当map中没有这个值的时候，不进行操作
				if (obj == null) {
					continue;
				}
				@SuppressWarnings("rawtypes")
				Class classType = field.getType();
				field.setAccessible(true);
				// 当是基本类型的时候，进行赋值的操作
				if (classType == List.class) {// 当是集合的时候判断泛型否是基本类型，当不是基本类型的时候进行转换
					Class<?> genericityClass = ClassUtil.getFieldGenericity(field);
					if (genericityClass == null || ClassUtil.isBaseType(classType)) {
						field.set(t, obj);
					} else {
						field.set(t, mapToListObj((List<Map<String, Object>>) obj, genericityClass));
					}
				} else if (ClassUtil.isBaseType(classType)) {
					field.set(t, obj);
				} else {
					if ((obj instanceof Map) || (obj instanceof HashMap)) {
						// 不是基本类型的时候，进行递归调用
						Object returnObj = mapToObject((Map) obj, classType);
						field.set(t, returnObj);
					} else {
						field.set(t, obj);
					}
				}
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将List<Map<String,Object>> 转换成对应的类的集合
	 * 
	 * @param list
	 *            需要转换的集合
	 * @param cla
	 *            对象的类型
	 * @return 转换之后的集合
	 */
	public static <T> List<T> mapToListObj(List<Map<String, Object>> list, Class<T> cla) {
		try {
			List<T> returnList = new ArrayList<T>();
			for (Map<String, Object> map : list) {
				T t = mapToObject(map, cla);
				returnList.add(t);
			}
			return returnList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将对象转换成map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> objToMap(Object obj) {
		String json = "";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ObjectMapper mapper = JsonUtil.initObjectMapper();
			json = mapper.writeValueAsString(obj);
			map = json2Map(json);
			return map;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
