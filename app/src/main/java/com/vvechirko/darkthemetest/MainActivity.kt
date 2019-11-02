package com.vvechirko.darkthemetest

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val modes = arrayOf(
        "Light" to AppCompatDelegate.MODE_NIGHT_NO,
        "Dark" to AppCompatDelegate.MODE_NIGHT_YES,
//        API 21–27
        "Set by Battery Saver" to AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY,
//        API 28+
        "Use system default" to AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recycleView)
        rv.adapter = Adapter(mock())

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_theme) {
                showDialog()
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun showDialog() {
        val items = modes.map { it.first }.toTypedArray()
        val t = AppCompatDelegate.getDefaultNightMode()
        val checked = modes.indexOfFirst { it.second == t }

        AlertDialog.Builder(this)
            .setTitle("App Theme")
            .setSingleChoiceItems(items, checked) { d, i ->
                val mode = modes[i].second
                AppCompatDelegate.setDefaultNightMode(mode)
                d.dismiss()
            }
            .show()
    }
}