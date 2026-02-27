# Finsales CRM API 

Status: Em andamento

API REST desenvolvida em Java com Spring Boot simulando um sistema de CRM (Customer Relationship Management).

O objetivo do projeto é treinar  conceitos fundamentais de backend, aplicando boas práticas de arquitetura, segurança e organização de código.

---

## Objetivo do Projeto

- Construção de APIs REST
- Arquitetura em camadas (Controller, Service, Repository)
- Autenticação com JWT
- Autorização baseada em perfil (Role-based access control)
- Spring Security (stateless)
- Modelagem de domínio

A proposta é simular um sistema real de CRM interno para equipe de vendas.

---

## Organização e Gestão do Projeto

- Foram criados **prompts estratégicos para a IA atuar como Product Manager (PM)**.
- As funcionalidades foram refinadas como features.
- As melhorias foram divididas em issues técnicas.
- O fluxo de desenvolvimento foi organizado utilizando o [**GitHub Projects**](https://github.com/users/Nicolesilvaa/projects/1).
- As tarefas seguem organização por backlog, refinamento e execução.
---

##  Domínio da Aplicação

O sistema representa um CRM onde podemos gerenciar:

- Usuários internos (ADMIN e VENDEDOR)
- Clientes
- Leads
- Oportunidades
- Produtos
- Vendas

O domínio pode evoluir conforme o aprendizado avança.

---
## Perfis de Usuário

O controle de acesso é baseado em perfil.

### Perfis disponíveis:

**ADMIN**
- Listar usuários
- Inativar usuários
- Acesso completo aos recursos administrativos

**VENDEDOR**
- Acesso limitado
- Visualização e atualização apenas do que for permitido

As permissões são configuradas no `SecurityConfiguration`.

---

##  Status de Usuário

O sistema controla o status:

- ATIVO
- INATIVO

Usuários INATIVOS não devem conseguir autenticar.

---

##  Regras de Negócio Implementadas

- Usuário padrão é criado como VENDEDOR (quando não informado)
- Apenas ADMIN pode listar usuários
- Token expira em 1 hora
- Senhas devem ser criptografadas com BCrypt
- API é totalmente stateless


Autora - Nicole Silva
