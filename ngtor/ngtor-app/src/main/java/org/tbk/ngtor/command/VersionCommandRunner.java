package org.tbk.ngtor.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;
import org.tbk.ngtor.conditional.ConditionalOnCommand;

@Component
@ConditionalOnCommand("version")
@RequiredArgsConstructor
public class VersionCommandRunner implements ApplicationRunner {

    @NonNull
    private final BuildProperties buildProperties;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(buildProperties.getVersion());
    }
}
