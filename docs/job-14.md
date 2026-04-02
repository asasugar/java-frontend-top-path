# Job 14 — 可观测：Actuator、日志与指标

承接 [job-13.md](job-13.md)：生产要能 **看活没活、慢不慢、错在哪**。

## 目标

- **`spring-boot-starter-actuator`**：暴露 **`/actuator/health`**（Compose / K8s 探针会用）。
- **Micrometer + Prometheus** 端点（`**/actuator/prometheus**`）或团队统一方案；至少能在本地 **curl 看到指标文本**。
- **结构化日志**：JSON 或 **key=value** 一致字段（`traceId` 占位即可）；理解 **日志级别**。

## 阅读与链接

- [ ] [Spring Boot Actuator](https://docs.spring.io/spring-boot/reference/actuator/index.html)
- [ ] [Micrometer Prometheus](https://docs.spring.io/spring-boot/reference/actuator/metrics.html#actuator.metrics.export.prometheus)

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| `/actuator/health` | **前端健康页 / status API**：LB 用来摘流量。 |
| Prometheus 指标 | **Web Vitals / RUM 上报**：RED（rate、errors、duration）看接口 SLO。 |
| traceId | **Sentry 的 event id / correlation id**：串联网关→服务→DB 日志。 |

## 任务

- [ ] 打开 **health**；配置 **management.endpoints** 暴露所需端点（注意安全，生产常需认证或网络隔离）。
- [ ] 拉通 **Prometheus 格式** 指标一页。
- [ ] 打一条带 **MDC** 或统一前缀的日志，口述如何与网关 **trace header** 对接（可先不写代码）。
- [ ] 笔记：一句卡点 + 结论。

## 排错

- **端点 404**：**`management.endpoints.web.exposure.include`** 未包含。
- **敏感信息泄露**：**`show-details`**、**heapdump** 等勿对公网开放。

**上一项**：[job-13.md](job-13.md)　**下一项**：[job-15.md](job-15.md)　**索引**：[jobs.md](jobs.md)
