package com.example.shoppingliststartcodekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingliststartcodekotlin.R

//adding inner class of viewholder
class ProductAdapter() :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

private var titles = arrayOf("Book One","Book Two","Book Three", "Book Four", "Book Five", "Book Six", "Book Seven", "Book Eight")
    private var details = arrayOf("Item one details","Item two details","Item three details","Item four details", "Item five details","Item six details","Item seven details","Item eight details")
    private var images = intArrayOf(R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
      //create view object
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
       return titles.size
    }
//handles data passed to cardview
    //recives itemview object with type View
    //RecyclerView class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)

            itemView.setOnClickListener {
                val position: Int = adapterPosition

                Toast.makeText(itemView.context, "you clicked on ${titles[position]}", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
