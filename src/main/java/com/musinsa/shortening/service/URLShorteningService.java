package com.musinsa.shortening.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.shortening.Utils.Base62Util;
import com.musinsa.shortening.Utils.PathCombineUtil;
import com.musinsa.shortening.dto.URLRequestDTO;
import com.musinsa.shortening.exception.KeyNotFoundException;
import com.musinsa.shortening.exception.OverMaxLengthException;
import com.musinsa.shortening.repo.URL;
import com.musinsa.shortening.repo.URLShorteningRepository;

@Service
@Transactional(readOnly = true)
public class URLShorteningService {

	private final URLShorteningRepository urlShorteningRepository;
	private final Base62Util base62;
	private final PathCombineUtil pathCombiner;
	
	public URLShorteningService(URLShorteningRepository urlShorteningRepository, Base62Util base62, PathCombineUtil pathCombiner) {
		this.urlShorteningRepository = urlShorteningRepository;
		this.base62 = base62;
		this.pathCombiner = pathCombiner;
	}
	
	@Transactional
	public String shortening(URLRequestDTO reqDTO){
		int seq;
		Optional<URL> shortURL = urlShorteningRepository.findByOriginURL(reqDTO.getOriginURL());
		
		if(!shortURL.isPresent()) {
			seq = urlShorteningRepository.save(reqDTO.toEntity()).getSeq();
		}
		else {
			seq = shortURL.get().getSeq();
		}
		return shortenConverting(seq);
	}
	
	public String shortenConverting (int seq){
		String key = base62.encode(seq);
		if(key.length() > 8) {
			throw new OverMaxLengthException();
		}
		return pathCombiner.combinePath(key);
	}

	@Transactional
	public String redirect(String key) {
		Optional<URL> originURL = urlShorteningRepository.findBySeq(base62.decode(key));
		if(!originURL.isPresent()) {
			throw new KeyNotFoundException();
		}
		
		urlShorteningRepository.updateReqCnt(originURL.get().getReqCnt()+1,originURL.get().getSeq());
		return originURL.get().getOriginURL();
	}
	
}
