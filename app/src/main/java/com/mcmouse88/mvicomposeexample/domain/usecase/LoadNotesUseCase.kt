package com.mcmouse88.mvicomposeexample.domain.usecase

import com.mcmouse88.mvicomposeexample.domain.model.NoteModel
import com.mcmouse88.mvicomposeexample.domain.repository.NoteRepository
import javax.inject.Inject

class LoadNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) : BaseUseCase<List<NoteModel>>() {

    override suspend fun execute(): List<NoteModel> {
        return repository.getAllNotes()
    }
}