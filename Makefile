include $(HOME)/.env
include .project.env

all : 	ci e2e clean
.PHONY: all

ci : 	check build build_and_publish_container_image launch_quality_scan generate_living_documentation_for_domain
.PHONY: ci

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
build_and_publish_container_image :
	./pipeline/scripts/4_build_and_publish_container_image/build_and_publish_container_image.sh "${CONTAINER_BUILD_TYPE}" "${DOCKER_IMAGE}"-snapshot "${VERSION}"-SNAPSHOT



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
	./pipeline/scripts/clean.sh "${BUILD_TYPE}"