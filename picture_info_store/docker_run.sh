#!/bin/bash
mvn install
docker-compose build
docker-compose up