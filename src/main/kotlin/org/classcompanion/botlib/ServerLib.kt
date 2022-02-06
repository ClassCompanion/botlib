package org.classcompanion.botlib

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.classcompanion.botlib.consumes.DefaultConsume
import org.classcompanion.botlib.rabbitmq.RabbitmqManager

class ServerLib(baseUrl: String) {
	private val serverQueueName = "server"
	private val botQueueName = "bot"
	var factory: ConnectionFactory = RabbitmqManager.createFactory("test", "tset", "/", baseUrl, 5672)
	private var connection: Connection? = RabbitmqManager.makeConnection(factory)
	private var channel: Channel? = connection?.let { RabbitmqManager.createChannel(it) }
	init {
		RabbitmqManager.declareQueue(channel!!, serverQueueName)
		RabbitmqManager.declareQueue(channel!!, botQueueName)
	}

	fun sendMessage(msg: String) {
		RabbitmqManager.basicPublish(channel!!, serverQueueName, msg)
		println("[x] Sent '$msg'")
	}

	fun setConsume(consume: DefaultConsume = DefaultConsume()) {
		RabbitmqManager.setBasicConsume(channel!!, botQueueName, consume)
	}

	fun closeConnection() {
		channel!!.close()
		connection!!.close()
	}
}