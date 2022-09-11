package br.com.marco.jokenpo

import android.R
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.marco.jokenpo.databinding.ActivityRankingBinding
import br.com.marco.jokenpo.firestore.Ranking
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class RankingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRankingBinding
    private val listRanking = ArrayList<String>()
    private val database = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.listRanking.add("Aguarde...")
        binding.listview.adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, listRanking)

        this.recuperaFirestore()
    }

    fun recuperaFirestore() {
        this.listRanking.clear()

        // TODO: Como a coluna score é string, verificar como fazer, se altera, ou se tem como ordenar como int
        // TODO: E ordenar por score e nome
        database.collection("ranking")
            .orderBy("score", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    this.listRanking.add(document.data.get("nome").toString() + ": " + document.data.get("score").toString())
                }

                binding.listview.adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, this.listRanking)
            }
            .addOnFailureListener { exception  ->
                Log.w(TAG, "Error getting documents : $exception")
            }
    }
}