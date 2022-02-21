package org.classcompanion.botlib.objects

import com.fasterxml.jackson.annotation.JsonProperty

data class Term(
	@JsonProperty("id")
	val id: Int,
	@JsonProperty("date")
	val date: String,
	@JsonProperty("entries")
	val entries: List<Entry>
)
