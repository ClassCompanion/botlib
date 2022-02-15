package org.classcompanion.botlib.rabbitmq

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery

class RabbitmqManager {
	companion object {
		fun createFactory(username: String, password: String, vhost: String, baseUrl: String, port: Int = 5672): ConnectionFactory {
			val factory = ConnectionFactory()
			factory.setUsername(username)
			factory.setPassword(password)
			factory.setVirtualHost(vhost)
			factory.setHost(baseUrl)
			factory.setPort(port)

			return factory
		}

		fun makeConnection(factory: ConnectionFactory): Connection? {
			return factory.newConnection()
		}

		fun createChannel(connection: Connection): Channel? {
			return connection.createChannel()
		}

		fun declareQueue(channel: Channel, queueName: String) {
			channel.queueDeclare(queueName, false, false, false, null)
		}

		fun basicPublish(channel: Channel, queueName: String, message: String) {
			channel.basicPublish("", queueName, null, message.toByteArray())
		}

		fun setBasicConsume(channel: Channel, queueName: String, onConsume: OnConsume) {
			val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
				onConsume.execute(delivery, charset("UTF-8"))
			}
			channel.basicConsume(queueName, true, deliverCallback) { consumerTag: String? -> }
		}
	}
}