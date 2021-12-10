package org.tbk.ngtor.command;

import com.google.common.base.Stopwatch;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.tbk.ngtor.conditional.ConditionalOnCommand;
import org.tbk.ngtor.tor.TorHttpCheck;

import java.io.IOException;

import static org.tbk.ngtor.util.ConsoleColors.*;

@Component
@ConditionalOnCommand("check")
public class CheckCommand implements ApplicationRunner {

    @Autowired
    @Qualifier("torHttpClient")
    private HttpClient torHttpClient;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Check started..");

        Stopwatch sw = Stopwatch.createStarted();
        try {
            boolean torEnabled = new TorHttpCheck(torHttpClient).check();

            if (torEnabled) {
                System.out.println(green("✔") + " Tor " + bold("is working") + ".");
            } else {
                System.out.println(red("✘") + " Tor is " + bold("NOT") + "working correctly.");
            }
            System.out.println("Check finished after " + sw.stop());
        } catch (IOException e) {
            System.err.println("Exception during tor http check: " + e.getMessage());
        }
    }
}
