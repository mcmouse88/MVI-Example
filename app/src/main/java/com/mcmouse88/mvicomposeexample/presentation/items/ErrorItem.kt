package com.mcmouse88.mvicomposeexample.presentation.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mcmouse88.mvicomposeexample.R

@Composable
fun ErrorItem(
    message: String?,
    onButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0x4DFF0000))
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Text(
                text = message ?: "Oops, something went wrong",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(onClick = { onButtonClicked.invoke() }) {
            Text(text = stringResource(R.string.retry))
        }
    }
}