package g.proux.ecole.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api")
                .displayName("/api")
                .packagesToScan("g.proux.ecole")
                .pathsToMatch("/api/**")
                .build();
    }

}
