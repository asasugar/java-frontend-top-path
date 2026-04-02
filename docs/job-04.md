# Job 04 — Spring Boot 起步与第一个 REST

承接 [job-03.md](job-03.md)：用 **Spring Boot 3.x + Java 17** 起可运行 Web 进程，写出第一个 **JSON API**（对齐你司栈：Boot **3.4.x** 亦可）。

## 目标

- `@SpringBootApplication`、内嵌 Tomcat、默认端口 **8080**。
- **`@RestController` + `@GetMapping`** 返回 JSON。
- **`application.yml`**（或 `properties`）里改端口、打一条日志。
- 可选：**`spring.profiles.active`** 区分 `dev` / `prod` 占位配置。

## 阅读与链接

- [ ] [Spring Boot Reference — Developing Your First Application](https://docs.spring.io/spring-boot/reference/using/using-the-springbootapplication-annotation.html)
- [ ] [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)（官方 Guide）
- [ ] [Externalized Configuration](https://docs.spring.io/spring-boot/reference/features/external-config.html) 浏览

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| Spring Boot 主类 | **`node server.js`** 或 **Next API route 所在服务进程**：入口启动 HTTP 服务。 |
| `@RestController` | **Express `app.get`** / **Hono route**：路径 + 返回 body；返回值序列化成 JSON 像 **`res.json()`**。 |
| `application.yml` | **`.env` + `vite.config`/`next.config`**：环境相关配置集中放，支持 profile 切换。 |

## 任务

- [ ] 用 [start.spring.io](https://start.spring.io) 生成 **Maven、Java 17、Spring Web**，或手写等价 `pom.xml` + 主类。
- [ ] 实现 **`GET /api/ping`** 返回 `{"ok":true}`（或自定义字段）。
- [ ] 用 **curl** 或 **Bruno** 验证 200 与 JSON body。
- [ ] 将工程放在本仓库子目录（如 `job04-app/`）或独立仓库，README 里写启动命令。
- [ ] 笔记：一句卡点 + 结论。

## 命令速查

```bash
cd job04-app
mvn -q spring-boot:run
curl -s http://localhost:8080/api/ping
```

## 排错

- **端口占用**：`server.port` 改掉。
- **404**：检查 context-path 与 `@RequestMapping` 前缀是否一致。

**上一项**：[job-03.md](job-03.md)　**下一项**：[job-05.md](job-05.md)　**索引**：[jobs.md](jobs.md)
