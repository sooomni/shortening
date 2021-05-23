package com.musinsa.shortening;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.musinsa.shortening.Utils.Base62Util;
import com.musinsa.shortening.dto.URLRequestDTO;
import com.musinsa.shortening.repo.URLShorteningRepository;
import com.musinsa.shortening.service.URLShorteningService;

@SpringBootTest
class ShorteningServiceTests {
	
	@Autowired
	private URLShorteningService urlShorteningService;
	
	@Autowired
	private URLShorteningRepository urlShorteningRepository;
	
	@Autowired
    private EntityManager em;
	
	private final String originURL = "https://en.wikipedia.org/wiki/URL_shortening";
	
	@Test
	@Order(1)
	@DisplayName("1> short Key 생성 테스트")
    public void shortening(){
		if(!urlShorteningRepository.findById(100003).isPresent())
			assertEquals("http://localhost:8080/7Aa",urlShorteningService.shortening(new URLRequestDTO(originURL)));
    }
	
	@Test
	@Order(2)
	@DisplayName("2> base62 encoding")
    public void base62test(){
		assertEquals("http://localhost:8080/6Aa",urlShorteningService.shortenConverting(100002));
	}
	
	@Test
	@Order(3)
	@DisplayName("3> 동일 short Key 생성 테스트")
	@Transactional
    public void sameShortKey(){
		URLRequestDTO reqDTO = new URLRequestDTO(originURL);
	    
		String shortURL_first = urlShorteningService.shortening(reqDTO);
	    em.flush();
	    
	    String shortURL_compare = urlShorteningService.shortening(reqDTO);
	    em.flush();

	    assertThat(shortURL_first.equals(shortURL_compare));
	}    
}
