package com.android.my_application.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.my_application.R
import com.android.my_application.databinding.ItemSearchBinding
import com.android.my_application.network.response.ImageResult
import com.android.my_application.room.BookmarkEntity
import com.android.my_application.ui.search.SearchViewModel
import com.bumptech.glide.Glide

class SearchAdapter(private val onItemClick: (ImageResult) -> Unit) :
    RecyclerView.Adapter<SearchViewHolder>() {
    private val imageList = mutableListOf<ImageResult>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int =
        imageList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(imageList[position], onItemClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<ImageResult>) {
        imageList.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        imageList.clear()
        notifyDataSetChanged()
    }

    fun toggleBookmark(item: ImageResult) {
        imageList.firstOrNull { it.number == item.number }?.let {
            val index = imageList.indexOf(it)
            imageList[index] = item.copy(isBookmarked = !item.isBookmarked)
            notifyDataSetChanged()
        }
    }

    fun deleteBookmark(item: BookmarkEntity) {
        imageList.firstOrNull { it.number == item.number }?.let {
            val index = imageList.indexOf(it)
            imageList[index] = imageList[index].copy(isBookmarked = false)
            notifyItemChanged(index)
        }
    }
}

class SearchViewHolder(private val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageResult, onItemClick: (ImageResult) -> Unit) {
        Glide.with(itemView.context).load(item.collection).into(binding.image)

        val btnState =
            if (item.isBookmarked) R.drawable.ic_bookmark_added else R.drawable.ic_bookmark_add
        binding.btnBookmark.setBackgroundResource(btnState)

        binding.btnBookmark.setOnClickListener { onItemClick(item) }
    }
}