# 2.1 - "Server-side programming with servlets"

Dentro da pasta bin,
chmod +x *.sh
./startup.sh
./shutdown.sh

http://localhost:8080
tail logs/catalina.out

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

http://localhost:8080/examples/servlets/reqparams.html
https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServlet.html

pload and deploy a .war 
file (http://localhost:8080/manager). 
or 
copy the .war file into <Tomcat root>/webapps

http://localhost:8080/WebProject-1.0-SNAPSHOT/
https://howtodoinjava.com/java/servlets/complete-java-servlets-tutorial#webservlet_annotation


HTTP Status 500 – Internal Server Error

Type Exception Report

Message Servlet execution threw an exception

Description The server encountered an unexpected condition that prevented it from fulfilling the request.

Exception

javax.servlet.ServletException: Servlet execution threw an exception
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)

Root Cause

java.lang.Error: Unresolved compilation problem: 
	The operator + is undefined for the argument type(s) int, null

	pt.ua.ies.MyFirstServlet.doGet(MyFirstServlet.java:18)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:670)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:779)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)

Note The full stack trace of the root cause is available in the server logs.

int x = 5 + null; //this will throw an exception
/logs/localhost.<date>.log
08-Oct-2022 21:48:06.620 SEVERE [http-nio-8080-exec-21] org.apache.catalina.core.StandardWrapperValve.invoke Servlet.service() for servlet [MyFirstServlet] in context with path [/WebProject-1.0-SNAPSHOT] threw exception [Servlet execution threw an exception] with root cause
	java.lang.Error: Unresolved compilation problem: 
	The operator + is undefined for the argument type(s) int, null

		at pt.ua.ies.MyFirstServlet.doGet(MyFirstServlet.java:18)
		at javax.servlet.http.HttpServlet.service(HttpServlet.java:670)
		at javax.servlet.http.HttpServlet.service(HttpServlet.java:779)
		at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)
		at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
		at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
		at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
		at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
		at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)
		at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)
		at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)
		at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)
		at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
		at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:687)
		at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)
		at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)
		at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)
		at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
		at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:893)
		at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1789)
		at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
		at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
		at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
		at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
		at java.base/java.lang.Thread.run(Thread.java:833)

http://localhost:8080/WebProject-1.0-SNAPSHOT/ServletToValidate?username=username&password=guest


Payara-Web 5.2022.3 (Web Profile) 
/bin/asadmin start-domain
After a few seconds, Payara Server will be up and ready to accept requests. The default 'domain1' domain is configured to listen on port 8080. In your browser, go to http://localhost:8080 to see the default landing page.
To manage Payara Server, just go to web administration console: http://localhost:4848
./bin/asadmin stop-domain



# 2.2

<packaging>war</packaging>
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-server</artifactId>
    <version>9.2.15.v20160210</version>
</dependency>
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-servlet</artifactId>
    <version>9.2.15.v20160210</version>
</dependency>


# 2.3


Create a Web Controller

In Spring’s approach to building web sites, HTTP requests are handled by a controller. You can easily identify the controller by the @Controller annotation. In the following example, GreetingController handles GET requests for /greeting by returning the name of a View (in this case, greeting). A View is responsible for rendering the HTML content. The following listing (from src/main/java/com/example/servingwebcontent/GreetingController.java) shows the controller:


This controller is concise and simple, but there is plenty going on. We break it down step by step.

The @GetMapping annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.

@RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method. This query string parameter is not required. If it is absent in the request, the defaultValue of World is used. The value of the name parameter is added to a Model object, ultimately making it accessible to the view template.

This code uses Spring @RestController annotation, which marks the class as a controller where every method returns a domain object instead of a view. It is shorthand for including both @Controller and @ResponseBody.

A key difference between a traditional MVC controller and the RESTful web service controller shown earlier is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service controller populates and returns a Greeting object

Thymeleaf is a modern server-side Java template engine for both web and standalone environments.

Thymeleaf's main goal is to bring elegant natural templates to your development workflow — HTML that can be correctly displayed in browsers and also work as static prototypes, allowing for stronger collaboration in development teams.


# 2.4

            Gson gson = new Gson();
            Type showListType = new TypeToken<ArrayList<ShowObject>>(){}.getType();
            ArrayList<ShowObject> results = gson.fromJson(response.toString(), showListType); 
            
            // JSONArray shows = new JSONArray(response.toString());
            // List<ShowObject> results = new ArrayList<ShowObject>();
            // for (int i = 0 ; i < shows.length(); i++) {
            //     JSONObject show = shows.getJSONObject(i);
            //     String name = show.getString("name");
            //     String slug = show.getString("slug");
            //     results.add(new ShowObject(name, slug));
            // }


# "Review questions"
**A.** Ao contrário de um simples applet Java, um servlet não tem *main*. Consequentemente, deve ser executado dentro de um *container*.
Servlet *containers*, também designados como servlet *engines*, executam e gerem servlets, sendo habitualmente integrados num servidor Web. São responsáveis pela invocação dos métodos do servlet e pelo fornecimento das dependências. Permitem ainda um acesso fácil aos cabeçalhos e parâmetros do pedido HTTP. Quando um servlet é chamado, o servidor Web passa o pedido HTTP para o *container*. Este, por sua vez, entrega o pedido ao servlet.
Durante a gestão de um servlet, um servlet *container* executa as seguintes tarefas:
- Cria uma instância do servlet e chama o seu método init() para o inicializar.
- Encapsula pedidos e respostas em objetos, que são passados ao servlet.
- Invoca o método service() do servlet, que envia pedidos para os métodos doGet() ou doPost(), dependendo do cabeçalho HTTP no pedido (GET ou POST). Em servlets HTTP, esse método é geralmente *overridden* na classe HttpServlet.
- Invoca o método destroy() do servlet, para o descartar, quando apropriado. Por razões de desempenho, é fequente o *container* preservar uma instância de servlet em memória para reutilização, em vez de o destruir de ccada vez que tenha terminado a sua tarefa. O servlet é geralmente destruído em eventos pouco frequentes, tais como o encerramento do servidor Web.

**B.** Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve 
web content. (You may exemplify with the context of the previous exercises.)


The Spring Web model-view-controller (MVC) framework is designed around a DispatcherServlet that dispatches requests to handlers, with configurable handler mappings, view resolution, locale and theme resolution as well as support for uploading files. The default handler is based on the @Controller and @RequestMapping annotations, offering a wide range of flexible handling methods. With the introduction of Spring 3.0, the @Controller mechanism also allows you to create RESTful Web sites and applications, through the @PathVariable annotation and other features.

In Spring Web MVC you can use any object as a command or form-backing object; you do not need to implement a framework-specific interface or base class. Spring's data binding is highly flexible: for example, it treats type mismatches as validation errors that can be evaluated by the application, not as system errors. Thus you need not duplicate your business objects' properties as simple, untyped strings in your form objects simply to handle invalid submissions, or to convert the Strings properly. Instead, it is often preferable to bind directly to your business objects.


https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html

**C**. Inspect the POM.xml for the previous Spring Boot projects. What is the role of the “starters” 
dependencies?
https://www.baeldung.com/spring-boot-starters
Spring Boot Starter Web is used for building RESTful applications using Spring MVC. Spring Boot Starter Tomcat is the default embedded container for Spring Boot Starter Web. We cannot exclude it while using web services. We can exclude it when we want to use another embedded container.

**D.** Which annotations are transitively included in the @SpringBootApplication?

@SpringBootApplication is a convenience annotation that adds all of the following:

    @Configuration: Tags the class as a source of bean definitions for the application context.

    @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.

    @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.


https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html

**E.** Search online for the topic “Best practices for REST API design”. From what you could learn, 
select your “top 5” practices, and briefly explain them in you own words. 
https://www.freecodecamp.org/news/rest-api-best-practices-rest-endpoint-design-examples/

pode usar-se API externa para fornecer dados à nossa própria API
retornar um array de JSONs

# Other
Java2EE
JavaEE
JakartaEE (V9)


Packaging
pom -> jar -> jre
    -> war -> Web container
           -> Application container

JavaEE -> JakartaEE ----|> SpringBoot

JakartaEE -> specifications
	  -> implementations

# Imagens
http://www.programcreek.com/wp-content/uploads/2013/04/servlet-container-life-cycle.jpg


# Referências
https://www.baeldung.com/java-servers
https://docs.oracle.com/cd/A97688_16/generic.903/a97680/overview.htm


https://www.javatpoint.com/spring-boot-thymeleaf-view

https://www.javatpoint.com/container
https://www.baeldung.com/spring-boot-starters
https://www.geeksforgeeks.org/difference-between-spring-boot-starter-web-and-spring-boot-starter-tomcat/
https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html

pode usar-se API externa para fornecer dados à nossa própria API (não tem id, mas slug)
retornar um array de JSONs
