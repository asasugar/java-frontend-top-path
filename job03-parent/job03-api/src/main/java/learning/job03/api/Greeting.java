package learning.job03.api;

/**
 * 供其它模块依赖的公共模型（Job03 演示用）。
 * 主代码不引用 commons-lang3，避免与「仅 test 依赖」示例混淆。
 */
public final class Greeting {

    private final String text;

    public Greeting(String text) {
        this.text = text == null ? "" : text;
    }

    public String text() {
        return text;
    }
}
