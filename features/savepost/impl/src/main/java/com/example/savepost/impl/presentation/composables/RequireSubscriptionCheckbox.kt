package com.example.savepost.impl.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.savepost.impl.R

@Composable
internal fun RequireSubscriptionCheckbox(
    requiresSubscription: Boolean,
    onRequireSubscriptionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Checkbox(
            checked = requiresSubscription,
            onCheckedChange = onRequireSubscriptionChange,
            modifier = Modifier.size(36.dp)
        )
        Text(
            text = stringResource(R.string.require_subscription),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
