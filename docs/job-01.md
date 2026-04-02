# Job 01 — `job01` 入门

对应总表「Java 语言与 JVM 初印象」：**工具链 + 小程序（读写文件、HTTP）+ 字节码 / classpath / jar 概念**，以下任务**按序做完**即可。示例工程目录：`job01/`。

## 1. 环境

- [ ] 安装 **JDK 17**（或 README 约定 LTS），`java -version`、`javac -version` 与工程一致（本模块 `job01` 为 **17**）。
- [ ] 安装 **Maven 3.8+**，`mvn -version` 与 `JAVA_HOME` 正确（[env-install-cli.md](env-install-cli.md)）。
- [ ] IDE 打开 `job01`，能运行主类 `learning.job01.Job01App`。

## 2. 源码 → 字节码 → 运行

- [ ] 阅读约各 20 分钟：官方教程 **Hello World** + **OOP Concepts**（或中文等价）；完整链接见下文「推荐阅读」。
- [ ] 运行 `Job01App`，确认有输出。
- [ ] `mvn -q compile`，查看 `target/classes` 下 **`.class`**，能说明 **`.java` → 字节码 → JVM 执行**。

## 3. 文件读写（NIO.2）

- [ ] 读 `Paths` / `Files` 相关代码，**改一处**：例如写到 `job01/data/`（`Files.createDirectories`）。
- [ ] 运行后核对磁盘文件与输出一致。

## 4. HTTP 客户端

- [ ] 读 `HttpClient` 部分；URL 换成**另一个**公开 JSON GET，仍能跑通。
- [ ] 能说明：**JDK `HttpClient`**；JSON 用 **Jackson**（Maven），类比前端 `JSON.parse`。

## 5. classpath 与 JAR

- [ ] 口述 **classpath** 含义；`mvn -q package` 后执行：
  `java -cp target/job01-1.0.0-SNAPSHOT.jar learning.job01.Job01App`
  主类为 **`learning.job01.Job01App`**，勿用 `groupId`。若缺 Jackson 等类 = 普通 jar 未打依赖，**预期现象**；平时用 `mvn -q exec:java`。

## 6. 收尾

- [ ] Git 提交；写一句 **最大卡点 + 结论**（笔记即可）。

## 命令速查

```bash
cd job01
mvn -q compile
mvn -q exec:java -Dexec.mainClass="learning.job01.Job01App"
mvn -q package
```

编译报「源选项 5」：确认 `pom.xml` 含 `maven-compiler-plugin` + `release 17`。

## 推荐阅读（官方链接）

| 顺序 | 主题 | 链接 |
|------|------|------|
| 1 | Java 教程总入口 | https://docs.oracle.com/javase/tutorial/ |
| 2 | 入门：Hello World | https://docs.oracle.com/javase/tutorial/getStarted/index.html |
| 3 | OOP 概念 | https://docs.oracle.com/javase/tutorial/java/concepts/index.html |
| 4 | 语言基础 | https://docs.oracle.com/javase/tutorial/java/nutsandbolts/index.html |
| 5 | 异常 | https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html |
| 6 | 基本 I/O | https://docs.oracle.com/javase/tutorial/essential/io/fileio.html |
| 7 | HTTP Client | https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpClient.html |
| 8 | Maven 入门 | https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html |

阅读节奏：**2～4** 配合第 2 节；**6** 配合第 3 节；**7** 配合第 4 节；**8** 在 `mvn compile` 通过后扫一遍。

### 阅读 × 前端类比

| 推荐阅读条目 | 前端可类比 |
|--------------|------------|
| 入门 Hello World | 第一个可执行入口：`node app.js`、Vite/React 的 `main` 挂载点；`main` 是程序起点。 |
| OOP 概念 | ES class / TS `class`：对象、引用、封装；**接口**更像「只约定形状、不管实现」的契约（接近 TS `interface`，但 Java 里实现关系更硬）。 |
| 语言基础 | 变量、分支、循环与 JS 同构；**类型**写在源码里且编译期检查，接近 **严格 TS**，错误更早暴露。 |
| 异常 | `try / catch / finally` 同形；差异是 Java 有 **checked exception**（调用方被迫处理或声明），前端习惯里更接近「要么 catch 要么让 Promise reject」。 |
| 基本 I/O | Node **`fs`** / **`fs.promises`** 读写文件；`Path`、`Files` 对应路径拼接与安全读写。 |
| HTTP Client | **`fetch`** / **axios**：发 GET、拿 body 字符串；`send` 是**阻塞**调用，像 **`await fetch` 写在 async 里**，但当前线程会卡住直到返回。 |
| Maven 入门 | **`npm install` + `package.json` + `node_modules`**：`pom.xml` 定依赖与插件，`~/.m2/repository` 像全局缓存的依赖目录；`mvn compile` ≈ 构建产物，**fat jar** 以后再对标「单文件 bundle」。 |
| （ bytecode，配合第 2 节任务） | **TypeScript → JS** 或 **源码 → 打包产物**：`.java` → `.class` 给 JVM 跑；改代码要 **重新编译** 才进 `target/classes`。 |

## 不求甚解（以后再啃）

泛型擦除、字节码细节、GC 调参、`module-info.java`。下一阶段见 [transition-plan.md](transition-plan.md)（并发、fat jar、Spring 等）。

## 排错与可选加练

- **Java Runtime**：shell 未加载 SDKMAN → `source "$HOME/.sdkman/bin/sdkman-init.sh"`，并写入 `~/.zshrc`。
- **mvn 非 JDK 17**：核对 `JAVA_HOME` 与 `mvn -version`。
- **首编译慢**：拉依赖至 `~/.m2/repository`；内网配镜像。
- **HTTP 失败**：代理 / 证书；换可浏览器访问的 JSON URL。
- **加练**：`javap -c -p target/classes/learning/job01/Job01App.class`；`jshell` 试两行代码后 `/exit`。

**与前端差异（一句）**：Java **静态类型 + 编译期检查**，改签名问题多在编译阶段暴露。

**下一项**：[job-02.md](job-02.md)　**索引**：[jobs.md](jobs.md)（共 job-01～job-16）
