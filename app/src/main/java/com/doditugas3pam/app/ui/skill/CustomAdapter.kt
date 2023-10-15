package com.doditugas3pam.app.ui.skill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doditugas3pam.app.R

class CustomAdapter(private var mList: List<itemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_skill_card, parent, false)

        return ViewHolder(view)
    }
    @Suppress("NotifyDataSetChanged")
    fun filterList(filterlist: ArrayList<itemsViewModel>){
        mList = filterlist
        notifyDataSetChanged()
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.skillnamaTextView.text = itemViewModel.nama

        // sets the text to the textview from our itemHolder class
        holder.skillpengalamanTextView.text = itemViewModel.pengalaman

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val skillnamaTextView : TextView = itemView.findViewById(R.id.nama_skill)
        val skillpengalamanTextView : TextView = itemView.findViewById(R.id.pengalaman_skill)
    }
}

