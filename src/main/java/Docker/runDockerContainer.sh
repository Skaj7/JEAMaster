#!/usr/bin/env bash

DOCKER_REPO=192.168.99.100:5000/JEAMasterTest
TAG=0.0.1-SNAPSHOT
CONTAINER_NAME=JEAMasterTest

containerId=`docker ps -qa --filter "name=$CONTAINER_NAME"`
if [ -n "$containerId" ]
then
	echo "Stopping and removing existing hello-world container"
	docker stop $CONTAINER_NAME
	docker rm $CONTAINER_NAME
fi

docker run -d --name $CONTAINER_NAME -p 9090:8080 $DOCKER_REPO:$TAG
#use -d to run in background
#use -it to run in foreground and get the output. add --rm to remove the container (container's file system) when it exits.