package com.example.handybook.books

enum class Categories(var categoryName:String, var status:Boolean = false) {
    BARCHASI("Barchasi"),
    ROMANLAR("Romanlar"),
    DARSLIKLAR("Darsliklar"),
    QISSALAR("Qissalar"),
    HIKOYALAR("Hikoyalar"),
    DINIY_KITOBLAR("Diniy kitoblar"),
    ERTAKLAR("Bolalar uchun ertak kitoblar")
}