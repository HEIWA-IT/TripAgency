#!/bin/bash

./mvnw clean
cd exposition && rm -rf ./logs && cd ..
cd e2e && ../mvnw clean && cd ..