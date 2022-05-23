## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
# Create Databases
CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

#Create database service accounts
CREATE USER 'sfg_dev_user'@'192.168.0.105' IDENTIFIED BY 'guru';
CREATE USER 'sfg_prod_user'@'192.168.0.105' IDENTIFIED BY 'guru';
CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'guru';
CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'guru';

#Database grants
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'192.168.0.105';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'192.168.0.105';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'192.168.0.105';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'192.168.0.105';
GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'192.168.0.105';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'192.168.0.105';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'192.168.0.105';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'192.168.0.105';
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'%';