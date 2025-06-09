package de.mindmarket.navigation3sampleapp.note

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavKey
import de.mindmarket.navigation3sampleapp.navigation.NoteListScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteDetailViewModel(
    private val noteId: Int
) : ViewModel() {
    /**
     * You can handle the backStack also completely in the VM. You have access to it.
     * But: Process death you need to handle by your own. In the view we do not handle this
     * because we are using rememberNavBackStack() but here in the VM this is not the case.
     * So you need to store the current state in some kind of file, sharedPref or so.
     */
    val backStack = mutableStateListOf<NavKey>(NoteListScreen)

    private val _noteState = MutableStateFlow(
        sampleNotes.first { it.id == noteId }
    )
    val noteState = _noteState.asStateFlow()
}