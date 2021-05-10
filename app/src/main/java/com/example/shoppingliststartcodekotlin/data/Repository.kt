package com.example.shoppingliststartcodekotlin.data

import androidx.lifecycle.MutableLiveData

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
        products.add(Product("Salt: A World History"))
        products.add(Product("Sapiens"))
        products.add(Product("The Devil in the White City"))
        products.add(Product("Kitchen Confidential"))
        products.add(Product("A Discovery of Witches"))
    }

}