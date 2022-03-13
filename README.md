[![Code coverage](https://codecov.io/gh/ES-UFABC/Muda/branch/main/graph/badge.svg?token=6e5dlQjjxr)](https://codecov.io/gh/ES-UFABC/Muda)
[![Back-end build](https://github.com/ES-UFABC/Muda/actions/workflows/backend.yaml/badge.svg)](https://github.com/ES-UFABC/Muda/actions/workflows/backend.yaml)
[![Front-end build](https://github.com/ES-UFABC/Muda/actions/workflows/frontend.yaml/badge.svg)](https://github.com/ES-UFABC/Muda/actions/workflows/frontend.yaml)

## Sobre

  Com um claro cenário dos efeitos da Pandemia, como a indisponibilidade de venda por canais tradicionais e a alta do desemprego, associados a crescente demanda por alimentos saudáveis pelos consumidores familiares urbanos, o Moba se propõe a facilitar a conexão de pequenos produtores de alimentos com seus respectivos consumidores a partir de uma plataforma web de compra de venda de alimentos. 
  
  Baseia-se em sistema digital integrado de Apoio à agricultura familiar e orgânica, agroEcológica, economia solidária, povos e comunidades tradicionais e artesanato de raiz.

## Como executar

### Back-end
Pré-requisitos:
- Docker 

Para o ambiente fo desenvolvido o arquivo docker-compose.dev.yml, assim para rodar a aplicação faça:  
1 - No terminal, navegue até a pasta `backend/agricultura-familiar`  
2 - Execute `docker-compose --file docker-compose.dev.yml up -d --build` 

Ao final da execução a aplicação está disponível em `localhost:8080` e o banco de dados em `localhost:3306`  
A aplicação está configurada para aceitar remote-debugging, assim consulte o guia da sua IDE para fazer uso desta funcionalidade.

## Testes

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

