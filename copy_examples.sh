#!/bin/sh
docker cp ./example_pictures/pictures/. picture_search_picture_store_1:/pictures
for file in ./example_pictures/data/*
do
curl -X POST \
  http://localhost:8082/pictures/ \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  --data-binary "@$file"
done