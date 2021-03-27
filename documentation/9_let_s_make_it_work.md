## 9 Let's make it work
### Use the Makefile to compile and test the project
Having a look at the **Makefile** file will show you which script with what parameter is used.

It is strongly advice to have a loo at it.  
In this case you should be able to use or rewrite the script in a way that suits you better.

### Requirements

#### Install the needed tools to
- docker and docker-compose
- kubectl command

#### Fill an .env file
To use it, you will need to have the following files:
- **~/.env** with the following content.   
  If you don't wish to have or change your .env file, you can do it in ht e.project.env file.   
  Just adapt the Makefile by removing first line if needed.  
  

  **.env file**:
```
############################### DEVELOPMENT COMMONS VARIABLE ################################
export DOCKER_PROJECT_REGISTRY={{DOCKER_PROJECT_REGISTRY}}
export DOCKER_REGISTRY_PASSWORD={{DOCKER_REGISTRY_PASSWORD}}
export DOCKER_REGISTRY_URL={{DOCKER_REGISTRY_URL}}
export DOCKER_REGISTRY_USERNAME={{DOCKER_REGISTRY_USERNAME}}

export KUBERNETES_API_SERVER={{KUBERNETES_API_SERVER}}
export KUBERNETES_CERTIFICATE_AUTHORITY_DATA={{KUBERNETES_CERTIFICATE_AUTHORITY_DATA}}
export KUBERNETES_SECRET_NAME={{KUBERNETES_SECRET_NAME}}
export KUBERNETES_TOKEN={{KUBERNETES_TOKEN}}

export MAVEN_REPOSITORY_PASSWORD={{MAVEN_REPOSITORY_PASSWORD}}
export MAVEN_REPOSITORY_RELEASES={{MAVEN_REPOSITORY_RELEASES}}
export MAVEN_REPOSITORY_SNAPSHOTS={{MAVEN_REPOSITORY_SNAPSHOTS}}
export MAVEN_REPOSITORY_URL={{MAVEN_REPOSITORY_URL}}
export MAVEN_REPOSITORY_USERNAME={{MAVEN_REPOSITORY_USERNAME}}

export SONARQUBE_CREDENTIALS={{SONARQUBE_CREDENTIALS}}
export SONARQUBE_URL={{SONARQUBE_URL}}
################################################################
```

- the **./pipelines/.project.env** can ovverride the value of the .env if it suits you better.  


  **./pipelines/.project.enve**:
```
############################### DEVELOPMENT COMMONS VARIABLE ################################
###############  This variables are define in the $(HOME)/.env file in principle. ###########
###############  You can create it or surcharge its values here.                  ###########

#export DOCKER_PROJECT_REGISTRY=${DOCKER_PROJECT_REGISTRY}
#export DOCKER_REGISTRY_PASSWORD=${DOCKER_REGISTRY_PASSWORD}
#export DOCKER_REGISTRY_URL=${DOCKER_REGISTRY_URL}
#export DOCKER_REGISTRY_USERNAME=${DOCKER_REGISTRY_USERNAME}

#export KUBERNETES_API_SERVER=${KUBERNETES_API_SERVER}
#export KUBERNETES_CERTIFICATE_AUTHORITY_DATA=${KUBERNETES_CERTIFICATE_AUTHORITY_DATA}
#export KUBERNETES_SECRET_NAME=${KUBERNETES_SECRET_NAME}
#export KUBERNETES_TOKEN=${KUBERNETES_TOKEN}

#export MAVEN_REPOSITORY_PASSWORD=${MAVEN_REPOSITORY_PASSWORD}
#export MAVEN_REPOSITORY_RELEASES=${MAVEN_REPOSITORY_RELEASES}
#export MAVEN_REPOSITORY_SNAPSHOTS=${MAVEN_REPOSITORY_SNAPSHOTS}
#export MAVEN_REPOSITORY_URL=${MAVEN_REPOSITORY_URL}
#export MAVEN_REPOSITORY_USERNAME=${MAVEN_REPOSITORY_USERNAME}

#export SONARQUBE_CREDENTIALS=${SONARQUBE_CREDENTIALS}
#export SONARQUBE_URL=${SONARQUBE_URL}
##############################################################################################


############################### GENERIC DEVELOPMENT VARIABLES ################################
export VERSION=$(shell git describe --tags --always)
export DOCKER_IMAGE=$(shell echo "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${VERSION}")

export GRADLE_SETTINGS=--rerun-tasks
export MAVEN_REPOSITORY=$(shell echo "${HOME}/.m2/repository")
export MAVEN_SETTINGS=$(shell echo "-s ${MAVEN_SETTINGS_XML} -Dmaven_repository_username=${MAVEN_REPOSITORY_USERNAME} -Dmaven_repository_password=${MAVEN_REPOSITORY_PASSWORD} -Dmaven_repository_url=${MAVEN_REPOSITORY_URL} -Dsonarqube_url=${SONARQUBE_URL}")
export MAVEN_SETTINGS_XML=pipeline/.m2/settings.xml

export CUKEDOCTOR_MAIN_VERSION=3.5.1
export CUKEDOCTOR_MAIN_JAR=$(shell echo "${MAVEN_REPOSITORY}/com/github/cukedoctor/cukedoctor-main/${CUKEDOCTOR_MAIN_VERSION}/cukedoctor-main-${CUKEDOCTOR_MAIN_VERSION}.jar")
##############################################################################################

############################### BUILD PROJECT DEVELOPMENT VARIABLES ###########################
export APP_NAME=trippricer
export E2E_TEST_MODE=DOCKER

export BUILD_TYPE=maven
export CONTAINER_BUILD_TYPE=maven
export HOST={{HOST}} // The HOST here is the base URL to reach your application 
export LOG_PATH={{LOG_PATH}} // Path where hte application logs will be written 
##############################################################################################
```

The placeholders (between double brackets) need to be fill with the correct values.  
Some values have been set (for example CUKEDOCTOR_MAIN_VERSION).  
You can change the value if it does not fit your environment, or the way you want to build your app.

#### Commands to execute
**make ci** will build the different components, the docker image, launch the sonarqube scan and generate the living 
documentation.  
**make e2e** will launch the e2e tests either on a kubernetes cluster or in a local docker image regarding your configuration.
**make clean** clean your local repository.

You can launch the following command **make ci && make e2e**.  
Check the living documentation generated in the domain and e2e modules.  
After that, you clean it by executing the **make clean** command.  



## Executing locally the application

### Launching locally your rest api
you can start the rest exposition by executing the following command line in the exposition folder:  
**./mvnw clean spring-boot:run**

The url to display the swagger page is the following:  
**http://localhost:12378/tripagency/api/swagger-ui/**

### Consulting the H2 DB
Connect to this url:
**http://localhost:12378/tripagency/api/backend/h2-console**
Fill the information regarding your configuration