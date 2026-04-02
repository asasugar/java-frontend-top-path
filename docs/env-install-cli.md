# 命令行安装：JDK 17.0.2 + Maven 3.8.6（Spring Boot 见文末）

目标版本：**OpenJDK 17.0.2**、**Apache Maven 3.8.6**。系统以 **macOS** 为主；Linux 可用同款 **SDKMAN** 流程。

---

## 方式 A：SDKMAN（推荐，便于锁小版本）

### 1. 安装 SDKMAN

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

### 2. 安装 JDK 17.0.2

先查看候选标识（不同发行版后缀不同）：

```bash
sdk list java
```

常见可选其一（以你本机 `sdk list` 输出为准）：

```bash
sdk install java 17.0.2-tem
```

若列表里没有 17.0.2，可用当前渠道里 **17.0.x 的 Temurin**，或改用下方「方式 B」 tarball 精确安装 17.0.2。

```bash
sdk default java 17.0.2-tem
java -version
```

### 3. 安装 Maven 3.8.6

```bash
sdk install maven 3.8.6
sdk default maven 3.8.6
mvn -version
```

确认 `mvn -version` 中 **Java version** 为 **17**（与 `java -version` 一致）。

---

## 方式 B：手动 tarball（严格 17.0.2）

### JDK（Eclipse Temurin 17.0.2）

1. 打开 [Adoptium Temurin 17 归档](https://adoptium.net/temurin/releases/?version=17)，选择 **17.0.2**、**macOS**、**aarch64 或 x64**、**JDK**、**.tar.gz**。
2. 解压并放到例如 `~/sdk/jdk-17.0.2+8`（目录名以实际为准）。

```bash
export JAVA_HOME="$HOME/sdk/jdk-17.0.2+8/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"
java -version
```

Intel Mac 下 `JAVA_HOME` 多为 `.../jdk-17.0.2+8/Contents/Home`；若解压后无 `Contents/Home`，以解压包内实际 `bin/java` 所在目录的上一级为准。

把 `export JAVA_HOME` / `PATH` 写入 `~/.zshrc` 或 `~/.bash_profile` 后执行 `source`。

### Maven 3.8.6

```bash
cd ~/sdk
curl -LO https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
tar xzf apache-maven-3.8.6-bin.tar.gz
export PATH="$HOME/sdk/apache-maven-3.8.6/bin:$PATH"
mvn -version
```

将 `PATH` 追加写入 shell 配置文件。

---

## Spring Boot 3.4.7 / Spring 6.2.7

**不需要**在系统里单独安装 Spring Boot。新建或修改 Maven 工程，使用官方父 POM 即可锁定 **Spring Boot 3.4.7**（其 BOM 会管理 **Spring Framework** 等版本，一般无需手写 6.2.7）：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.4.7</version>
    <relativePath/>
</parent>
```

命令行生成骨架（需已安装 [Spring Boot CLI](https://docs.spring.io/spring-boot/installing.html#getting-started.installing.cli) 时才可用；多数团队直接用 [start.spring.io](https://start.spring.io) 下载或手写 `pom.xml`）：

```bash
# 若已安装 spring 命令
spring init --build=maven --java-version=17 --boot-version=3.4.7 demo
```

未装 CLI 时：浏览器打开 start.spring.io，选 Maven、Java 17、Spring Boot **3.4.7**，生成并解压后用 `mvn -q spring-boot:run` 验证。

---

## 验收

```bash
java -version
javac -version
mvn -version
```

期望：`java` 为 **17.0.2**（或你刻意选用的 17 发行版）、`mvn` 为 **3.8.6**，且 Maven 输出的 Java 与 `java -version` 一致。
