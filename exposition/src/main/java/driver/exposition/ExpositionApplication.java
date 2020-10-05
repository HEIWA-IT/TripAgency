package {{project_namespace}}.{{project_name}}.driver.exposition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"{{project_namespace}}.{{project_name}}"})
@EnableJpaRepositories("{{project_namespace}}.{{project_name}}.driven.repository.springdata")
@EntityScan("{{project_namespace}}.{{project_name}}.driven.repository.springdata")
public class ExpositionApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExpositionApplication.class, args);
	}
}