package com.example.shoppingliststartcodekotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingliststartcodekotlin.adapters.ProductAdapter
import com.example.shoppingliststartcodekotlin.data.Product
import com.example.shoppingliststartcodekotlin.data.Repository
import com.example.shoppingliststartcodekotlin.data.Repository.products
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //you need to have an Adapter for the products
    // lateinit var adapter: ProductAdapter

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>? = null


    private var productList = mutableListOf<String>(products.toString())
    private var displayList = mutableListOf<String>(products.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//initialize layoutManager object
        layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager

        adapter = ProductAdapter()
        recyclerView.adapter = adapter

        Repository.getData().observe(this, Observer {
            Log.d("Products", "Found ${it.size} products")
            updateUI()
        })


    }


    fun updateUI() {
        // val layoutManager = LinearLayoutManager(this)

        /*you need to have a defined a recylerView in your
        xml file - in this case the id of the recyclerview should
        be "recyclerView" - as the code line below uses that */

        //recyclerView.layoutManager = layoutManager

        // adapter = ProductAdapter(Repository.products)

        /*connecting the recyclerview to the adapter  */
        //recyclerView.adapter = adapter

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            Toast.makeText(this, "settings clicked", Toast.LENGTH_LONG).show()
            return true
        } else if (id == R.id.action_delete) {
            Toast.makeText(this, "Delete Entire List Clicked!", Toast.LENGTH_LONG).show()
            //  game?.newGame()
            return true
        } else if (id == R.id.action_help) {
            Toast.makeText(this, "Help Button Clicked", Toast.LENGTH_LONG).show()
            //  game?.newGame()
            return true


        }else if (id == R.id.action_read) {

                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.goodreads.com")
                    )
                    startActivity(intent)
                }else if (id == R.id.action_send) {

           //val text = inputText.text.toString()
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Shared Data")
           // sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(sharingIntent, "Share Using"))

        }
else if (id == R.id.action_search){
            var searchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query:String?): Boolean{
                return true
                }

               // check if new text is not empty
                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        var search = newText.toLowerCase(Locale.getDefault())
                        for(Product in productList){
                            if(Product.toLowerCase(Locale.getDefault()).contains(search)){
                displayList.add(Product)
                            }

                            recyclerView.adapter!!.notifyDataSetChanged()
                        }
                    }else {
                        displayList.clear()
                        displayList.addAll(productList)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }


            return super.onOptionsItemSelected(item)
        }


}

