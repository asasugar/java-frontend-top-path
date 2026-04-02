# Job 10 — Spring Security 与 JWT

承接 [job-09.md](job-09.md)：保护 API，完成 **认证 → 鉴权** 最小闭环，便于 SPA **Bearer Token** 联调。

## 目标

- **Spring Security 6** 过滤器链基本概念。
- **JWT 资源服务**形态：`Authorization: Bearer <token>` 校验（可用 **spring-security-oauth2-resource-server** 或团队常用库）。
- **放行**：`/v3/api-docs/**`、`/swagger-ui/**`、登录接口（若有）；其余需认证。
- **CORS**：允许前端开发域 **`http://localhost:5173`**（或你的端口）。

## 阅读与链接

- [ ] [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)（Servlet 部分浏览）
- [ ] [OAuth2 Resource Server JWT](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html)

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| SecurityFilterChain | **Express 中间件链**：请求依次穿过鉴权、日志等。 |
| JWT | **无状态票**：服务端验签即可；refresh 流程类似 **silent refresh token**。 |
| CORS | 浏览器 **跨域策略**；后端必须显式允许 **Origin** 与 **Authorization** 头。 |

## 任务

- [ ] 实现 **登录或签发 token** 的最小路径（可硬编码用户、也可用内存用户，以搞清流程为先）。
- [ ] 保护至少一个 **GET** 接口：**无 token 401**，有 token **200**。
- [ ] 用 curl 带 **`-H "Authorization: Bearer ..."`** 验证；再用前端 **fetch** 试一次。
- [ ] 笔记：一句卡点 + 结论。

## 排错

- **403 vs 401**：未登录/无凭证多为 **401**；已登录无权限多为 **403**（团队规范可能不同，要统一）。
- **OPTIONS 预检失败**：CORS 配置 **allowedMethods** 含 **OPTIONS**，**allowCredentials** 与 `*` 规则搞清楚。

**上一项**：[job-09.md](job-09.md)　**下一项**：[job-11.md](job-11.md)　**索引**：[jobs.md](jobs.md)
