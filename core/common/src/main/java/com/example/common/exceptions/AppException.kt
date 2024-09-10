package com.example.common.exceptions

sealed class AppException(message: String) : Exception(message) {
    class AuthInvalidCredentialsException(message: String) : AppException(message)
    class AuthUserDisabledException(message: String) : AppException(message)
    class AuthUnknownException(message: String) : AppException(message)

    class FirestorePermissionDeniedException(message: String) : AppException(message)
    class FirestoreServiceUnavailableException(message: String) : AppException(message)
    class FirestoreUnknownException(message: String) : AppException(message)

    class FirebaseGenericException(message: String) : AppException(message)
    class GeneralException(message: String) : AppException(message)
}