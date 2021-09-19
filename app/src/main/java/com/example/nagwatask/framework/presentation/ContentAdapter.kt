package com.example.nagwatask.framework.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nagwatask.business.entities.Content
import javax.inject.Inject

class ContentAdapter @Inject constructor() :
    ListAdapter<Content, ContentHolder>(ContentDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentHolder.from(parent)

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getContent(position: Int): Content = getItem(position)

    class ContentDiffCallback : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Content, newItem: Content) = oldItem == newItem
    }
}