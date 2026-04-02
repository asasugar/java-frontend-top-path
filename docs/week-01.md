# 第 1 周学习 Job

对应总表「Java 语言与 JVM 初印象」的**前半**：工具链就绪 + 小程序能跑通文件与 HTTP。第 2 周再补并发初识与 jar/classpath 实操（总表 1～2 周合并完成亦可）。

## 环境验收（Job 0）

- [ ] 安装 **JDK 17**（或 README 约定的 LTS），终端执行：`java -version`、`javac -version`，主版本与工程一致（本仓库 `week01` 使用 **17**）。
- [ ] 安装 **Maven 3.8+**：`mvn -version`，能解析 `JAVA_HOME`（安装说明见 [env-install-cli.md](env-install-cli.md)）。
- [ ] IDE 任选（IDEA / VS Code + Extension Pack for Java），打开本仓库下的 `week01` 目录，能定位到主类并运行。

## Job 1：搞清「源码 → 字节码 → 运行」

- [ ] 阅读（各约 20 分钟）：Oracle/Java 官方教程 *Trail: Getting Started* 中 **Hello World** 与 **Object-Oriented Programming Concepts** 章节（或任意中文等价入门里对应两节）。
- [ ] 在 `week01` 里找到 `Week01App`，用 IDE 或命令行运行；确认控制台有输出。
- [ ] 在 `week01` 目录执行：`mvn -q compile`，查看生成目录 `target/classes` 下的 **`.class` 文件**，能口述：**`.java` 编译成字节码 `.class`，由 JVM 加载执行**。

## Job 2：文件读写（NIO.2）

- [ ] 读完 `Week01App` 中与 `Paths`、`Files` 相关的代码，自己改一处：例如把写入目录从系统临时目录改到 `week01/data/`（需自行创建目录或使用 `Files.createDirectories`）。
- [ ] 运行后检查磁盘上文件内容与程序打印一致。

## Job 3：HTTP 客户端（对齐前端的 fetch）

- [ ] 读完 `Week01App` 中 `HttpClient` 部分；将 URL 换成 `https://jsonplaceholder.typicode.com/posts/1` 以外的**另一个**公开 GET（返回 JSON 即可），重新运行。
- [ ] 能说明：**JDK 自带 `java.net.http.HttpClient`**；JSON 解析本示例使用 **Jackson**（Maven 依赖），类似前端 `JSON.parse` + 类型，只是放在服务端。

## Job 4：classpath 与 JAR（概念 + 一条命令）

- [ ] 口述：**classpath** 是 JVM 找 **`.class` 与资源** 的路径集合；Maven 把依赖 jar 放在本地仓库，编译/运行时由插件帮你拼 classpath。
- [ ] 执行：`mvn -q package`，得到 `target/week01-1.0.0-SNAPSHOT.jar`；执行：
  `java -cp target/week01-1.0.0-SNAPSHOT.jar learning.week01.Week01App`
  若报缺依赖，对比「非 fat jar 不含传递依赖」——**第 3～4 周**会系统学 Maven 与可执行 fat jar；本周只需观察现象并记下问题。

## Job 5：本周收尾

- [ ] Git 提交本周改动（含 `docs/week-01.md` 勾选进度可在本地笔记记录，不必强行提交勾选状态）。
- [ ] 写一句：**本周最大卡点 + 结论**（可写在个人笔记或本文件末尾）。

## 命令速查

```bash
cd week01
mvn -q compile
mvn -q exec:java -Dexec.mainClass="learning.week01.Week01App"
mvn -q package
```

若未装 `exec-maven-plugin`，可直接在 IDE 运行 `Week01App`，或用 `java -cp ...` 指向 `target/classes` 加上依赖 classpath（略繁琐，IDE 更简单）。

---

## 推荐阅读（官方链接）

| 顺序 | 主题 | 链接 |
|------|------|------|
| 1 | Java 教程总入口（选 JDK 对应版本文档亦可） | https://docs.oracle.com/javase/tutorial/ |
| 2 | 入门：环境、Hello World | https://docs.oracle.com/javase/tutorial/getStarted/index.html |
| 3 | 面向对象核心概念（类、对象、继承等） | https://docs.oracle.com/javase/tutorial/java/concepts/index.html |
| 4 | 语言基础（变量、运算符、控制流、数组） | https://docs.oracle.com/javase/tutorial/java/nutsandbolts/index.html |
| 5 | 异常 | https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html |
| 6 | 基本 I/O（与 `Files` / `Path` 衔接） | https://docs.oracle.com/javase/tutorial/essential/io/fileio.html |
| 7 | HTTP Client（JDK 自带） | https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpClient.html |
| 8 | Maven 五分钟 / 入门指南 | https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html |

阅读节奏：先完成 **2 → 4** 覆盖 Job 1；**6** 对照 Job 2；**7** 对照 Job 3；**8** 在跑通 `mvn compile` 后扫一遍即可。

---

## 本周不求甚解清单（留给第 2 周及以后）

- 泛型擦除、字节码指令细节
- GC 算法与调参
- `module-info.java`（JPMS）

第 2 周建议：线程与 `j.u.concurrent` 入门、`jar` manifest 与 Spring 前置的注解初识（见届时 `docs/week-02.md`，若未创建可先按总表自学）。

---

## 补充：排错与可选加练

**环境**

- `mvn` 用的不是 JDK 21：检查 `echo $JAVA_HOME`、`mvn -version` 里的 Java version，与 `java -version` 对齐后再跑 `week01`。
- 首次 `mvn compile` 很慢：正在拉依赖进本地仓库 `~/.m2/repository`，属正常；仅在公司内网时需配置镜像（如阿里云 Maven 镜像）。

**网络**

- `HttpClient` 请求失败：排查代理、防火墙、HTTPS 证书；可临时换浏览器能打开的 JSON GET 地址做 Job 3。

**可选加练（各约 10 分钟）**

- 编译后对任一 `.class` 执行：`javap -c -p target/classes/learning/week01/Week01App.class`，粗看字节码长什么样即可。
- 终端输入 `jshell`，写两行整数运算与 `System.out.println`，体会「解释执行 / 快速试代码」；输入 `/exit` 退出。

**和前端习惯的差异（本周记住一句）**

- Java 是**静态类型 + 编译期检查**，改签名后「全红」多在编译阶段，而不是跑到一半才在控制台报错。
