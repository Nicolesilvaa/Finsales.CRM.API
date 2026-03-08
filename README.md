# Finsales CRM API 

Status: Em andamento

API REST desenvolvida em Java com Spring Boot simulando um sistema de CRM (Customer Relationship Management).

---

## Objetivo do Projeto

- Construção de APIs REST
- Arquitetura em camadas (Controller, Service, Repository)
- Autenticação com JWT
- Autorização baseada em perfil (Role-based access control)
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

## Perfis de Acesso

### ADMIN
Pode:
- Criar usuários
- Criar produtos
- Gerenciar leads
- Gerenciar clientes
- Gerenciar oportunidades
- Listar usuários

### VENDEDOR
Pode:
- Criar e atualizar leads
- Converter leads
- Criar clientes
- Criar e atualizar oportunidades
- Listar oportunidades

Não pode:
- Criar usuários
- Criar produtos
- Listar usuários
- Atualizar clientes (conforme testes atuais)

---

## Regras de Negócio

### Usuários
- Email deve ser único
- Perfil deve ser ADMIN ou VENDEDOR
- Usuário pode ser ATIVO ou INATIVO
- Apenas ADMIN pode listar e inativar usuários

### Leads
- Email do lead deve ser único
- Status possíveis:
  - NOVO
  - QUALIFICADO
  - CONTRATADO
  - DESCARTADO
  - CONVERTIDO
- Lead DESCARTADO ou CONVERTIDO não pode ser alterado
- Conversão gera cliente automaticamente

### Clientes
- Documento deve ser único
- Tipo: PF ou PJ
- Estado: ATIVO ou INATIVO
- Atualização exige cliente existente

### Produtos
- Apenas ADMIN pode criar
- Nome e dados obrigatórios

### Oportunidades
- Deve estar vinculada a cliente e produto existentes
- Status possíveis:
  - ABERTA
  - NEGOCIANDO
  - GANHA
  - PERDIDA

---

## Autenticação

### Login
POST /auth/login

Body:
```json
{
  "email": "admin@crm.com",
  "senha": "123456"
}
``
```
Autora - Nicole Silva
