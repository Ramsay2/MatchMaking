package com.sachin.matchmaking.ui.adapter.profiles_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sachin.matchmaking.R

class MyLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MyLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: MyLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MyLoadStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false)
        return MyLoadStateViewHolder(view, retry)
    }
}

class MyLoadStateViewHolder(
    itemView: View,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val errorMsg: TextView = itemView.findViewById(R.id.errorText)
    private val retryButton: Button = itemView.findViewById(R.id.retryButton)

    init {
        retryButton.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
            retryButton.visibility = View.VISIBLE
        } else {
            errorMsg.text = ""
            retryButton.visibility = View.GONE
        }
    }
}
