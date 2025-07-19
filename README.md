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

- **Prometheus** – Collects and stores metrics from the Spring Boot app. It scrapes data from `/actuator/prometheus`.
- **Alertmanager** – Sends email alerts when Prometheus detects defined issues (e.g., high CPU usage, heavy traffic).
- **Grafana** – Visualizes metrics in dashboards; includes panels for CPU usage, HTTP request volume, and more.
- **ELK Stack (Elasticsearch, Logstash, Kibana)** – Collects and visualizes logs from the Spring Boot app.
  - **Elasticsearch** stores log data.
  - **Logstash** processes and transforms logs.
  - **Kibana** provides dashboards to search and analyze log data interactively.
- **Elastic APM** – Provides performance tracing and transaction details for the Spring Boot application. Captures end-to-end latency, errors, and spans between services, visualized in the Kibana APM dashboard.
- **SkyWalking APM** – An alternative APM solution that offers distributed tracing, metrics collection, service topology, and performance analysis for the Spring Boot app. It provides its own UI for visualizing service dependencies and tracing data.
- **Istio (optional)** – A service mesh that allows fine-grained traffic and access control. Enables IP-based access control and observability enhancements.

---

### Kubernetes YAML Files

| File                         | Description                                                                    |
| ---------------------------- | ------------------------------------------------------------------------------ |
| `namespace.yaml`             | Creates a dedicated `monitoring` namespace.                                    |
| `prometheus.yaml`            | Deploys Prometheus, its alert rules, and RBAC setup.                           |
| `alertmanager.yaml`          | Deploys Alertmanager with email alerting.                                      |
| `grafana.yaml`               | Deploys Grafana for visualization.                                             |
| `elasticsearch.yaml`         | Deploys Elasticsearch for storing logs/APM traces.                             |
| `filebeat.yaml`              | Deploys Filebeat to collect Kubernetes logs and forward them to Elasticsearch. |
| `kibana.yaml`                | Deploys Kibana to visualize logs from Elasticsearch.                           |
| `apmserver.yaml`             | Deploys Elastic APM Server to collect APM data.                                |
| `skywalking.yaml`            | Deploys Apache SkyWalking (OAP server).                                        |
| `mysql.yaml`                 | Deploys MySQL database (sample app database).                                  |
| `spring-app-elastic.yaml`    | Deploys a Spring Boot app integrated with Elastic APM.                         |
| `spring-app-skywalking.yaml` | Deploys a Spring Boot app integrated with SkyWalking agent.                    |
| `ip-policy.yaml`             | (Optional) Applies IP-based access control using Istio AuthorizationPolicy.    |

---

## 🔧 Scripts

### Package the Spring Boot app into a JAR

```bash
./mvnw clean package -DskipTests=true
```

### Start / Stop the Minikube cluster

Start the Minikube cluster

```bash
minikube start --memory=8192 --cpus=4
```

Stop the Minikube cluster

```bash
minikube stop
```

### Accessing Services in Minikube

When using Minikube on macOS with the Docker driver, NodePort services (e.g., Grafana, Prometheus) aren't directly accessible via the Docker network IP like 192.168.49.2.

Instead, you should retrieve the exposed service URLs using the minikube service command:

```bash
minikube -n <namespace> service <service-name> --url
```

example

```bash
minikube -n monitoring service grafana --url
```

This will return a URL (e.g., http://127.0.0.1:49234) that you can open in your browser locally.

### Build Docker Image Inside Minikube

If you prefer not to push your image to Docker Hub and just want to build and use it locally, you can build the image directly inside the Minikube cluster.

First, switch your terminal to use Minikube’s Docker daemon:

```bash
eval $(minikube docker-env)
```

To confirm that the Docker environment is correctly set to Minikube, run:

```bash
docker info | grep 'Name'
```

You should see the following output (or similar):

```bash
Name: minikube
```

### Statup all the services using Docker Compose

This command builds the images (if necessary) and starts all the defined services.

```bash
docker compose up --build -d
```

To stop and remove all services:

```bash
docker compose down
```

### Run Unit Test

Run all tests

```bash
./mvnw test
```

Run tests in a specific class:

```bash
./mvnw -Dtest=UserServiceImplTest test
```

Run a specific test method:

```bash
./mvnw -Dtest=UserServiceImplTest#createUser_shouldReturnUserDto_whenEmailNotExists test
```

## 🛡️ Optional: Istio Setup & IP-Based Access Control

If you want to control access to the Spring Boot /actuator/prometheus endpoint based on client IP, you can install Istio and apply an AuthorizationPolicy.

🔧 Installing Istio with Homebrew

```bash
brew install istioctl
istioctl install --set profile=demo -y
```

✅ Enable Sidecar Injection for Monitoring Namespace

```bash
kubectl label namespace monitoring istio-injection=enabled
```

🔀 Redeploy Spring Boot App (for sidecar to take effect)

```bash
kubectl rollout restart deployment <deployment-name> -n monitoring
```

🔒 Apply IP-Based DENY Policy

This policy denies requests to /actuator/prometheus from any IP in the 10.0.0.0/8 range (i.e., any IP starting with 10.).

```bash
kubectl apply -f ip-policy.yaml
```

ℹ️ You can use the following command to check if requests are being blocked and verify the client IP address:

```bash
kubectl logs <pod-name> -c istio-proxy -n monitoring
```
