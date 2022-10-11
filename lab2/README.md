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


Thymeleaf is a modern server-side Java template engine for both web and standalone environments.

Thymeleaf's main goal is to bring elegant natural templates to your development workflow — HTML that can be correctly displayed in browsers and also work as static prototypes, allowing for stronger collaboration in development teams.


# Other
Java2EE
JavaEE
JakartaEE (V9)


Packaging
pom -> jar -> jre
    -> war -> Web container
           -> Application container

# Referências
https://www.baeldung.com/java-servers
