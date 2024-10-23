package com.example.savepost.usecase

import com.example.common.utils.Constants
import com.example.domain.model.ValidationResult
import javax.inject.Inject

class ValidatePostFormUseCase @Inject constructor() {

    fun validateTitle(title: String): ValidationResult =
        if (title.length in Constants.MIN_POST_TITLE_LENGTH..Constants.MAX_POST_TITLE_LENGTH)
            ValidationResult(isValid = true)
        else ValidationResult(
            isValid = false,
            errorMessage =
                "Title must be between ${Constants.MIN_POST_TITLE_LENGTH} and " +
                        "${Constants.MAX_POST_TITLE_LENGTH} characters"
        )

    fun validateDescription(description: String): ValidationResult =
        if (description.length in 0..Constants.MAX_POST_DESCRIPTION_LENGTH)
            ValidationResult(isValid = true)
        else
            ValidationResult(
            isValid = false,
            errorMessage =
                "Description must be less than ${Constants.MAX_POST_DESCRIPTION_LENGTH} characters"
        )
}
