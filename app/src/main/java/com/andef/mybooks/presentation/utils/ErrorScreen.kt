package com.andef.mybooks.presentation.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.mybooks.R

@Composable
fun ErrorScreen(
    modifier: Modifier,
    onRetryButtonClickListener: () -> Unit
) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.query_error_retry),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = colorResource(R.color.black)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Button(
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.blue)
                ),
                onClick = {
                    onRetryButtonClickListener()
                }
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 3.dp,
                        bottom = 3.dp,
                        start = 12.dp,
                        end = 12.dp
                    ),
                    text = stringResource(R.string.retry),
                    fontSize = 16.sp,
                    color = colorResource(R.color.white)
                )
            }
        }
    }
}