package com.xunyun.infanteduplatform.constants;

public class SystemConstants {

	// shiro 缓存名称
	public static final String AUTHORIZATION_CACHE_NAME = "SHIRO_AUTHORIZATION";

	// 缓存大小 5K
	public static final Integer CACHE_SIZE = 1024 * 5;

	// Pos 默认文件上传格式 为ZIP
	public static final String FILE_SUFFIX = ".zip";

	// Pos WebService 当前版本
	public static final String CURRENT_POS_WEBSERVICE_VERSION = "1";

	// 解密&解压消息动作指令
	public static final String DECRYPT_AND_UNZIP_MESSAGE = "1";

	// 数据处理消息动作指令
	public static final String DATA_PROCESSING_MESSAGE = "2";

	// 导入数据消息动作指令
	public static final String DATA_IMPORT_MESSAGE = "3";

	public static final String ENCODING = "UTF-8";

	// 初始化密码
	public static final String PASSWORD = "123123";

	/**
	 * 密钥 别名 &密码
	 * 
	 * @author 
	 *
	 */
	public static class Store {
		public static final String SERVER_STORE_ALIAS = "tomcat";

		public static final String SERVER_STORE_PASSWORD = "123456";

		public static final String SERVER_STORE_PATH = "F:/infantEdu/keystore/tomcat.keystore";

	}

	/**
	 * 
	 * @author 
	 *
	 */
	public static class SpringProFile {
		public static final String ACTIVE = "spring.profiles.active";
		public static final String ACTIVE_TEST = "product";
		public static final String CONFIG = "classpath:config.properties";

	}

	/**
	 * 消息返回
	 * 
	 * @author 
	 *
	 */
	public static class Response {
		public static final Integer SUCCESS = 1;

		public static final Integer ERROR = 10001;
	}

	/**
	 * 是否有效标识
	 * 
	 * @author 
	 *
	 */
	public static class ActiveFlag {

		public static final Integer VALID = 0;

		public static final Integer INVALID = 1;

	}

	/**
	 * 影响事件标识（1，天气状况。2，其他事件）
	 * 
	 * @author 
	 *
	 */
	public static class ThingsFlag {

		public static final Integer WEATHTYPE = 1;

		public static final Integer THINGTYPE = 2;

	}

	/**
	 * 组织分组级别
	 * 
	 * 总部：1，分部：2，县区: 3，幼儿园
	 *
	 */
	public static class OrganizationFlag {

		public static final Integer HEAD_QUARTERS = 1;

		public static final Integer BRANCH = 2;

		public static final Integer STORE = 3;

	}

	/**
	 * 错误码
	 * 
	 * @author 
	 *
	 */
	public static class ErrorCode {

	}

	public static class OrgCode {
		// 分部
		public static final String PARTS = "parts";

		//县区 
		public static final String SMALLS = "smalls";

		// 幼儿园
		public static final String SCHOOLS = "schools";
	}
}
