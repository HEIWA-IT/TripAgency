include $(HOME)/.env

VERSION := $(shell git describe --tags --always)-SNAPSHOT
APP_NAME := $(shell ./mvnw org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.name -q -DforceStdout)
DOCKER_IMAGE := $(shell echo "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${VERSION}")

all : 	ci e2e clean
.PHONY: all

ci : 	check build build_docker_image launch_quality_scan generate_living_documentation_for_domain
.PHONY: ci

e2e : 	check start_exposition launch_e2e_tests stop_exposition generate_living_documentation_for_e2e
.PHONY: e2e

build :
	./scripts/ci/build.sh "${VERSION}"
build_docker_image :
	./scripts/ci/build_docker_image.sh "${DOCKER_IMAGE}" "${VERSION}" "${APP_NAME}"
launch_quality_scan :
	./scripts/ci/launch_quality_scan.sh

check :
	./scripts/commons/check_pipeline_variables.sh
clean :
	./scripts/commons/clean.sh
generate_living_documentation_for_domain :
	./scripts/commons/generate_living_documentation.sh domain "${VERSION}"

launch_e2e_tests :
	./scripts/e2e/launch_e2e_tests.sh "${VERSION}"
start_exposition :
	./scripts/e2e/start_exposition.sh "${DOCKER_IMAGE}" "${VERSION}"
generate_living_documentation_for_e2e :
	./scripts/commons/generate_living_documentation.sh e2e "${VERSION}"
stop_exposition :
	./scripts/e2e/stop_exposition.sh