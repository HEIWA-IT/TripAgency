include $(HOME)/.env

VERSION := $(shell git describe --tags --always)-SNAPSHOT
APP_NAME := $(shell ./mvnw org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.name -q -DforceStdout)
DOCKER_IMAGE := $(shell echo "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${VERSION}")

check : 	check_variables
.PHONY: check

all : 	check_variables setup_maven build build_docker_image sonarqube_scan generate_living_documentation_for_domain \
		start_exposition launch_e2e_tests generate_living_documentation_for_e2e stop_exposition clean
.PHONY: all

ci : 	check_variables setup_maven build build_docker_image sonarqube_scan generate_living_documentation_for_domain revert_maven_setup
.PHONY: ci

e2e : 	check_variables setup_maven start_exposition launch_e2e_tests generate_living_documentation_for_e2e stop_exposition revert_maven_setup
.PHONY: e2e

clean : revert_maven_setup cleaning
.PHONY: clean

build :
	./scripts/ci/build.sh "${VERSION}"
build_docker_image :
	./scripts/ci/build_docker_image.sh "${DOCKER_IMAGE}"
sonarqube_scan :
	./scripts/ci/sonarqube_scan.sh

check_variables :
	./scripts/commons/check_pipeline_variables.sh
cleaning :
	./scripts/commons/clean.sh
generate_living_documentation_for_domain :
	./scripts/commons/generate_living_documentation.sh domain "${VERSION}"
revert_maven_setup :
	./scripts/commons/revert_maven_setup.sh
setup_maven :
	./scripts/commons/setup_maven.sh "${VERSION}"

launch_e2e_tests :
	./scripts/e2e/launch_e2e_tests.sh
start_exposition :
	./scripts/e2e/start_exposition.sh "${DOCKER_IMAGE}" "${VERSION}"
generate_living_documentation_for_e2e :
	./scripts/commons/generate_living_documentation.sh e2e "${VERSION}"
stop_exposition :
	./scripts/e2e/stop_exposition.sh