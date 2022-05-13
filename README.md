[![Code coverage](https://codecov.io/gh/ES-UFABC/Muda/branch/main/graph/badge.svg?token=6e5dlQjjxr)](https://codecov.io/gh/ES-UFABC/Muda)
[![backend-build build](https://github.com/ES-UFABC/Muda/actions/workflows/backend.yaml/badge.svg)](https://github.com/ES-UFABC/Muda/actions/workflows/backend.yaml)
[![frontend-build build](https://github.com/ES-UFABC/Muda/actions/workflows/frontend.yaml/badge.svg)](https://github.com/ES-UFABC/Muda/actions/workflows/frontend.yaml)
[![backend-deploy deploy](https://github.com/ES-UFABC/Muda/actions/workflows/backend-deploy.yml/badge.svg)](https://github.com/ES-UFABC/Muda/actions/workflows/backend-deploy.yml)
[![frontend-deploy deploy](https://github.com/ES-UFABC/Muda/actions/workflows/frontend-deploy.yml/badge.svg)](https://github.com/ES-UFABC/Muda/actions/workflows/frontend-deploy.yml)

## Sobre

  Com um claro cenário dos efeitos da pandemia, como a indisponibilidade de venda por canais tradicionais e a alta do desemprego, associados a crescente demanda por alimentos saudáveis pelos consumidores familiares urbanos, o Muda se propõe a facilitar a conexão de pequenos produtores de alimentos com seus respectivos consumidores a partir de uma plataforma web de compra de venda de alimentos. 
  
  Baseia-se em sistema digital integrado de apoio à agricultura familiar e orgânica, agroEcológica, economia solidária, povos e comunidades tradicionais e artesanato de raiz.
  
## Tech Stack

Back-end: Spring Boot  
Front-end: VueJS  
Banco de dados: MySQL  
CI/CD: Github Actions  
Hospedagem: Heroku

## Como executar

### Back-end

- Docker 

Para o ambiente de desenvolvimento está disponibilizado o arquivo docker-compose.dev.yml, assim para rodar a aplicação faça:  
1 - No terminal, navegue até a pasta `backend/agricultura-familiar`  
2 - Execute `docker-compose --file docker-compose.dev.yml up --build` 

Ao final da execução a aplicação está disponível em `localhost:8080` e o banco de dados em `localhost:3306`  
A aplicação está configurada para aceitar remote-debugging, assim consulte o guia da sua IDE para fazer uso desta funcionalidade.

### Front-end

- Node.js 

Para rodar a aplicação siga os seguintes passos:  
1 - No terminal, navegue até a pasta `frontend/agricultura-familiar`  
2 - Execute `npm install`
3 - Execute `npm run dev`

Ao final da execução a aplicação estará disponível em `localhost:9000`.

- Docker

Para o ambiente de desenvolvimento está disponibilizado o arquivo docker-compose.dev.yml, assim para rodar a aplicação faça:  
1 - No terminal, navegue até a pasta `frontend/agricultura-familiar`  
2 - Execute `docker-compose --file docker-compose.dev.yml up --build` 

## Membros


1. Fernanda Felix da Silva
2. Maik Henrique
3. Matheus Ribeiro
4. Larissa Nunes de Almeida 
5. Daniel Fusimoto Pires
6. Diego Sousa Santos
7. Anderson Pedro Velasco

## License

MIT license.

