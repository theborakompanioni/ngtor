package org.tbk.ngtor.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.berndpruenster.netlayer.tor.Tor;
import org.berndpruenster.netlayer.tor.TorCtlException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.tbk.tor.hs.HiddenServiceDefinition;
import org.tbk.ngtor.conditional.ConditionalOnCommand;

import java.util.Optional;

@Slf4j
@Component
@ConditionalOnCommand("demo")
@RequiredArgsConstructor
public class DemoCommand implements ApplicationRunner {

    @NonNull
    private final Tor tor;

    @NonNull
    private final HiddenServiceDefinition hiddenServiceDefinition;

    @Override
    public void run(ApplicationArguments args) {
        Optional<String> httpUrl = hiddenServiceDefinition.getVirtualHost()
                .map(val -> "http://" + val + ":" + hiddenServiceDefinition.getVirtualPort());

        log.info("=================================================");
        log.info("url: {}", httpUrl.orElse("unavailable"));
        log.info("virtual host: {}", hiddenServiceDefinition.getVirtualHost().orElse("unavailable"));
        log.info("virtual port: {}", hiddenServiceDefinition.getVirtualPort());
        log.info("host: {}", hiddenServiceDefinition.getHost());
        log.info("port: {}", hiddenServiceDefinition.getPort());
        log.info("directory: {}", hiddenServiceDefinition.getDirectory().getAbsolutePath());
        httpUrl.ifPresent(url -> {
            log.info("-------------------------------------------------");
            try {
                log.info("Or browse to {} in Tor Browser, or", url);
                log.info("run: `torsocks -p {} curl {}/index.html -v`", tor.getProxy().getPort(), url);
            } catch (TorCtlException e) {
                log.warn("Could not get tor proxy port");
            }
        });
        log.info("=================================================");
    }
}
