package com.musinsa.shortening.dto;

import com.musinsa.shortening.repo.URL;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class URLRequestDTO {
	
	private String originURL;

	@Builder
	public URLRequestDTO(String originURL) {
		this.originURL = originURL;
	}
	
	public URL toEntity(){
		return URL.builder()
				.originURL(originURL)
				.build();
	}

}
