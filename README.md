projetopoo12014

===============


Projeto controle de finanças - POO 1/2014 
Professor Rodrigo Bonifácio UnB


Caio Yuri da Silva Costa  09/0108213

** obs.
Com o abandono de curso do colega de equipe, os seguintes requisitos funcionais não foram implementados 
- Transferência
- Relatório de Grupo de gasto
Os seguintes requisitos ficaram deficientes.
- Testes JUnit
- Relatório de contas
*******

Projeto realizado em Eclipse. Adicionar a pasta como um projeto do Eclipse.
Bibliotecas necessárias ao projeto estão na pasta LIB. O projeto eclipse
já está configurado com elas.

Executar o projeto em modo "não-debug". Se executado em debug, uma exceção 
acontece no início, mas pode se continuar a execução normalmente.

Com o projeto executando, acessar de um navegador a URL:

http://localhost:4567/

Documentação JavaDoc já pré-compilada está no diretório "doc".

Para escolher a implementação de persistência (Memória ou SQLite) trocar o construtor da Fábrica, que é chamado na classe Rota do package web.

A base de dados já está criada e é composta pelo arquivo "test.s3db".

Testes estão situados no pacote  br.unb.cic.poo.controlefinancas.testes; apenas um par de testes está implementado.
Os testes podem ser executado diretamente pelo Eclipse, através do menu Run -> Run As -> JUnit


Obs.Para _construir_ a documentação JavaDoc é necessário ter a JDK 7 instalada, não somente a JRE.
(http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)






