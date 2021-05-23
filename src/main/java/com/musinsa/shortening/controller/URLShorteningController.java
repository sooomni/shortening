package com.musinsa.shortening.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.musinsa.shortening.dto.URLRequestDTO;
import com.musinsa.shortening.service.URLShorteningService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class URLShorteningController {
	private final URLShorteningService urlShorteningService;
	
	public URLShorteningController(URLShorteningService urlShorteningService) {
		this.urlShorteningService = urlShorteningService;
	}
	
	//main page
	@GetMapping("")
    public ModelAndView main() {
        return new ModelAndView("views/main");
    }
	
	//URL 변환
	@PostMapping("/shorten")
	public String shorten(@RequestBody URLRequestDTO reqDTO, Model model) {
		model.addAttribute("shortURL",urlShorteningService.shortening(reqDTO));
		return "views/result";
	}
	 
	//바뀐 URL로 redirect
	@GetMapping("{key}")
    public String redirect(@PathVariable("key") String key) {
        return "redirect:"+urlShorteningService.redirect(key);
    }
}
