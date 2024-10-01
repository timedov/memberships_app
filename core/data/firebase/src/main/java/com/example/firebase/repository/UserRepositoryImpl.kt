package com.example.firebase.repository

import android.util.Log
import com.example.domain.model.UserDomainModel
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.example.common.utils.Keys
import com.example.domain.model.SubscribeStatus
import com.example.domain.model.UserDetailsDomainModel
import com.example.firebase.mapper.UserDomainModelMapper

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val mapper: UserDomainModelMapper
) : UserRepository {

    override suspend fun signUp(
        username: String,
        email: String,
        password: String
    ): UserDomainModel {
//        Log.i("UserRepositoryImpl",
//            "SignUp(username=$username, email=$email, password=$password)")
//        val user = createUserWithEmailAndPassword(email, password).copy(username = username)
//        saveUserToStore(user)
//        return user
        TODO("Not yet implemented")
    }

    //    private suspend fun createUserWithEmailAndPassword(
//        email: String,
//        password: String
//    ): UserDomainModel {
//        val userFB = auth.createUserWithEmailAndPassword(email, password).await()
//        return mapper.firebaseUserToUserModel(userFB.user!!)
//    }

//    private suspend fun saveUserToStore(user: UserDomainModel) {
//        db.collection(Keys.USERS_COLLECTION_KEY).document(user.uid).set(user).await()
//    }

    override suspend fun signIn(email: String, password: String): UserDomainModel {
        Log.i("UserRepositoryImpl", "SignIn(email=$email, password=$password)")
        val userFB = auth.signInWithEmailAndPassword(email, password).await()
        return userFB.user?.let { mapper.firebaseUserToUserModel(it) } ?: UserDomainModel()
    }


    override suspend fun getUserById(userId: String): UserDomainModel =
        mapper.firebaseDocToUserModel(
            db.collection(Keys.USERS_COLLECTION_KEY).document(userId).get().await()
        )

    override suspend fun getCurrentUserId(): String? = auth.currentUser?.uid

    override suspend fun getCurrentUserCredentials(): String {
        val userId = getCurrentUserId()
        if (userId.isNullOrEmpty()) throw IllegalArgumentException("User not authorized")
        val documents = db.collection(Keys.USERS_COLLECTION_KEY).document(userId).get().await()

        return documents.get(Keys.USERNAME_KEY) as String
    }

    override suspend fun updateUserCredentials(username: String): Boolean {
        val userId = getCurrentUserId() ?: throw IllegalArgumentException("User not authorized")

        db.collection(Keys.USERS_COLLECTION_KEY).document(userId).update(
            Keys.USERNAME_KEY, username
        ).await()
        return true
    }

    override suspend fun getUserDetailsByUsername(username: String): UserDetailsDomainModel {
        val documents = db.collection(Keys.USERS_COLLECTION_KEY)
            .whereEqualTo(Keys.USERNAME_KEY, username)
            .get()
            .await()
        return mapper.firebaseDocToUserDetails(documents.first())
    }

    override suspend fun getUserSubscribersCount(username: String): Int =
        db.collection(Keys.SUBSCRIBES_COLLECTION_KEY)
            .whereEqualTo(Keys.SUBSCRIBED_TO_KEY, username)
            .whereEqualTo(Keys.STATUS_KEY, SubscribeStatus.ACTIVE)
            .get()
            .await()
            .size()

    override suspend fun signOut() {
        auth.signOut()
    }
}