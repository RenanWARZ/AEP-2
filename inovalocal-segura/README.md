<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>InovaLocal Segura - Projeto AEP</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5faff;
      color: #003366;
      margin: 40px auto;
      max-width: 800px;
      line-height: 1.6;
    }
    h1 {
      text-align: center;
      color: #004c99;
    }
    code, pre {
      background-color: #eef4ff;
      border-radius: 6px;
      padding: 6px 10px;
      display: block;
      margin: 10px 0;
      overflow-x: auto;
    }
    ul {
      list-style-type: none;
      padding: 0;
    }
    li {
      margin-bottom: 6px;
    }
    .box {
      background-color: #ffffff;
      border-radius: 10px;
      padding: 20px;
      box-shadow: 0 2px 6px rgba(0, 0, 50, 0.1);
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
  <h1>InovaLocal Segura - Projeto AEP</h1>

  <div class="box">
    <h2>Descrição</h2>
    <p>Aplicação <strong>Spring Boot (Java 17)</strong> com:</p>
    <ul>
      <li>Spring Web</li>
      <li>Spring Data JPA</li>
      <li>Banco de dados H2 (em memória)</li>
    </ul>
  </div>

  <div class="box">
    <h2>Como executar</h2>
    <p>No diretório do projeto, execute:</p>
    <pre><code>mvn spring-boot:run</code></pre>
  </div>

  <div class="box">
    <h2>Endpoints principais</h2>
    <ul>
      <li>GET  /api/users</li>
      <li>POST /api/users</li>
      <li>GET  /api/occurrences</li>
      <li>POST /api/occurrences</li>
      <li>GET  /api/occurrences/category/{category}</li>
      <li>PUT  /api/occurrences/{id}/status</li>
    </ul>
  </div>

  <div class="box">
    <h2>Banco de Dados H2</h2>
    <p>Acesse pelo navegador:</p>
    <p><strong>URL:</strong> <a href="http://localhost:8082/h2-console" target="_blank">http://localhost:8082/h2-console</a></p>
    <p><strong>JDBC URL:</strong> jdbc:h2:mem:inovalocaldb</p>
    <p><strong>User:</strong> sa</p>
    <p><strong>Password:</strong> (vazio)</p>
  </div>

  <div class="box">
    <h2>Acesso à Aplicação</h2>
    <p>Front-end e API disponíveis em:</p>
    <p><a href="http://localhost:8082" target="_blank">http://localhost:8082</a></p>
  </div>
</body>
</html>
