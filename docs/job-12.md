# Job 12 — 超时、重试与幂等

承接 [job-11.md](job-11.md)：对外部依赖 **不稳** 时有 **超时与重试**；写接口要有 **幂等** 意识。

## 目标

- **HTTP 客户端**（`RestClient` / `WebClient` / JDK `HttpClient`）配置 **connect/read timeout**。
- **重试**：**Resilience4j** 或 **Spring Retry**（`@Retryable`）任选，对**可重试错误**（如 503）演示一次。
- **幂等 POST**：用 **`Idempotency-Key`** 头或业务唯一键，口述如何避免 **双击提交扣款两次**。

## 阅读与链接

- [ ] [Resilience4j Spring Boot](https://resilience4j.readme.io/docs/getting-started-3)（或 Spring Retry 官方文档）
- [ ] [REST 设计 — 幂等性](https://restfulapi.net/idempotent-rest-apis/)（概念）

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| 超时 | **fetch AbortSignal.timeout**；避免请求挂死占满连接池。 |
| 重试 | **axios-retry** / **tanstack-query retry**：只对 **网络错/5xx** 退避重试，**4xx 业务错**别瞎重试。 |
| 幂等键 | **表单提交防重复**：按钮 disable + **服务端去重表** 双保险；移动端弱网重复请求同理。 |

## 任务

- [ ] 调一个 **故意慢或随机失败** 的 mock URL（如 httpbin delay），加上 **超时** 捕获异常。
- [ ] 加 **重试**（限制次数 + 退避可选），日志打印重试次数。
- [ ] 设计一个 **POST 接口** 的幂等策略（可只写文档 + 伪代码，或最小内存去重实现）。
- [ ] 笔记：一句卡点 + 结论。

## 排错

- **重试放大故障**：对 **非幂等写操作** 盲目重试会重复下单；必须 **幂等 + 重试安全**。
- **超时过短**：误杀正常慢查询；按 **P99** 设阈值。

**上一项**：[job-11.md](job-11.md)　**下一项**：[job-13.md](job-13.md)　**索引**：[jobs.md](jobs.md)
