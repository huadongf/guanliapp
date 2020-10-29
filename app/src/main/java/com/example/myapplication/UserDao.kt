package com.example.myapplication

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from User where idd = :idd")
    fun chazhao(idd: Long): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where idd = :idd")
    fun deleteUserbyidd(idd: Long): Int

    @Query("select * from User order by id desc")
    fun orid(): List<User>

    @Query("select * from User order by Name desc")
    fun orname(): List<User>

    @Query("select * from User order by hometown desc")
    fun orhome(): List<User>

    @Query("select * from User order by grade desc")
    fun orgrade(): List<User>

    @Query("select * from User where id like'%'||:aa||'%' or Name like'%'||:aa||'%'  or hometown like'%'||:aa||'%' or grade like'%'||:aa||'%' or gender like'%'||:aa||'%'")
    fun chaxun(aa:String): List<User>
}