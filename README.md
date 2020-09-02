# Dynamic RabbitMQ Management
RabbitMQ has an option of configuring the RabbitMQ management through `definitions.json`. In this project, I worked to create users, queues, exchange and bindings dynamically through spring boot and running it on rabbitmq image.

## How to run it?
- Simply by `docker-compose up`

## Properties of creating exchanges
```
# creates exchange with name of exchang1 and other information
manage.exchanges.position.name=exchange1
manage.exchanges.position.type=fanout
manage.exchanges.position.durable=false
manage.exchanges.position.auto_delete=false
```

## Properties of creating queues
```
# creates queue with name of queue1
manage.queues.queue1.name=queue1
manage.queues.queue1.durable=false
manage.queues.queue1.auto_delete=false
```

## Properties of creating bindings
```
# binds exchange1 to queue1
manage.bindings.queue1Binding.source=exchange1
manage.bindings.queue1Binding.destination=queue1
manage.bindings.queue1Binding.destination_type=queue
```

## Properties of creating users
```
# create user with username of user and password of password
manage.users.user1.username=user
manage.users.user1.password=password
manage.users.user1.permission=^[queue1].*  # gives permission
```
