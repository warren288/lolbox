package com.warren.lolbox;

import java.util.HashMap;
import java.util.Map;

/**
 * Http请求的所需的请求头
 * @author yangsheng
 * @date 2015年3月12日
 */
public class SystemConfig {

	private static SystemConfig config;

	private Map<String, String> mMapCommon;
	private String mStrDwGuid;
	private String mStrDwUa;
	
	private static final String DEFAULT_DWGUID = "0A1520BAA4D48A54755261EA62EA7212";
	private static final String DEFAULT_DWUA = "lolbox&2.0.9d-209&adr&xiaomi";
	
	public static SystemConfig getIntance() {
		if (config == null) {
			synchronized (SystemConfig.class) {
				if (config == null) {
					config = new SystemConfig();
				}
			}
		}
		return config;
	}

	private SystemConfig() {
		mStrDwGuid = DEFAULT_DWGUID;
		mStrDwUa = DEFAULT_DWUA;
		mMapCommon = new HashMap<String, String>();
		mMapCommon.put("Dw-Guid", mStrDwGuid);
		mMapCommon.put("Dw-Ua", mStrDwUa);
	}

	/**
	 * 部分网络请求所需的请求头
	 * @return
	 */
	public Map<String, String> getCommHead() {

		return mMapCommon;
	}
}
