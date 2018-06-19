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
import com.chhaileng.ams.service.user.UserService;
import com.chhaileng.article.service.upload.FileUploadService;

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
		return "admin/add";
	}
	
	@PostMapping("/add")
	public String saveArticle(@RequestParam("image") MultipartFile file, @Valid @ModelAttribute("article") Article article, BindingResult result,  ModelMap m) {
		if (result.hasErrors() || file.isEmpty()) {
			m.addAttribute("categories", categoryService.findAll());
			m.addAttribute("isAddPage", true);
			m.addAttribute("article", article);
			return "admin/add";
		}
		
		User author = userService.findOne(1);
		article.setAuthor(author);
		article.setCategory(categoryService.findOne(article.getCategory().getId()));
		article.setDate(Timestamp.valueOf(LocalDateTime.now()));
		article.setThumbnail(fileUploadService.upload(file));
		
		articleService.save(article);
		return "redirect:/";
	}
	
	@GetMapping(value = {"/delete", "/edit"})
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		articleService.delete(id);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(ModelMap m, @PathVariable("id") Integer id) {
		m.addAttribute("categories", categoryService.findAll());
		m.addAttribute("isAddPage", false);
		m.addAttribute("article", articleService.findOne(id));
		return "admin/add";
	}
	
	@PostMapping("/edit")
	public String saveEdit(@RequestParam("image") MultipartFile file, @Valid @ModelAttribute("article") Article article, BindingResult result, ModelMap m) {
		if (result.hasErrors()) {
			m.addAttribute("categories", categoryService.findAll());
			m.addAttribute("isAddPage", false);
			m.addAttribute("article", article);
			return "admin/add";
		}
	
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
	
	@GetMapping("/article")
	public String Article(@RequestParam(required = false) Integer id, ModelMap m) {
		if (id==null) {
			return "redirect:/";
		}
		Article article = articleService.findOne(id);
		if (article==null) {
			m.addAttribute("found", false);
		} else {
			m.addAttribute("found", true);
			m.addAttribute("article", article);
		}
		return "user/article";
	}
	
}
