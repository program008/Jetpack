package com.canbot.paging.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by tao.liu on 2018/9/11.
 * description this is description
 */
@Entity
data class Student(@PrimaryKey(autoGenerate = true) val id:Int, val name:String)