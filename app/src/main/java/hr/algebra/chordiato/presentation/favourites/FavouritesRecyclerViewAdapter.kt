package hr.algebra.chordiato.presentation.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.chordiato.R
import hr.algebra.chordiato.domain.model.Track

class FavouritesRecyclerViewAdapter(
    private var tracks: List<Track>,
    private val clickListener: (Track) -> Unit,
) : RecyclerView.Adapter<FavouritesRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(trackView: View, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(trackView) {

        init {
            trackView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        private val tvSong = trackView.findViewById<TextView>(R.id.tvSongFav)
        private val tvArtist = trackView.findViewById<TextView>(R.id.tvArtistFav)
        fun bind(track: Track) {
            tvSong.text = track.name
            tvArtist.text = track.artist
        }
    }

    fun filterList(filterList: List<Track>) {
        tracks = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            trackView = LayoutInflater.from(parent.context)
                .inflate(R.layout.favourites_list_item, parent, false)) {
            clickListener(tracks[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tracks[position])
    }

    override fun getItemCount(): Int {
        return tracks.size
    }
}