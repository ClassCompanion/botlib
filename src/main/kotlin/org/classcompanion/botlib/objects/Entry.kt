package org.classcompanion.botlib.objects

import com.fasterxml.jackson.annotation.JsonProperty

data class Entry(
	@JsonProperty("id")
	val id: Int,
	@JsonProperty("user_uuid")
	val user_uuid: String,
	@JsonProperty("chat_id")
	val chat_id: String?
)
