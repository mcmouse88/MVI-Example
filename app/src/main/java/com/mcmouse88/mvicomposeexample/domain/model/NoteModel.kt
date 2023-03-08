package com.mcmouse88.mvicomposeexample.domain.model

import java.time.LocalDate

data class NoteModel(
    val id: Long,
    val title: String,
    val subTitle: String,
    val date: LocalDate,
    val author: String
)
