package kairos.poc.web.kairoswebpoc.Config;

import kairos.poc.web.kairoswebpoc.server.KairosWebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Class bootstraps the bean configuration for the dependencies using spring xml bean.
 */
@Configuration
@ImportResource({"classpath*:kairos-application-context.xml"})
public class KairosConfig {
    private static Logger logger = LoggerFactory.getLogger(KairosWebServer.class);
}
