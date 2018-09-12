package com.canbot.paging

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.canbot.paging.db.Student
import com.canbot.paging.db.StudentDb

/**
 * Created by tao.liu on 2018/9/11.
 * description this is description
 */
class MainViewModel(app: Application) : AndroidViewModel(app) {
        val dao = StudentDb.get(app).studentDao()

        val allStudent = LivePagedListBuilder(dao.getAllStudent(), PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .build()).build()

        fun insertStudent(name: String) {
                dao.insert(Student(id = 0, name = name))
        }

        companion object {
                private const val PAGE_SIZE = 15
                private const val ENABLE_PLACEHOLDERS = false
        }

}