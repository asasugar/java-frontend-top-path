package learning.job03.core;

import learning.job03.api.Greeting;
import org.apache.commons.lang3.StringUtils;

/**
 * 入口：主代码使用 compile 范围的 commons-lang3，与 job03-api 中「仅 test」形成对比。
 * 运行：在 job03-core 目录下 mvn -q exec:java
 */
public final class CoreApp {

    public static void main(String[] args) {
        String raw = args.length > 0 ? args[0] : "  job03-core  ";
        Greeting g = new Greeting(StringUtils.trim(raw));
        System.out.println(g.text());
    }
}
