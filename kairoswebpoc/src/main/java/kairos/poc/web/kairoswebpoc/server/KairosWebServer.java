package kairos.poc.web.kairoswebpoc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;

@ComponentScan(basePackages = "kairos.poc.web.kairoswebpoc")
@SpringBootApplication
public class KairosWebServer {
	private static Logger logger = LoggerFactory.getLogger(KairosWebServer.class);
	public static void main(String[] args) {
		ApplicationContext applicationContext =  SpringApplication.run(KairosWebServer.class, args);
		logger.info("Listing {} beans registered by spring boot.",applicationContext.getBeanDefinitionNames().length);
		for (String beaNames: applicationContext.getBeanDefinitionNames()){
			logger.info("Bean {}",beaNames);
		}
		logger.info("Listing of beans completed.");
	}
}
