build :
	./mvnw install

sonarqube_scan:
	./CI_CD/sonarqubeScan.sh

generate_domain_living_doc :
	./CI_CD/generateLivingDocumentation.sh domain 1.0.0

start_exposition :
	./CI_CD/startExposition.sh

launch_e2e_tests :
	./CI_CD/launchE2eTests.sh

generate_e2e_living_doc :
	./CI_CD/generateLivingDocumentation.sh e2e 1.0.0

stop_exposition :
	./CI_CD/stopExposition.sh

clean :
	./mvnw clean && cd e2e && ../mvnw clean
