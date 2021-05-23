package com.musinsa.shortening.repo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "url_master")
@DynamicInsert
@SequenceGenerator(name = "URL_SEQ_GENERATOR"
					, sequenceName = "URL_SEQ"
					, initialValue = 100000
					, allocationSize = 1)
public class URL {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE,
		generator = "URL_SEQ_GENERATOR")
    private Integer seq;

    @Column(length = 1000, nullable = false)
    private String originURL;
    
    @Column(columnDefinition ="integer default 0")
    private Integer reqCnt;

    @Builder
	public URL(Integer seq, String originURL,Integer reqCnt) {
    	this.seq = seq;
    	this.originURL = originURL;
    	this.reqCnt = reqCnt;
    }
    
    @Builder
	public URL(Integer seq, String originURL) {
    	this.seq = seq;
    	this.originURL = originURL;
    }
    
    @Builder
 	public URL(String originURL) {
     	this.originURL = originURL;
     }
    
}
