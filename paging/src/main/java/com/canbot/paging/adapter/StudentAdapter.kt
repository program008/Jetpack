package com.canbot.paging.adapter

import android.arch.paging.PagedListAdapter
import com.canbot.paging.db.Student
import android.support.v7.util.DiffUtil
import android.view.ViewGroup

/**
 * Created by tao.liu on 2018/9/11.
 * description this is description
 */
class StudentAdapter : PagedListAdapter<Student, StudentViewHolder>(diffCallback) {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StudentViewHolder =
                StudentViewHolder(p0)


        override fun onBindViewHolder(p0: StudentViewHolder, p1: Int) {
                p0.bindTo(getItem(p1))
        }

        companion object {
                private val diffCallback = object : DiffUtil.ItemCallback<Student>() {
                        override fun areItemsTheSame(p0: Student, p1: Student): Boolean =
                                p0.id == p1.id

                        override fun areContentsTheSame(p0: Student, p1: Student): Boolean =
                                p0 == p1

                }
        }

}