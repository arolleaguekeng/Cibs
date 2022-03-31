package cm.pam.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomePage : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var repasAdapter: RepasAdapter
    lateinit var recyclerView2: RecyclerView
    lateinit var categoryAdapteur: CategoryAdapteur
    lateinit var recyclerView3: RecyclerView
    lateinit var friendAdapter: FriendAdaptater
    lateinit var txtSeeAll: TextView
    lateinit var txtSeeAllCat: TextView
    lateinit var txtSeeAllProf: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        txtSeeAll = findViewById(R.id.txtSeeAll)
        txtSeeAll.setOnClickListener {
            val intent : Intent =  Intent(this,CategoriesList::class.java)
            startActivity(intent)
        }

        txtSeeAllCat = findViewById(R.id.txtSeeAllCategory)
        txtSeeAllCat.setOnClickListener {
            val intent : Intent =  Intent(this,CategorySearch::class.java)
            startActivity(intent)
        }

        txtSeeAllProf= findViewById(R.id.txtSeeAllUser)
        txtSeeAllProf.setOnClickListener {
            val intent : Intent =  Intent(this,UserProfile::class.java)
            startActivity(intent)
        }
        var friendList = mutableListOf(
            Friend(R.drawable.luffy, "Natural"),
            Friend(R.drawable.light, "Chine"),
            Friend(R.drawable.asta, "Gras"),
            Friend(R.drawable.one, "Fromage"),
            Friend(R.drawable.light, "Natural"),
            Friend(R.drawable.luffy2, "Chine"),
            Friend(R.drawable.code, "Gras"),
            Friend(R.drawable.light, "Fromage")
        )


        var categoryList = mutableListOf(
            Category(R.drawable.fruit, "Natural"),
            Category(R.drawable.chine, "Chine"),
            Category(R.drawable.cuisto, "Gras"),
            Category(R.drawable.degeux, "Fromage"),
        )

        var repasList = mutableListOf(
            Repas( R.drawable.fritte, "burger au fritte", "delicieux burger au fritte de pomme fait maison", 4.3f),
            Repas( R.drawable.propre2, "", "repas de luxe rien que pour les bourgoies", 3.2f),
            Repas( R.drawable.chine2, "patte de nouille", "repas chinois chez nous avec de plat de nouille", 5f),
            Repas( R.drawable.cuisto, "viande rotie", "plat de viande rotie a la braise", 1.5f),
            Repas( R.drawable.fruit, "salade au fruit", "consommer leger et equilibre cette salade au fruit", 4.3f),
            Repas( R.drawable.degeux, "fromage", "delicieux fromage ao lait de chevre", 4.8f),
            Repas( R.drawable.plantain, "DG", "delicieux fromage ao lait de chevre", 2.2f),
            Repas( R.drawable.dg1, "plantain saute", "delicieux fromage ao lait de chevre", 4.5f)

        )

        recyclerView3 = findViewById(R.id.recycle3)
        friendAdapter = FriendAdaptater(friendList)

        recyclerView2 = findViewById(R.id.recycle2)
        categoryAdapteur = CategoryAdapteur(categoryList)

        recyclerView = findViewById(R.id.recycle1)
        repasAdapter = RepasAdapter(repasList)


        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = repasAdapter

        }

        recyclerView2.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapteur

        }

        recyclerView3.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = friendAdapter
        }
    }
}