# Job 05 — 校验与统一异常

承接 [job-04.md](job-04.md)：API 要有 **入参校验** 和 **一致的错误响应**，前端才能稳定处理。

## 目标

- **`jakarta.validation`**：`@Valid`、`@NotNull`、`@Size` 等用在 DTO 或方法参数上。
- **`@ControllerAdvice` + `@ExceptionHandler`**：把校验失败、业务异常映射为统一 JSON（可选对齐 **Problem Details** `application/problem+json`）。
- 理解 **400**（参数错）与 **500**（未捕获）的分工。

## 阅读与链接

- [ ] [Validation with Spring Boot](https://spring.io/guides/gs/validating-form-input/)（原理可迁移到 REST body）
- [ ] [Spring MVC Exception Handling](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-exceptionhandler.html)

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| Bean Validation | **Zod / Yup / valibot** 在入参上校验；失败时返回结构化错误列表给表单。 |
| `@ControllerAdvice` | **Axios 响应拦截器** 或 **全局 `onError`**：把各种后端错误统一成 UI 可消费的 shape。 |

## 任务

- [ ] 在 Job 04 工程上增加 **`spring-boot-starter-validation`**。
- [ ] 定义 POST body DTO，字段加约束；故意传非法 body，应得到 **400** + 你定义的错误 JSON。
- [ ] 抛出自定义 **`RuntimeException`**（或业务异常类），被统一处理为 **4xx/5xx** + 固定字段（如 `code`, `message`）。
- [ ] 笔记：一句卡点 + 结论。

## 命令速查

```bash
curl -s -X POST http://localhost:8080/api/... -H 'Content-Type: application/json' -d '{}'
```

## 排错

- **校验不生效**：确认方法参数上有 **`@Valid`**，且 DTO 字段上有注解。
- **返回 HTML 错误页**：请求头加 **`Accept: application/json`**，或全局配置 MVC 默认 JSON 错误。

**上一项**：[job-04.md](job-04.md)　**下一项**：[job-06.md](job-06.md)　**索引**：[jobs.md](jobs.md)
