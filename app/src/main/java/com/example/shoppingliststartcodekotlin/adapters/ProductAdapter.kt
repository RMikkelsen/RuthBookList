package com.example.shoppingliststartcodekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingliststartcodekotlin.MainActivity
import com.example.shoppingliststartcodekotlin.R
import com.example.shoppingliststartcodekotlin.data.Product
import com.example.shoppingliststartcodekotlin.data.Repository
import com.example.shoppingliststartcodekotlin.data.Repository.addProduct
import com.example.shoppingliststartcodekotlin.data.Repository.deleteProduct
import com.example.shoppingliststartcodekotlin.data.Repository.products
import com.google.android.material.snackbar.Snackbar


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

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemAuthor = itemView.findViewById(R.id.item_author)
            itemDelete = itemView.findViewById(R.id.item_Delete)


           itemView.setOnClickListener {
               val position: Int = adapterPosition
               Toast.makeText(
                       itemView.context, "You Clicked on ${products[position]}", Toast.LENGTH_SHORT,
               ).show()
           }
                itemDelete.setOnClickListener {
                    val position: Int = adapterPosition
                    deleteProduct(position)
                    notifyItemRemoved(position)

                    //snackbar from book
                    var undoOnClickListener: View.OnClickListener = View.OnClickListener { view ->

                        //products.add(Product("$itemDelete"))
                     products.add(position, Product("Error showing product"))

                        notifyDataSetChanged()

                        Snackbar.make(view, "Book back on SwapList", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }

                    Snackbar.make(itemDelete, "Book Deleted From SwapList", Snackbar.LENGTH_LONG)
                        .setAction("Undo", undoOnClickListener).show()

                }


            }
        }
    }








