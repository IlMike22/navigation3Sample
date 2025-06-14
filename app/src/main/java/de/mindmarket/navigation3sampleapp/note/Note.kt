package de.mindmarket.navigation3sampleapp.note

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val color: Color
)

val sampleNotes = List(100) {
    Note(
        id = it,
        title = "Note $it",
        content = "Content $it",
        color = Color(Random.nextLong(0xFFFFFFF)).copy(alpha = 0.5f)
    )
}
