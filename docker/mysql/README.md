
# Docker MySQL Setup
### Create Docker container with MySQL database:
`docker create --name mysql-db -e MYSQL_ROOT_PASSWORD=xxxxxxxx -p 3306:3306 mysql:latest`
### Start container:

`docker start mysql-db`

### Stop container:

`docker stop mysql-db`
### Connection Info:

JDBC URL: `jdbc:mysql://localhost:3306/car_fleet`

Username: `root`

Password: `xxxxxxxx`

### Connect to MYSQL prompt from docker:
`docker exec -it mysql-db bash`

# Application Database Setup
`mysql -u root -p`

### Create the Database:

`mysql> CREATE DATABASE car_fleet;`
### Setup the Tables:

`docker cp create_tables.sql mysql-db:/create_tables.sql`

`docker exec -i mysql-db sh -c "cat /create_tables.sql | mysql -u root -prootpassword car_fleet"`
### Install the Data:

`docker cp insert_data.sql mysql-db:/insert_data.sql`

`docker exec -i mysql-db sh -c "cat /insert_data.sql | mysql -u root -prootpassword car_fleet"`
