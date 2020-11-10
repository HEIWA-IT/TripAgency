include $(HOME)/.env

VERSION := $(shell git describe --tags --always)-SNAPSHOT
APP_NAME := $(shell ./mvnw org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.name -q -DforceStdout)
DOCKER_IMAGE := $(shell echo "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${VERSION}")

all : 	ci e2e clean
.PHONY: all

ci : 	setup build build_docker_image launch_quality_scan generate_living_documentation_for_domain
.PHONY: ci

e2e : 	setup start_exposition launch_e2e_tests generate_living_documentation_for_e2e stop_exposition
.PHONY: e2e

clean : revert_project_version cleaning
.PHONY: clean

setup : check_variables setup_project_version
.PHONY: setup

build :
	./scripts/ci/build.sh "${VERSION}"
build_docker_image :
	./scripts/ci/build_docker_image.sh "${DOCKER_IMAGE}" "${VERSION}" "${APP_NAME}"
launch_quality_scan :
	./scripts/ci/launch_quality_scan.sh

check_variables :
	./scripts/commons/check_pipeline_variables.sh
cleaning :
	./scripts/commons/clean.sh
generate_living_documentation_for_domain :
	./scripts/commons/generate_living_documentation.sh domain "${VERSION}"
revert_project_version :
	./scripts/commons/revert_project_version.sh
setup_project_version :
	./scripts/commons/setup_project_version.sh "${VERSION}"

launch_e2e_tests :
	./scripts/e2e/launch_e2e_tests.sh
start_exposition :
	./scripts/e2e/start_exposition.sh "${DOCKER_IMAGE}" "${VERSION}"
generate_living_documentation_for_e2e :
	./scripts/commons/generate_living_documentation.sh e2e "${VERSION}"
stop_exposition :
	./scripts/e2e/stop_exposition.sh