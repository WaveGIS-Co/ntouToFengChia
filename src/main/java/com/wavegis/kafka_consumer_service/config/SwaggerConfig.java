package com.wavegis.kafka_consumer_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Bean
    public Docket swaggerSpringMvcPlugin() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
                .groupName("business-api")
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any()) 
                .build();
		
        docket.alternateTypeRules(AlternateTypeRules.newMapRule(String.class,Object.class));
        
        return docket ;
    }
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("昕傳內部API-kafka消費者服務")
                .description("昕傳內部API-kafka消費者服務")
                .version("1.0")
                .build();
    }

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}