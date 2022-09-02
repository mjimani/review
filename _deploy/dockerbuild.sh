#!/usr/bin/env bash
reviewApi=review_api_image
mongoDbReview=mongodb_review

docker rm $(docker stop $(docker ps -a -q --filter ancestor=$reviewApi --format="{{.ID}}"))
docker rmi -f $reviewApi
docker rm $(docker stop $(docker ps -a -q --filter ancestor=$mongoDbReview --format="{{.ID}}"))
docker build -t $reviewApi .
docker-compose up -d
