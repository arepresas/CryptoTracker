package stream.arepresas.cryptotracker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.StreamSupport;

@Slf4j
@Component
@Profile("dev")
public class PropertyLogger {

  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) {
    Environment env = event.getApplicationContext().getEnvironment();
    log.info("====== Environment and configuration ======");
    log.info("Active profiles: {}", Arrays.toString(env.getActiveProfiles()));
    MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
    StreamSupport.stream(sources.spliterator(), false)
        .filter(EnumerablePropertySource.class::isInstance)
        .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
        .flatMap(Arrays::stream)
        .distinct()
        .filter(prop -> !(prop.contains("credentials") || prop.contains("password")))
        .sorted()
        .forEach(prop -> log.info("{}: {}", prop, env.getProperty(prop)));
    log.info("===========================================");
  }
}
