package de.mindmarket.navigation3sampleapp.di

import de.mindmarket.navigation3sampleapp.note.NoteDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::NoteDetailViewModel)
}