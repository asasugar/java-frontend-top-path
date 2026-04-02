# Job 07 — JPA、数据库与 Flyway

承接 [job-06.md](job-06.md)：把数据**落库**，用 **迁移脚本** 管理表结构，避免「人手改库」。

## 目标

- **Spring Data JPA**：`Entity`、`CrudRepository` 或 `JpaRepository`。
- **PostgreSQL 或 MySQL**（本地 Docker 或托管二选一）；**不要用仅内存库糊弄生产形态**（H2 仅作单元测可选）。
- **Flyway**：`V1__xxx.sql` 建表；应用启动自动迁移。
- 实现 **CRUD 一条业务**（如「物品」增删改查）经 REST 暴露。

## 阅读与链接

- [ ] [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [ ] [Flyway — Getting Started](https://documentation.red-gate.com/flyway/getting-started/concepts/migrations)
- [ ] [Spring Boot — Data JPA](https://docs.spring.io/spring-boot/reference/data/sql.html#data.sql.jpa-and-spring-data)

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| Entity | **DB 行** 的对象视图；不要直接当 API 返回给前端时要想是否脱敏。 |
| Repository | **DAO / Serverless 里的 DB client**：封装 CRUD，Controller 不手写 SQL 字符串。 |
| Flyway 迁移 | **Prisma migrate / Drizzle migrations**：版本化 schema，可回放、可审查。 |

## 任务

- [ ] Docker 或本地安装 **Postgres/MySQL**，配置 **`spring.datasource.*`**。
- [ ] 写 **Flyway** 脚本创建至少一张表。
- [ ] **Entity** 与表映射，**Repository** + **Service** + **Controller** 打通一条链路。
- [ ] 用 curl/Bruno 验证 **POST 后 GET 能查到**。
- [ ] 笔记：一句卡点 + 结论。

## 命令速查

```bash
docker run -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:16
mvn -q spring-boot:run
```

## 排错

- **Flyway 校验失败**：不要手改已执行过的迁移文件；新增 **`V2__`**。
- **时区 / 时间类型**：统一用 **`Instant` 或 `OffsetDateTime`** 意识，避免前后端各说各话。

**上一项**：[job-06.md](job-06.md)　**下一项**：[job-08.md](job-08.md)　**索引**：[jobs.md](jobs.md)
