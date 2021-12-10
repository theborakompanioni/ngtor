package org.tbk.ngtor;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.berndpruenster.netlayer.tor.Tor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.tbk.ngtor.tor.TorHttpCheck;
import org.tbk.tor.hs.HiddenServiceDefinition;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class NgtorApplicationTest {

    @Autowired
    private Tor tor;

    @Autowired
    @Qualifier("torHttpClient")
    private HttpClient torHttpClient;

    @Autowired
    private HiddenServiceDefinition applicationHiddenServiceDefinition;

    @Test
    void contextLoads() {
        assertThat(tor, is(notNullValue()));
        assertThat(torHttpClient, is(notNullValue()));
        assertThat(applicationHiddenServiceDefinition, is(notNullValue()));
    }

    @Test
    void itShouldVerifyTorIsWorking() throws IOException {
        boolean success = new TorHttpCheck(torHttpClient).check();
        assertThat("tor is working", success, is(true));
    }

    @Test
    void itShouldVerifyHiddenServiceIsAvailable() {
        String onionUrl = applicationHiddenServiceDefinition.getVirtualHostOrThrow();

        boolean hiddenServiceAvailable = tor.isHiddenServiceAvailable(onionUrl);
        assertThat("hidden service is available", hiddenServiceAvailable, is(true));
    }

}
