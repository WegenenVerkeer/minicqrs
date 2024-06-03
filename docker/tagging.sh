if [ $1 ]; then
  export VERSION_TAG=$1
  docker tag 162510209540.dkr.ecr.eu-west-1.amazonaws.com/rood/minicqrs-postgres:latest 162510209540.dkr.ecr.eu-west-1.amazonaws.com/rood/minicqrs-postgres:$VERSION_TAG
  docker tag 162510209540.dkr.ecr.eu-west-1.amazonaws.com/rood/minicqrs:latest 162510209540.dkr.ecr.eu-west-1.amazonaws.com/rood/minicqrs:$VERSION_TAG
else
  echo "Moet een version tag doorgeven als argument, e.g.: ./tagging.sh v.1.0.0"
fi