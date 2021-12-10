package org.tbk.ngtor.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;
import org.tbk.ngtor.conditional.ConditionalOnCommand;

@Component
@ConditionalOnCommand("help")
@RequiredArgsConstructor
public class HelpCommand implements ApplicationRunner {

    @NonNull
    private final BuildProperties buildProperties;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Usage: ./ngtor.jar COMMAND [OPTION...]");
        System.out.println();
        System.out.println("  Command");
        System.out.println("      http                serve the specified port");
        //System.out.println("      serve              serve the current directory");
        System.out.println("      demo                spin up a webserver and serve a demo page via tor");
        System.out.println("      check               perform system health checks (e.g. tor connection)");
        System.out.println("      version             print application version");
        System.out.println("      help                print this help text");
        System.out.println();
        System.out.println("  Examples");
        System.out.println("      ./ngtor.jar http             # onion url for port 8080 (default) web server");
        System.out.println("      ./ngtor.jar http --port=80   # onion url for port 80 web server");
        System.out.println();
        System.out.println("  Version");
        System.out.println("      " + buildProperties.getVersion());
        System.out.println();
    }
}
