package org.classcompanion.botlib.rabbitmq

import com.rabbitmq.client.Delivery
import java.nio.charset.Charset

interface OnConsume {
	fun execute(delivery: Delivery, charset: Charset)
}