package com.dhxxn17.escape96app.ui.pages.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.data.Theme

class ThemeAdapter : RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>() {

    private val list = mutableListOf<Theme>()
    var onClick: (Int) -> Unit = {}

    fun updateData(themes: List<Theme>) {
        this.list.clear()
        this.list.addAll(themes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThemeAdapter.ThemeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_theme, parent, false)
        return ThemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThemeAdapter.ThemeViewHolder, position: Int) {
        val result = list[position]
        holder.bind(result)
        holder.itemView.setOnClickListener { onClick(result.id) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ThemeViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        private val image: ImageView = view!!.findViewById(R.id.item_theme_image)
        private val title: TextView = view!!.findViewById(R.id.item_theme_title)
        private val location: TextView = view!!.findViewById(R.id.item_theme_location)

        fun bind(theme: Theme) {
            Glide.with(itemView)
                .load(theme.thumbnail)
                .into(image)
            image.clipToOutline = true
            title.text = theme.title
            location.text = theme.location
        }
    }
}