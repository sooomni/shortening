package com.musinsa.shortening.exception;
import lombok.Getter;

@Getter
public class OverMaxLengthException extends RuntimeException {
	public OverMaxLengthException() {
	        super("Shortening Key 길이가 8보다 큽니다.");
	}     
}
