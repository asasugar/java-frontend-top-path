# Job 08 — 事务：传播与隔离

承接 [job-07.md](job-07.md)：写业务必碰 **事务边界**，搞错会出现「一半成功一半失败」或「读脏数据」。

## 目标

- **`@Transactional`** 打在 **Service** 层方法上的习惯与原因。
- **`propagation = REQUIRED` vs `REQUIRES_NEW`** 各在什么场景用（口述即可，可配合最小 demo）。
- **隔离级别**四个名字能对应到「会读到什么并发现象」；默认在 Spring + JPA 下多为 **READ_COMMITTED**（依方言）。
- **只读事务** `@Transactional(readOnly = true)` 对查询路径的意义。

## 阅读与链接

- [ ] [Spring Transaction Management](https://docs.spring.io/spring-framework/reference/data-access/transaction.html)
- [ ] [JPA — Transactions](https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html)（与 Spring 集成关系）

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| 事务 | **数据库上的 saga 原子块**：要么全提交要么全回滚；不像前端 local state 可随时改一半。 |
| `REQUIRES_NEW` | **单独开子任务提交**：外层失败时子任务已提交的部分不回滚（慎用，要业务允许）。 |
| 隔离级别 | **多人同时改同一份文档** 时你看到的版本：能否读到别人未提交的修改等。 |

## 任务

- [ ] 在 Service 写两个方法：**A 调 B**，分别用默认 **`REQUIRED`** 与一次实验性 **`REQUIRES_NEW`**（或内层抛异常观察外层是否回滚），记录现象。
- [ ] 列表查询接口用 **`readOnly = true`**（若用 JPA）。
- [ ] 口述：**为什么事务一般不进 Controller**。
- [ ] 笔记：一句卡点 + 结论。

## 排错

- **自调用导致事务不生效**：同类内 `this.method()` 不走代理；抽 **另一 Bean** 或 **@Async** 等绕开（进阶再细究）。
- **懒加载 LazyInitializationException**：在事务外访问关联集合；用 **DTO 投影** 或在事务内取齐数据。

**上一项**：[job-07.md](job-07.md)　**下一项**：[job-09.md](job-09.md)　**索引**：[jobs.md](jobs.md)
