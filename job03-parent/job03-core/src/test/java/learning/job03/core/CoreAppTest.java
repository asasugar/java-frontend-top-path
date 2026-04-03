package learning.job03.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CoreAppTest {

    @Test
    void mainRuns() {
        assertDoesNotThrow(() -> CoreApp.main(new String[] {"  x  "}));
    }
}
