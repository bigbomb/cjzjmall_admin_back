package com.chaojizuojia.carmall.util;

import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import com.chaojizuojia.carmall.util.constants.Constants;



/**
 * <P>密码工具类</P>
 * 
 * @author  2013-4-21 下午12:28:39
 */
public class PasswordUtils {


	/**
	 * 特殊指定-数字密码集合字节表 去除0 O i 1 l I ? /等特殊不易辨别的的密码
	 */
	private final static char[] numberChars = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * 特殊指定-小写字符集合字节表 去除0 O i 1 l I ? /等特殊不易辨别的的密码
	 */
	private final static char[] letterChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z' };
	/**
	 * 特殊指定-大写字符集合字节表 去除0 O i 1 l I ? /等特殊不易辨别的的密码
	 */
	private final static char[] letterCapChars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };
	/**
	 * 特殊指定-特殊字符集合字节表 去除0 O i 1 l I ? /等特殊不易辨别的的密码
	 */
	private final static char[] specialChars = { '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '<', '>', '?', '/' };
	/**
	 * 基本密码字节数组 去除0 O i 1 l I ? /等特殊不易辨别的的密码
	 */
	private final static char[] baseChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * <p>生成指定随机密码</p>
	 * 
	 * @param pwdLength 密码长度
	 * @param pwdType 指定生成密码类型 @See {@link PwdGeneratorType}
	 * @return 指定密码长度的随机密码
	 * @author  2013-4-21 下午12:54:47
	 */
	public static String generateRandomPwd(final int pwdLength, final int pwdType) {
		final Random r = new Random();
		final StringBuilder pwdSb = new StringBuilder("");
		int count = 0; // 计数器
		int maxNum = 0;// 数组中最大个数
		switch (pwdType) {
			case PwdGeneratorType.NUMBER:
				// 纯数字采样
				maxNum = numberChars.length;// 集合中密码字符集总数
				while (count < pwdLength) {
					// 生成随机数，取绝对值，防止nextInt生成负数;并且从0开始取值
					pwdSb.append(numberChars[Math.abs(r.nextInt(maxNum - 1))]);
					count++;
				}
				break;
			case PwdGeneratorType.NUMBER_LETTER:
				// 采样2种： 数字+字母小写 【必中】
				pwdSb.append(numberChars[Math.abs(r.nextInt(numberChars.length - 1))]);
				pwdSb.append(letterChars[Math.abs(r.nextInt(letterChars.length - 1))]);
				count = 2; // 已经包含一个数字和一个字母
				loopPwdArrayRandom(pwdLength, r, pwdSb, count);
				break;
			case PwdGeneratorType.NUMBER_LETTER_CAP:
				// 采样3种: 数字+字母大写+字母小写 【必中】
				pwdSb.append(numberChars[Math.abs(r.nextInt(numberChars.length - 1))]);
				pwdSb.append(letterChars[Math.abs(r.nextInt(letterChars.length - 1))]);
				pwdSb.append(letterCapChars[Math.abs(r.nextInt(letterCapChars.length - 1))]);
				count = 3; // 已经包含一个数字和一个字母和一个大写字母
				loopPwdArrayRandom(pwdLength, r, pwdSb, count);
				break;
			case PwdGeneratorType.NUMBER_LETTER_CAP_SPECIAL_CHAR:
				// 采样3种:数字+字母大写+字母小写+特殊字符 【必中】
				pwdSb.append(numberChars[Math.abs(r.nextInt(numberChars.length - 1))]);
				pwdSb.append(letterChars[Math.abs(r.nextInt(letterChars.length - 1))]);
				pwdSb.append(letterCapChars[Math.abs(r.nextInt(letterCapChars.length - 1))]);
				pwdSb.append(specialChars[Math.abs(r.nextInt(specialChars.length - 1))]);
				count = 4; // 已经包含一个数字和一个字母和一个大写字母
				loopPwdArrayRandom(pwdLength, r, pwdSb, count);
				break;
			default:
				// 采样2种： 数字+字母小写 【必中】
				pwdSb.append(numberChars[Math.abs(r.nextInt(numberChars.length - 1))]);
				pwdSb.append(letterChars[Math.abs(r.nextInt(letterChars.length - 1))]);
				count = 2; // 已经包含一个数字和一个字母
				loopPwdArrayRandom(pwdLength, r, pwdSb, count);
				break;
		}
		return pwdSb.toString();
	}

	/**
	 * <p>循环密码数组随机获取</p>
	 * 
	 * @author  2013-4-21 下午12:49:05
	 */
	private static void loopPwdArrayRandom(final int pwdLength, final Random r, final StringBuilder pwdSb, int count) {
		final int maxNum = baseChars.length;
		while (count < pwdLength) {
			// 生成随机数，取绝对值，防止生成负数;并且从0开始取值
			pwdSb.append(baseChars[Math.abs(r.nextInt(maxNum - 1))]);
			count++;
		}
	}

	/**
	 * <p>加密密码</p>
	 * @param password
	 * @return
	 * @throws PortalAppCheckedException
	 * @author Jonathan
	 */
	public static String encryptPassword(String password) {
		SHAPasswordEncoder passwordEncoder = new SHAPasswordEncoder();
		String shaPassword = passwordEncoder.encodePassword(password, null);
		byte[] key = Base64.decodeBase64(Constants.USER_PWD_SECRET_KEY.getBytes());
		byte[] enPassword = null;

		try {
			enPassword = EncryptionUtils.encryptByAES(shaPassword.getBytes(), key, Constants.USER_PWD_SECRET_IV.getBytes());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return Base64.encodeBase64String(enPassword);
	}

	public static String decryptPassword(String password) throws RuntimeException  {
		byte[] key = Base64.decodeBase64(Constants.USER_PWD_SECRET_KEY.getBytes());
		return StringUtils.newStringUtf8(EncryptionUtils.decryptByAES(Base64.decodeBase64(password), key,  Constants.USER_PWD_SECRET_IV.getBytes()));
	}

	/**
	 * <P>密码生成类型</P>
	 * 
	 * @author  2013-4-21 下午1:01:14
	 */
	public interface PwdGeneratorType {
		/**
		 * 纯数字
		 */
		int NUMBER = 1;
		/**
		 * 数字+字母(小写)
		 */
		int NUMBER_LETTER = 2;
		/**
		 * 数字+字母(小写)+字字大写字母
		 */
		int NUMBER_LETTER_CAP = 3;
		/**
		 * 数字+字母(小写)+大写字母1位+特殊字符1位
		 */
		int NUMBER_LETTER_CAP_SPECIAL_CHAR = 4;

	}
}
