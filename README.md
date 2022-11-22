# ChatVoice

<p> O ChatVoice é um projeto desenvolvido para a matéria Redes de Computadores II do curso de Ciência da Computação da Universidade Federal Fluminense.</p>

### 👨‍💻 Desenvolvedores: Camila Ferreira, Thiago Mozart e Rodrigo Barroso

### :computer: Objetivo do projeto:
Desenvolver uma aplicação com interface para o usuário que simula uma sala virtual de conversação por ligações de voz.   
  
A aplicação apresenta um módulo servidor, responsável por criar as salas virtuais, e armazenar informações pertinentes dos usuários conectados, como Nome, IP e Porta. 
  
Os usuários da aplicação podem estabelecer conexões entre eles utilizando as informações guardadas pelo servidor. Essa conexão é feita por pares, utilizando o protocolo UDP. 
  
Assim, de forma fácil e interativa, os usuários podem estabelecer conexões entre si e receber chamadas de diferentes clientes conectados no servidor.

### :book: Bibliotecas utilizada: JavaFX

### :pencil2: Como executar a aplicação?
**Para executar o servidor de registros :**
```
Execute a classe "Servidor" 
```
**Para executar o Cliente:**
```
Modifique o endereço ip na classe Cliente 
```
**Execute o comando em seu terminal:**  
```
maven clean package  
```
**Logo após, execute a classe Application.**
