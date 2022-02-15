package org.classcompanion.botlib.objects

data class Assesment(
	val assesmentName: String,
	val dates: HashMap<String, Array<Long>>,
	val messageId: String,
	val channel: String,
	val uuid: String
	)
