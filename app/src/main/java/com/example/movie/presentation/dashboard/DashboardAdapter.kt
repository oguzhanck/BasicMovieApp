package com.example.movie.presentation.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.model.Movie
import com.example.movie.util.Constant

class DashboardAdapter(private val context: Context, private val dataSet: List<Movie>, private val onMovieClick: (Movie) -> Unit) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        Glide.with(context).load(Constant.posterUrl + dataSet[position].posterPath).into(viewHolder.posterImageView)
        viewHolder.titleTextView.text = dataSet[position].name
        viewHolder.rateTextView.text = String.format(context.getString(R.string.rate), dataSet[position].votePoint)
        viewHolder.voteTextView.text = String.format(context.getString(R.string.voted), dataSet[position].voteCount)
        viewHolder.itemView.setOnClickListener {
            onMovieClick.invoke(dataSet[position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val posterImageView: ImageView
        val titleTextView: TextView
        val rateTextView: TextView
        val voteTextView: TextView

        init {
            posterImageView = view.findViewById(R.id.posterImageView)
            titleTextView = view.findViewById(R.id.titleTextView)
            rateTextView = view.findViewById(R.id.rateTextView)
            voteTextView = view.findViewById(R.id.votedTextView)

        }
    }

    override fun getItemCount() = dataSet.size
}