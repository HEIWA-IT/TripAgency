include $(HOME)/.env

VERSION := $(shell git describe --tags --always)-snapshot
APP_NAME := trippricer
DOCKER_IMAGE := $(shell echo "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${VERSION}")

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
	./pipeline/scripts/2_build/build.sh "${VERSION}"

# Report
generate_living_documentation_for_domain :
	./pipeline/scripts/3_reports/generate_living_documentation.sh domain "${VERSION}" "${CUKEDOCTOR_MAIN_JAR}"
launch_quality_scan :
	./pipeline/scripts/3_reports/launch_quality_scan.sh

# Build container image
build_docker_image_with_dockerfile :
	./pipeline/scripts/4_docker_build/build_docker_image_with_dockerfile.sh "${DOCKER_IMAGE}"
build_docker_image_with_jib :
	./pipeline/scripts/4_docker_build/build_docker_image_with_jib.sh "${DOCKER_IMAGE}" "${VERSION}"


# Deployment on k8s
deploy_to_kubernetes :
	./pipeline/kubernetes/scripts/deploy_to_kubernetes.sh "${VERSION}"

delete_deployment_from_kubernetes :
	./pipeline/kubernetes/scripts/delete_deployment_from_kubernetes.sh "${VERSION}"

# e2e
launch_e2e_tests :
	./pipeline/scripts/5_e2e/launch_e2e_tests.sh "${VERSION}"
start_exposition :
	./pipeline/scripts/5_e2e/start_exposition.sh "${DOCKER_IMAGE}" "${VERSION}"
generate_living_documentation_for_e2e :
	./pipeline/scripts/3_reports/generate_living_documentation.sh e2e "${VERSION}" "${CUKEDOCTOR_MAIN_JAR}"
stop_exposition :
	./pipeline/scripts/5_e2e/stop_exposition.sh

# clean
clean :
	./pipeline/scripts/clean.sh