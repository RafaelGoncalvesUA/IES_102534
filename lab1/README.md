# 1.1 - "Basic setup for Java development"
## Configuração do Maven
No contexto empresarial, os developers devem optar por versões Long-Term Support (LTS) do Java, para garantir a sua estabilidade.
Atualmente, recomenda-se a versão OpenJDK 17 LTS. Contudo, neste trabalho prático, recorri a uma mais recente: OpenJDK 18.

```javac -version```: verifica se o JDK está instalado e obter a sua versão.

```sudo update-alternatives --config java```: atualiza versão do comando "java".

```sudo update-alternatives --config javac```: atualiza versão do comando "javac".

```echo $JAVA_HOME```: retorna a raiz do JDK (não do JRE).

```mvn -version```: retorna a versão do Maven, a sua localização e a versão do Java, entre outras informações.

## Configuração do Git
```git -version```: retorna a versão do Git.

```git config --list```: retorna a atual configuração do Git.

# 1.2 - "Build management with the Maven tool"
## Criação de projeto Maven

```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -
DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -
DinteractiveMode=false
```

Dos parâmetros indicados, destacam-se as seguintes convenções:
- groupID: identifica unicamente um projeto e deve seguir as regras de nomencaltura dos Java packages.
- artifactID: corresponde ao nome do JAR resultante, excluindo a versão.

Na linha de comandos, o prefixo "-D" é usado para definir uma propriedade do Maven.

### Estrutura do projeto Maven (após build)
```
.
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── mycompany
│   │               └── app
│   │                   └── App.java
│   └── test
│       └── java
│           └── com
│               └── mycompany
│                   └── app
│                       └── AppTest.java
└── target
    ├── classes
    │   └── com
    │       └── mycompany
    │           └── app
    │               └── App.class
    └── test-classes
        └── com
            └── mycompany
                └── app
                    └── AppTest.class
```

## Project Object Model (pom.xml)

### Codificação e versão do compilador
```
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>18</maven.compiler.source>
    <maven.compiler.target>18</maven.compiler.target>
</properties>
```

### Declaração de dependências
```
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>com.squareup.retrofit2</groupId>
      <artifactId>retrofit</artifactId>
      <version>2.9.0</version>
    </dependency>
    <dependency>
      <groupId>com.squareup.retrofit2</groupId>
      <artifactId>converter-gson</artifactId>
      <version>2.9.0</version>
    </dependency>
</dependencies>
```
No POM, declaram-se as dependências diretas. Estes "artifacts", por sua vez, têm outras dependências, formando um grafo de dependências.

### Codificação e versão do compilador
```
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>18</maven.compiler.source>
    <maven.compiler.target>18</maven.compiler.target>
</properties>
```

### Organização e equipa de desenvolvimento
```
<organization>
    <name>Universidade de Aveiro</name>
    <url>http://www.ua.pt</url>
</organization>

<developers>
    <developer>
        <id>102534</id>
        <name>Rafael Fernandes Gonçalves</name>
        <email>rfg@ua.pt</email>
        <url>https://www.linkedin.com/in/rafael-goncalves-ua/</url>
        <organization>DETI</organization>
        <organizationUrl>https://www.ua.pt/pt/deti</organizationUrl>
        <roles>
            <role>Aluno de IES</role>
            <role>Java Developer</role>
        </roles>
    </developer>
</developers>
```

## Cliente HTTP no terminal
```curl http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1010500.json | json_pp```

## Retrofit
Esta ferramenta permite transformar uma HTTP API numa interface Java.
```
public interface IpmaService {
    @GET("forecast/meteorology/cities/daily/{city_id}.json")
    Call<IpmaCityForecast> getForecastForACity(@Path("city_id") int cityId);
}
```

> **_NOTE:_** Em Java, o nome de uma anotação (annotation) começa com o símbolo @. Este recurso permite associar informação suplementar (metadata) a elementos do programa, tais como variáveis, métodos ou classes. A sua declaração é similiar à das interfaces, iniciando-se por "public @interface [nome]".

## Gson
As <em>annotations</em> desta ferramenta permitem a serialização para JSON de um dado membro de uma classe.
```
public class CityForecast {
    [...]
    @SerializedName("tMax")
    @Expose
    private String tMax;
    [...]
    //getters e setters
}
```

## Compilação e execução de um projeto Maven
``` mvn package```: obtém as dependências, compila o projeto e cria um JAR.

```mvn exec:java -Dexec.mainClass="weather.WeatherStarter"```: executa o projeto, referenciando a classe principal.

```mvn exec:java -Dexec.mainClass="pt.ua.ies.WeatherStarter" -Dexec.args="1010500"```: executa o projeto, referenciando a classe principal e fornecendo-lhe **argumentos**.

# 1.3) "Source code management using Git"
## Criação de um repositório
```git init```: inicia um repositório Git local no diretório corrente.

```git remote add origin <REMOTE_URL>```: associa um repositório remoto (por exemplo, no GitHub).

```git add . ```: indica que todas as alterações no diretório corrente serão carregadas para o repositório Git local, aquando do <em>commit</em>.

```git commit -m "first commit"```: cria um <em>commit snapshot</em> localmente.

```git push -u origin main"```: carrega o <em>commit</em> local para o repositório Git remoto. A opção "-u" indica que o <em>push</em> será feito no sentido <em>upstream</em>.

## Gestão dos ficheiros a rastrear
- .gitkeep
- .gitignore

Templates para ```.gitignore```: https://github.com/github/gitignore

## Criação de um log com Log4j 2
O ficheiro de configuração ```log4j2.xml``` pode ser colocado em qualquer subdiretório do classpath da aplicação. Porém, é habitualmente colocado na pasta ```src/main/resources```.

**Guia passo-a-passo**: https://howtodoinjava.com/log4j2/log4j2-xml-configuration-example/

### Escrever para um log:

```
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(final String... args) 
	{
		logger.debug("Debug Message Logged !!!");
		logger.info("Info Message Logged !!!");
		logger.error("Error Message Logged !!!", new NullPointerException("NullError"));
	}
}
```

## Histórico do repositório
```git log --reverse --oneline```: ver o histórico do repositório (commit messages).

Este deve ser claro e explícito para os restantes colaboradores do projeto.



# "Review questions"
**a)**