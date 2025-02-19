package com.example.shoppingliststartcodekotlin


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingliststartcodekotlin.adapters.ProductAdapter
import com.example.shoppingliststartcodekotlin.data.Product
import com.example.shoppingliststartcodekotlin.data.Repository
import com.example.shoppingliststartcodekotlin.data.Repository.addProduct
import com.example.shoppingliststartcodekotlin.data.Repository.products
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.name_alert.*
import kotlinx.android.synthetic.main.name_alert.view.*
import org.w3c.dom.Text
import java.util.*


class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>? = null



    private var productList = mutableListOf(products.toString())
    private var displayList = mutableListOf(products.toString())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(applicationContext)
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

            val builder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            builder.setTitle("Customize your Welcome ")
            val dialogLayout = inflater.inflate(R.layout.name_alert, null)
          val editText  = dialogLayout.findViewById<TextInputEditText>(R.id.nameEdit)
            builder.setView(dialogLayout)
            builder.setPositiveButton("Add Name") { dialogInterface, i -> Toast.makeText(this,
              //"Welcome to BookSwap $nameInput", Toast.LENGTH_LONG).show() }
            "Welcome to BookSwap" +" " + editText.text.toString(), Toast.LENGTH_LONG).show() }
            builder.show()

            //Toast.makeText(this, "$nameInput", Toast.LENGTH_SHORT).show()
            return true


        } else if (id == R.id.action_delete) {
           // products.clear()
           // adapter?.notifyDataSetChanged()
           // Toast.makeText(this, "You are about to delete entire list!", Toast.LENGTH_SHORT).show()

          val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Confirm Delete")
           builder.setMessage("Whoa There! Are you Sure you want to delete the entire list??")
          builder.setPositiveButton("Yes") { dialog, id ->
               products.clear()
              adapter?.notifyDataSetChanged()
              dialog.cancel()
          }
           builder.setNegativeButton("No") { dialog, id ->
               dialog.cancel()
           }
          val alert = builder.create()
            alert.show()




            return true

        } else if (id == R.id.action_help) {

            Toast.makeText(this, "Contact us at 123456 for help", Toast.LENGTH_SHORT).show()

            return true
        } else if (id == R.id.action_sort) {
          products.sortBy { it.title }
           // Repository.products.sortBy { it.author }
            adapter?.notifyDataSetChanged()
            return true

        //intent to goodreads
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
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "My BookSwap list $products")
            //sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(sharingIntent, "Share Using"))

        }
        //search bar implemented but not working :(
            else if (id == R.id.action_search){
            var searchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                // check if new text is not empty
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        var search = newText.toLowerCase(Locale.getDefault())
                        for (Product in productList) {
                            if (Product.toLowerCase(Locale.getDefault()).contains(search)) {
                                displayList.add(Product)
                            }

                            recyclerView.adapter!!.notifyDataSetChanged()
                        }
                    } else {
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


    fun addProduct(view: View) {

      var product = Product(book_input.text.toString(), author_input.text.toString())

            addProduct(product).observe(this, Observer {
                Log.d("Products", "Found ${it.size} products")
                updateUI()
                adapter?.notifyDataSetChanged()
            })

            //Toast.makeText(this, "Book added to List", Toast.LENGTH_SHORT).show()
        //snackbar instead of toast with undo option from kotlin book
        Snackbar.make(view, "Book added to SwapList", Snackbar.LENGTH_LONG)
            .setAction("Undo", undoOnClickListener).show()
    }

    var undoOnClickListener: View.OnClickListener = View.OnClickListener { view ->
        products.removeAt(products.size - 1)
        adapter?.notifyDataSetChanged()
        Snackbar.make(view, "Book removed", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}



