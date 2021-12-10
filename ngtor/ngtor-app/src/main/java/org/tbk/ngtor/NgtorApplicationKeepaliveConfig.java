package org.tbk.ngtor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.tbk.ngtor.conditional.ConditionalOnCommand;

/**
 * Some console commands need to stay running.
 * This config schedules a proxy task to keep the application alive.
 *
 * Insert any console command that should be kept running.
 * e.g. {@link org.tbk.ngtor.command.HttpCommand}
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableScheduling
@Conditional(NgtorApplicationKeepaliveConfig.AnyKeepAliveCommandCondition.class)
@ConditionalOnNotWebApplication
class NgtorApplicationKeepaliveConfig {

    static class AnyKeepAliveCommandCondition extends AnyNestedCondition {
        AnyKeepAliveCommandCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnCommand("http")
        static class HttpCommandCondition {
        }

        @ConditionalOnCommand("serve")
        static class ServeCommandCondition {
        }
    }

    // keeps console app running
    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void keepAlive() {
        log.trace("keepalive");
    }
}
