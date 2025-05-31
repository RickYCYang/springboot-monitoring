# Spring Boot User Management Web Service

This is a simple Spring Boot user management project that demonstrates the use of commonly utilized libraries and features in a Spring Boot application.

In addition to core backend features, this project also showcases a basic system **monitoring stack** built with **Prometheus**, **Alertmanager**, and **Grafana**, allowing developers to track metrics and receive alerts for issues like high CPU usage or elevated HTTP request rates.

The application can be launched via either **Docker Compose** or **Kubernetes**:

- Use `docker-compose.yml` to run locally with Docker (Prometheus and Grafana only, no Alertmanager).
- Use the YAML files under the `/kubernetes` directory to run in a Kubernetes environment (includes Alertmanager).

---

## 🚀 Features

### 🧩 Spring Boot

- **REST API** – Expose RESTful endpoints for user management operations.
- **Spring Data JPA** – Use Hibernate and JPA for seamless data access.
- **Validation** – Ensure incoming data integrity with Spring Boot validation.
- **Spring Boot Actuator** – Gain insights into app health, metrics, and more.
- **DTO Mapping** – Use ModelMapper or MapStruct to map between DTOs and entities.
- **Lombok** – Eliminate boilerplate code with annotations like `@Getter`, `@Setter`, etc.
- **Global Exception Handling** – Return clean, consistent error messages.
- **OpenAPI / Swagger** – Generate interactive API documentation automatically.
- **MySQL Integration** – Connect to a MySQL database for persistent storage.
- **Docker Support** – Package the app as a Docker image for easy deployment.

### 📈 Monitoring & Alerting

- **Prometheus** – Collect and store metrics from the Spring Boot app. Scrapes metrics from `/actuator/prometheus`.
- **Alertmanager** – Sends email alerts when Prometheus detects defined issues (e.g., high CPU usage, heavy traffic).
- **Grafana** – Visualize metrics in dashboards; includes panels for CPU usage, HTTP request volume, and more.

---

### Kubernetes YAML Files

| File                           | Description                                                                                                 |
| ------------------------------ | ----------------------------------------------------------------------------------------------------------- |
| `namespace.yaml`               | Defines the `monitoring-demo` namespace to scope all resources.                                             |
| `mysql-deployment.yaml`        | Deploys a MySQL instance with credentials and database name, exposed via a `ClusterIP` service.             |
| `spring-app.yaml`              | Deploys the Spring Boot application using a local image and exposes it via a `NodePort`.                    |
| `grafana.yaml`                 | Deploys Grafana with default admin credentials, accessible via `NodePort`.                                  |
| `prometheus-config.yaml`       | Contains a `ConfigMap` with the main Prometheus config (`prometheus.yml`).                                  |
| `prometheus-deployment.yaml`   | Deploys Prometheus and exposes it via `NodePort`, mounting the config from the corresponding `ConfigMap`.   |
| `prometheus-alert-rules.yaml`  | Defines Prometheus alert rules such as high CPU usage and high HTTP request volume.                         |
| `alertmanager-config.yaml`     | Contains Alertmanager configuration (SMTP settings, receivers, and routes) as a `ConfigMap`.                |
| `alertmanager-deployment.yaml` | Deploys Alertmanager and exposes it via `NodePort`, mounting the config from the corresponding `ConfigMap`. |

---

## 🔧 Scripts

### Package the Spring Boot app into a JAR

```bash
./mvnw clean package -DskipTests=true
```

### Accessing Services in Minikube

When using Minikube on macOS with the Docker driver, NodePort services (e.g., Grafana, Prometheus) aren't directly accessible via the Docker network IP like 192.168.49.2.

Instead, you should retrieve the exposed service URLs using the minikube service command:

```bash
minikube -n <namespace> service <service-name> --url
```

example

```bash
minikube -n monitoring-demo service grafana --url
```

This will return a URL (e.g., http://127.0.0.1:49234) that you can open in your browser locally.
