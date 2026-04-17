# job04-app（Job 04）

```bash
cd job04-app
mvn spring-boot:run
```

另开终端：

```bash
curl -s http://localhost:8080/api/ping
```

期望：`{"ok":true}`

切换 profile：`SPRING_PROFILES_ACTIVE=prod mvn spring-boot:run`

开发时已加 **spring-boot-devtools**：它监听的是 **`target/classes` 是否更新**，不是监听你保存 `.java` 本身。因此：

- **用终端 `mvn spring-boot:run` 跑着时**：保存源码**不会**自动编译；另开一个终端执行 **`cd job04-app && mvn compile`**（或 `mvn -q compile`），看到控制台 **Restarting** 后再 `curl`。
- **用 VS Code / Cursor 跑 Spring Boot**：仓库已开 **`java.autobuild.enabled`**；用 **Run and Debug** 起应用，保存后等几秒让 JDT 编译进 `target/classes`，才会触发重启。
- **IntelliJ**：**Build project automatically** + Registry **`compiler.automake.allow.when.app.running`**。

仍不是 Vite 级 HMR，本质是 **快速重启** Spring 上下文。
