package org.tbk.ngtor;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.tbk.ngtor.tor.TorHttpCheck;

@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
class NgtorApplicationWebConfig {

    @Autowired
    @Qualifier("torHttpClient")
    private HttpClient torHttpClient;

    @Bean
    @Profile("!test")
    public ApplicationRunner torHttpCheckRunner() {
        return args -> {
            boolean torEnabled = new TorHttpCheck(torHttpClient).check();

            log.info("=================================================");
            if (torEnabled) {
                log.info("Tor is working correctly.");
            } else {
                log.warn("Tor is NOT working correctly.");
            }
            log.info("=================================================");
        };
    }
}
