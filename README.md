# java-frontend-top-path

面向**前端背景**的开发者，系统化记录向 **Java 全栈**（含服务端与交付链路）转型的学习路径与实践沉淀。本仓库以「主线技术栈跟随社区主流、长期版本优先」为原则，路线随实践持续迭代。

## 路线原则

- **长期支持优先**：语言与框架以 LTS / 稳定主线为准，再按需补充新特性。
- **云原生与可观测默认**：容器、健康检查、指标与追踪按生产习惯看齐，而非仅「能跑」。
- **与前端能力衔接**：HTTP、异步、类型与模块化思维可直接迁移；补齐 JVM、并发、事务与分布式语义。

## 技术栈主线（后端与工程）

| 领域 | 建议选型（主流/常配） |
|------|------------------------|
| 语言与运行时 | Java **21 LTS**（后续可跟进下一 LTS）；熟悉 `record`、模式匹配、`Virtual Thread` 等现代特性 |
| 应用框架 | **Spring Boot 3.x**（**Spring Framework 6**、Jakarta EE 命名空间）、**Spring Web / Spring MVC** |
| 数据访问 | **Spring Data JPA**、**Querydsl** 或原生 **JdbcClient**（按项目复杂度）；迁移工具 **Flyway** 或 **Liquibase** |
| 关系型库 | **PostgreSQL** 或 **MySQL 8**（二选一吃透即可） |
| 缓存 | **Redis**（缓存、分布式锁、限流常用场景） |
| API 契约 | **OpenAPI 3**（springdoc 等），与前端联调与 Mock 一致 |
| 安全 | **Spring Security 6**、**JWT** / **OAuth2 / OIDC**（与网关或 IdP 集成） |
| 消息与异步 | **Kafka** 或 **RabbitMQ**（先精通一种）；**Spring** 事件与 **`@Async`** 作切入点 |
| HTTP 客户端 | **Spring WebClient** 或 JDK **HttpClient**（替代旧版 RestTemplate 思维） |
| 构建 | **Maven** 或 **Gradle Kotlin DSL**（团队用什么深用什么） |
| 测试 | **JUnit 5**、**AssertJ**、**Testcontainers**、**WireMock**（按需要） |
| 可观测性 | **Micrometer**、**Prometheus** 生态；**OpenTelemetry** 与链路追踪；结构化日志 |
| 运行与交付 | **Docker**；编排以 **Kubernetes** 基础概念为目标（本地可用 **minikube / kind**） |

## 分阶段路线

1. **Java 与 JVM 基础**
   语法与类型系统、集合与流、异常、泛型；**内存模型与并发**（`j.u.concurrent`）；基础性能与 **JFR / 简单排障** 概念。

2. **Spring 核心与 Web**
   IoC、AOP、配置与 Profile；REST 设计、校验、统一异常与 Problem Details；与 OpenAPI、前端联调闭环。

3. **数据与一致性**
   事务隔离与传播、JPA 实体与边界、N+1 与索引意识；迁移脚本与多环境数据策略。

4. **中间件与分布式语义**
   缓存穿透/击穿/雪崩；消息投递语义；幂等、分布式锁的常见实现与局限。

5. **全栈项目闭环**
   从你熟悉的前端栈（如 **TypeScript + 现代框架**）对接本仓库后端；完成鉴权、部署与基本监控的一条龙演练。

## 本仓库用途

记录笔记、命令清单、Demo 模块与阶段总结；对外可作为个人转型时间线与复习索引。

---

名称「java-frontend-top-path」表示：**从前端视角出发，通向 Java 全栈的顶层路径**（非官方排名，仅为自训路线标签）。
