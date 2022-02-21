package org.classcompanion.botlib.objects

data class Term(
	val id: Int,
	val date: String,
	val entries: List<Entry>
)
