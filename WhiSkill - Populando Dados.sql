﻿--Usuário de Testes 
INSERT INTO USUARIO(Nome, Email, Senha) VALUES('Teste', 'teste@teste.com.br','senha');
-- Cadastro de Trilhas
INSERT INTO TRILHA(Nome, Descricao) VALUES('.NET', '.NET é uma plataforma de desenvolvimento da Microsoft.');
INSERT INTO TRILHA(Nome, Descricao) VALUES('Java', 'A plataforma Java permite desenvolver aplicativos utilizando a linguagem Java com a vantagem de não estar presa a um único sistema operacional.');
INSERT INTO TRILHA(Nome, Descricao) VALUES('Mobile', 'Tecnologias para desenvolvimento Mobile.');
INSERT INTO TRILHA(Nome, Descricao) VALUES('Testes:', 'Tecnologias para testes de Software.');
-- Cadastros de Skills nas tecnologias 
	-- Skills .NET
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('C#','C# é uma linguagem orientada a objeto elegante e de tipo seguro que permite aos desenvolvedores criar uma variedade de aplicativos seguros e robustos', SELECT IDTrilha FROM Trilha WHERE Nome LIKE '.NET');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('ASP.NET MVC' , 'ASP.NET - MVC - Model-View-Controller é um padrão de arquitetura que provê uma alternativa ao ASP.NET Web Forms, para criação de websites', SELECT IDTrilha FROM Trilha WHERE Nome LIKE '.NET');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('ASP.NET Web Forms' , 'ASP.NET Web Forms é a plataforma da Microsoft para o desenvolvimento de aplicações Web e é o sucessor da tecnologia VB', SELECT IDTrilha FROM Trilha WHERE Nome LIKE '.NET');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Windows Services' , 'Usando a plataforma .NET você pode facilmente criar serviços criando um aplicativo que será instalado como um serviço. Esse tipo de aplicativo é chamado um Windows Service', SELECT IDTrilha FROM Trilha WHERE Nome LIKE '.NET');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('ASP.NET WebAPI' , 'ASP.NET Web API é um Framework que nos possibilita a criação e disponibilizção de serviços HTTP para que os mesmos possam ser consumidos por browsers e dispositivos móveis.', SELECT IDTrilha FROM Trilha WHERE Nome LIKE '.NET');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Entity Framework' , 'O Microsoft Entity Framework é uma ferramenta de  ORM – Object Relational Management, que permite aos desenvolvedores trabalhar com classes (entidades) que correspondem a tabelas em um banco de dados', SELECT IDTrilha FROM Trilha WHERE Nome LIKE '.NET');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('SharePoint' , 'O SharePoint é uma tecnologia com utilização na criação de portais e intranets empresariais, gestão documental e criação de portais colaborativos, e publicação de aplicações web.', SELECT IDTrilha FROM Trilha WHERE Nome LIKE '.NET');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('IIS' , 'IIS (Internet Information Services é um servidor web criado pela Microsoft', SELECT IDTrilha FROM Trilha WHERE Nome LIKE '.NET');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('NuGet' , 'NuGet é uma ferramenta open source de gerenciamento de pacotes package manager para a plataforma .NET', SELECT IDTrilha FROM Trilha WHERE Nome LIKE '.NET');
	-- Skills Java
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Java' , 'Java é uma linguagem de programação orientada a objetos e que é compilada para um bytecode que é executado por uma máquina virtual.', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Java');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Spring' , 'O Spring é um framework open source para a plataforma Java ', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Java');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('JSF' , 'JavaServer Faces (JSF) é um framework MVC baseado em Java para a construção de interfaces de usuário baseadas em componentes para aplicações web.', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Java');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('JSP' , 'JavaServer Pages é uma tecnologia que ajuda os desenvolvedores de software a criarem páginas web geradas dinamicamente baseadas em HTML ou outros tipos de documentos', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Java');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Apache' , 'O Servidor Apache  é o mais bem sucedido servidor web livre', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Java');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Hibernate' , 'O Hibernate é um framework para o mapeamento objeto-relacional escrito na linguagem Java.', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Java');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Maven' , 'Maven, é uma ferramenta de automação de compilação e gerenciamento de dependências utilizada primariamente em projetos Java.', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Java');
	-- Skills Mobile
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Design Responsivo' , 'Busca criar sites onde a experiência do usuário é otimizada independente da forma a qual ele acessa o website, facilitando a navegação e scrolling em qualquer caso.', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Mobile');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('AngularJS' , 'AngularJS é um framework JavaScript open-source, mantido pelo Google, que auxilia na execução de single-page applications visando criar aplicativos que podem ser acessados por um navegador web.', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Mobile');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Android' , 'Desenvolvimento de aplicativos no sistema operacional Android', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Mobile');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES(' iOS' , 'Desenvolvimento de aplicativos no sistema operacional  iOS', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Mobile');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES(' Cordova' , 'Desenvolvimento de aplicativos a partir de HTML5, CSS3 e Javascript', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Mobile');
	-- Testes
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES(' JUnit' , ' JUnit é um framework open-source, criado por Erich Gamma e Kent Beck, com suporte à criação de testes automatizados na linguagem de programação Java.', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Testes');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('  Selenium IDE' , ' Selenium é um framework de testes para aplicações Web', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Testes');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('MSTest ' , ' MSTest  é um utilitário para execução de testes unitários de sistemas criados no Visual Studio', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Testes');
INSERT INTO Skill(Nome,Descricao,Trilha_Id) VALUES('Cucumber' , ' Cucumber é uma plataforma de testes para desktop, web, mobile, e server applications em diversas plataformas', SELECT IDTrilha FROM Trilha WHERE Nome LIKE 'Testes');