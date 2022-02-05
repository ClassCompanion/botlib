package org.classcompanion.botlib

import com.rabbitmq.client.*
import org.classcompanion.botlib.consumes.DefaultConsume
import org.classcompanion.botlib.rabbitmq.RabbitmqManager


class BotLib(baseUrl: String, guildId: String) {
	private val queueName = "hello"
	var factory: ConnectionFactory = RabbitmqManager.createFactory("test", "tset", "/", baseUrl, 5672)
	private var connection: Connection? = RabbitmqManager.makeConnection(factory)
	private var channel: Channel? = connection?.let { RabbitmqManager.createChannel(it) }
	init {
		channel?.let { RabbitmqManager.declareQueue(it, queueName) }
	}

	fun sendMessage(msg: String) {
		RabbitmqManager.basicPublish(channel!!, queueName, msg)
		println(" [x] Sent '$msg'")
	}

	fun setConsume(consume: DefaultConsume = DefaultConsume()) {
		RabbitmqManager.setBasicConsume(channel!!, queueName, consume)
	}

	fun closeConnection() {
		channel!!.close()
		connection!!.close()
	}
}