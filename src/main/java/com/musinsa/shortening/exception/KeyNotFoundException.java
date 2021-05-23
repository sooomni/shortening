package com.musinsa.shortening.exception;
import lombok.Getter;

@Getter
public class KeyNotFoundException extends RuntimeException {
	public KeyNotFoundException() {
        super("Shortening Key를 찾을 수 없습니다.");
	}   
}
