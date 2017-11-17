package com.lish.dongfang.core.doc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lish.dongfang.core.adapter.SwaggerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger的配置
 * @author lisong
 *
 */
@Configuration
@ConditionalOnProperty("fast.boot.swagger.enable")
@EnableSwagger2
public class SwaggerConfiguration {
	private static Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);
	@Autowired  
	private SwaggerAdapter swaggerAdapter;
	@Autowired  
	private FastSwaggerConfig swaggerConfig;
	
	@Bean
    public Docket createRestApi() {
		logger.info("----------------服务添加swagger功能------------------");
        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.lish.dongfang.vote.ms"))
                .paths(PathSelectors.any());
		return swaggerAdapter.addScanBasePackages(builder).build();
//        return builder.build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerConfig.getTitle())
                .description(swaggerConfig.getDescription())
                .termsOfServiceUrl(swaggerConfig.getServiceUrl())
                .contact(swaggerConfig.getContact())
                .version(swaggerConfig.getVersion())
                .license(swaggerConfig.getLicense())
                .licenseUrl(swaggerConfig.getLicenseUrl())
                .build();
    }
}
