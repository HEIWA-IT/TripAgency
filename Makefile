include $(HOME)/.env
include .project.env

all : 	ci e2e clean
.PHONY: all

ci : 	check build build_and_publish_container_image launch_quality_scan generate_living_documentation_for_domain
.PHONY: ci

e2e : 	check deploy_to_kubernetes launch_e2e_tests delete_deployment_from_kubernetes generate_living_documentation_for_e2e
.PHONY: e2e

# Init
check :
	./pipeline/scripts/1_setup/check_pipeline_variables.sh

# Build
build :
	./pipeline/scripts/2_build_artifacts/build.sh "${VERSION}"
# Build container image
build_and_publish_container_image :
	./pipeline/scripts/4_build_and_publish_container_image/build_and_publish_container_image.sh "${CONTAINER_BUILD_TYPE}" "${DOCKER_IMAGE}" "${VERSION}"

# Quality
generate_living_documentation_for_domain :
	./pipeline/scripts/3_quality/generate_living_documentation.sh domain "${VERSION}" "${CUKEDOCTOR_MAIN_JAR}"
launch_quality_scan :
	./pipeline/scripts/3_quality/launch_quality_scan.sh

# K8S
connecting_to_kubernetes_cluster :
	./k8s/scripts/connecting_to_kubernetes.sh "${VERSION}"
deploy_to_kubernetes :
	./k8s/scripts/deploy_to_kubernetes.sh "${VERSION}"
delete_deployment_from_kubernetes :
	./k8s/scripts/delete_deployment_from_kubernetes.sh "${VERSION}"

# e2e
launch_e2e_tests :
	./pipeline/scripts/5_e2e/launch_e2e_tests.sh "${VERSION}"
start_exposition :
	./pipeline/scripts/5_e2e/start_exposition.sh "${DOCKER_IMAGE}" "${VERSION}"
generate_living_documentation_for_e2e :
	./pipeline/scripts/3_quality/generate_living_documentation.sh e2e "${VERSION}" "${CUKEDOCTOR_MAIN_JAR}"
stop_exposition :
	./pipeline/scripts/5_e2e/stop_exposition.sh

# clean
clean :
	./pipeline/scripts/clean.sh "${BUILD_TYPE}"