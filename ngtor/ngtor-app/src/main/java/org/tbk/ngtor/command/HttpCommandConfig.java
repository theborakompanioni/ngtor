package org.tbk.ngtor.command;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tbk.ngtor.conditional.ConditionalOnCommand;
import org.tbk.tor.hs.HiddenServiceDefinition;
import org.tbk.tor.spring.config.TorAutoConfigProperties;

import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnCommand("http")
public class HttpCommandConfig {

    private static final int DEFAULT_PORT = 8080;

    private static final String DEFAULT_IDENTITY = "ngtor_main";

    @SuppressFBWarnings(
            value = "PATH_TRAVERSAL_IN",
            justification = "Path is controlled by operator not by user input."
    )
    @Bean
    public HiddenServiceDefinition hiddenServiceDefinition(ApplicationArguments args,
                                                           TorAutoConfigProperties torProperties) {
        int port = Optional.ofNullable(args.getOptionValues("port"))
                .flatMap(it -> it.stream().findFirst())
                .map(Integer::parseUnsignedInt)
                .orElse(DEFAULT_PORT);

        String identity = DEFAULT_IDENTITY;

        System.out.printf("Setting up hidden service '%s' for port %d%n", identity, port);

        Path hiddenServiceDir = Paths.get(torProperties.getWorkingDirectory()).resolve(identity);

        return HiddenServiceDefinition.builder()
                .directory(hiddenServiceDir.toFile())
                .virtualPort(80)
                .port(port)
                .host(InetAddress.getLoopbackAddress().getHostAddress())
                .build();
    }
}
