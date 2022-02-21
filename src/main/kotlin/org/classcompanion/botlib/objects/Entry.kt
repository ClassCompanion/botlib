package org.classcompanion.botlib.objects

import com.fasterxml.jackson.annotation.JsonProperty

data class Entry(
	@JsonProperty("id")
	val id: Int,
	@JsonProperty("userUuid")
	val userUuid: String,
	@JsonProperty("chatId")
	val chatId: String
)
