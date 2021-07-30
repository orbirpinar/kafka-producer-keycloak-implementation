### **Why do we need Kafka?**

We don't need Kafka specifically but we need a producer-consumer mechanism.

### Why do we need a producer-consumer mechanism?

Keycloak stores user information in a relational database

Because When you use keycloak as an authentication system for your backend rest api, You need the registered user 
information to use in your own database. In Keycloak You can implement the EventListenerProvider interface and listen to
register events or a bunch of other events.

So When users register in keycloak you can capture user information and produce it in Kafka Producer. 
Then consume this record in spring boot application and save it in your application database.

For demo purpose I  just printed user id in consumer app but you can do whatever you need.