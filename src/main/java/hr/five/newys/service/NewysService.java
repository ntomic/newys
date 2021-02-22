package hr.five.newys.service;

import hr.five.newys.exception.RecordNotFoundException;
import hr.five.newys.model.Article;
import hr.five.newys.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Slf4j
public class NewysService {
	
	final ArticleRepository articleRepository;
	
	public NewysService(ArticleRepository articleRepository) {this.articleRepository = articleRepository;}
	
	public Page<Article> getArticles(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}
	
	public Article getArticle(Long id) throws RecordNotFoundException {
		Optional<Article> article = articleRepository.findById(id);
		if (article.isPresent()) {
			return article.get();
		}
		else {
			log.error("No article record exist for given id: {}", id);
			throw new RecordNotFoundException("No article record exist for given id: " + id);
		}
	}
	
	public void deleteArticle(Long id) throws RecordNotFoundException {
		Optional<Article> article = articleRepository.findById(id);
		if (article.isPresent()) {
			articleRepository.deleteById(id);
			log.info("Deleted article with id: {}", id);
		}
		else {
			log.error("No article record exist for given id: {}", id);
			throw new RecordNotFoundException("No article record exist for given id: " + id);
		}
	}
	
	public Article saveArticle(Article article) throws RecordNotFoundException {
		
		if (article.getArticleID() == null) {
			article = articleRepository.save(article);
			log.info("Created article with id: {}", article.getArticleID());
			return article;
		}
		else {
			Optional<Article> currentArticle = articleRepository.findById(article.getArticleID());
			
			if (currentArticle.isPresent()) {
				Article updatedArticle = currentArticle.get();
				
				updatedArticle.setAuthor(article.getAuthor());
				updatedArticle.setTitle(article.getTitle());
				updatedArticle.setContent(article.getContent());
				updatedArticle.setIsPublic(article.getIsPublic());
				updatedArticle.setDateModified(LocalDateTime.now());
				
				articleRepository.save(updatedArticle);
				
				log.info("Updated article with id: {}", article.getArticleID());
				return updatedArticle;
			}
			else {
				log.error("No article record exist for given id: {}", article.getArticleID());
				throw new RecordNotFoundException("No article record exist for given id: " + article.getArticleID());
			}
		}
	}
	
}
