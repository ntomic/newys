package hr.five.newys.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "USERS")
public class User {
	
	
	@Id
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String enabled;
	
/*
	@OneToMany(targetEntity=Article.class)
	List<Article> writtenArticles;
*/


}
