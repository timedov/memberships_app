package com.example.savetier.usecase

import com.example.domain.model.ValidationResult
import javax.inject.Inject

class ValidateTierFormUseCase @Inject constructor() {

    fun validateName(name: String): ValidationResult =
        if (name.length in 3..16) ValidationResult(isValid = true)
        else ValidationResult(
            isValid = false,
            errorMessage = "Name must be between 3 and 16 characters"
        )



    fun validatePrice(price: Double): ValidationResult =
        if (price in 0.1..1000.0) ValidationResult(isValid = true)
        else ValidationResult(
            isValid = false,
            errorMessage = "Price must be between 0.1 and 1000.0"
        )

    fun validateDescription(description: String): ValidationResult =
        if (description.length in 3..100) ValidationResult(isValid = true)
        else ValidationResult(
            isValid = false,
            errorMessage = "Description must be between 3 and 100 characters"
        )
}