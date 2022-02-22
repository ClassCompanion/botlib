package org.classcompanion.botlib.objects

import com.fasterxml.jackson.annotation.JsonProperty

data class Assessment(
	@JsonProperty("id")
	val id: Int,
	@JsonProperty("title")
	val title: String,
	@JsonProperty("per_day")
	val per_day: Int,
	@JsonProperty("message_id")
	val message_id: String?,
	@JsonProperty("terms")
	val terms: List<Term>
	)
