~subir banco de dados

sudo docker run -it -p 1521:1521 -e ORACLE_PASSWORD=123 gvenzl/oracle-xe:21.3.0-slim

~gerar token jwt

POST localhost:8080/auth/login, body: {'username': 'teste', 'password': 'teste'}

~autenticar requisições

GET localhost:8080/api/v1/cliente, Header: {'Authorization': 'Bearer ${token}'}
