package com.example.savetier.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.savetier.R
import com.example.ui.themes.Shapes

@Composable
fun TierForm(
    name: String,
    nameError: String?,
    price: Double,
    priceError: String?,
    description: String,
    descriptionError: String?,
    onNameChange: (String) -> Unit,
    onPriceChange: (Double) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { onNameChange(it) },
            label = { Text(stringResource(R.string.name)) },
            isError = !nameError.isNullOrEmpty(),
            shape = Shapes.large,
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (!nameError.isNullOrEmpty()) {
            Text(text = nameError, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = if (price == 0.0) "" else price.toString(),
            onValueChange = { onPriceChange(it.toDouble()) },
            label = { Text(stringResource(R.string.price)) },
            isError = !priceError.isNullOrEmpty(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            shape = Shapes.large,
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (!priceError.isNullOrEmpty()) {
            Text(text = priceError, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = description,
            onValueChange = {
                onDescriptionChange(it)
            },
            label = { Text(stringResource(R.string.description)) },
            isError = !descriptionError.isNullOrEmpty(),
            maxLines = 5,
            shape = Shapes.large,
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${description.length}/100",
                color = if (description.length > 100) MaterialTheme.colorScheme.error
                else Color.Gray
            )
        }
        if (!descriptionError.isNullOrEmpty()) {
            Text(text = descriptionError, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}
