package com.musinsa.shortening.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface URLShorteningRepository extends JpaRepository<URL, Integer>{
	public Optional <URL> findByOriginURL(String originURL);

	public URL save(URL url);
	
	@Modifying
	@Query("UPDATE URL u set u.reqCnt = ?1 where u.seq = ?2")
	int updateReqCnt(@Param("req_cnt") Integer reqCnt, @Param("seq") Integer seq);

	public Optional<URL> findBySeq(int decode);
}
