package com.example.project5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SleepAdapter(private val sleepss: ArrayList<DisplaySleep>) : RecyclerView.Adapter<SleepAdapter.ViewHolder>()
{
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView
        val priceTextView:TextView
        val urlTextView:TextView

        init {
            nameTextView = itemView.findViewById(R.id.hoursTextView)
            priceTextView=itemView.findViewById(R.id.sleepNumberTextView)
            urlTextView=itemView.findViewById(R.id.moreSleepTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.sleep_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wish = sleepss.get(position)
        holder.nameTextView.text = wish.sleepRate.toString()
        holder.priceTextView.text = wish.moreSleep.toString()
        holder.urlTextView.text= wish.sleepNumber.toString()

    }

    override fun getItemCount(): Int {
        return sleepss.size
    }

}
