#!/bin/sh

while getopts a:p: flag; do
  case "${flag}" in
  a) action=${OPTARG} ;;
  p) pro=${OPTARG} ;;
  esac
done
echo "Action: $action"
echo "Pro: $pro"

############################################################################################### Build
if [ "$action" = "build" ]; then
  # remove target directory
  rm -rf target
  # Build
  mvn clean package -P$pro
  if ! [ $? -eq 0 ]; then
    echo "Build fail [mvn clean package -P$pro]"
    exit
  fi
fi

if [ "$pro" = "dev" ]; then
  rm -rf _deploy/application.jar
  cp target/*.jar _deploy/application.jar
  # shellcheck disable=SC2164
  cd _deploy
  ./dockerbuild.sh
fi
