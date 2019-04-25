## Install and configure ecs-cli

https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ECS_CLI_installation.html

https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ECS_CLI_Configuration.html

## Build docker images

See **uild-images.sh**

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
ecs-cli configure --cluster tutorial  --default-launch-type FARGATE --config-name tutorial
ecs-cli configure profile --access-key AWS_ACCESS_KEY_ID --secret-key AWS_SECRET_ACCESS_KEY --profile-name tutor

```

config name refers to the name of the local configuration stored under ~/.ecs/config

Check that the above mentioned file exists and has the following config

```
version: ""
default: rentacar
clusters:
  cluster-test-config:
    cluster: cluster-test
    region: eu-west-2
    default_launch_type: FARGATE

```

Then run the following command to apply all changes to the cloud

```
ecs-cli up
```

### Security Groups

 - Create a security group

```
aws ec2 create-security-group --group-name "ecs-tutorial-sg" --description "ECS Tutorial security group" --vpc-id "vpc-0f0200a8d252b9cbf"
```

```

 - Allow ingress traffic (0.0.0.0/0 allow all inbound IPv4 traffic)
 
```
aws ec2 authorize-security-group-ingress --group-id "sg-00c6c3e24a8bfa4ca" --protocol tcp --port 8080 --cidr 0.0.0.0/0

```

 - view security group description :

```
aws ec2 describe-security-groups --group-ids sg-0f7c4b769bdf79bda



# create subnets

```

- aws ec2 describe-availability-zones --region eu-west-2

- aws ec2 create-subnet --vpc-id vpc-id --availability-zone AZName --cidr-block ip

```

# create load balancer

```
aws elb create-load-balancer --load-balancer-name rentacar-load-balancer --listeners "Protocol=HTTP,LoadBalancerPort=80,InstanceProtocol=HTTP,InstancePort=80" --subnets subnet-id1 subnet-id2 --security-groups sg-id

```


### ECS Fargate Task definition

One the VPC, Security Group and Cluster have been created, you need to update the config file for the task definition **ecs-params.yml**
using the oinfos in the previous steps 

### Create a cluster

```
aws instance type list : https://aws.amazon.com/fr/ec2/pricing/on-demand/

aws emr create-cluster --instance-type t2.nano --instance-count X

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
