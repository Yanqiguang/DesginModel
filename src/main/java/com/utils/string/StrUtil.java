package com.utils.string;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.utils.date.DateUtil;


public class StrUtil {
	// 半角符号
	private static final String hSymbol = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,./:;<=>?@[\\]^_`{|}-~ ";
	// 全角符号
	private static final String zSymbol = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＥＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ０１２３４５６７８９！”＃＄％＆’（）＊＋，．／：；＜＝＞？＠［￥］＾＿‘｛｜｝－～　";

	/**
	 * 单引号取拼接拼接字段串 <br>
	 * 注意：在IN中使用时要自己在字段串两端各加入一个单引号!
	 * 
	 * @param deptList
	 *            被拼接字符源
	 * @param key
	 *            被拼接字段名
	 * @return 拼接成110001','210001','310001','410001形式的字符串
	 * 
	 */
	public static String joinString(List<Map<String, Object>> list, String key) {
		String deptListString = "";
		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> oneDept : list) {
				deptListString += "'" + (String) oneDept.get(key) + "',";
			}
			if (deptListString != null && !deptListString.equals("")) {
				deptListString = deptListString.substring(1, deptListString.length() - 2);
			}

		}
		return deptListString;
	}

	/**
	 * 单引号取拼接拼接字段串 <br>
	 * 注意：在IN中使用时要自己在字段串两端各加入一个单引号!
	 * 
	 * @param deptList
	 *            被拼接字符源
	 * 
	 * @return 拼接成110001','210001','310001','410001形式的字符串
	 * 
	 */
	public static String joinString(List<String> list) {
		String deptListString = "";
		if (list != null && !list.isEmpty()) {
			for (String s : list) {
				deptListString += "'" + s + "',";
			}
			if (deptListString != null && !deptListString.equals("")) {
				deptListString = deptListString.substring(1, deptListString.length() - 2);
			}
		}
		return deptListString;
	}

	/**
	 * 单引号取拼接拼接字段串 <br>
	 * 注意：在IN中使用时要自己在字段串两端各加入一个单引号!
	 * 
	 * @param deptList
	 *            被拼接字符源
	 * 
	 * @return 拼接成110001','210001','310001','410001形式的字符串
	 * 
	 */
	public static String joinString(String[] list) {
		String deptListString = "";
		if (list != null && list.length != 0) {
			for (String s : list) {
				deptListString += "'" + s + "',";
			}
			if (deptListString != null && !deptListString.equals("")) {
				deptListString = deptListString.substring(1, deptListString.length() - 2);
			}
		}
		return deptListString;
	}

	/**
	 * 左对齐
	 * 
	 * @param string
	 *            要对齐的字符串
	 * @param maxLength
	 *            对齐的长度
	 * @param appendString
	 *            对齐空位的字符串
	 * @return 对齐后的字符串 @
	 */
	public static String alignLeft(Object string, int maxLength, String appendString) {
		int len = StrUtil.getBytesLength(StrUtil.trimStr(string));
		if (len <= maxLength) {
			String appended = "";
			int appendCount = maxLength - len;
			for (int i = 0; i < appendCount; i++)
				appended = appended + appendString;
			return appended + string;
		} else {
			// 字符串长度超出最大长度
			throw new RuntimeException("字符串[" + string + "]长度超出最大长度[" + maxLength + "]");
		}
	}

	/**
	 * 使用0进行左对齐
	 * 
	 * @param string
	 *            要对齐的字符串
	 * @param maxLength
	 *            对齐的长度
	 * @return 对齐后的字符串 @
	 */
	public static String alignLeft(Object string, int maxLength) {
		return alignLeft(string, maxLength, "0");
	}

	/**
	 * 右对齐
	 * 
	 * @param string
	 *            要对齐的字符串
	 * @param maxLength
	 *            对齐的长度
	 * @param appendString
	 *            对齐空位的字符串
	 * @return 对齐后的字符串 @
	 */
	public static String alignRight(Object string, int maxLength, String appendString) {
		int len = StrUtil.getBytesLength(StrUtil.trimStr(string));
		StringBuffer buffer = new StringBuffer();
		buffer.append(string);
		if (len <= maxLength) {
			int appendCount = maxLength - len;
			for (int i = 0; i < appendCount; i++)
				buffer.append(appendString);
		} else {
			// 字符串长度超出最大长度
			throw new RuntimeException("字符串[" + string + "]长度超出最大长度[" + maxLength + "]");
		}
		if (buffer.substring(buffer.length() - 1).getBytes().length >= 2)
			throw new RuntimeException("字符串[" + string + "]不能以汉字结尾。");
		return buffer.toString();
	}

	/**
	 * 使用空格进行右对齐
	 * 
	 * @param string
	 *            要对齐的字符串
	 * @param maxLength
	 *            对齐的长度
	 * @return 对齐后的字符串 @
	 */
	public static String alignRight(Object string, int maxLength) {
		return alignRight(string, maxLength, "0");
	}

	/**
	 * 小数点补齐
	 * 
	 * @param num
	 *            要补齐的数字
	 * @param befDecimal
	 *            小数点前补齐位数
	 * @param aftDecimal
	 *            小数点后补齐位数
	 * @return 补齐后的小数位 @
	 */
	public static String alignDecimal(Object num, int befDecimal, int aftDecimal) {
		String pointBef = "";
		String pointAft = "";
		// 是否为数字
		Double.parseDouble(StrUtil.trimStr(num));
		String[] decimal = StrUtil.trimStr(num).split("\\.");
		pointBef = alignLeft(decimal[0], befDecimal);
		if (decimal.length >= 2) {
			pointAft = alignRight(decimal[1], aftDecimal, "0");
		} else {
			pointAft = alignRight("", aftDecimal, "0");
		}
		return pointBef + "." + pointAft;
	}

	/**
	 * 获取字符串字节数
	 * <p>
	 * 基于汉字串计算出字节数，每个汉字串会多加2个字节数。
	 * 
	 * @param str
	 *            字符串
	 * @return 字符串的字节数
	 */
	public static int getBytesLength(String str) {
		// 汉字串数目
		if (str == null || "".equals(str))
			return 0;
		int hanZiChuan = 0;
		int hanZiFlag = 0;
		char arr[] = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (String.valueOf(arr[i]).getBytes().length >= 2) {
				if (hanZiFlag == 0)
					hanZiChuan++;
				hanZiFlag = 1;
			} else {
				if (hanZiFlag == 1)
					hanZiFlag = 0;
			}
		}
		return str.getBytes().length + hanZiChuan * 2;
	}

	/**
	 * 将字符串数组，转换为字符串List
	 * 
	 * @param array
	 *            字符串数组
	 * @return 字符串List
	 */
	public static List<String> array2List(String[] array) {
		List<String> list = new ArrayList<String>();
		for (String s : array) {
			if (s == null) {
				continue;
			}
			list.add(s.trim());
		}
		return list;
	}

	// 将字符串中的半角字符转换成全角字符
	public static String toSBC(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		String tmp = str;
		for (int i = 0; i < hSymbol.length(); i++) {
			tmp = tmp.replace(hSymbol.charAt(i), zSymbol.charAt(i));
		}
		StringBuffer sb = new StringBuffer(tmp);
		return sb.toString();
	}

	// 将字符串中的全角字符转换成半角字符
	public static String toDBC(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		String tmp = str;
		for (int i = 0; i < zSymbol.length(); i++) {
			tmp = tmp.replace(zSymbol.charAt(i), hSymbol.charAt(i));
		}
		StringBuffer sb = new StringBuffer(tmp);
		return sb.toString();
	}

	// 判断是否为空 true:为空 false:不为空
	public static boolean isNull(Object content) {
		if (content != null && !"".equals(StrUtil.trimStr(content)) && !"null".equals(StrUtil.trimStr(content))) {
			return false;
		}
		return true;
	}

	/**
	 * 将字符串去空格，并且做null的判断，如果为null则返回""
	 * 
	 * @param str
	 * @return
	 */
	public static String trimStr(Object str) {
		if (str == null || "".equals(str) || "NULL".equals(str.toString().toUpperCase())) {
			return "";
		}
		if (str instanceof String) {
			return str.toString().trim();
		}
		if (str instanceof Timestamp) {
			return DateUtil.date2String((Date) str, "yyyy-MM-dd HH:mm:ss");
		}
		if (str instanceof Date) {
			return DateUtil.date2String((Date) str);
		}
		return str.toString().trim();
	}

	/**
	 * 首字母小写
	 * 
	 * @return
	 */
	public static String lowFstChar(Object obj) {
		String arg = obj.toString();
		if (arg == null)
			return arg;
		if ("".equals(arg))
			return "";
		if (arg.length() == 1)
			return arg.toLowerCase();
		return arg.substring(0, 1).toLowerCase() + arg.substring(1, arg.length());
	}

	/**
	 * 首字母大写
	 * 
	 * @return
	 */
	public static String uppFstChar(Object obj) {
		String arg = obj.toString();
		if (arg == null)
			return arg;
		if ("".equals(arg))
			return "";
		if (arg.length() == 1)
			return arg.toUpperCase();
		return arg.substring(0, 1).toUpperCase() + arg.substring(1, arg.length());
	}

	public static boolean isNum(Object o) {
		if (StrUtil.isNull(o)) {
			return false;
		}
		String s = o.toString();
		if (s.length() > 1 && s.toLowerCase().charAt(s.length() - 1) == 'e')
			try {
				s = StrUtil.doubleTo(o);
			} catch (Exception e) {
				return false;
			}
		String regex = "^[1-9][0-9]*\\.[0-9]+$|^[1-9][0-9]*$|^0+\\.[0-9]+$|^[0-9]{1}$";
		Pattern pattern = Pattern.compile(regex);
		char c = s.charAt(0);
		if (c == '+' || c == '-') {
			s = s.substring(1);
		}
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	public static String doubleTo(Object value) {
		DecimalFormat decimalFormat = new DecimalFormat("###0.000000");// 格式化设置
		return decimalFormat.format(value);
	}
}
