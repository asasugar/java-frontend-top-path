# Job 02 — 集合、Stream、异常与线程池

承接 [job-01.md](job-01.md)：在能编译运行单类程序的基础上，补齐**日常写业务最常见的语言块**，为后续 Spring / 多线程 Web 打底。示例工程：`job02/`。

## 目标（做完能口述）

- `List` / `Map` 各在什么场景用；**不要**在并发下随便共享可变 `ArrayList` 乱改。
- **Stream** 做一条「读内存集合 → 过滤/映射 → 聚合」的流水线，和前端 `filter` / `map` / `reduce` 的对应关系。
- **try-with-resources** 自动关闭资源；和前端「记得 close」、`using` 类语法接近。
- **线程池**：`ExecutorService` 提交任务、`shutdown`；知道 **`Future.get` 会阻塞**。

## 1. 阅读（约 40～60 分钟，可拆两天）

- [ ] 集合框架概览：`Collection`、`List`、`Set`、`Map`（官方 [Collections](https://docs.oracle.com/javase/tutorial/collections/intro/index.html) 入门段）。
- [ ] Stream：[Aggregate Operations](https://docs.oracle.com/javase/tutorial/collections/streams/index.html) 前半（pipeline、中间操作与终端操作）。
- [ ] 异常：[The try-with-resources Statement](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)。
- [ ] 并发入门：[Executor 接口](https://docs.oracle.com/javase/tutorial/essential/concurrency/exinter.html)、[线程池](https://docs.oracle.com/javase/tutorial/essential/concurrency/threadpools.html)（读到能提交任务、`shutdown` 即可）。

### 阅读 × 前端类比

| 正在读的概念 | 前端可类比 |
|--------------|------------|
| 集合 `List` / `Set` / `Map` | **`Array`**、**`Set`**、**`Map`**（ES6）；`List` 有序可重复，接近「动态数组」；业务里「键值查找」优先想 `Map`，不要到处 `find`。 |
| Stream 流水线 | **`arr.filter().map().reduce()`** 或 **Lodash chain**；中间操作惰性、终端操作触发计算，类似「链式调用到最后一步才真正跑」。 |
| try-with-resources | **`try { const r = await open(); } finally { await r?.close() }`**；或 **`useEffect` 里 return 清理函数**；保证 Reader/Stream 类资源退出作用域时关掉。 |
| Executor / 线程池 | 浏览器里 **Web Worker 池**（要自己管队列）或 Node **worker_threads**；**不是**微任务里的 `Promise`——线程池里是**真并行线程**（CPU 可占满），`submit` 像「把任务丢进池子排队」。 |
| `Future` 与 `get()` | 像 **`Promise` 但必须 `.get()` 才拿到结果**，且 **`get()` 会阻塞当前线程**；对应前端心智：**`await` 在 async 里不阻塞整个进程，但这里阻塞的是当前 Java 线程**。 |
| `shutdown` | 类似 **Worker 池要 terminate**、或 **关闭 HTTP server**；不关池子 JVM 可能一直不退出。 |

## 2. 跑通示例 `Job02App`

- [ ] 在仓库根目录进入 `job02`，执行 `mvn -q compile`、`mvn -q exec:java`（或 `-Dexec.mainClass=learning.job02.Job02App`）。
- [ ] 读 `Job02App` 源码：哪一段是 **try-with-resources**、哪一段是 **Stream**、哪一段是 **线程池**。

## 3. 必做改动（自己动手）

- [ ] 编辑 `job02/data/input.txt`，增加或删减行，重新运行，确认 **distinct 统计** 随数据变化。
- [ ] 在线程池上再 **`submit` 第三个任务**（例如返回 `lines.size()` 或其它计算），主线程用 **`Future.get`** 打印结果（体会多任务与阻塞）。

## 4. 收尾

- [ ] Git 提交；笔记写一句：**Job 02 最大卡点 + 结论**。

## 命令速查

```bash
cd job02
mvn -q compile
mvn -q exec:java -Dexec.mainClass="learning.job02.Job02App"
```

## 不求甚解（留给 Job 03 及以后）

- `ForkJoinPool` 与并行 Stream 的坑、线程安全集合细分、`CompletableFuture` 组合、内存模型与 `happens-before`。

## 排错

- **`distinct()`**：按 **`equals` 判重**（`String` 即内容相同且大小写一致）；只保留每种值的第一次。前面若 **`map` 成小写**，去重是按**小写后的串**算的，`Apple` 与 `apple` 会合并成一条。
- **`data/input.txt` 找不到**：必须在 **`job02` 目录下**执行 `mvn exec:java`（工作目录为模块根）；或改成绝对路径 / 从 `user.dir` 拼路径并自查 `System.getProperty("user.dir")`。
- **线程池不结束**：忘记 `shutdown` 会让 JVM 挂住；示例已在 `finally` 里关闭，改代码时注意保留。

**上一项**：[job-01.md](job-01.md)　**索引**：[jobs.md](jobs.md)
