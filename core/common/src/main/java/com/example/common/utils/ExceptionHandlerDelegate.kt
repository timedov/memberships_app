package com.example.common.utils

import com.example.common.exceptions.AppException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestoreException
import retrofit2.HttpException
import javax.inject.Inject

class ExceptionHandlerDelegate @Inject constructor() {

    fun handleException(exception: Throwable): AppException =
        when (exception) {
            is FirebaseAuthException -> handleAuthException(exception)
            is FirebaseFirestoreException -> handleFirestoreException(exception)
            is FirebaseException -> handleGeneralFirebaseException(exception)
            is HttpException -> handleHttpException(exception)
            else -> handleGeneralException(exception)
        }

    private fun handleAuthException(exception: FirebaseAuthException): AppException =
        when (exception) {
           is FirebaseAuthInvalidCredentialsException -> {
               AppException.AuthInvalidCredentialsException(exception.message.toString())
            }
            is FirebaseAuthInvalidUserException ->
                AppException.AuthUserDisabledException(exception.message.toString())
            else -> AppException.AuthUnknownException("An unknown authentication error occurred.")
        }

    private fun handleFirestoreException(exception: FirebaseFirestoreException): AppException =
        when (exception.code) {
            FirebaseFirestoreException.Code.PERMISSION_DENIED ->
                AppException.FirestorePermissionDeniedException(
                    "You do not have permission to access this resource."
                )
            FirebaseFirestoreException.Code.UNAVAILABLE ->
                AppException.FirestoreServiceUnavailableException(
                    "Firestore service is currently unavailable."
                )
            else -> AppException.FirestoreUnknownException(
                "An unknown Firestore error occurred."
            )
        }

    private fun handleGeneralFirebaseException(exception: FirebaseException): AppException =
        AppException.FirebaseGenericException(
            "${exception.message}"
        )

    private fun handleGeneralException(exception: Throwable): AppException =
        AppException.GeneralException("${exception.message}")

    private fun handleHttpException(exception: HttpException): AppException =
        when (exception.code()) {
            400 -> AppException.HttpBadRequestException("Bad request: ${exception.message()}")
            401 -> AppException.HttpUnauthorizedException(
                    "Unauthorized access: ${exception.message()}"
                )
            404 -> AppException.HttpNotFoundException("Resource not found: ${exception.message()}")
            500 -> AppException.HttpInternalServerErrorException(
                "Internal server error: ${exception.message()}"
            )
            else -> AppException.HttpUnknownException("Unknown HTTP error: ${exception.message()}")
        }
}
