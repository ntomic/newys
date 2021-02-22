package hr.five.newys.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "ARTICLES")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "article_id", nullable = false)
	private Long articleID;
	
	@Column(name = "title", nullable = false)
	@Size(min = 3, message = "Title must be at least 3 characters long")
	@NotBlank
	@NotNull
	private String title;
	
	@Column(name = "content", nullable = false)
	@Size(min = 3, message = "Content must be at least 3 characters long")
	@NotBlank
	@NotNull
	private String content;
	
	@Column(name = "author", nullable = false)
	@Size(min = 3, message = "Author must be at least 3 characters long")
	@NotBlank
	@NotNull
	private String author;
	
	@Column(name = "is_public")
	private Boolean isPublic = true;
	
	@Column(name = "date_created", nullable = false)
	@CreationTimestamp
	private LocalDateTime dateCreated;
	
	@Column(name = "date_modified")
	@CreationTimestamp
	private LocalDateTime dateModified;
	
	@PrePersist
	void createdAt() {
		this.dateCreated  = LocalDateTime.now();
		this.dateModified = LocalDateTime.now();
	}
	
	
}
