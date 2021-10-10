package com.example.code.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.base.DataclassDiffCallback

internal class RepositoriesAdapter(private val onItemClicked: (RepositoryItem) -> Unit) :
    ListAdapter<RepositoryItem, RepositoryViewHolder>(DataclassDiffCallback<RepositoryItem>()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder =
      RepositoryViewHolder(
          LayoutInflater.from(parent.context).inflate(
              R.layout.repositories_list_item,
              parent,
              false
          )
      )

  override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
    holder.bind(getItem(position), onItemClicked)
  }
}

internal class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  private val title: TextView = itemView.findViewById(R.id.repositoryTitle)
  private val stars: TextView = itemView.findViewById(R.id.repositoryStars)
  private val description: TextView = itemView.findViewById(R.id.repositoryDescription)

  fun bind(item: RepositoryItem, onItemClicked: (RepositoryItem) -> Unit) {
    with(itemView) {
      title.text = item.title
      stars.text = item.stars
      description.text = item.description
      setOnClickListener { onItemClicked(item) }
    }
  }
}
