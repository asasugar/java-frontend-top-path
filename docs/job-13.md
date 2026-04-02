# Job 13 — Docker 与 Compose

承接 [job-12.md](job-12.md)：应用 **可镜像交付**，本地 **一条命令** 起 **App + DB（+ Redis）**。

## 目标

- 编写 **`Dockerfile`**（多阶段构建可选）：产出可运行 **fat jar** 的镜像。
- **`docker-compose.yml`**：`app` 依赖 `postgres`、`redis`，网络互通。
- 健康检查：容器 **`depends_on` + condition** 或应用内 **Actuator**（与 Job 14 可合并迭代）。

## 阅读与链接

- [ ] [Spring Boot — Container Images](https://docs.spring.io/spring-boot/reference/packaging/container-images/dockerfiles.html)
- [ ] [Docker Compose Specification](https://docs.docker.com/compose/compose-file/)

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| Dockerfile | **把构建 + 运行环境钉死**：像 **CI 里同版本 Node** 打镜像，避免「我本地能跑」。 |
| Compose | **`docker-compose up` = 一键起全套 mock 后端**：对齐 **devcontainer** 多服务编排思路。 |

## 任务

- [ ] `docker build` 成功，`docker run` 能访问 **8080** 与接口。
- [ ] Compose **同时起** 数据库与 App，**数据源 URL** 指向 **服务名**（如 `jdbc:postgresql://postgres:5432/...`）。
- [ ] 在 README 写 **`docker compose up --build`** 步骤。
- [ ] 笔记：一句卡点 + 结论。

## 命令速查

```bash
docker build -t myapp:local .
docker compose up --build
```

## 排错

- **连不上 DB**：检查 **同一 network**、**端口映射**、**环境变量** 是否注入容器。
- **镜像过大**：用 **多阶段** 只带 JRE + jar。

**上一项**：[job-12.md](job-12.md)　**下一项**：[job-14.md](job-14.md)　**索引**：[jobs.md](jobs.md)
