package com.mcmouse88.mvicomposeexample.domain.repository

import com.mcmouse88.mvicomposeexample.domain.model.NoteModel

interface NoteRepository {

    suspend fun getAllNotes(): List<NoteModel>
}