package com.anmol2805.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anmol2805.base.ui.binding.BindableAdapter
import com.anmol2805.base.ui.binding.diffCallback
import com.anmol2805.home.domain.model.VideoDetails
import com.anmol2805.home.ui.ItemVideoBinding.inflate
import com.anmol2805.home.ui.VideoPlayerRecyclerAdapter.ViewHolder

class VideoPlayerRecyclerAdapter(
    private val viewModel: VideoPlayerViewModel,
    private val bookMarkHandler: BookMarkHandler
): RecyclerView.Adapter<ViewHolder>(),
    BindableAdapter<VideoDetails> {

    override var items: List<VideoDetails> by diffCallback(emptyList()) { o, n ->
        o.videoUri == n.videoUri
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], viewModel, bookMarkHandler)
    }

    class ViewHolder(
        private val binding: ItemVideoBinding
    ) : RecyclerView.ViewHolder(binding.root){

        companion object {
            operator fun invoke(parent: ViewGroup): ViewHolder {
                val binding = inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(
            viewState: VideoDetails,
            viewModel: VideoPlayerViewModel,
            bookMarkHandler: BookMarkHandler
        ) {
            binding.viewState = viewState
            binding.viewModel = viewModel
            binding.handler = bookMarkHandler
            binding.executePendingBindings()
        }
    }
}