mvn clean install
docker-compose up

#### Follow this steps in bash

# 1. chmod +x ./script.sh && ./script.sh
#  ...wait...
# (If there is an error during initialization, run again a command in bash - "docker-compose up" )

#### Steps for sql.scripts (after initializing springApp)

# 2. cd d:/workJava/TravelProject/
# 3. docker cp ./src/main/resources/db/data.sql mysqldb:/data.sql
# 4. docker container exec -it mysqldb bash
# 5. mysql -uroot -proot
# 6. use travell;
# 7. source data.sql
# 8. select * from user;
# 9. exit;
# 10. exit
# 11. open app http://"your_machine_IP":8080/


#### DELETE images, containers, networks
# docker stop ex && docker stop mysqldb && docker rm ex && docker rm mysqldb && docker rmi ex mysql openjdk && docker network rm springjdbctemplate_empl-mysql