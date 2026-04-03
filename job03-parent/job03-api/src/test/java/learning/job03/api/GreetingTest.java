package learning.job03.api;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 仅在 test 里使用 commons-lang3，对应 pom 中 commons-lang3 的 test scope。
 */
class GreetingTest {

    @Test
    void trimsViaCommons() {
        Greeting g = new Greeting(StringUtils.trim("  hi  "));
        assertEquals("hi", g.text());
    }
}
