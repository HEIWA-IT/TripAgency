VERSION := $(shell git describe --tags --always)
APP_NAME := $(shell ./mvnw org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.name -q -DforceStdout)
DOCKER_IMAGE := $(shell echo "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${VERSION}")

all_gradle : 	ci_gradle e2e clean
.PHONY: all_gradle

all : 	ci e2e clean
.PHONY: all_maven

ci : 	check build build_docker_image_with_jib launch_quality_scan generate_living_documentation_for_domain
.PHONY: ci

e2e : 	check start_exposition launch_e2e_tests stop_exposition generate_living_documentation_for_e2e
.PHONY: e2e

# Check
check :
	./pipeline/scripts/1_check/check_pipeline_variables.sh

# Build
build :
	./pipeline/scripts/2_build/build.sh "${VERSION}"

# Report
generate_living_documentation_for_domain :
	./pipeline/scripts/3_reports/generate_living_documentation.sh domain "${VERSION}" "${CUKEDOCTOR_MAIN_JAR}"
launch_quality_scan :
	./pipeline/scripts/3_reports/launch_quality_scan.sh

# Docker
build_docker_image_with_dockerfile :
	./pipeline/scripts/4_docker_build/build_docker_image_with_dockerfile.sh "${DOCKER_IMAGE}"
build_docker_image_with_jib :
	./pipeline/scripts/4_docker_build/build_docker_image_with_jib.sh "${DOCKER_IMAGE}" "${VERSION}"

# e2e
launch_e2e_tests :
	./pipeline/scripts/5_e2e/launch_e2e_tests.sh "${VERSION}"
start_exposition :
	./pipeline/scripts/5_e2e/start_exposition.sh "${DOCKER_IMAGE}" "${VERSION}"
generate_living_documentation_for_e2e :
	./pipeline/scripts/3_reports/generate_living_documentation.sh e2e "${VERSION}"
stop_exposition :
	./pipeline/scripts/5_e2e/stop_exposition.sh

# clean
clean :
	./pipeline/scripts/clean.sh