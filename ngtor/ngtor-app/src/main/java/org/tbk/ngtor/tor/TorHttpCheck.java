package org.tbk.ngtor.tor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public final class TorHttpCheck {

    @NonNull
    private final HttpClient torHttpClient;

    public boolean check() throws IOException {
        String successPhrase = "Congratulations. This browser is configured to use Tor.";
        String errorPhraseIgnoreCase = "not using Tor";

        HttpGet req = new HttpGet("https://check.torproject.org/");

        HttpResponse rsp = torHttpClient.execute(req);

        String body = EntityUtils.toString(rsp.getEntity(), StandardCharsets.UTF_8);

        boolean containsErrorPhrase = body.toLowerCase().contains(errorPhraseIgnoreCase.toLowerCase());
        boolean containsSuccessPhrase = body.contains(successPhrase);

        return containsSuccessPhrase && !containsErrorPhrase;
    }
}
