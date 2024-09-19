package com.example.firebase.mapper

import android.util.Log
import com.example.domain.model.UserDetailsModel
import com.example.domain.model.UserDomainModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject

class UserDomainModelMapper @Inject constructor() {

    fun firebaseUserToUserModel(input: FirebaseUser): UserDomainModel {
        Log.d("UserDomainModelMapper",
            "firebaseUserToUserModel(), uid = ${input.uid}, " +
                    "displayName = ${input.displayName}, email = ${input.email}")
        return UserDomainModel(
            uid = input.uid,
            username = input.displayName ?: "",
            email = input.email!!
        )
    }

    fun firebaseDocToUserModel(input: DocumentSnapshot): UserDomainModel =
        input.toObject(UserDomainModel::class.java)!!

    fun firebaseDocToUserDetails(input: DocumentSnapshot): UserDetailsModel =
        input.toObject(UserDetailsModel::class.java)!!

}