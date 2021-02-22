package hr.five.newys.controller;

import hr.five.newys.exception.RecordNotFoundException;
import hr.five.newys.model.Article;
import hr.five.newys.service.NewysService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@Slf4j
public class NewysController {
	
	final NewysService newysService;
	
	public NewysController(NewysService newysService) {this.newysService = newysService;}
	
	
	@RequestMapping(path = {"/home", "/index"}, method = RequestMethod.GET)
	public String getHomePage() {
		return "index";
	}
	
	
	@RequestMapping(path = {"/articles"}, method = RequestMethod.GET)
	public String getArticles(@PageableDefault(size = 10) Pageable pageable, Model model) {
		var articles = newysService.getArticles(pageable);
		model.addAttribute("articles", articles);
		return "articles";
	}
	
	
	@RequestMapping(path = "/article/create", method = RequestMethod.GET)
	public String createArticle(Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("title", "Create");
		return "create-edit-article";
	}
	
	
	@RequestMapping(path = "/article/edit/{id}", method = RequestMethod.GET)
	public String editArticle(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			var article = newysService.getArticle(id.get());
			model.addAttribute("article", article);
			model.addAttribute("title", "Edit");
		}
		return "create-edit-article";
	}
	
	
	@RequestMapping(path = "/article/save", method = RequestMethod.POST)
	public String createArticle(@Valid Article article) throws RecordNotFoundException {
		newysService.saveArticle(article);
		return "redirect:/articles";
	}
	
	
	@RequestMapping(path = "/article/delete/{id}", method = RequestMethod.GET)
	public String deleteArticle(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		newysService.deleteArticle(id);
		return "redirect:/articles";
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({RecordNotFoundException.class, ConstraintViolationException.class, BindException.class})
	public String handleError(Model model, HttpServletRequest req, Exception ex) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		model.addAttribute("message", ex.getMessage());
		return "error";
	}
	
	
}
