# Job 09 — Redis 与缓存

承接 [job-08.md](job-08.md)：用 **Redis** 做热点读缓存，理解 **穿透 / 击穿 / 雪崩** 名词即可，本 Job 先跑通 **Cache-Aside**。

## 目标

- **Spring Data Redis** 或 **Spring Cache abstraction**（`@Cacheable`）二选一走通。
- 同一查询 **第二次命中缓存**（日志或断点可证）。
- 配置 **TTL**（time-to-live）或注解级过期策略（按你选型）。

## 阅读与链接

- [ ] [Caching with Spring Boot](https://spring.io/guides/gs/caching/)
- [ ] [Spring Data Redis](https://spring.io/projects/spring-data-redis)

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| Redis | **内存级全局 store**（多实例共享）；类似 **SWR/React Query 的服务端版**，但键空间是共享的、要设计 key 规范。 |
| `@Cacheable` | **memo 化**：同参数重复算/查 DB 时直接返回缓存结果。 |
| TTL | **staleTime / gcTime** 思路：过期后要回源或重建。 |

## 任务

- [ ] Docker 起 **Redis**：`docker run -p 6379:6379 -d redis:7`。
- [ ] Spring 配置 **`spring.data.redis.*`**，打通 **PING** 或读写 **String**。
- [ ] 对 Job 07 某只读接口加 **缓存**，压测或连续请求观察 **DB 日志次数下降**（无日志则用计数器/调试）。
- [ ] 笔记：一句卡点 + 结论。

## 命令速查

```bash
docker run -p 6379:6379 -d redis:7-alpine
redis-cli ping
```

## 排错

- **连不上 Redis**：检查 **bind**、密码、`application` 里 host 是否写 **`localhost`**（容器网络另论）。
- **缓存永远不中**：检查 **key 生成**、方法是否 **同类自调用** 绕过代理。

**上一项**：[job-08.md](job-08.md)　**下一项**：[job-10.md](job-10.md)　**索引**：[jobs.md](jobs.md)
