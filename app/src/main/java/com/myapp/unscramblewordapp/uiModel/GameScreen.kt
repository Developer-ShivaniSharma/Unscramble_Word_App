package com.myapp.unscramblewordapp.uiModel

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.unscramblewordapp.R

@Composable
fun GameStatus(score:Int,modifier: Modifier=Modifier){
    Card {
        Text(
            text = stringResource(R.string.score, score),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )

    }
}

@Preview
@Composable
fun GameStatusPreview(){
    GameStatus(0,)
}