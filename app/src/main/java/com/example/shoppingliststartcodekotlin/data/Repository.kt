package com.example.shoppingliststartcodekotlin.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


object Repository {


    var products = mutableListOf<Product>()
   //private lateinit var db: Firebase.firestore


    private val db = Firebase.firestore
//var db = FirebaseFirestore.getInstance()

    //listener to changes that we can then use in the Activity
    var productListener = MutableLiveData<MutableList<Product>>()


    fun getData(): MutableLiveData<MutableList<Product>> {
       if (products.isEmpty())
            createTestData()

            readDataFromFireBase()

        productListener.value = products //we inform the listener we have new data
        return productListener
    }

    fun createTestData() {
        //add some products to the products list - for testing purposes
        products.add(Product("Salt: A World History", "Mark Kurlansky"))
      products.add(Product("Sapiens", "Yuval Noah Harari"))
        products.add(Product("The Devil in the White City", "Erik Larson"))
        products.add(Product("Kitchen Confidential", "Anthony Bourdain"))
       products.add(Product("A Discovery of Witches", "Deborah Harkness"))
       products.add(Product("Dune", "Frank Herbert"))
        products.add(Product("Guns, Germs, & Steel", "Frank Diamond"))
        products.add(Product("Shantaram", "Gregory David Roberts"))

    }

    fun addProduct(product: Product): MutableLiveData<MutableList<Product>> {

        //db = Firebase.firestore
      productListener.value = products
     products.add(product)
        db.collection("products")
            .add(product)
            .addOnSuccessListener { documentReference ->
                Log.d("Error", "DocumentSnapshot written with ID: " + documentReference.id)
                product.id = documentReference.id
            }
            .addOnFailureListener { e -> Log.w("Error", "Error adding document", e) }

      return productListener
    }

    private fun readDataFromFireBase()
    {
        //val db = Firebase.firestore
        db.collection("products").get()
            .addOnSuccessListener {result ->
                for (document in result){
                    Log.d("Repository", "${document.id} =>${document.data}")
                    val product = document.toObject<Product>()
                    product.id = document.id
                    //products.add(product)
                }
                productListener.value = products
            }
            .addOnFailureListener{exception ->
                Log.d("Repository", "Error getting documents: ", exception)
            }
    }
    fun deleteProduct(index: Int): MutableLiveData<MutableList<Product>> {
        products.removeAt(index)
        productListener.value = products
        return productListener
    }

}

