package com.musinsa.shortening.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PathCombineUtil {

	@Value("${server.host}")
	private String host;
	@Value("${server.port}")
	private String port;

	public String combinePath(String path) {
		return "http://" + host + ":" + port + "/" + path;
	}

}
