## Docker Template

This project includes the Spotify **docker-maven-plugin**. 
It is used to automate the creating of docker container and downloading
of the **openjdk:8-jdk-alpine** image.

The Dockerfile contains:

<pre>
FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ARG JAR_FILE
ADD target/dockertemplate-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["java","-jar","/usr/share/app.jar"]
</pre>

1) Port 8080 is exposed to allow access to the webserver
2) Create /tmp folder to speed resource extraction
3) If using **ONLY** Maven to provision/build/deploy, JAR_FILE is 
a POM plugin configuration setting set to the name of the final `.jar` 
4) Since I use IntelliJ to interface with Docker directly, the POM
setting doesn't take effect - so the final name is used directly
5) The Entrypoint is the command line command needed to run the application.

#### To use this with IntelliJ
You'll need to enable the Docker plugin. Then you'll 
have to set the port mapping to 
map port 8080 on the image to some port on the host. To do that;
1) Open the Services tab (at the bottom)
2) select the Dockerfile
3) click the pencil icon on the left
4) In the pop up window, add the port mapping (click the ? at the bottom for detailed instrucctions)

#### To use this with Maven inside IntelliJ
1) Expand the `Maven` tab (top right side of IDE)
2) Expand `Plugins` under project name
3) Expand `dockerfile` 
4) Use the Maven actions that are listed there as needed


#### POM file property settings:
    <properties>
        <java.version>1.8</java.version>
        <dockerfile.version>1.4.1</dockerfile.version>
        <docker.image.prefix>CHANGEME</docker.image.prefix>
    </properties>

#### POM file Plugin  

           <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>${dockerfile.version}</version>
                <!-- Wire up to the default build phases -->
                <executions>
                    <execution>
                        <id>default</id>
                        <goals>
                            <goal>build</goal>
                            <goal>push</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <repository>${docker.image.prefix}/${project.artifactId}</repository>
                    <writeTestMetadata>false</writeTestMetadata>
                    <buildArgs>
                        <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                    </buildArgs>
                </configuration>
                <dependencies>
                    <!-- To make this work on JDK 9+ -->
                    <dependency>
                        <groupId>javax.activation</groupId>
                        <artifactId>javax.activation-api</artifactId>
                        <version>1.2.0</version>
                    </dependency>
                </dependencies>
            </plugin>
