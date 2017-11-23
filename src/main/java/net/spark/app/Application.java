package net.spark.app;

import java.util.Currency;
import java.util.Locale;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vaadin.spring.events.annotation.EnableEventBus;


import net.spark.app.security.SecurityConfig;
import net.spark.backend.OrderRepository;
import net.spark.backend.data.entity.Order;
import net.spark.backend.service.UserService;
import net.spark.backend.util.LocalDateJpaConverter;
import net.spark.cellar.Wine;
import net.spark.ui.AppUI;

@SpringBootApplication(scanBasePackageClasses = { AppUI.class, Application.class, UserService.class,
		SecurityConfig.class })
@EnableJpaRepositories(basePackageClasses = { OrderRepository.class })
@EntityScan(basePackageClasses = { Order.class, LocalDateJpaConverter.class ,Wine.class})
@EnableEventBus
public class Application extends SpringBootServletInitializer {

	public static final String APP_URL = "/";
	public static final String LOGIN_URL = "/login.html";
	public static final String LOGOUT_URL = "/login.html?logout";
	public static final String LOGIN_FAILURE_URL = "/login.html?error";
	public static final String LOGIN_PROCESSING_URL = "/login";

	public static void main(String[] args) {
		Locale newLocale = new Locale("en", "IN");
		
		Locale.setDefault(newLocale);

		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	@Bean
	public EmbeddedServletContainerFactory servletContainer () {
		TomcatEmbeddedServletContainerFactory tomcat = new  TomcatEmbeddedServletContainerFactory();
		Connector ajpConnector = new Connector("AJP/1.3");
		ajpConnector.setProtocol("AJP/1.3");
		ajpConnector.setPort(9090);
		ajpConnector.setSecure(false);
		ajpConnector.setAllowTrace(false);
		tomcat.addAdditionalTomcatConnectors(ajpConnector);
		return tomcat;
	}
}
