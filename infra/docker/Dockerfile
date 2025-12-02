FROM tomcat:9.0-jdk11-openjdk

# Install unzip and wget
RUN apt-get update && apt-get install -y unzip wget

# Download Apache ODE
ENV ODE_VERSION=1.3.8
RUN wget https://archive.apache.org/dist/ode/apache-ode-war-${ODE_VERSION}.zip -O /tmp/ode.zip \
    && unzip /tmp/ode.zip -d /tmp/ode \
    && mv /tmp/ode/apache-ode-war-${ODE_VERSION}/ode.war $CATALINA_HOME/webapps/ode.war \
    && rm -rf /tmp/ode.zip /tmp/ode

# Download missing dependencies for Java 11 / Tomcat 9 compatibility (Xalan Serializer)
RUN wget https://repo1.maven.org/maven2/xalan/serializer/2.7.1/serializer-2.7.1.jar -O $CATALINA_HOME/lib/serializer.jar \
    && wget https://repo1.maven.org/maven2/xalan/xalan/2.7.1/xalan-2.7.1.jar -O $CATALINA_HOME/lib/xalan.jar

# Manually expand the WAR file to copy processes into it
RUN mkdir -p $CATALINA_HOME/webapps/ode \
    && cd $CATALINA_HOME/webapps/ode \
    && jar -xvf ../ode.war \
    && rm ../ode.war

# Copy the BPEL processes into the ODE processes directory
COPY processes/src/main/resources/processes/ManufacturerProcess $CATALINA_HOME/webapps/ode/WEB-INF/processes/ManufacturerProcess
COPY processes/src/main/resources/processes/ShipperProcess $CATALINA_HOME/webapps/ode/WEB-INF/processes/ShipperProcess
COPY processes/src/main/resources/processes/StoreProcess $CATALINA_HOME/webapps/ode/WEB-INF/processes/StoreProcess

EXPOSE 8080
CMD ["catalina.sh", "run"]
