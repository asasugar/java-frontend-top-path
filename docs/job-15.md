# Job 15 — Kubernetes 部署骨架

承接 [job-14.md](job-14.md)：把镜像丢进 **K8s**，理解 **Pod / Deployment / Service** 与 **探针**。

## 目标

- 本地 **minikube / kind / Docker Desktop K8s** 任选，**`kubectl apply`** 跑起来。
- **`Deployment`** + **`Service` (ClusterIP 或 NodePort)**，集群外或端口转发能访问 API。
- **`livenessProbe` / `readinessProbe`** 指向 **`/actuator/health`**（或拆 **liveness** 与 **readiness** 路径若你区分了）。

## 阅读与链接

- [ ] [Kubernetes Concepts](https://kubernetes.io/docs/concepts/)（Workload / Service 概览）
- [ ] [Configure Liveness Readiness Probes](https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes/)

### 阅读 × 前端类比

| 概念 | 前端可类比 |
|------|------------|
| Deployment | **无状态服务多副本**：类似 **Vercel 多 region 实例**，由控制面维持期望副本数。 |
| Service | **稳定 DNS + 负载均衡到 Pod**：像 **内部 API 网关** 后面的 upstream pool。 |
| readiness | **实例未就绪不进流量**：类似 **金丝雀未通过健康检查不接请求**。 |

## 任务

- [ ] 编写 **`deployment.yaml` + `service.yaml`**（或单文件多资源），镜像用 Job 13 构建的 tag。
- [ ] **`kubectl port-forward`** 验证接口。
- [ ] 故意让 **readiness 失败**，观察 **Endpoint 摘除** 行为（口述即可）。
- [ ] 笔记：一句卡点 + 结论。

## 命令速查

```bash
kubectl apply -f k8s/
kubectl get pods,svc
kubectl port-forward svc/myapp 8080:8080
```

## 排错

- **ImagePullBackOff**：镜像名、**imagePullSecrets**、私有仓库凭证。
- **探针太激进**：启动慢的应用要 **`initialDelaySeconds`** / **startupProbe**。

**上一项**：[job-14.md](job-14.md)　**下一项**：[job-16.md](job-16.md)　**索引**：[jobs.md](jobs.md)
