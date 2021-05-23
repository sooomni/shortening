package com.musinsa.shortening;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.musinsa.shortening.repo.URL;
import com.musinsa.shortening.repo.URLShorteningRepository;

@SpringBootTest
class ShorteningJPATests {
	
	@Autowired
	private URLShorteningRepository urlShorteningRepository;
	
	private final String originURL = "https://store.musinsa.com";
	
    @Test
	@Order(1)
	@DisplayName("1> URL info 정보 저장 테스트")
    public void insertURLinfo(){
    	if(!urlShorteningRepository.findByOriginURL(originURL).isPresent()) {
    		URL url = urlShorteningRepository.save(new URL(originURL));
    		if(!url.equals(null)) {
    			assertEquals(url.getOriginURL(), originURL);
    		}
    	}
    }
    
    @Test
	@Order(2)
	@DisplayName("2> 저장된 URL 조회 테스트")
    public void readURLinfo(){
    	Optional<URL> url = urlShorteningRepository.findByOriginURL(originURL);
    	url.ifPresent(readURL -> {
			assertEquals(readURL.getOriginURL(), originURL);
    	});
    }
   
}
