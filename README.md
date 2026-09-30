# 🐾 Clínica Pet — Sistema CRUD em Java

![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![POO](https://img.shields.io/badge/Paradigma-Orientação_a_Objetos-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge)

Sistema de console para gerenciar uma **clínica veterinária**: proprietários, veterinários, animais e consultas. Desenvolvido em **Java puro** (sem frameworks e sem banco de dados) para aplicar na prática os quatro pilares da **Programação Orientada a Objetos**.

> Projeto acadêmico da disciplina de Programação Orientada a Objetos — curso de Análise e Desenvolvimento de Sistemas, UNINASSAU.

---

## 📌 Sumário

- [Funcionalidades](#-funcionalidades)
- [Conceitos de POO aplicados](#-conceitos-de-poo-aplicados)
- [Tecnologias e ferramentas](#-tecnologias-e-ferramentas)
- [Arquitetura e modelo de classes](#-arquitetura-e-modelo-de-classes)
- [Regras de negócio](#-regras-de-negócio)
- [Como executar](#-como-executar)
- [Exemplo de uso](#-exemplo-de-uso)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Aprendizados](#-aprendizados)
- [Próximos passos](#-próximos-passos)
- [Autor](#-autor)

---

## ✨ Funcionalidades

CRUD completo para cada entidade, acessível por um menu interativo no terminal:

| Entidade | Criar | Listar | Atualizar | Remover |
|---|:---:|:---:|:---:|:---:|
| Proprietários | ✅ | ✅ | ✅ | ✅ |
| Veterinários | ✅ | ✅ | ✅ | ✅ |
| Animais (Cachorro / Gato) | ✅ | ✅ | ✅ | ✅ |
| Consultas | ✅ | ✅ | ✅ | ✅ |

- Dados iniciais de exemplo carregados automaticamente (*seed data*) para testar rapidamente.
- Leitura de entrada com tratamento de erro: valores inválidos não derrubam o programa.

---

## 🧠 Conceitos de POO aplicados

| Pilar | Onde aparece no código |
|---|---|
| **Abstração** | `Animal` é uma classe abstrata com o método abstrato `emitirSom()` |
| **Encapsulamento** | Atributos `private` com getters/setters em todas as classes |
| **Herança** | `Cachorro` e `Gato` estendem `Animal` |
| **Polimorfismo** | Cada subclasse implementa `emitirSom()` ("Au Au!" / "Miau!") e a listagem trata todos como `Animal` |

Outros recursos da linguagem utilizados: `enum` (papel da pessoa: `PROPRIETARIO` / `VETERINARIO`), `AtomicInteger` (geração automática de IDs), `List`/`ArrayList`, **Streams e expressões lambda** (filtros e buscas), `@Override` e `toString()` personalizado.

---

## 🛠 Tecnologias e ferramentas

- **Linguagem:** Java (desenvolvido com OpenJDK 25)
- **IDE:** IntelliJ IDEA
- **Entrada/Saída:** `Scanner` (console)
- **Armazenamento:** estruturas em memória (`ArrayList`)
- **Controle de versão:** Git e GitHub

---

## 🧩 Arquitetura e modelo de classes

```
            Pessoa                      Animal (abstract)
   ┌────────────────────┐         ┌───────────────────────┐
   │ id, nome, telefone │ 1     * │ id, nome, idade,      │
   │ role (enum)        │◄────────│ espécie, proprietário │
   └────────────────────┘         │ emitirSom() (abstract)│
            ▲                     └───────────┬───────────┘
            │                          ┌──────┴──────┐
            │                       Cachorro        Gato
            │                       (raça)     (gostaDeBrincar)
            │
      ┌─────┴──────────────────────┐
      │ Consulta                   │
      │ id, dataHora, descrição    │
      │ veterinário → Pessoa       │
      │ animal → Animal            │
      └────────────────────────────┘
```

A classe `ClinicaPetMain` concentra o menu, as listas em memória e a lógica dos CRUDs.

---

## 🔒 Regras de negócio

- Não é possível remover uma **pessoa** que possua animais ou consultas associadas.
- Não é possível remover um **animal** que possua consultas agendadas.
- Todo animal deve ser vinculado a um **proprietário existente**.
- Toda consulta exige um **veterinário** e um **animal** válidos, escolhidos por ID.
- O tipo do animal (`Cachorro` ou `Gato`) é instanciado automaticamente conforme a espécie informada.

---

## ▶️ Como executar

### Pré-requisitos

- **JDK 17 ou superior** instalado (o projeto foi desenvolvido com JDK 25)
- Verifique com: `java -version`

### Pelo terminal (Windows, Linux ou macOS)

```bash
# 1. Clone o repositório
git clone https://github.com/Alefe-William/CRUD_ClinicaPET.git
cd CRUD_ClinicaPET

# 2. Compile
javac -d out src/*.java

# 3. Execute
java -cp out ClinicaPetMain
```

> Se a pasta `src` estiver dentro de outra pasta (por exemplo `Crud/src`), ajuste o caminho no comando de compilação.

### Pelo IntelliJ IDEA

1. `File > Open` e selecione a pasta do projeto.
2. Configure o SDK em `File > Project Structure > Project`.
3. Abra `ClinicaPetMain.java` e clique no ícone ▶ ao lado do método `main`.

### Ambientes compatíveis

| Ambiente | Compatível |
|---|:---:|
| IntelliJ IDEA | ✅ |
| Eclipse / NetBeans | ✅ |
| VS Code (Extension Pack for Java) | ✅ |
| Terminal (`javac` / `java`) | ✅ |
| Windows, Linux e macOS | ✅ |

---

## 💻 Exemplo de uso

```
=== Clínica Pet - Menu ===
1. Cadastrar Proprietários
2. Listar Proprietários
3. Atualizar Proprietários
4. Remover Proprietário
5. Cadastrar Veterinário
6. Listar Veterinário
7. Atualizar Veterinário
8. Remover Veterinário
9. Cadastrar Animal
10. Listar Animais
11. Atualizar Animal
12. Remover Animal
13. Agendar Consulta
14. Listar Consultas
15. Atualizar Consulta
16. Remover Consulta
0. Sair
Escolha uma opção:
```

## 📂 Estrutura do projeto

```
📦 Crud
 ┗ 📂 src
    ┣ 📜 Animal.java         # Classe abstrata (base dos animais)
    ┣ 📜 Cachorro.java       # Herda de Animal
    ┣ 📜 Gato.java           # Herda de Animal
    ┣ 📜 Pessoa.java         # Proprietários e veterinários (enum Role)
    ┣ 📜 Consulta.java       # Relaciona veterinário e animal
    ┗ 📜 ClinicaPetMain.java # Menu e lógica dos CRUDs
```

---

## 🎓 Aprendizados

- Modelagem de domínio com classes, herança e polimorfismo.
- Validação de regras de negócio e integridade entre entidades relacionadas.
- Manipulação de coleções com Streams e lambdas.
- Construção de menus interativos e tratamento de entradas inválidas.
- Organização de código com responsabilidades bem definidas.

---

## 🚀 Próximos passos

- [ ] Separar a lógica em camadas (`model`, `service`, `repository`, `view`)
- [ ] Persistir os dados (arquivo ou banco de dados com JDBC)
- [ ] Adicionar testes unitários com JUnit 5
- [ ] Trocar `String` por `LocalDateTime` nas consultas e validar conflitos de horário
- [ ] Evoluir para uma API REST com Spring Boot

---

## 👤 Autor

**Álefe**

[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Alefe-William)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/álefe-soares-6739a5436)

