package com.fizz.compose.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户信息
 */
@Entity(tableName = "account")
data class AccountEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "0",

    @ColumnInfo(name = "json")
    val json: String
)