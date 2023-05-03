package com.cankarademir.cankarademir_odev5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.widget.*
import com.cankarademir.cankarademir_odev5.services.XmlResult

class MainActivity : AppCompatActivity() {

    lateinit var txtDate :TextView
    lateinit var btnPopup:Button
    lateinit var txtDovizTuru:TextView
    lateinit var txtBanknoteSelling:TextView
    lateinit var txtBanknoteBuying:TextView
    lateinit var txtForexSelling:TextView
    lateinit var txtForexBuying:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtDate =findViewById(R.id.txtTarih)
        btnPopup = findViewById(R.id.btnPopup)
        txtDovizTuru = findViewById(R.id.txtDovizTuru)
        txtForexBuying = findViewById(R.id.txtForexBuying)
        txtForexSelling = findViewById(R.id.txtForexSelling)
        txtBanknoteBuying = findViewById(R.id.txtBanknoteBuying)
        txtBanknoteSelling = findViewById(R.id.txtBanknoteSelling)

        val xml = XmlResult()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val date=xml.date()
        txtDate.text= "$date Günü Belirlenen Türkiye Cumhuriyet Merkez Bankası Kurları"

        btnPopup.setOnClickListener { view ->
            val popupMenu = PopupMenu(this@MainActivity, view)

            val arr = xml.xmlCurrency()
            for (i in arr.indices) {
                popupMenu.menu.add(Menu.NONE, i, i, arr[i].Isim)
            }

            popupMenu.setOnMenuItemClickListener { menuItem ->
                txtDovizTuru.text =  arr[menuItem.itemId].Isim
                txtBanknoteBuying.text =  arr[menuItem.itemId].BanknoteBuying
                txtBanknoteSelling.text =  arr[menuItem.itemId].BanknoteSelling
                txtForexBuying.text =  arr[menuItem.itemId].ForexBuying
                txtForexSelling.text =  arr[menuItem.itemId].ForexSelling
                true
            }
            popupMenu.show()
        }

    }
}