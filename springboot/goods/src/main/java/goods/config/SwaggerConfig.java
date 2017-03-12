package goods.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author zhanghuan
 * @date 2016年10月9日 下午4:45:20
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	final String BASE_PATH = "goods.controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage(BASE_PATH)) // 在这里设置扫描的api的包
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                .apiInfo(apiInfo());
    }
    
    // 配置api信息
    ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("商品管理 api")
    			.description("商品管理的api接口描述")
    			.version("0.0.1")
    			.build();

    }
}