package cm.pam.cibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategorySearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_search)

        var recyclerView : RecyclerView
        var repas = mutableListOf(
            Repas( R.drawable.fritte, "burgeur au fritte","qsdqs",3f),
            Repas( R.drawable.fritte, "burgeur au fritte","dqsdqsd",2f),
            Repas( R.drawable.fritte, "burgeur au fritte","qsdqsdqsd",1f)

        )

        recyclerView = findViewById(R.id.RecycleSearch)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = RepasAdapter(repas)
        }
    }
}