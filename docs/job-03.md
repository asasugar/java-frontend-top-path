# Job 03 — Maven 多模块与依赖范围

承接 [job-02.md](job-02.md)：在单模块 `job01`/`job02` 之上，掌握**企业项目常见的父子工程**，为后续独立 `api` / `service` 模块铺路。

## 目标

- 父 POM **`packaging` 为 `pom`**，用 **`<modules>`** 聚合子模块。
- 理解 **`dependencyManagement`** 与 **子模块不写版本** 的协作方式。
- 能选对 **`compile` / `provided` / `runtime` / `test`**（何时进最终包、何时仅编译期需要）。

## 阅读与链接

- [ ] [Maven 多模块](https://maven.apache.org/guides/mini/guide-multiple-modules.html)
- [ ] [Introduction to the Dependency Mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)（Scopes 一节）
- [ ] [Optional] [BOM / import scope](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html) 浏览

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| 父 POM / 聚合 | **monorepo 根 `package.json` workspaces**：根不产可运行包，只锁版本、统管子包。 |
| `dependencyManagement` | 根里 **统一依赖版本**，子模块像 `workspace:*` 只引用名字、版本继承。 |
| `compile` / `runtime` / `test` | **dependencies vs devDependencies**；`provided` 接近「运行环境已带、打包不打进 bundle」（如容器里的 JDBC 驱动由平台提供时）。 |

## 任务

- [ ] 在本仓库新建目录（例如 `job03-parent/`），包含 **父 `pom.xml`** + **至少两个子模块**（如 `job03-api` 空 jar、`job03-core` 依赖 api）。
- [ ] 在父 POM 声明 **`dependencyManagement`**（任选一个常用库统一版本），子模块引用时不写版本号。
- [ ] 故意在一个子模块把某依赖设为 **`test` scope**，在另一个子模块 **`compile`**，对比 `mvn dependency:tree` 输出差异。
- [ ] 根目录执行 **`mvn -q clean install`** 能全量通过。
- [ ] 笔记：一句卡点 + 结论。

## 命令速查

```bash
cd job03-parent
mvn -q clean install
mvn -q dependency:tree -pl job03-core
```

## 排错

- **子模块找不到父 POM**：检查父 POM 的 `relativePath` 与目录层级。
- **版本冲突**：用 `dependency:tree` 看传递依赖，必要时在父 `dependencyManagement` 里 **显式对齐版本**。

**上一项**：[job-02.md](job-02.md)　**下一项**：[job-04.md](job-04.md)　**索引**：[jobs.md](jobs.md)
