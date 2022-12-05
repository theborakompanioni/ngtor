package org.tbk.ngtor.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.tbk.tor.hs.HiddenServiceDefinition;
import org.tbk.ngtor.conditional.ConditionalOnCommand;
import org.tbk.ngtor.util.ConsoleColors;

@Slf4j
@Component
@ConditionalOnCommand("http")
@RequiredArgsConstructor
public class HttpCommand implements ApplicationRunner {

    @NonNull
    private final HiddenServiceDefinition hiddenServiceDefinition;

    @Override
    public void run(ApplicationArguments args) {
        log.debug("Hidden service served from directory '{}'", hiddenServiceDefinition.getDirectory());

        String message = String.format("Service '%s' to %s:%d activated on: %s",
                hiddenServiceDefinition.getName(),
                hiddenServiceDefinition.getHost(),
                hiddenServiceDefinition.getPort(),
                buildOnionUrl(hiddenServiceDefinition));

        // inform user by logging to console...
        System.out.println(ConsoleColors.green("✔") + " " + message);

        // ...but also write to log file
        log.info(message);
    }

    private String buildOnionUrl(HiddenServiceDefinition applicationHiddenServiceDefinition) {
        return applicationHiddenServiceDefinition.getVirtualHost()
                .map(virtualHost -> {
                    int port = applicationHiddenServiceDefinition.getVirtualPort();
                    if (port == 80) {
                        return "http://" + virtualHost;
                    } else if (port == 443) {
                        return "https://" + virtualHost;
                    }
                    return "http://" + virtualHost + ":" + port;
                }).orElseThrow(() -> new IllegalStateException("Could not build onion url"));
    }
}
