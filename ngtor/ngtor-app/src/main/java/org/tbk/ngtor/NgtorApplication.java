package org.tbk.ngtor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.Banner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.context.WebServerPortFileWriter;
import org.springframework.context.ApplicationListener;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@SpringBootApplication
public class NgtorApplication implements InitializingBean {

    private static final Set<String> commands = Set.of("http", "serve", "check", "demo", "help", "version");
    // enable tor on a subset of commands (e.g. "help" does not need tor daemon)
    private static final Set<String> torCommands = Set.of("http", "serve", "check", "demo");

    public static void main(String[] args) {
        DefaultApplicationArguments arguments = new DefaultApplicationArguments(args);

        boolean demoMode = args.length > 0 && "demo".equals(args[0]);
        boolean webapp = arguments.containsOption("webapp") || demoMode;

        List<String> argsList = buildArgsList(args);
        if (webapp) {
            startWebApplication(StringUtils.toStringArray(argsList));
        } else {
            startConsoleApplication(StringUtils.toStringArray(argsList));
        }
    }

    private static List<String> buildArgsList(String[] args) {
        List<String> argsList = new ArrayList<>(Arrays.asList(args));

        if (argsList.isEmpty()) {
            argsList.add("help");
        }

        String command = argsList.get(0);
        boolean unknownCommand = !commands.contains(command);
        if (unknownCommand) {
            throw new IllegalArgumentException("Unknown command: " + command);
        }

        // TODO: currently done stupidly.. should not be done via manipulating args list
        // -> e.g. extend core autoconfigure instead of using tor-starter (maybe)
        boolean enableTor = torCommands.contains(command);
        argsList.add("--org.tbk.tor.enabled=" + enableTor);

        boolean publishHiddenService = "demo".equals(command);
        argsList.add("--org.tbk.tor.auto-publish-enabled=" + publishHiddenService);

        return argsList;
    }

    private static void startConsoleApplication(String[] args) {
        new SpringApplicationBuilder()
                .sources(NgtorApplication.class)
                .listeners(applicationPidFileWriter())
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .run(args);
    }

    public static void startWebApplication(String[] args) {
        new SpringApplicationBuilder()
                .sources(NgtorApplication.class)
                .listeners(applicationPidFileWriter(), webServerPortFileWriter())
                .web(WebApplicationType.SERVLET)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(true)
                .run(args);
    }


    public static ApplicationListener<?> applicationPidFileWriter() {
        return new ApplicationPidFileWriter("application.pid");
    }

    public static ApplicationListener<?> webServerPortFileWriter() {
        return new WebServerPortFileWriter("application.port");
    }

    @Override
    public void afterPropertiesSet() {
        log.info("Starting..");
    }
}
