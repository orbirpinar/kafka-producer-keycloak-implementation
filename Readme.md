### **Why do we need Kafka?**

We don't need Kafka specifically but we need a producer-consumer mechanism.

### Why do we need a producer-consumer mechanism?

Keycloak stores user information in a relational database

Because When you use keycloak as an authentication system for your backend rest api, You need the registered user information to use in your own [database.](http://database.in/) In Keycloak You can implement the EventListenerProvider interface and listen to register events or a bunch of other events.

So When users register in keycloak you can capture user information and produce it in Kafka Producer. Then consume this record in spring boot application and save it in your application database.

### TO DO

1-To create jar file you must build project once. For this just type below in your terminal. Of course you have to in project directory.

```bash
mvn clean install
```

2- For  kafka, zookeeper and keycloak we user docker. To spin up all docker container You can just type below in your terminal

```bash
docker-compose up -d
```

3-) Wait until all docker containers is ready then Your jar file will be deployed in keycloak server.

4-) Then you can login keycloak admin console in

[http://localhost:8084](http://localhost:8084)

username —> admin

password —> secret

5-) Then you click Events at left side bar.

6-) After you  clicked events you  select 'custom-event-listener' in Even Listeners input and hit save button.

7-) When you register new user.Registered user's id publish by kafka.

![keycloak](/images/keycloak-event.png)

For demo purpose I  just printed user id in consumer app but you can do whatever you need.

To see consumer app you can visit [this](https://github.com/orbirpinar/spring-kafka-consumer-with-keycloak.git) repo