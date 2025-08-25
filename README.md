# Sistema de Agenda Telefônica

Este é um sistema de agenda telefônica web desenvolvido em Java com o framework **Spring Boot**. O objetivo do projeto é demonstrar as funcionalidades básicas de um sistema de gestão de contatos, incluindo login, cadastro e operações CRUD (Criar, Ler, Atualizar e Deletar).

<br>

## Funcionalidades Principais

* **Login e Cadastro de Usuário**: Cada usuário possui sua própria lista de contatos, garantindo a privacidade dos dados.
* **Gestão de Contatos (CRUD)**:
    * **Cadastrar**: Adiciona novos contatos com informações como nome, sobrenome, data de nascimento e telefone.
    * **Listar**: Exibe a lista completa de contatos do usuário logado.
    * **Atualizar**: Permite a edição de informações de um contato já existente.
    * **Deletar**: Remove um contato da agenda de forma permanente.
* **Campos Obrigatórios**: Os campos `Nome`, `Sobrenome`, `Data de Nascimento` e `Telefone` são de preenchimento obrigatório.
* **Múltiplos Telefones**: Um único contato pode ter um ou mais telefones associados.
* **Persistência de Dados**: Os dados são armazenados em um banco de dados relacional.
* **Feedback ao Usuário**: O sistema exibe mensagens de sucesso ou falha após cada operação (inserção, alteração e exclusão).

<br>

## Tecnologias Utilizadas

* **Linguagem de Programação**: Java 17
* **Framework Web**: Spring Boot
* **Interface (Templates)**: Thymeleaf
* **Persistência**: Spring Data JPA com Hibernate
* **Banco de Dados**: H2 Database (em memória, ideal para desenvolvimento)
* **Gerenciador de Dependências**: Maven
* **Testes**: JUnit 5 e Mockito para testes unitários.

<br>

## Como Rodar o Projeto

Para rodar o projeto localmente, siga estes passos:

1.  **Pré-requisitos**: Certifique-se de ter o **JDK 17** e uma IDE (como **IntelliJ IDEA** ou **VS Code**) instalada.

2.  **Clone o Repositório**:
    ```bash
    git clone [https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git](https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git)
    cd nome-do-projeto
    ```

3.  **Abra na IDE**: Importe o projeto como um projeto **Maven** na sua IDE. Ela irá baixar todas as dependências automaticamente.

4.  **Execute a Aplicação**: Navegue até a classe principal `AgendaTelefonicaApplication.java` e a execute.

5.  **Acesse no Navegador**: Com a aplicação rodando, abra seu navegador e acesse:
    ```
    http://localhost:8080
    ```

Você será direcionado para a tela de login. Use a opção de cadastro para criar um novo usuário e começar a gerenciar seus contatos.

<br>

## Testes Unitários

O projeto inclui testes unitários para a lógica de negócio principal. Para executá-los, basta rodar a classe `ContatoServiceTest.java` a partir da sua IDE.
