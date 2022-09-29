#Script que gera rapidamente um projeto Maven, a partir de dois argumentos fornecidos pelo utilizador:
#$1 = groupID
#$2 = artifactID
mvn archetype:generate -DgroupId=$1 -DartifactId=$2 -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false