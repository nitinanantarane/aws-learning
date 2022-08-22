# Quickstart

mvn -B archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DgroupId=com.nitinrane.learning.aws -DartifactId=quickstart 

mvn package

mvn exec:java -Dexec.mainClass="com.nitinrane.learning.aws.QuickstartApp"