package br.com.marco.jokenpo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.marco.jokenpo.databinding.ActivityGameOverBinding
import br.com.marco.jokenpo.databinding.ActivityRankingBinding
import br.com.marco.jokenpo.firestore.Ranking
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore

class RankingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRankingBinding
    lateinit var recyclerView: RecyclerView

    lateinit var ref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val score = intent.getIntExtra("score",0);
        recuperaFirestore()


    }

    fun recuperaFirestore() {
        var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val listRanking =  arrayListOf<Ranking>()
        val collection =
            db.collection("ranking").get().addOnSuccessListener { result ->
                try {
                    for  (document in result ){
                        listRanking.add(document.toObject(Ranking::class.java));
                    }

                } finally {
                    var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
                    val adapter = CustomAdapter(this, listRanking)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = adapter

                }
            }
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = CustomAdapter(this, listRanking)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}