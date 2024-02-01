package com.android.my_application.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.my_application.databinding.ItemBookmarkBinding
import com.android.my_application.room.BookmarkEntity
import com.bumptech.glide.Glide

class BookmarkAdapter(private val onItemClick: (BookmarkEntity) -> Unit) :
    RecyclerView.Adapter<BookmarkViewHolder>() {

    private val bookmarkList = mutableListOf<BookmarkEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun getItemCount(): Int =
        bookmarkList.size

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(bookmarkList[position], onItemClick)
    }

    fun addAll(items: List<BookmarkEntity>) {
        bookmarkList.clear()
        bookmarkList.addAll(items)
        notifyDataSetChanged()
    }
}

class BookmarkViewHolder(private val binding: ItemBookmarkBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BookmarkEntity, onItemClick: (BookmarkEntity) -> Unit) {
        Glide.with(itemView.context).load(item.collection).into(binding.image)
        binding.btnClose.setOnClickListener {
            onItemClick(item)
        }
    }

}