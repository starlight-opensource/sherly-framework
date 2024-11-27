package cn.starlightsoftware.sherly.swagger.config;

import cn.hutool.core.util.StrUtil;
import cn.starlightsoftware.sherly.enums.BaseEnum;
import com.fasterxml.jackson.databind.type.SimpleType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.PropertyCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 谷子毅
 */
@Data
@Configuration
public class Knife4jConfig {

    @Value("${spring.application.name}")
    private String title;

    @Value("${sherly.info.version}")
    private String version;

    @Bean
    public GroupedOpenApi adminApi() {
        String[] paths = {"/admin-api/**"};
        return GroupedOpenApi.builder()
                .group(title + "-admin-api")
                .pathsToMatch(paths)
                .packagesToScan()
                .addOperationCustomizer((operation, handlerMethod) -> operation
                        .addParametersItem(createParameter()))
                .build();
    }

    @Bean
    public GroupedOpenApi appApi() {
        String[] paths = {"/app-api/**"};
        return GroupedOpenApi.builder()
                .group(title + "-app-api")
                .pathsToMatch(paths)
                .packagesToScan()
                .addOperationCustomizer((operation, handlerMethod) -> operation
                        .addParametersItem(createParameter()))
                .build();
    }

    @Bean
    public GroupedOpenApi rpcApi() {
        String[] paths = {"/rpc-api/**"};
        return GroupedOpenApi.builder()
                .group(title + "-rpc-api")
                .pathsToMatch(paths)
                .packagesToScan()
                .addOperationCustomizer((operation, handlerMethod) -> operation
                        .addParametersItem(createParameter()))
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title + "-api")
                        .version(version)
                );
    }

    public Parameter createParameter() {
        return new Parameter()
                .name("token")
                .description("访问令牌")
                .in(String.valueOf(SecurityScheme.In.HEADER))
                .schema(new StringSchema().name("token").description("访问令牌"))
                .required(false);
    }

    @Bean
    PropertyCustomizer propertyCustomizer() {
        return (property, type) -> {
            if (type.getType() instanceof SimpleType && ((SimpleType) type.getType()).getRawClass().isEnum()) {
                SimpleType simpleType = (SimpleType) type.getType();
                Object[] enumConstants = simpleType.getRawClass().getEnumConstants();
                StringBuilder sb = new StringBuilder(property.getDescription() + ":");
                for (Object enumItem : enumConstants) {
                    if (enumItem instanceof BaseEnum) {
                        BaseEnum baseEnum = (BaseEnum) enumItem;
                        sb.append(baseEnum.getValue())
                                .append("-")
                                .append(baseEnum.getKey())
                                .append("-")
                                .append(baseEnum.getDescription())
                                .append(",");
                    }
                }
                property.setDescription(StrUtil.sub(sb, 0, -1));
            }
            return property;
        };
    }

}
