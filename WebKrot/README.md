# Create react app in docker

Create docker hub repository - publish
```
docker build -t pv116-api . 
docker run -it --rm -p 5085:8080 --name pv116_container pv116-api
docker run -d --restart=always --name pv116_container -p 5085:8080 pv116-api
docker ps -a
docker stop pv116_container
docker rm pv116_container

docker images --all
docker rmi pv116-api

docker login
docker tag pv116-api:latest igorgapich/pv116-api:latest
docker push igorgapich/pv116-api:latest

docker pull igorgapich/pv116-api:latest
docker ps -a
docker run -d --restart=always --name pv116_container -p 5085:8080 igorgapich/pv116-api


docker pull igorgapich/pv116-api:latest
docker images --all
docker ps -a
docker stop pv116_container
docker rm pv116_container
docker run -d --restart=always --name pv116_container -p 5085:8080 igorgapich/pv116-api
```

```nginx options /etc/nginx/sites-available/default
server {
    server_name   pv116.itstep.click *.pv116.itstep.click;
    location / {
       proxy_pass         http://localhost:5085;
       proxy_http_version 1.1;
       proxy_set_header   Upgrade $http_upgrade;
       proxy_set_header   Connection keep-alive;
       proxy_set_header   Host $host;
       proxy_cache_bypass $http_upgrade;
       proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
       proxy_set_header   X-Forwarded-Proto $scheme;
    }
}

sudo systemctl restart nginx
certbot
```



