# Projeto-alunoIMC

### Projeto proposto pela professora Juliana, com o objetivo de desenvolver o projeto utilizando a linguagem Java e SGBD MYSQL

## Código do banco de dados:

````mysql
create database AlunoIMC;

use AlunoIMC;

create table aluno(
id int primary key auto_increment,
cpf varchar(100),
nome varchar(100),
dataNascimento varchar(100),
peso float,
altura float
);
````