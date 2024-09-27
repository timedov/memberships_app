package com.example.ui.utils

import com.example.domain.model.TierDomainModel
import com.example.ui.model.TierUiModel

fun TierDomainModel.toUiModel() =
    TierUiModel(
        id = id,
        name = name,
        price = "$$price",
        description = description
    )