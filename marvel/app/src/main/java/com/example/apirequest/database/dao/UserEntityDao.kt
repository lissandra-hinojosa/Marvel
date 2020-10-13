package com.example.apirequest.database.dao

import androidx.room.*
import com.example.apirequest.database.entities.UserEntity


//CONNECTS THE APPLICATION WITH THE DATABASE
@Dao //Kapt
interface UserEntityDao {
    @Insert
    fun insertUser(userEntity: UserEntity): Long //Return id of the element to insert


//    @Delete
//    fun deleteUser()

    @Query("Select * FROM user")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM user WHERE email = :email and password = :password")
    fun logIn(email: String, password: String): UserEntity?

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUser(email: String): UserEntity?

    @Update
    fun updateUser(userEntity: UserEntity): Int

//    @Query("UPDATE user " +
//            "SET email = :email, name = :name, last_name = :lastName, " +
//            "password = :password, register_date = :registerDate, " +
//            "last_login_date = :lastLoginDate, profile_image = :profileImage " +
//            "WHERE email = :email")
//    fun updateUser(email: String, name: String, lastName: String, password: String, registerDate: String, lastLoginDate: String?, profileImage: String? ): Int
}