package interview_mock_api.doc;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Contact Contact() {
		return new Contact(
				"Charles Viegas",
				"https://github.com/CharlesBrun", 
				"charlesbrviegas@gmail.com");
	}

    private ApiInfoBuilder apiInfo() {
		 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("Grocery Store API");
		apiInfoBuilder.description("Springboot REST API");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open Source");
		apiInfoBuilder.contact(this.Contact());
 
		return apiInfoBuilder;
 
	}

    @Bean
    public Docket apiDetail() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
    
        docket
        .select()
        .apis(RequestHandlerSelectors.basePackage("interview_mock_api.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(this.apiInfo().build())
        .consumes(new HashSet<String>(Arrays.asList("application/json")))
        .produces(new HashSet<String>(Arrays.asList("application/json")));
        
        return docket;
    }
}
