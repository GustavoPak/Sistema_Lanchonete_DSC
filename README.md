# 🍔 Sistema de Lanchonete

<p align="center">

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge\&logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=for-the-badge\&logo=apachemaven)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge\&logo=mysql)
![Swing](https://img.shields.io/badge/Java-Swing-green?style=for-the-badge)
![JPA](https://img.shields.io/badge/JPA-EclipseLink-success?style=for-the-badge)

</p>

Sistema desktop desenvolvido em **Java** para gerenciamento de uma lanchonete, utilizando arquitetura em camadas, persistência de dados com **JPA (EclipseLink)**, banco de dados **MySQL** e interface gráfica construída com **Java Swing**.

---

## ✨ Funcionalidades

### 🔐 Autenticação

* Login de usuários
* Senhas protegidas utilizando **BCrypt**
* Controle de acesso ao sistema

### 📂 Cadastros

* Categorias
* Produtos
* Clientes
* Pedidos

### 🔎 Busca

O sistema possui uma tela exclusiva de busca com duas opções:

* Buscar produtos por **Nome**
* Buscar produtos por **Categoria**

### 📊 Relatórios

* Relatório de Produtos
* Relatório de Pedidos

### 🛒 Pedidos

Durante o cadastro de um pedido:

* Caso o cliente já exista, ele é utilizado automaticamente.
* Caso não exista, o sistema realiza o cadastro automaticamente antes de concluir o pedido.

---

## 🛠 Tecnologias Utilizadas

| Tecnologia                | Descrição                     |
| ------------------------- | ----------------------------- |
| Java                      | Linguagem principal           |
| Swing                     | Interface gráfica             |
| Maven                     | Gerenciamento de dependências |
| JPA (Jakarta Persistence) | Persistência                  |
| EclipseLink               | Implementação JPA             |
| MySQL                     | Banco de dados                |
| BCrypt                    | Criptografia das senhas       |

---

## 📁 Estrutura do Projeto

```text
src
├── controller
├── dao
├── model
├── test
├── util
└── view
```

O projeto segue a arquitetura em camadas:

* **Model** → Entidades do sistema
* **DAO** → Acesso ao banco de dados
* **Controller** → Regras de negócio
* **View** → Interface gráfica
* **Util** → Classes auxiliares
* **Test** → Testes e semeadura do banco

---

## 🗄 Banco de Dados

O sistema utiliza **MySQL** como banco de dados.

Após configurar a conexão no arquivo:

```text
src/main/resources/META-INF/persistence.xml
```

é possível executar a classe:

```text
SeedBanco
```

para gerar automaticamente dados de exemplo.

---

## 👤 Usuário padrão

Após executar o **SeedBanco**:

| Campo   | Valor   |
| ------- | ------- |
| Usuário | `admin` |
| Senha   | `123`   |

---

## ▶ Como executar

1. Clone este repositório.

```bash
git clone https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git
```

2. Configure um banco MySQL.

3. Ajuste a conexão no `persistence.xml`.

4. Execute a classe `SeedBanco` (opcional).

5. Execute a aplicação.

---

## 🖥 Telas do Sistema

### Login

> Adicione aqui uma captura de tela da tela de login.

### Menu Principal

> Adicione aqui uma captura do menu principal.

### Cadastro de Produtos

> Adicione aqui uma captura da tela de produtos.

### Cadastro de Pedidos

> Adicione aqui uma captura da tela de pedidos.

### Busca

> Adicione aqui uma captura da tela de busca.

### Relatórios

> Adicione aqui uma captura da tela de relatórios.

---

## 📚 Objetivo

Este projeto foi desenvolvido para fins acadêmicos com o objetivo de aplicar conceitos de:

* Programação Orientada a Objetos (POO)
* Arquitetura em Camadas
* Persistência de Dados com JPA
* Interface Gráfica com Swing
* CRUD Completo
* Criptografia de Senhas
* Integração com Banco de Dados MySQL

---

## 👨‍💻 Desenvolvido por

Projeto desenvolvido como atividade acadêmica para a disciplina de Desenvolvimento de Sistemas Orientados a Objetos.
