package hr.five.newys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Newys")
			.description("News related web application")
			.version("1.0.0")
			.contact(new Contact("newys support", "https://support.newys.com", "support@newys.com"))
			.build();
		
	}
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("hr.five.newys"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo());
	}
	
	@Bean
	RouterFunction<ServerResponse> routerFunction() {
		return route(GET("/swagger"),
		             req -> ServerResponse.temporaryRedirect(URI.create("swagger-ui/"))
		                                  .build());
	}
}
