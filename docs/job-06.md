# Job 06 — OpenAPI（springdoc）

承接 [job-05.md](job-05.md)：让 **契约可浏览、可导出**，前端用 **Orval / openapi-typescript** 生成类型与请求代码。

## 目标

- 集成 **springdoc-openapi**（与 Boot 3 / Jakarta 匹配的 starter）。
- 浏览器打开 **Swagger UI**，能调通已有接口。
- 导出 **`/v3/api-docs`** JSON，在前端项目里试跑 **一次代码生成**（或仅记录命令）。

## 阅读与链接

- [ ] [springdoc-openapi 文档](https://springdoc.org/)
- [ ] [OpenAPI 3 规范概览](https://swagger.io/specification/)（浏览结构：paths、components.schemas）

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| OpenAPI 文档 | **TypeScript 类型 + API 清单**：单源真相；前后端对齐字段名与必填。 |
| Swagger UI | **Postman 集合可视化** + 内嵌试跑；比散落 curl 更易协作。 |

## 任务

- [ ] 引入 **springdoc-openapi-starter-webmvc-ui**（版本与 Boot 3 兼容）。
- [ ] 为 Controller 或 DTO 补充 **@Tag / @Operation**（可选）使文档可读。
- [ ] 保存一份导出的 **`openapi.json`** 到仓库 `docs/` 或前端仓库。
- [ ] 笔记：一句卡点 + 结论。

## 命令速查

```bash
curl -s http://localhost:8080/v3/api-docs | head
```

## 排错

- **404 /v3/api-docs**：检查 starter 与 **Spring MVC** 是否匹配；是否用了 **WebFlux** 却引了 webmvc starter。
- **与 Spring Security 冲突**：为 `/v3/api-docs/**`、`/swagger-ui/**` 放行（Job 10 会系统做）。

**上一项**：[job-05.md](job-05.md)　**下一项**：[job-07.md](job-07.md)　**索引**：[jobs.md](jobs.md)
