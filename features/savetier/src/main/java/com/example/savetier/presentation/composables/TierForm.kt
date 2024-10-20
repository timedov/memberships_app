package com.example.savetier.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.common.utils.Constants
import com.example.savetier.R
import com.example.ui.view.composables.FormTextField
import com.example.ui.view.composables.TextLengthCounter

@Composable
fun TierForm(
    name: String,
    nameError: String,
    price: Double,
    priceError: String,
    description: String,
    descriptionError: String,
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
            label = { Text(text = stringResource(id = R.string.name)) },
            error = nameError,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormTextField(
            value = if (price == 0.0) "" else price.toString(),
            onValueChange = onPriceChange,
            label = { Text(text = stringResource(id = R.string.price)) },
            error = priceError,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text(text = stringResource(id = R.string.description)) },
            error = descriptionError,
            modifier = Modifier.fillMaxWidth()
        )
        TextLengthCounter(
            textLength = description.length,
            maxLength = Constants.MAX_TIER_DESCRIPTION_LENGTH,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}
