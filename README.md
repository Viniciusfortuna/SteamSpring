# GameVerse - Backend

## Descrição do Projeto

O **GameVerse** é uma plataforma backend construída com **Spring Boot**, destinada a gerenciar uma plataforma de distribuição de jogos digitais. Com a API REST completa, o sistema oferece funcionalidades essenciais para gerenciamento de usuários, jogos, avaliações e inventário de itens virtuais. Inspirado em plataformas como **Steam** e **Epic Games Store**, o **GameVerse** proporciona uma base sólida e escalável para o desenvolvimento de frontends web ou mobile, bem como integrações com outros serviços.

## Funcionalidades

- **Gestão de Usuários** e autenticação com **JWT**.
- **Biblioteca de jogos adquiridos**.
- **Inventário de itens virtuais**.
- **Perfis públicos** de usuários.
- **Avaliações de jogos**.
- **Cadastro e organização de jogos**.
- Relacionamentos entre as principais entidades do sistema (Usuários, Jogos, Itens, etc.).

## Tecnologias Utilizadas

- **Spring Boot 3+**
- **Spring Data JPA**
- **Spring Security** para autenticação e autorização.
- **JWT** para autenticação de usuários.
- **PostgreSQL** para banco de dados.
- **Flyway** para versionamento de banco de dados.
- **Lombok** para simplificação do código.

## Estrutura do Projeto

O backend segue uma arquitetura em camadas:

1. **Controller**: Responsável por expor a API e tratar as requisições HTTP.
2. **Service**: Contém a lógica de negócio do sistema.
3. **Repository**: Interage diretamente com o banco de dados utilizando JPA.
4. **Entity**: Representa as tabelas do banco de dados.
5. **DTO (Data Transfer Object)**: Utilizado para transferir dados entre as camadas, garantindo segurança e padronização.
6. **Validation**: Responsável pela validação dos dados de entrada.
7. **Exception Handler**: Gerencia erros e exceções, retornando respostas adequadas para o usuário.




Link para protitagem das telas: https://www.figma.com/design/Q7TKxuWbryBfk12Pk2VdNi/GameVerse?node-id=0-1&t=0mZxDBKAAKWw81qK-1


No arquivo SteamAPI.postman_collection.json contém as requisições pré-definidas, para utilização.

Documentação (Após rodar o projeto): http://localhost:8080/swagger-ui/index.html#/


