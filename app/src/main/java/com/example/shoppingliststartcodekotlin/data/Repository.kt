package com.example.shoppingliststartcodekotlin.data

import androidx.lifecycle.MutableLiveData
import com.example.shoppingliststartcodekotlin.R

object Repository {


    var products = mutableListOf<Product>()

    //listener to changes that we can then use in the Activity
    private var productListener = MutableLiveData<MutableList<Product>>()


    fun getData(): MutableLiveData<MutableList<Product>> {
        if (products.isEmpty())
            createTestData()
        productListener.value = products //we inform the listener we have new data
        return productListener
    }

    fun createTestData()
    {
        //add some products to the products list - for testing purposes
        products.add(Product("Salt: A World History", "Mark Kurlansky",
            R.drawable.whitebook))
        products.add(Product("Sapiens", "Yuval Noah Harari", R.drawable.whitebook))
        products.add(Product("The Devil in the White City", "Erik Larson", R.drawable.whitebook))
        products.add(Product("Kitchen Confidential", "Anthony Bourdain", R.drawable.whitebook ))
        products.add(Product("A Discovery of Witches", "Deborah Harkiness", R.drawable.whitebook))
        products.add(Product("Dune", "Frank Herbert", R.drawable.whitebook))
        products.add(Product("Guns, Germs, & Steel", "Frank Diamond", R.drawable.whitebook))
        products.add(Product("Shantaram", "Gregory David Roberts", R.drawable.whitebook ))


    }

fun addProduct(product: Product) {
    products.add(product)
    productListener.value = products
        products.add(product)


}

    fun deleteProduct(index: Int){
        products.removeAt(index)
    }

    fun deleteAllProducts() {

    }


}