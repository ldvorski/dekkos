package hr.mc2.dekkos;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class DekkosApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DekkosApplication.class, args);
	}
}

@Push
@Configuration
class AppConfiguration implements AppShellConfigurator {

}
