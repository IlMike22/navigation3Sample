package de.mindmarket.navigation3sampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import de.mindmarket.navigation3sampleapp.note.NoteDetailScreenUi
import de.mindmarket.navigation3sampleapp.note.NoteListScreenUi
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Serializable
data object NoteListScreen: NavKey

@Serializable
data class NoteDetailScreen(val id: Int): NavKey

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    // full control now over backstack -> crucial change in nav3!
    // backstack gets NavKey values (which is nothing else then a simple screen)
    val backStack = rememberNavBackStack(NoteListScreen)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf( // add some more custom functionality to your nav display e.g. binding viewModels to screens
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        sceneStrategy = TwoPaneSceneStrategy(),
        entryProvider = { key ->
            when (key) {
                is NoteListScreen -> {
                    NavEntry(
                        key = key,
                        metadata = TwoPaneScene.twoPane()
                    ) {
                        NoteListScreenUi(
                            onNoteClick = { noteId ->
                                backStack.add(NoteDetailScreen(noteId))
                                /**
                                 * We can also shuffle the backstack entries.
                                 * Or add the screen multiple times on the backstack.
                                 * Then removing the first one again etc...
                                 * Lot of options here with the backstack.
                                 */
                            }
                        )
                    }
                }
                is NoteDetailScreen -> {
                    NavEntry(
                        key = key,
                        metadata = TwoPaneScene.twoPane()
                    ) {
                        NoteDetailScreenUi(
                            viewModel = koinViewModel {
                                parametersOf(key.id)
                            }
                        )
                    }
                }
                else -> throw RuntimeException("Invalid nav key!")
            }
        }
    )
}