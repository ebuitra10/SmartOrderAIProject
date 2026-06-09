<h1 align="center">🚀 SmartOrderAIProject – Backend</h1>

<p align="center">
Backend distribuido basado en microservicios para la gestión de inventario y órdenes.<br>
Arquitectura limpia · OAuth2 · JWT · API Gateway · Docker · PostgreSQL
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-red" />
  <img src="https://img.shields.io/badge/SpringBoot-3.x-brightgreen" />
  <img src="https://img.shields.io/badge/SpringSecurity-OAuth2-blue" />
  <img src="https://img.shields.io/badge/Docker-Containerized-2496ED" />
  <img src="https://img.shields.io/badge/Architecture-Microservices-orange" />
</p>

<hr>

<h2> Arquitectura General</h2>

<p>
El sistema está compuesto por un <b>API Gateway</b>, múltiples microservicios independientes,
un servidor de identidad (<b>Keycloak</b>) y bases de datos aisladas por servicio.
</p>

<ul>
  <li>🔹 API Gateway centralizado</li>
  <li>🔹 Autenticación OAuth2 basada en JWT</li>
  <li>🔹 Validación automática de firma mediante JWK</li>
  <li>🔹 Base de datos por microservicio</li>
  <li>🔹 Comunicación REST interna con FeignClient</li>
  <li>🔹 Propagación de token entre servicios</li>
</ul>

<hr>

<h2> Microservicios</h2>

<table>
  <tr>
    <th>Servicio</th>
    <th>Puerto</th>
    <th>Base de Datos</th>
  </tr>
  <tr>
    <td>msvc-gateway</td>
    <td>8080</td>
    <td>—</td>
  </tr>
  <tr>
    <td>msvc-orders</td>
    <td>8002</td>
    <td>PostgreSQL</td>
  </tr>
  <tr>
    <td>msvc-products</td>
    <td>8003</td>
    <td>PostgreSQL</td>
  </tr>
  <tr>
    <td>msvc-inventory</td>
    <td>8004</td>
    <td>PostgreSQL</td>
  </tr>
  <tr>
    <td>msvc-products-orders</td>
    <td>8005</td>
    <td>PostgreSQL</td>
  </tr>
  <tr>
    <td>Keycloak</td>
    <td>9090</td>
    <td>Interna</td>
  </tr>
</table>

<hr>

<h2>Seguridad</h2>

<p>
El sistema implementa autenticación y autorización distribuida usando
<b>Spring Security OAuth2 Resource Server</b>.
</p>

<h3>Flujo de Autenticación</h3>

<ol>
  <li>El usuario obtiene un JWT desde Keycloak.</li>
  <li>El cliente envía el token al Gateway.</li>
  <li>El Gateway valida firma, expiración y algoritmo automáticamente.</li>
  <li>Se construye el objeto Authentication.</li>
  <li>El Gateway enruta la petición al microservicio correspondiente.</li>
  <li>El microservicio valida nuevamente el JWT.</li>
  <li>Se aplican reglas de autorización basadas en roles.</li>
</ol>

<p>
La validación criptográfica del JWT se realiza automáticamente mediante el <b>JwtDecoder</b>
configurado con <code>jwk-set-uri</code>.
</p>

<hr>

<h2> Stack Tecnológico</h2>

<ul>
  <li>Java 17</li>
  <li>Spring Boot 3</li>
  <li>Spring Security</li>
  <li>Spring Cloud Gateway</li>
  <li>OAuth2 Resource Server</li>
  <li>Keycloak 26.x</li>
  <li>JPA / Hibernate</li>
  <li>OpenFeign</li>
  <li>PostgreSQL 15</li>
  <li>Docker & Docker Compose</li>
</ul>

<hr>

<h2> Comunicación Interna</h2>

<p>
Los microservicios se comunican mediante <b>FeignClient</b>.
El JWT se propaga automáticamente a través de una configuración personalizada,
permitiendo autorización consistente entre servicios.
</p>

<hr>

<h2> Dockerización</h2>

<ul>
  <li>Multi-stage builds optimizados</li>
  <li>Red bridge compartida</li>
  <li>Persistencia en bases de datos</li>
  <li>Configuración por variables de entorno (.env)</li>
</ul>

<h3>Levantar el sistema</h3>

<pre>
docker compose up -d --build
</pre>

<hr>

<h2> Pruebas</h2>

<ol>
  <li>Obtener token desde Keycloak.</li>
  <li>Enviar request al Gateway con header:</li>
</ol>

<pre>
Authorization: Bearer &lt;token&gt;
</pre>

<ul>
  <li>200 → Acceso permitido</li>
  <li>401 → Token inválido</li>
  <li>403 → Sin permisos suficientes</li>
</ul>

<hr>

<h2>🚀 Próxima Evolución</h2>

<p>
El backend está diseñado para integrarse con una futura capa analítica en Python orientada a:
</p>

<ul>
  <li>Procesamiento de datos</li>
  <li>Reportes inteligentes</li>
  <li>ETL</li>
  <li>Machine Learning aplicado a inventario y órdenes</li>
</ul>

<hr>

<h3 align="center">
Desarrollado con enfoque en arquitectura moderna, seguridad distribuida y despliegue profesional.
</h3>
