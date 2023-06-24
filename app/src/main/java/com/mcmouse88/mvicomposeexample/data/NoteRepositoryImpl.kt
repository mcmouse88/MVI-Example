package com.mcmouse88.mvicomposeexample.data

import com.mcmouse88.mvicomposeexample.domain.model.NoteModel
import com.mcmouse88.mvicomposeexample.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.ConnectException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor() : NoteRepository {

    override suspend fun getAllNotes(): List<NoteModel> = withContext(Dispatchers.IO) {
        return@withContext when (count) {
            0 -> {
                count++
                delay(3_000L)
                emptyList()
            }
            1 -> {
                count++
                delay(10_000L)
                throw ConnectException("Lost Internet Connection")
            }
            2 -> {
                delay(3_000L)
                val list = List(10) { id ->
                    NoteModel(
                        id = id.toString(),
                        title = "title: $id",
                        subTitle = "subtitle: $id",
                        date = "2023-01-01",
                        author = "author: $id"
                    )
                }
                list
            }
            else -> emptyList()
        }
    }

    companion object {
        var count = 0
    }
}