package com.musinsa.shortening;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.musinsa.shortening.dto.URLRequestDTO;

@SpringBootTest
public class ShortenDTOTest {
	
	@Test
	@DisplayName("DTO 생성 테스트")
	public void checkOriginURL(){
		URLRequestDTO reqDTO = URLRequestDTO.builder() 
											.originURL("https://en.wikipedia.org/wiki/URL_shortening")
											.build();
	
		assertEquals("https://en.wikipedia.org/wiki/URL_shortening",reqDTO.getOriginURL());
	}
}

