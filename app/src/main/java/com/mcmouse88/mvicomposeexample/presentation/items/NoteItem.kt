package com.mcmouse88.mvicomposeexample.presentation.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mcmouse88.mvicomposeexample.domain.model.NoteModel
import com.mcmouse88.mvicomposeexample.presentation.res.theme.MviComposeExampleTheme
import java.time.LocalDate
import java.util.*

@Composable
fun NoteItem(
    item: NoteModel,
    modifier: Modifier
) {
    val color = Color(
        Random().nextInt(256),
        Random().nextInt(256),
        Random().nextInt(256),
        alpha = 30
    )
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color)
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Column {
                Text(text = item.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = item.subTitle,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 24.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item.author, fontSize = 12.sp, fontWeight = FontWeight.Light)
                    Text(
                        text = "${item.date.dayOfMonth} ${item.date.month}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}

@[Composable Preview(showBackground = true)]
fun NoteItemPreview() {
    MviComposeExampleTheme {
        val note = NoteModel(
            id = 1,
            title = "title",
            subTitle = "subtitle",
            date = LocalDate.now(),
            author = "author"
        )

        NoteItem(
            item = note,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}