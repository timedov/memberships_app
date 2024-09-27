package com.example.subscribe.presentation.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ui.model.TierUiModel
import com.example.domain.model.UserDetailsModel

@Composable
fun SubscribeContent(
    userDetails: UserDetailsModel,
    tiers: List<TierUiModel>,
    selectedTier: TierUiModel,
    onTierSelected: (TierUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
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
}