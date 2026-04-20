import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.ncca.CustomAdapter
import com.example.ncca.DBHelper
import com.example.ncca.R
import java.io.File

class StudDoc : AppCompatActivity() {
    var lv1: ListView? = null
    var g: DBHelper? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_doc)

        lv1 = findViewById<View>(R.id.listview) as ListView

        g = DBHelper(this)

        val cursor = g!!.getDoc()

        val item1 = mutableListOf<String>()
        val item2 = mutableListOf<String>()
        val item3 = mutableListOf<String>()
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("Name"))
                val date = cursor.getString(cursor.getColumnIndex("Date"))
                val fpath = cursor.getString(cursor.getColumnIndex("FPath"))
                item1.add(name)
                item2.add(date)
                item3.add(fpath)
            } while (cursor.moveToNext())
        }
        cursor.close()

        val ad1 = CustomAdapter(this, item1, item2)
        lv1!!.adapter = ad1

        lv1!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedPdf = item3[position] // Use the file path, not just the name
            openPdf(selectedPdf)
        }
    }

    private fun openPdf(pdfFilePath: String) {
        val file = File(pdfFilePath)
        val uri = Uri.fromFile(file)

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/pdf")
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        try {
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle exceptions here (e.g., PDF viewer not found)
        }
    }
}
