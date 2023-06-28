
```shell
docker run -i -t --rm --name plant-name-parser-service -p 8081:8080 --memory=2G --cpus=1 -e JAVA_OPTS="-Xms2G -Xmx2G -server" plant-name-parser-service:v1.0.1
```

http://localhost:8080/actuator/gateway/routes

http://localhost:8080/actuator/gateway/globalfilters




```shell
docker run -p 9000:9000 -p 9001:9001 -e MINIO_ROOT_USER=admin -e MINIO_ROOT_PASSWORD=password quay.io/minio/minio server /data --console-address ":9001"
```

http://localhost:8080/binary


```shell
docker build -t apigateway-service:0.0.1 .
docker run -i -t --rm --name apigateway-service -p 8080:8080 --memory=1G --cpus=1 apigateway-service:0.0.1
```