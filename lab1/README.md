# 1.1 - "Basic setup for Java development"
## Configuração do Maven
No contexto empresarial, os developers devem optar por versões Long-Term Support (LTS) do Java, para garantir a sua **estabilidade**.
Atualmente, recomenda-se a versão OpenJDK 17 LTS. Contudo, neste trabalho prático, recorri a uma mais recente: OpenJDK 18.

```javac -version```: verifica se o JDK está instalado e retorna a sua versão.

```sudo update-alternatives --config java```: atualiza versão do comando "java".

```sudo update-alternatives --config javac```: atualiza versão do comando "javac".

```echo $JAVA_HOME```: retorna a raiz do JDK (não do JRE).

```mvn -version```: retorna a versão do Maven, a sua localização e a versão do Java, entre outras informações.

## Configuração do Git
```git -version```: retorna a versão do Git.

```git config --list```: retorna a atual configuração do Git.

# 1.2 - "Build management with the Maven tool"
## Criação de um projeto Maven

```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -
DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -
DinteractiveMode=false
```

Dos parâmetros indicados, destacam-se duas **Maven *coordinates***:
- **groupID**: identifica unicamente um projeto e deve seguir as regras de nomencaltura dos Java *packages*.
- **artifactID**: corresponde ao nome do JAR resultante, excluindo a versão.

> **Nota:** Na linha de comandos, o prefixo "-D" é usado para definir uma propriedade do Maven.

O nome completo de um projeto Maven segue a sintaxe ```<GroupId>:<artifactId>:<version>```, sendo a *version* inicial ```"1.0-SNAPSHOT”```, por predefinição.

### Estrutura de um projeto Maven (antes da build)
```
.
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── mycompany
    │               └── app
    │                   └── App.java
    └── test
        └── java
            └── com
                └── mycompany
                    └── app
                        └── AppTest.java
```

Após a build, é criada uma pasta target, que incluirá o JAR do projeto.

## Maven Build Lifecycle
Agrega 3 *lifecycles*:
- **Default**: responsável pelo *deployment* do projeto.
- **Clean**: responsável pela limpeza do projeto.
- **Site**: responsável pela criação da documentação do site do projeto.

## Tipos de repositórios Maven
1. **Repositório Local**: reside na máquina do *developer*.

2. **Repositório Remoto**: contém *artifacts* privados de determinada organização.

3. **Repositório Central**: fornece as dependências disponíveis na comunidade Maven, quando essas não estão presentes no repositório local.

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
No POM, declaram-se as dependências diretas. Estes *artifacts*, por sua vez, têm outras dependências, formando um grafo de dependências.

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

> **Nota:** Em Java, o nome de uma anotação (annotation) começa com o símbolo @. Este recurso permite associar informação suplementar (metadata) a elementos do programa, tais como variáveis, métodos ou classes. A sua declaração é similiar à das interfaces, iniciando-se por "public @interface [nome]".

## Gson
As *annotations* desta ferramenta permitem a serialização para JSON de um dado membro de uma classe.
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
```mvn clean```: limpa o projeto, removendo todos os ficheiros gerados pela *build* anterior.

```mvn compile```: obtém as dependências e compila o projeto.

```mvn package```: obtém as dependências, compila o projeto e cria um JAR.

```mvn install```: obtém as dependências, compila o projeto, cria um JAR e copia o código "empacotado" (*package code*) para o repositório local.

```mvn exec:java -Dexec.mainClass="pt.ua.ies.WeatherStarter"```: executa o projeto, referenciando a classe principal.

```mvn exec:java -Dexec.mainClass="pt.ua.ies.WeatherStarter" -Dexec.args="1010500"```: executa o projeto, referenciando a classe principal e fornecendo-lhe **argumentos**.

# 1.3) "Source code management using Git"
## Criação de um repositório
```git init```: inicia um repositório Git local no diretório corrente.

```git remote add origin <REMOTE_URL>```: associa um repositório remoto (por exemplo, do GitHub).

```git add . ```: indica que todas as alterações no diretório corrente devem ser carregadas para o repositório Git local, aquando do *commit*.

```git commit -m "first commit"```: cria um *commit snapshot* localmente.

```git push -u origin main"```: envia o *commit* local para o repositório Git remoto. A opção "-u" indica que o *push* será feito no sentido *upstream*.

## Gestão dos ficheiros a rastrear
- .gitkeep
- .gitignore

Templates para ```.gitignore```: https://github.com/github/gitignore

## Criação de um log com Log4j 2
O ficheiro de configuração ```log4j2.xml``` pode ser colocado em qualquer subdiretório do *classpath* da aplicação. Porém, é habitualmente colocado na pasta ```src/main/resources```.

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
		logger.error("Error Message Logged !!!",
        new NullPointerException("NullError"));
	}
}
```

## Histórico do repositório
```git log --reverse --oneline```: ver o histórico do repositório (*commit* messages).

Este deve ser claro e explícito para os restantes colaboradores do projeto.

# 1.4) "Introduction to Docker"
## Níveis de virtualização
- Máquinas virtuais
- Linux Container (LXC) | Docker container/swarm
- Kubernetes

## Configuração do Docker

1. Configurar o repositório Docker, seguindo estes passos: https://docs.docker.com/engine/install/ubuntu/
2. Instalar o Docker Engine:

    ```sudo apt-get update```

    ```sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin```

3. Verificar a instalação, correndo a imagem "hello-world":

    ```sudo service docker start```

    ```docker container ls -a```

4. Instalar o Docker Desktop (facultativo):

    ```sudo apt-get update```

    ```sudo apt-get install ./docker-desktop-<version>-<arch>.deb```

### Autenticação no Docker
É possível alternar entre o Docker Desktop e o Docker *daemon*: 

```docker context ls```

```docker context use [CONTEXTNAME]```

### Gestão do Docker como utilizador *non-root*
- ```sudo groupadd docker```: criar um grupo "docker".

- ```sudo usermod -aG docker $USER```: adicionar o utilizador ao grupo "docker".

- Log out e log back

## Correr uma imagem num container

 ```docker run -d -p 80:80 docker/getting-started```
- "-d": corre um container em *background* (como *daemon*).
- "-p 80:80": mapeia a porta 80 do *host* para a porta 80 do *container*.
- "docker/getting-started" - imagem a utilizar. Pode estar já na máquina ou pode ser necessário um *pull* da Docker Hub.

Outras opções para o comando ```docker run```:
- --rm: remove automaticamente o container e o sistema de ficheiros associados, aquando do seu encerramento.
- --name: nome identificativo do *container*. 
- -v: persistir os dados do *container* num volume montado numa pasta local.

## Criar uma imagem de uma aplicação

Tutorial recomendado: https://docs.docker.com/get-started/02_our_app/

Comando importante: ```docker build -t getting-started .```

## Portainer (alternativa ao Docker Desktop para browser)
```docker volume create portainer_data```: criar um volume para o Portainer.

```docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:latest https://localhost:9443```: correr o Portainer num container, fazendo *pull* da sua imagem, se necessário.

## Correr PostegreSQL com *default image*
```docker run --name pg-docker -e POSTGRES_PASSWORD=docker -e POSTGRES_DB=sampledb -e PGDATA=/tmp -d -p 5433:5432 -v ${PWD}:/var/lib/postgresql/data postgres:11```

- Nome do container: pg-docker

- Imagem/Repositório: postgres

- Tag: 11 (consultar na Docker Hub)

> **Nota**: O Docker *daemon*, também conhecido como *dockerd*, é um processo que gere objetos Docker (imagens, *containers*, volumes, etc.). Por isso, para executar comandos Docker, pode ser necessário iniciar o *daemon* manualmente, escrevendo no terminal ```sudo service docker start```.

```docker stop pg-docker```: parar a execução do *container* "pg-docker".

```docker start pg-docker```: executar o *container* pg-docker.

```docker exec -it pg-docker psql -U postgres```: iniciar o postgres no container.

## Serviços múltiplos (Docker compose)
### Dockerfile
```
# syntax=docker/dockerfile:1
FROM python:3.7-alpine
WORKDIR /code
ENV FLASK_APP=app.py
ENV FLASK_RUN_HOST=0.0.0.0
RUN apk add --no-cache gcc musl-dev linux-headers
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt
EXPOSE 5000
COPY . .
CMD ["flask", "run"]
```
### docker-compose.yml
```
version: "3.9"
services:
  web:
    build: .
    ports:
      - "5500:5000"
  redis:
    image: "redis:alpine"
```
### Execução dos serviços
```docker compose up```

## Outros comandos Docker
```docker container [ls/rm/exec] [argumentos]```
```docker volume [ls/rm/create] [argumentos]```
```docker image [ls/rm/pull] [argumentos]```


# 1.5) "Wrapping-up & integrating concepts"
## Projeto Maven 1
**Nome**: IpmaApiClient

**Dependências**: Retrofit / Gson

## Projeto Maven 2
**Nome**: WeatherForecastByCity

**Dependências**: IpmaApiClient (Projeto 1)

## Instalação do projeto 1 no repositório Maven local
```cd IpmaApiClient```

```mvn clean install```

## Execução do projeto 2
```cd ../WeatherForecastByCity"```

```mvn package```

```mvn exec:java -Dexec.mainClass="pt.ua.ies.WeatherForecastByCity.WeatherStarter" -Dexec.args="1010500"```



# "Review questions"
**a)** Fases do *default lifecyle*:
- **Validate**: validação da estrutura do projeto. Nesta fase, verifica-se, por exemplo, se todos as dependências foram carregadas para o repositório local.
- **Compile**: compilação do código fonte e conversão dos ficheiros .java em .class.
- **Test**: execução de testes unitários.
- **Package**: transformação do código compilado num formato distribuído, como o JAR.
- **Integration test**: execução de testes de integração.
- **Verify**: verficação do cumprimento dos padrões de qualidade.
- **Install**: cópia do código "empacotado" (*packaged code*) para o repositório local Maven.
- **Deploy**: cópia do código "empacotado" (*packaged code*) para o repositório remoto Maven, ficando disponível para outros *developers*.

**b)** Sim, é apropriado para executar o projeto. Isso pode ser feito com o comando:

```mvn exec:java -Dexec.mainClass="<groupId>.MainClass" -Dexec.args="<lista de argumentos>"```

**c)** Se o repositório já tiver sido clonado previamente:

- ```git pull``` 

- [desenvolvimento do novo incremento]

- ```git add .```

- ```git commit -m "feat newFeatureName"```

- ```git push -u origin main``` 


**d)** O *developer* deve assumir que os seus colaboradores não entendem o contexto do commit. Uma boa prática é refletir sobre o motivo, a localização e os efeitos das alterações. Além disso, surgiram ainda algumas convenções, tais como:
- **feat** – adição de nova funcionalidade.
- **fix** – correção de um bug.
- **refactor** – código refinado, sem correção de bugs ou adição de funcionalidades.
- **docs** – atualização de documentação.
- **style** – alterações que não afetam o propósito do código, mas apenas a sua formatação.
- **test** – inclusão de novos testes ou correção de testes prévios.
- **perf** – melhorias de performance.
- **build** – alterações que afetam a*build* ou dependências externas.
- **revert** – reversão de um *commit* anterior. 


**e)** Frequentemente, é necessário partilhar ficheiros entre *containers*, permitindo um acesso contínuo das aplicações não só a bases de dados, como a logs e conteúdo gerado pelo utilizador. Uma base de dados de produção (*production database*), como o nome sugere, contém dados que estão a ser usados para atividades de produção, como a criação ou a atualização de funcionalidades de um produto. Como tal, devem manter-se inalterados, independentemente do ciclo de vida do *container* envolvente. Uma solução para alcançar essa persistência dos dados é a configuração de um volume Docker, que está associado a um diretório do *host* subjacente e é gerido pelo Docker, em tempo de execução.


# Referências
https://www.geeksforgeeks.org/maven-lifecycle-and-basic-maven-commands/
https://www.simplilearn.com/tutorials/maven-tutorial/maven-interview-questions
https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
https://www.docker.com/blog/top-tips-and-use-cases-for-managing-your-volumes/
https://www.educative.io/answers/what-is-the-docker-container-lifecycle