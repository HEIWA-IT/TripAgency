include $(HOME)/.env

VERSION := $(shell git describe --tags --always)
APP_NAME := trippricer
DOCKER_IMAGE := $(shell echo "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${VERSION}")

BUILD_TYPE := maven
MAVEN_REPOSITORY := ${HOME}/.m2/repository
MAVEN_SETTINGS_XML := pipeline/.m2/settings.xml
MAVEN_SETTINGS := -s ${MAVEN_SETTINGS_XML} -Dmaven_repository_username=${MAVEN_REPOSITORY_USERNAME} -Dmaven_repository_password=${MAVEN_REPOSITORY_PASSWORD} -Dmaven_repository_url=${MAVEN_REPOSITORY_URL} -Dsonarqube_url=${SONARQUBE_URL}
GRADLE_SETTINGS := --rerun-tasks
LOG_PATH := /Volumes/DATA/var/log

CUKEDOCTOR_MAIN_VERSION := 3.5.1
CUKEDOCTOR_MAIN_JAR := ${MAVEN_REPOSITORY}/com/github/cukedoctor/cukedoctor-main/${CUKEDOCTOR_MAIN_VERSION}/cukedoctor-main-${CUKEDOCTOR_MAIN_VERSION}.jar

E2E_TEST_MODE := DOCKER
HOST := http://173.212.241.172:12378
#HOST := http://localhost:12378

all : 	ci e2e clean
.PHONY: all

ci : 	check build build_docker_image_with_jib launch_quality_scan generate_living_documentation_for_domain
.PHONY: ci

e2e_docker : 	check start_exposition launch_e2e_tests stop_exposition generate_living_documentation_for_e2e
.PHONY: e2e

e2e : 	check deploy_to_kubernetes launch_e2e_tests delete_deployment_from_kubernetes generate_living_documentation_for_e2e
.PHONY: e2e

# Check
check :
	./pipeline/scripts/1_init/check_pipeline_variables.sh

# Build
build :
	./pipeline/scripts/2_build_artifacts/build.sh "${VERSION}"-SNAPSHOT

# Report
generate_living_documentation_for_domain :
	./pipeline/scripts/3_quality/generate_living_documentation.sh domain "${VERSION}" "${CUKEDOCTOR_MAIN_JAR}"
launch_quality_scan :
	./pipeline/scripts/3_quality/launch_quality_scan.sh

# Build container image
build_docker_image_with_dockerfile :
	./pipeline/scripts/4_build_and_publish_container_image/build_docker_image_with_dockerfile.sh "${DOCKER_IMAGE}"-snapshot
build_docker_image_with_jib :
	./pipeline/scripts/4_build_and_publish_container_image/build_docker_image_with_jib.sh "${DOCKER_IMAGE}"-snapshot "${VERSION}"-SNAPSHOT


# Deployment on k8s
deploy_to_kubernetes :
	./pipeline/kubernetes/scripts/deploy_to_kubernetes.sh "${VERSION}"-snapshot

delete_deployment_from_kubernetes :
	./pipeline/kubernetes/scripts/delete_deployment_from_kubernetes.sh "${VERSION}"-snapshot

# e2e
launch_e2e_tests :
	./pipeline/scripts/5_e2e/launch_e2e_tests.sh "${VERSION}"-SNAPSHOT
start_exposition :
	./pipeline/scripts/5_e2e/start_exposition.sh "${DOCKER_IMAGE}"-snapshot "${VERSION}"-SNAPSHOT
generate_living_documentation_for_e2e :
	./pipeline/scripts/3_quality/generate_living_documentation.sh e2e "${VERSION}"-SNAPSHOT "${CUKEDOCTOR_MAIN_JAR}"
stop_exposition :
	./pipeline/scripts/5_e2e/stop_exposition.sh

# clean
clean :
	./pipeline/scripts/clean.sh