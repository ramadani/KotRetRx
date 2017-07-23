package id.ramadani.retrx

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by dani on 7/23/17.
 */
class QuakeAdapter(val earthQuakes: ArrayList<EarthQuake>) :
        RecyclerView.Adapter<QuakeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val itemView = inflater.inflate(R.layout.item_quake, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = earthQuakes.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(earthQuakes[position])
    }

    fun replaceData(items: List<EarthQuake>) {
        earthQuakes.clear()
        earthQuakes.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTvTitle = itemView.findViewById<TextView>(R.id.tv_quake_title)

        fun bind(earthQuake: EarthQuake) {
            mTvTitle.text = earthQuake.title
        }
    }
}