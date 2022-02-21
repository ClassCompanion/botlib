package org.classcompanion.botlib

import com.rabbitmq.client.*
import org.classcompanion.botlib.consumes.DefaultConsume
import org.classcompanion.botlib.rabbitmq.OnConsume
import org.classcompanion.botlib.rabbitmq.RabbitmqManager


class BotLib(baseUrl: String, guildId: String, username: String, password: String, vhost: String) {
	private val serverAssesmentsQueue = "s:assesments"
	private val botMessageIdQueue = "b:messageid"
	// private val botDefaultChannelQueue = "b:defaultchannel"

	private var factory: ConnectionFactory = RabbitmqManager.createFactory(username, password, vhost, baseUrl, 5672)
	private var connection: Connection? = RabbitmqManager.makeConnection(factory)
	private var channel: Channel? = connection?.let { RabbitmqManager.createChannel(it) }

	init {
		// declare queues
		RabbitmqManager.declareQueue(channel!!, serverAssesmentsQueue)
		RabbitmqManager.declareQueue(channel!!, botMessageIdQueue)
		//RabbitmqManager.declareQueue(channel!!, botDefaultChannelQueue)
	}

	fun sendMessageId(messageId: Long, aId: Int) {
		val msg = """{"message_id": "$messageId", "assessment_id": $aId}"""
		RabbitmqManager.basicPublish(channel!!, botMessageIdQueue, msg)
	}

	/*fun sendDefaultMessage(mchannel: String) {
		val msg = """{"newDefaultChannel": "$mchannel"}"""
		RabbitmqManager.basicPublish(channel!!, botMessageIdQueue, msg)
	}*/

	fun setAssesmentsConsume(consume: OnConsume = DefaultConsume()) {
		RabbitmqManager.setBasicConsume(channel!!, serverAssesmentsQueue, consume)
	}

	fun closeConnection() {
		channel!!.close()
		connection!!.close()
	}
}