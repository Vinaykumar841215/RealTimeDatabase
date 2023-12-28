package com.example.realtimedatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val userData :ArrayList<UserModel?>):RecyclerView.Adapter<UserAdapter.HolderView> (){
    class HolderView(itemView:View):RecyclerView.ViewHolder(itemView){

        val get_id = itemView.findViewById<TextView>(R.id.userId)
        val get_name = itemView.findViewById<TextView>(R.id.getName)
        val get_email = itemView.findViewById<TextView>(R.id.getEmail)
        val get_phone = itemView.findViewById<TextView>(R.id.getPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.user_list,parent,false)

        return HolderView(layout)
     }

    override fun getItemCount(): Int {
        return userData.size
    }

    override fun onBindViewHolder(holder: HolderView, position: Int) {
        holder.get_id.text ="Id :- " + userData[position]?.id
        holder.get_name.text ="Name :- "+ userData[position]?.name
        holder.get_email.text ="Email :- " +userData[position]?.email
        holder.get_phone.text = "Phone :- " +userData[position]?.phone.toString()
    }
}