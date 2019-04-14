## Install and configure ecs-cli

https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ECS_CLI_installation.html

https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ECS_CLI_Configuration.html

## Push images to ECR
 
```
ecs-cli push ecs-frontend:latest
ecs-cli push ecs-backend:latest
```

NB: To login to ECR via docker

```
aws ecr get-login --no-include-email
```

## Create ECS Fargate Cluster

https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ecs-cli-tutorial-fargate.html

Run the following to init an ecs project


```
ecs-cli configure --cluster tutorial --region us-east-1 --default-launch-type FARGATE --config-name tutorial
ecs-cli configure profile --access-key AWS_ACCESS_KEY_ID --secret-key AWS_SECRET_ACCESS_KEY --profile-name tutorial
ecs-cli up
```


### Security Groups

 - Create a security group

```
aws ec2 create-security-group --group-name "ecs-tutorial-sg" --description "ECS Tutorial security group" --vpc-id "vpc-0f0200a8d252b9cbf"
```

 - Allow ingress traffic
 
```
aws ec2 authorize-security-group-ingress --group-id "sg-00c6c3e24a8bfa4ca" --protocol tcp --port 8080 --cidr 0.0.0.0/0
```

### Running commands

 - Composer UP

```
ecs-cli compose --project-name tutorial service up --create-log-groups --cluster-config tutorial
```

 - Composer PS
 
```
ecs-cli compose --project-name tutorial service ps --cluster-config tutorial
```

## Configure Logs

Simply add the following to the docker-compose.yml services:

```
services:
  web:
    logging:
      driver: awslogs
      options:
        awslogs-group: ecs-frontend
        awslogs-region: eu-west-2
        awslogs-stream-prefix: frontend
```

## Clean up

Important due to costs

 - Stop Existing containers

```
ecs-cli compose --project-name tutorial service down --cluster-config tutorial
```

 - Delete cluster

```
ecs-cli down --force --cluster-config tutorial
```

## Next Steps
   
   ALB (Application Load Balancing)
   
   https://docs.aws.amazon.com/AmazonECS/latest/developerguide/service-load-balancing.html
