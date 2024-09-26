package com.example.subscribe.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.TierModel
import com.example.domain.model.UserDetailsModel

@Composable
fun SubscribeContent(
    userDetails: UserDetailsModel,
    tiers: List<TierModel>,
    buttonTitle: String,
    selectedTier: TierModel?,
    onTierSelected: (TierModel) -> Unit,
    onButtonClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { UserDetailsTitle(userDetails) }
            items(tiers.size) { index ->
                TierItem(
                    tier = tiers[index],
                    isSelected = { tiers[index] == selectedTier },
                    onClick = { onTierSelected(tiers[index]) }
                )
            }
        }
        Button(
            onClick = onButtonClick,
            enabled = selectedTier != null,
            colors = ButtonDefaults.filledTonalButtonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = buttonTitle,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
    }
}