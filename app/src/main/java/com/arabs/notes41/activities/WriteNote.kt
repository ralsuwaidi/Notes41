package com.arabs.notes41.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arabs.notes41.R
import android.content.Context.MODE_PRIVATE
import android.net.wifi.hotspot2.pps.HomeSp
import android.support.design.widget.Snackbar
import android.util.Log
import kotlinx.android.synthetic.main.activity_write_note.*
import java.io.*
import android.support.v4.app.NavUtils
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*


class WriteNote : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_note)


        writeToFile(write_note.toString(), this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home -> {
                Snackbar.make(write_note, "hi", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun writeToFile(data: String, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString())
        }

    }

    private fun readFromFile(context: Context): String {

        var ret = ""

        try {
            val inputStream = context.openFileInput("config.txt")

            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                var bufferedReader = BufferedReader(inputStreamReader)
                var receiveString = ""
                val stringBuilder = StringBuilder()


                while ((receiveString == bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString).append("\n")
                }

                inputStream.close()
                ret = stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: " + e.toString())
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: " + e.toString())
        }

        return ret
    }
}
