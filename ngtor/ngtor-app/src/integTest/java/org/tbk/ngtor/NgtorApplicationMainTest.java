package org.tbk.ngtor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NgtorApplicationMainTest {

    @Test
    void withoutArgs() {
        Assertions.assertDoesNotThrow(() -> NgtorApplication.main(new String[]{}));
    }

    @Test
    void executeCommandHelp() {
        Assertions.assertDoesNotThrow(() -> NgtorApplication.main(new String[]{"help"}));
    }

    @Test
    void executeCommandCheck() {
        Assertions.assertDoesNotThrow(() -> NgtorApplication.main(new String[]{"check"}));
    }

}
