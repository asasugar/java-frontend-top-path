# Job 11 — 消息队列（Kafka 或 RabbitMQ）

承接 [job-10.md](job-10.md)：**异步解耦** 下单/通知类场景；先精通 **一种** 中间件即可。

## 目标

- **本地**跑起 **Kafka** 或 **RabbitMQ**（Docker Compose 推荐）。
- Spring **发一条消息**、**消费一条消息**，日志可证。
- 口述：**至少一次 vs 至多一次** 与「消费者要做 **幂等**」的关系。

## 阅读与链接

- [Kafka] [Spring for Apache Kafka](https://spring.io/projects/spring-kafka)
- [RabbitMQ] [Spring AMQP](https://spring.io/projects/spring-amqp)
- [ ] 任选官方 **Getting Started** 一篇跟完骨架

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| Topic / Queue | **事件总线** + **持久化队列**：发布者不关心谁消费，像 **window.postMessage** 但可落盘、可重试。 |
| Consumer 组 | **多个 worker 抢同一队列**：分区/竞争消费，扩展吞吐。 |
| 幂等消费 | **Webhook 重复投递**：用 **业务 id** 去重，类似前端 **request dedupe**。 |

## 任务

- [ ] Compose 或单容器启动所选中间件。
- [ ] 应用内 **Producer** 在 REST 或定时任务里 **send** 一条。
- [ ] **@KafkaListener** 或 **@RabbitListener** 打印 payload。
- [ ] 笔记：一句卡点 + 结论。

## 命令速查

```bash
docker compose up -d
mvn -q spring-boot:run
```

## 排错

- **连不上 broker**：检查 **advertised listeners**（Kafka）或 **5672/15672**（Rabbit）。
- **重复消费**：设计 **幂等键**；本 Job 先观察现象再记结论。

**上一项**：[job-10.md](job-10.md)　**下一项**：[job-12.md](job-12.md)　**索引**：[jobs.md](jobs.md)
