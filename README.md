**Extreamly sample Kafka real time job:**
In this example, we using docker and single node for all config.
_Function_:
   We have data in oracle, when we add new row, new line is append to file real time, I just implement very sample code for add data, this data will be append to file. 

_Preparing_:
+ Dowload and install docker:
+ Install JVM 8.
+ Clone project.
+ Dowload ojdbc11.jar (Oracle 21): https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html ( note: we need to dowload exactly driver for oracle version)

_Procedure_:
+ Run cmd : docker compose up( you need to rename file to docker-compose.yml)
dockerfile\kafkaclusterandsampledata\docker-compose.yml
+ Run cmd step by step:
 dockerfile\kafkaclusterandsampledata\configdb.txt
+  Run cmd : docker compose up
dockerfile\flink\docker-compose.yml
+ Call api to config debezium: 
     curl --location 'localhost:8083/connectors' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data '{
    "name": "customers-connector",
    "config": {
        "connector.class": "io.debezium.connector.oracle.OracleConnector",
        "tasks.max": "1",
        "database.hostname": "dbz_oracle21",
        "database.port": "1521",
        "topic.prefix": "fix",
        "database.user": "c##dbzuser",
        "database.password": "dbz",
        "database.dbname": "ORCLCDB",
        "database.pdb.name": "ORCLPDB1",
        "database.server.name": "server1",
        "schema.history.internal.kafka.bootstrap.servers": "kafka:9092",
        "schema.history.internal.kafka.topic": "schema-changes"
    }
}' 
(note: you can only create use with format "c##...".)
+ Check config at: localhost:8088.
+ Run project: 
You can run it as a standalone or submit job to flink cluster. If you subit to flink cluster, you need to build by maven shape "mvn shade:shade", access and submit add : localhost:8081
+ Add new row to data base.
+ check data on kafka.
+ Check data in file.
 
