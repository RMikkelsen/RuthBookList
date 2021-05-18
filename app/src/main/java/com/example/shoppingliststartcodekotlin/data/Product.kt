package com.example.shoppingliststartcodekotlin.data

import com.google.firebase.firestore.Exclude


data class Product(var title: String = "", var author: String = "", @get:Exclude var id: String ="")

