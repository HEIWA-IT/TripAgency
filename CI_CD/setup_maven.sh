#!/bin/bash

VERSION=$1

./mvnw versions:set -DnewVersion="${VERSION}"