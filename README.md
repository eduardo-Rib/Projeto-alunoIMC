# Projeto-alunoIMC

### Projeto proposto pela professora Juliana, com o objetivo de desenvolver o projeto utilizando a linguagem Java e SGBD MYSQL

## Código do banco de dados:

````mysql
create database AlunoIMC;

use AlunoIMC;

create table aluno(
cpf varchar(100) primary key,
nome varchar(100),
dataNascimento varchar(100),
peso float,
altura float
);
````