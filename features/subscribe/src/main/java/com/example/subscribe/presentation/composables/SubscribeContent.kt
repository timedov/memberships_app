package com.example.subscribe.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.model.TierUiModel
import com.example.ui.model.UserDetailsUiModel

@Composable
fun SubscribeContent(
    userDetails: UserDetailsUiModel,
    tiers: List<TierUiModel>,
    selectedTierId: Long,
    isSubscribeButtonEnabled: Boolean,
    subscribeButtonTitle: String,
    onTierSelected: (Long) -> Unit,
    onSubscribeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    //This Colum is for fixed Subscribe button, that will not cover tier list
    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { UserDetailsTitle(userDetails) }
            items(tiers.size) { index ->
                TierItem(
                    tier = tiers[index],
                    isSelected = { tiers[index].id == selectedTierId },
                    onClick = { onTierSelected(tiers[index].id) }
                )
            }
        }

        SubscribeButton(
            onButtonClick = onSubscribeClick,
            isEnabled = isSubscribeButtonEnabled,
            buttonTitle = subscribeButtonTitle,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 8.dp, vertical = 12.dp)
        )
    }
}