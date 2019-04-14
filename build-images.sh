cd ecs-backend
mvn clean package -DskipTests

cd ../ecs-frontend
ng build --prod

cd ..

docker build . -f Dockerfile_back -t ecs-backend
docker build . -f Dockerfile_front -t ecs-frontend
