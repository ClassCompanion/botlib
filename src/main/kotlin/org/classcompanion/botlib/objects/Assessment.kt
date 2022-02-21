package org.classcompanion.botlib.objects

import com.fasterxml.jackson.annotation.JsonProperty

data class Assessment(
	@JsonProperty("id")
	val id: Int,
	@JsonProperty("title")
	val title: String,
	@JsonProperty("perDay")
	val perDay: Int,
	@JsonProperty("messageId")
	val messageId: String,
	@JsonProperty("terms")
	val terms: List<Term>
	)
