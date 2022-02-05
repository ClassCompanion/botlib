package org.classcompanion.botlib.consumes

import com.rabbitmq.client.Delivery
import org.classcompanion.botlib.rabbitmq.OnConsume
import java.nio.charset.Charset

class DefaultConsume: OnConsume {
	override fun execute(delivery: Delivery, charset: Charset) {
		println("[x] Received data: ${String(delivery.body, charset)}")
	}
}