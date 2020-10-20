include $(HOME)/.env

OPTIONS := -mw
VERSION := $(shell git describe --tags --always)-SNAPSHOT
APP_NAME := $(shell ./mvnw org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.name -q -DforceStdout)
DOCKER_IMAGE := $(shell echo "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${VERSION}")

all : 	setup_maven build build_docker_image sonarqube_scan generate_living_documentation_for_domain \
		start_exposition launch_e2e_tests generate_living_documentation_for_e2e stop_exposition clean
.PHONY: all

ci : 	setup_maven build build_docker_image sonarqube_scan generate_living_documentation_for_domain revert_maven_setup
.PHONY: ci
setup_maven :
	./CI_CD/setup_maven.sh "${OPTIONS}" "${VERSION}"
build :
	./CI_CD/build.sh "${OPTIONS}" "${VERSION}"
build_docker_image :
	./CI_CD/build_docker_image.sh "${OPTIONS}" "${DOCKER_IMAGE}"
sonarqube_scan :
	./CI_CD/sonarqube_scan.sh
generate_living_documentation_for_domain :
	./CI_CD/generate_living_documentation.sh domain "${VERSION}"
revert_maven_setup :
	./CI_CD/revert_maven_setup.sh "${OPTIONS}"

e2e : 	setup_maven start_exposition launch_e2e_tests generate_living_documentation_for_e2e stop_exposition revert_maven_setup
.PHONY: e2e
start_exposition :
	./CI_CD/start_exposition.sh "${DOCKER_IMAGE}" "${VERSION}"
launch_e2e_tests :
	./CI_CD/launch_e2e_tests.sh "${OPTIONS}"
generate_living_documentation_for_e2e :
	./CI_CD/generate_living_documentation.sh e2e "${VERSION}"
stop_exposition :
	./CI_CD/stop_exposition.sh

clean : revert_maven_setup cleaning
.PHONY: clean
cleaning :
	./CI_CD/clean.sh "${OPTIONS}"