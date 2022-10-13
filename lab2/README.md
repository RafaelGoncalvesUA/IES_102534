# 2.1 - "Server-side programming with servlets"

## Servlet
Um servlet é uma classe Java que corre num servidor e trata de pedidos do cliente, processando-os e respondendo-lhes.

Para criar um servlet, basta criar uma classe que implemente a interface `javax.servlet.Servlet`. Esta interface define um método `service` que recebe um objeto `javax.servlet.ServletRequest` e um objeto `javax.servlet.ServletResponse`, os quais representam respetivamente o pedido e a resposta do cliente.

Um servlet deve estar inserido num **Servlet *container***. Quando um servidor web recebe um pedido, entrega-o ao *container* que, por sua vez, o passa ao Servlet destino.

### Quais são as responsabilidades de um servlet container?
>Ver questão A. da secção "Review Questions"

![Servlet](https://lh3.googleusercontent.com/Ig7kjtbUiYNt86tx49lpvMsZY-Cc-4teAFboao1-szXV450v4J11BEfUFX1qhAnlM5g7t2gp3flPbitnaSqHv285gttOEPkNFCZf8IIk6gkpatXSba3XtbkfnNnmUlRn96SsuGVr9g=w2400)

## JavaEE vs JakartaEE

JavaEE (Java Enterprise Edition) é uma plataforma Java que permite a criação de aplicações web escaláveis e seguras. JavaEE, Java2EE, J2EE ou Jakarta EE são nomes diferentes para a mesma plataforma.

![Java Enterprise Edition](https://www.baeldung.com/wp-content/uploads/2018/12/java_evolution-1.png)

- Em 2018, a Oracle cedeu o Java EE para a Eclipse Foundation.
- Como a Oracle detinha os diretos de autor sobre a marca Java, a Eclipse Foundation alterou o nome da plataforma para Jakarta EE.
- O grande objetivo do Jakarta EE é manter o Java **corporativo** e sempre a par das tendências e necessidades do mercado.
- Quando se trabalha com esta plataforma, é importante distinguir **especificações** e **implementações**. Uma especificação é um conjunto de funcionalidades (interface) que um software deve implementar. Uma implementação é um software que implementa uma ou mais especificações.
	- Por exemplo, a especificação **Servlet 4.0** define uma interface `javax.servlet.Servlet`. Uma das suas implementações é o **Apache Tomcat 9.0**.

## JAR vs WAR
**JAR**: Java Archive. É um ficheiro que contém código Java e dependências.

**WAR**: Web Application Archive. É um ficheiro que contém código Java, dependências e ficheiros de configuração de uma aplicação web.


## Web vs Application Server
**Web Server**: Servidor que se encarrega exclusivamente do processamento de pedidos HTTP/HTTPS, respondendo-lhes com ficheiros estáticos (HTML, CSS, JavaScript, etc.).

**Application Server**: Servidor que se encarrega do processamento dos pedidos recebidos por uma aplicação, em vários protocolos (incluindo HTTP/HTTPS), e lhes responde com ficheiros, não só estáticos como dinâmicos, na medida em que o conteúdo apresentado depende do contexto do pedido. Uma vantagem é o uso de multithreading, o que permite atender vários pedidos ao mesmo tempo.

## Apache Tomcat 9.0
### Inicialização
```cd apache-tomcat-9.0.67/bin```

```chmod +x *.sh```

```./startup.sh```: arranque do servidor.

```./shutdown.sh```: paragem do servidor.

### Ver log do servidor
```tail logs/catalina.out```

### Programação de Servlets
A classe Servlet é abstrata, pelo que as suas subclasses devem implementar pelo menos um método dos seguintes:
- ```doGet```
- ```doPost```
- ```doPut```
- ```doDelete```
- ```init``` e ```destroy```
- ```getServletInfo```

### Passagem de parâmetros
```java
@WebServlet(name = "ServletToValidate", urlPatterns = {"/ServletToValidate"})
public class ServletToValidate extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {     
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(username + "=" + password);
	}
}
```
**URL**: ```http://localhost:[PORT]/[APP-NAME]/ServletToValidate?username=username&password=guest```

### Fazer *deploy* de uma aplicação
*Upload* e *deploy* do .war em ```http://localhost:[PORT]/manager```.

**OU**

Cópia do .war para ```<Tomcat root>/webapps```.

### Aceder ao *endpoint* raiz da aplicação
```http://localhost:[PORT]/[APP_NAME]```

No caso do exercício 2.1, era ```http://localhost:8080/WebProject-1.0-SNAPSHOT/```.


## Payara Server (Web Profile)
No âmbito da alínea opcional j) do exercício 2.1, escolhi o Payara Server, como alternativa ao Apache Tomcat.

**Versão utilizada**: Payara-Web 5.2022.3 (Web Profile) 

```cd payara5/bin```

```chmod +x asadmin```

```./asadmin start-domain```: arranque do servidor.

```./asadmin stop-domain```: paragem do servidor.

Para gerir o Payara Server, é preciso a aceder ao endereço ```http://localhost:4848```, por default.

# 2.2) "Server-side programming with embedded servers"
## Eclipse Jetty
O Jetty é um servidor HTTP e Servlet Container 100% escrito em Java. É o grande concorrente do Tomcat e ficou famoso por ser utilizado como o servlet container do JBoss, um servidor de aplicações Java.

A grande vantagem do Jetty relativamente ao Tomcat é a sua fácil configuração. Além disso, foi pioneiro na implementação de I/O assíncrono, para aguentar uma carga maior de utilizadores em simultâneo, sem recorrer a *thread-per-connection*.

### Programação de um Jetty *embedded server*

```java
Server server = new Server(PORT);       
ServletHandler servletHandler = new ServletHandler();
server.setHandler(servletHandler);
servletHandler.addServletWithMapping(HelloServlet.class, "/");
server.start();
server.join();
```
```java
public static class HelloServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>THIS IS AN EMBEDDED SERVER</h1>"); 
	} 
}
```

# 2.3) Introduction to web apps with a full-featured framework (Spring Boot)
## Spring Boot
O Spring Boot, uma extensão da popular framework Spring para Java, tem como propósito permitir um desenvolvimento rápido e fácil de aplicações (nomeadamente, para a web), sem que o *developer* se tenha de preocupar muito com configurações e dependências.

### Inicialização de uma aplicação Web
- ```https://start.spring.io/```
- Selecionar ```Dependências > Spring Web```

### Spring Bean
Um Spring Bean é basicamente um objeto instanciado, configurado e gerido pelo Spring Framework *container*. Os beans são definidos em ficheiros de configuração Spring (ou, mais recentemente, com **anotações**).

### Programação de uma aplicação Web
```java
@Controller //retorna uma vista
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}
```
```java
@RestController //retorna um objeto do domínio como JSON
public class RESTGreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/restgreeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
```
A principal diferença entre um *controller* MVC tradicional e um *controller* de uma RESTful API é a forma como o corpo da resposta HTTP é criado. Em vez de entregar ao HTML, este segundo *controller* devolve um POJO (Plain Old Java Object), que é posteriormente apresentado em JSON.

### Thymeleaf
O Thymeleaf é um *template engine* para Java, que permite a criação de páginas HTML dinâmicas, com base em modelos.
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Getting Started: Serving Web Content</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>
```

# 2.4) "Wrapping-up & integrating concepts"

| Método | *Endpoint* 				   | Conteúdo           |
| -------|---------------------------- | ------------------ |
| GET    | api/quote       			   | Citação aleatória. |
| GET    | api/shows                   | Lista de séries.   |
| GET    | api/quotes?show=\<slug\>    | Citação aleatória de uma série específica |

>**Nota**: o campo *slug* é único e é equivalente a usar um ID numérico.

## Serialização de JSON para objetos Java
Considere-se ```response``` o conteúdo de uma resposta HTTP, num JSON array.
### org.json
```java
JSONArray shows = new JSONArray(response.toString());
List<ShowObject> results = new ArrayList<ShowObject>();
for (int i = 0 ; i < shows.length(); i++) {
    JSONObject show = shows.getJSONObject(i);
    String name = show.getString("name");
    String slug = show.getString("slug");
    results.add(new ShowObject(name, slug));
}
```
### com.google.gson
```java
Gson gson = new Gson();
Type showListType = new TypeToken<ArrayList<ShowObject>>(){}.getType();
ArrayList<ShowObject> results = gson.fromJson(response.toString(), showListType);
```

# "Review questions"
**A.** Ao contrário de um simples applet Java, um servlet não tem *main*. Consequentemente, deve ser executado dentro de um *container*.
Servlet *containers*, também designados como servlet *engines*, executam e gerem servlets, sendo habitualmente integrados num servidor Web. São responsáveis pela invocação dos métodos do servlet e pelo fornecimento das dependências. Permitem ainda um acesso fácil aos cabeçalhos e parâmetros do pedido HTTP. Quando um servlet é chamado, o servidor Web passa o pedido HTTP para o *container*. Este, por sua vez, entrega o pedido ao servlet.
Durante a gestão de um servlet, um servlet *container* executa as seguintes tarefas:
- Cria uma instância do servlet e chama o seu método init() para o inicializar.
- Encapsula pedidos e respostas em objetos, que são passados ao servlet.
- Invoca o método service() do servlet, que envia pedidos para os métodos doGet() ou doPost(), dependendo do cabeçalho HTTP no pedido (GET ou POST). Em servlets HTTP, esse método é geralmente *overridden* na classe HttpServlet.
- Invoca o método destroy() do servlet, para o descartar, quando apropriado. Por razões de desempenho, é fequente o *container* preservar uma instância de servlet em memória para reutilização, em vez de o destruir de ccada vez que tenha terminado a sua tarefa. O servlet é geralmente destruído em eventos pouco frequentes, tais como o encerramento do servidor Web.

**B.** A dinâmica MVC do SpringBoot foi desenhada em torno de um DispatcherServlet, que atua como *front controller*. Quando interceta um pedido HTTP, o DispatcherServlet escolhe o *handler*, para o qual aquele tipo de pedido está mapeado. O *handler* correspondente tanto pode ser um *controller* como um *view resolver*. O *controller* é responsável por processar o pedido e devolver um *ModelAndView*. O *view resolver* é responsável por resolver a vista, geralmente uma página HTML, mas não necessariamente. Por fim, o DispatcherServlet devolve a vista ao cliente.

Tomando como exemplo o tutorial "Greeting":
- O *model* é a classe Greeting, que encapsula o nome e o ID do utilizador.
- O *controller* é a classe GreetingController, que recebe pedidos HTTP e devolve uma *view*.
- A *view* é o template Thymeleaf, que recebe os dados do modelo e os renderiza.

**Arquitetura MVC**
- Modelo (Model): encapsula os dados da aplicação.
- Vista (View): renderiza os dados do modelo, gerando um output.
- Controlador (Controller): processa os pedidos do utilizador e entrega-os à *view* para renderização.

>*Nota*: O "spring-boot-starter-web" depende transitivamente do "spring-webmvc".

![DispatecherServlet](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/images/mvc.png)

A dinâmica MVC do SpringBoot é orientada a pedidos, tendo sido desenhada em torno de um Servlet central, que envia pedidos aos *controllers* e oferece outras funcionalidades que facilitam o desenvolvimento de aplicações web. O Spring's DispatcherServlet, no entanto, faz mais do que apenas isso.

**C.**

- **spring-boot-starter-web**: para desenvolvimento web, nomeadamente serviços RESTful.
- **spring-boot-starter-tomcat**: para usar o Tomcat como *embedded servlet container*. 
- **spring-boot-starter-test**: para testes unitários.
- **spring-boot-starter-thymeleaf** (não é automaticamente explicitado): para usar o Thymeleaf como *template engine*. 

Estas *starter packs*, por sua vez, têm várias dependências transitivas, que são automaticamente incluídas no projeto. Por exemplo, o "spring-boot-starter-web" depende transitivamente do "spring-webmvc".

**D.**
- ```@Configuration```: permite o registo de *beans* extra no contexto da aplicação, bem como a importação de mais classes de configuração.

- ```@EnableAutoConfiguration```: solicita ao Spring Boot que adicione *beans*, com base em várias configurações, entre os quais o *classpath*, e outros *beans*. Por exemplo, se o "Spring-webmvc" estiver no *classpath*, esta anotação assinala a aplicação como do tipo web e ativa comportamentos chave, tais como a criação de um DispatcherServlet.

- ```@ComponentScan```: permite procurar por outros componentes, configurações e serviços no *package* onde se encontra a aplicação.

**E.** 
	
**Boas práticas para o desenho de uma REST API**:

1. Encapsular os dados em JSON, visto que é atualmente um formato mais suportado pelas frameworks Web do que o XML, por exemplo.

2. Usar nomes em vez de verbos nos *endpoints*, pois os métodos HTTP já são verbos (POST, GET, DELETE,...).
	- Por exemplo, um *endpoint* para aceder a uma lista de livros não deve ser algo do género "library.com/getBooks", mas sim "library.com/books".

3. Usar o plural para nomear as coleções, pois é mais fácil para os humanos entender o seu significado, sem as abrir.

4. Adicionar a versão ao endpoint: por exemplo, "library.com/v1/books".

5. Permitir subrecursos, de modo a representar relacionamentos entre recursos. Por exemplo, constata-se intutivamente que "library.com/v1/students/102534/books" retorna a lista de livros do estudante com o ID "102534".


# Referências
https://www.baeldung.com/java-servers

https://docs.oracle.com/cd/A97688_16/generic.903/a97680/overview.htm

https://www.javatpoint.com/spring-boot-thymeleaf-view

https://www.javatpoint.com/container

https://www.educative.io/answers/web-server-vs-application-server

https://www.baeldung.com/jetty-embedded

https://www.baeldung.com/spring-bean

https://www.baeldung.com/spring-boot-starters

https://www.geeksforgeeks.org/spring-mvc-framework/

https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServlet.html

https://www.geeksforgeeks.org/difference-between-spring-boot-starter-web-and-spring-boot-starter-tomcat/

https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html

https://www.freecodecamp.org/news/rest-api-best-practices-rest-endpoint-design-examples/