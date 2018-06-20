package com.chhaileng.ams.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chhaileng.ams.entity.User;
import com.chhaileng.ams.entity.Article;
import com.chhaileng.ams.service.article.ArticleService;
import com.chhaileng.ams.service.category.CategoryService;
import com.chhaileng.ams.service.upload.FileUploadService;
import com.chhaileng.ams.service.user.UserService;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@GetMapping("/")
	public String index(ModelMap m, HttpServletRequest request) {
		m.addAttribute("categories", categoryService.findAll());
		m.addAttribute("articles", articleService.findAll());
		System.out.println("Client IP Address: " + request.getRemoteAddr());
		return "index";
	}
	
	@GetMapping("/add")
	public String newArticle(ModelMap m) {
		m.addAttribute("categories", categoryService.findAll());
		m.addAttribute("isAddPage", true);
		m.addAttribute("article", new Article());
		return "add";
	}
	
	@PostMapping("/add")
	public String saveArticle(@RequestParam("image") MultipartFile file, @Valid @ModelAttribute("article") Article article, BindingResult result,  ModelMap m) {
		if (result.hasErrors() || file.isEmpty()) {
			m.addAttribute("categories", categoryService.findAll());
			m.addAttribute("isAddPage", true);
			m.addAttribute("article", article);
			return "add";
		}
		
		User author = userService.findOne(1);
		article.setAuthor(author);
		article.setCategory(categoryService.findOne(article.getCategory().getId()));
		article.setDate(Timestamp.valueOf(LocalDateTime.now()));
		article.setThumbnail(fileUploadService.upload(file));
		
		articleService.save(article);
		return "redirect:/";
	}
	
	@GetMapping(value = {"/delete", "/update"})
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		articleService.delete(id);
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String edit(ModelMap m, @PathVariable("id") Integer id) {
		m.addAttribute("categories", categoryService.findAll());
		m.addAttribute("isAddPage", false);
		m.addAttribute("article", articleService.findOne(id));
		return "add";
	}
	
	@PostMapping("/update")
	public String saveUpdate(@RequestParam("image") MultipartFile file, @Valid @ModelAttribute("article") Article article, BindingResult result, ModelMap m) {
		if (result.hasErrors()) {
			m.addAttribute("categories", categoryService.findAll());
			m.addAttribute("isAddPage", false);
			m.addAttribute("article", article);
			return "add";
		}
		
		System.out.println(article);
	
		User author = userService.findOne(1);
		article.setAuthor(author);
		article.setCategory(categoryService.findOne(article.getCategory().getId()));
		article.setDate(Timestamp.valueOf(LocalDateTime.now()));
		
		if (!file.isEmpty())
			article.setThumbnail(fileUploadService.upload(file));
		else
			article.setThumbnail(articleService.findOne(article.getId()).getThumbnail());
		articleService.update(article);
		return "redirect:/";
	}
	
	@GetMapping("/article/{id}")
	public String Article(@PathVariable("id") int id, ModelMap m) {
		
		Article article = articleService.findOne(id);
		if (article==null) {
			m.addAttribute("found", false);
		} else {
			m.addAttribute("found", true);
			m.addAttribute("article", article);
		}
		return "detail";
	}
	
}
