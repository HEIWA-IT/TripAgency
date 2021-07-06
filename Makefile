include $(HOME)/.development.env
include .project.env

ARTIFACT_VERSION := $(shell ./pipeline/scripts/1_setup/determine_version.sh "${COMMIT_BRANCH}" "${VERSION}")
CONTAINER_VERSION := $(shell ./pipeline/scripts/1_setup/determine_container_version.sh "${COMMIT_BRANCH}" "${VERSION}")
DOCKER_IMAGE := "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${CONTAINER_VERSION}"

all : 	ci e2e clean
.PHONY: all

ci : 	setup build build_and_publish_container_image launch_quality_scan generate_living_documentation_for_domain
.PHONY: ci

e2e : 	setup deploy_to_kubernetes launch_e2e_tests delete_deployment_from_kubernetes generate_living_documentation_for_e2e
.PHONY: e2e

setup :  check
.PHONY: setup

# setup
check :
	./pipeline/scripts/1_setup/check_pipeline_variables.sh

# Build
build :
	./pipeline/scripts/2_build_artifacts/build.sh "${ARTIFACT_VERSION}"

# Build container image
build_and_publish_container_image :
	./pipeline/scripts/4_build_and_publish_container_image/build_and_publish_container_image.sh "${CONTAINER_BUILD_TYPE}" "${CONTAINER_IMAGE}" "${ARTIFACT_VERSION}"

# Quality
generate_living_documentation_for_domain :
	./pipeline/scripts/3_quality/generate_living_documentation.sh domain "${ARTIFACT_VERSION}" "${CUKEDOCTOR_MAIN_JAR}"
launch_quality_scan :
	./pipeline/scripts/3_quality/launch_quality_scan.sh

# K8S
connecting_to_kubernetes_cluster :
	./k8s/scripts/connecting_to_kubernetes_cluster.sh
deploy_to_kubernetes :
	./k8s/scripts/deploy_to_kubernetes.sh "${CONTAINER_VERSION}"
delete_deployment_from_kubernetes :
	./k8s/scripts/delete_deployment_from_kubernetes.sh "${CONTAINER_VERSION}"

# e2e
launch_e2e_tests :
	./pipeline/scripts/5_e2e/launch_e2e_tests.sh "${ARTIFACT_VERSION}"
start_exposition :
	./pipeline/scripts/5_e2e/start_exposition.sh "${CONTAINER_IMAGE}" "${ARTIFACT_VERSION}"
generate_living_documentation_for_e2e :
	./pipeline/scripts/3_quality/generate_living_documentation.sh e2e "${ARTIFACT_VERSION}" "${CUKEDOCTOR_MAIN_JAR}"
stop_exposition :
	./pipeline/scripts/5_e2e/stop_exposition.sh

# clean
clean :
	./pipeline/scripts/clean.sh "${BUILD_TYPE}"