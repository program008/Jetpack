package com.canbot.paging.adapter

import android.support.v7.widget.DecorContentParent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.canbot.paging.R
import com.canbot.paging.db.Student

/**
 * Created by tao.liu on 2018/9/11.
 * description this is description
 */
class StudentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)) {
        private val nameView = itemView.findViewById<TextView>(R.id.name)
        var student: Student? = null

        fun bindTo(student: Student?) {
                this.student = student
                nameView.text = student?.name
        }

}