package hr.algebra.chordiato.presentation.history

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import hr.algebra.chordiato.R
import hr.algebra.chordiato.domain.model.Track

class HistoryRecyclerViewAdapter(
    private var tracks: List<Track>,
    private val clickListener: (Track) -> Unit,
) : RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(trackView: View, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(trackView) {

        init {
            val star = trackView.findViewById<MaterialButton>(R.id.btnStar)
            star.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        private val ivStar = trackView.findViewById<MaterialButton>(R.id.btnStar)
        private val tvSong = trackView.findViewById<TextView>(R.id.tvSongHist)
        private val tvArtist = trackView.findViewById<TextView>(R.id.tvArtistHist)
        private val tvTimestamp = trackView.findViewById<TextView>(R.id.timestamp)

        fun bind(track: Track) {
            if (track.favourite == true) {
                ivStar.setIconResource(R.drawable.ic_star_filled)
            } else {
                ivStar.setIconResource(R.drawable.ic_star_border)
            }
            tvSong.text = track.name
            tvArtist.text = track.artist
            tvTimestamp.text = track.lastPlayed.toString()
        }
    }

    fun filterList(filterList: List<Track>) {
        tracks = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            trackView = LayoutInflater.from(parent.context)
                .inflate(R.layout.history_list_item, parent, false)) {
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