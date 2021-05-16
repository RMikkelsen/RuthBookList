package com.example.shoppingliststartcodekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingliststartcodekotlin.R
import com.example.shoppingliststartcodekotlin.data.Repository
import com.example.shoppingliststartcodekotlin.data.Repository.deleteProduct
import com.example.shoppingliststartcodekotlin.data.Repository.products


//adding inner class of viewholder
class ProductAdapter() :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

//private var titles = arrayOf("Book One","Book Two","Book Three", "Book Four", "Book Five", "Book Six", "Book Seven", "Book Eight")
    //private var details = arrayOf("Item one details","Item two details","Item three details","Item four details", "Item five details","Item six details","Item seven details","Item eight details")
    //private var images = intArrayOf(R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover,R.drawable.bookcover)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        //create view object
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = products[position].title
        holder.itemAuthor.text = products[position].author
        //holder.itemImage.id = products[position].image
        //holder.itemDelete.view = products[position].

    }

    override fun getItemCount(): Int {
        return products.size
    }

    //handles data passed to cardview
    //recives itemview object with type View
    //RecyclerView class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var itemTitle: TextView
        var itemAuthor: TextView
        var itemImage: ImageView
        var itemDelete: ImageButton
       //var allDelete: ItemView


        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemAuthor = itemView.findViewById(R.id.item_author)
            itemDelete = itemView.findViewById(R.id.item_Delete)

           // allDelete = itemView.findViewById(R.id.action_delete)


            // itemView.setOnClickListener {
            // val position: Int = adapterPosition
            //Toast.makeText(itemView.context, "you clicked on ${products[position]}", Toast.LENGTH_SHORT).show()

            itemDelete.setOnClickListener {
                val position: Int = adapterPosition
                deleteProduct(position)
                notifyItemRemoved(position)
            }


        }

    }
}






