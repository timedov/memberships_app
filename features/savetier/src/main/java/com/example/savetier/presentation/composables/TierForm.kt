package com.example.savetier.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.savetier.R

@Composable
fun TierForm(
    name: String,
    nameError: String?,
    price: Double,
    priceError: String?,
    description: String,
    descriptionError: String?,
    onNameChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        FormTextField(
            value = name,
            onValueChange = onNameChange,
            label = stringResource(id = R.string.name),
            error = nameError,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormTextField(
            value = if (price == 0.0) "" else price.toString(),
            onValueChange = onPriceChange,
            label = stringResource(id = R.string.price),
            error = priceError,
            keyboardType = KeyboardType.Number,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = stringResource(id = R.string.description),
            error = descriptionError,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.description_length_counter, description.length),
                color = if (description.length > 100) MaterialTheme.colorScheme.error
                else Color.Gray
            )
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