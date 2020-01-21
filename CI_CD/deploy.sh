#!/bin/bash

echo "Deploying artifact to $1";
ssh pxl 'cd /home/upxl/containers/tripagency/$1 && pwd';
