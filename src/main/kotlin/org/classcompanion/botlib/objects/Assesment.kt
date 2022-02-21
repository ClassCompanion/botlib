package org.classcompanion.botlib.objects

data class Assesment(
	val id: Int,
	val title: String,
	val perDay: Int,
	val messageId: String,
	val terms: List<Term>
	)
