package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("function1 output", getTestDataArray().toString())
        Log.d("function2 output", averageLessThanMedian(listOf(3.0, 1.5, 2.0, 4.0, 5.0)).toString())
//        Log.d("function2", getView(2, null, listOf(1, 2, 3, 4, 5), context).toString())
        Log.d("function3 output", getView(2,null, listOf(1,2,3,4,5), this).toString())


        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())

    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    private fun getTestDataArray() : List<Int> = List(10){Random.nextInt()}.sorted()

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean = listOfNumbers.run {
        average().let { avg ->
            sorted().let { sortedList ->
                val median = if (size % 2 == 0)
                    (sortedList[size / 2] + sortedList[(size - 1) / 2]) / 2
                else
                    sortedList[size / 2]
                avg < median
            }
        }
    }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply {
            text = collection[position].toString()
        }




}