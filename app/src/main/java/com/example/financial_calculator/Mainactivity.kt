package com.example.mrcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.widget.PopupMenu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.GravityCompat
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.core.view.iterator
import androidx.drawerlayout.widget.DrawerLayout
import com.example.financial_calculator.R

class MainActivity : AppCompatActivity() {

    // TextView used to display the input and output
    lateinit var mainLayout: LinearLayout
    lateinit var secondaryLayout: LinearLayout
    lateinit var hiddenLayout: LinearLayout
    lateinit var archivebar : Toolbar
    lateinit var mdrawer : DrawerLayout
    lateinit var drawerToggle : ActionBarDrawerToggle
     val arraylist : ArrayList<Double> = ArrayList()

    // Represent whether the lastly pressed key is numeric or not

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        archivebar = findViewById(R.id.toolbar)
        setSupportActionBar(archivebar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        mdrawer = findViewById(R.id.drawer_layout)
        drawerToggle = setupDrawerToggle()
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()
        mdrawer.addDrawerListener(drawerToggle)
        mainLayout = findViewById(R.id.main_layout)
        secondaryLayout = findViewById(R.id.jesus)
        hiddenLayout = findViewById(R.id.customview)

    }


    fun showpopup(view: View) {
        val popup = PopupMenu(this,view)
        popup.inflate(R.menu.menu_example)
        popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.custom -> showhidden(view)
                    else -> additem(view, item!!.itemId)
                }
                return true
            }
        })
        popup.show()
    }

    private fun additem(view: View, name: Int) {
        var editext : EditText = EditText(this)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        editext.setInputType(InputType.TYPE_CLASS_NUMBER)
        editext.hint=resources.getResourceEntryName(name)
        editext.layoutParams = params
        editext.id = name
        editext.setEms(10)
        mainLayout.addView(editext)

    }
    private fun showhidden(view: View) {
        hiddenLayout.isVisible = true
    }

    fun calculatehydration(view: View){
        var editext1 : EditText
        var answer : TextView
        var group : ViewGroup
        var watercontent : Double = 0.0
        var flourcontent: Int = 0
        var counter : Int = 0
        answer = findViewById(R.id.texting)
        group = findViewById(R.id.main_layout)
        group.forEach {
            if(it is EditText) {
                when(it.id) {
                    R.id.flour -> flourcontent = Integer.parseInt(it.text.toString())
                    R.id.water -> watercontent += Integer.parseInt(it.text.toString())
                    R.id.egg -> watercontent += Integer.parseInt(it.text.toString()) * .74
                    R.id.butter -> watercontent += Integer.parseInt(it.text.toString()) * .18
                    R.id.milk -> watercontent += Integer.parseInt(it.text.toString()) * .90
                    R.id.starter -> watercontent += Integer.parseInt(it.text.toString()) * .50
                    R.id.yogurt -> watercontent += Integer.parseInt(it.text.toString()) * .88
                    else -> {
                        watercontent += Integer.parseInt(it.text.toString()) * arraylist.get(counter)
                        counter += 1
                    }
                }

            }

        }

        val total: Double = (watercontent / flourcontent  )
        answer.setText((total.toString()))
    }


    fun addcustom(view: View){
        var editext : EditText = EditText(this)
        var editext2 : EditText = EditText(this)
        var editext1 : EditText = EditText(this)
        editext2 = findViewById(R.id.textView2)
        editext1 = findViewById(R.id.textView)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        editext.setInputType(InputType.TYPE_CLASS_NUMBER)
        editext.hint= editext2.text.toString()
        editext.layoutParams = params
        editext.id = View.generateViewId()
        editext.setEms(10)
        arraylist.add(editext1.text.toString().toDouble())
        mainLayout.addView(editext)
        hiddenLayout.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.home -> mdrawer.openDrawer(GravityCompat.START)

        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle{
        return ActionBarDrawerToggle(this, mdrawer, archivebar , R.string.drawer_open,  R.string.drawer_close)

    }


}


