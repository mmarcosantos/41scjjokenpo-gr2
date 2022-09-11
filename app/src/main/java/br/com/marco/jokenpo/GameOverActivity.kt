package br.com.marco.jokenpo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.marco.jokenpo.databinding.ActivityGameOverBinding
import br.com.marco.jokenpo.databinding.ActivityMainBinding
import br.com.marco.jokenpo.firestore.Ranking
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class GameOverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameOverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val score = intent.getIntExtra("score",0);

        binding.btJogarNovamente.setOnClickListener {
            var nomeDoJogador = binding.txNomeUsuario.text.toString()
            salvaFirestore(nomeDoJogador,score.toFloat());
            val proximaTela = Intent(this, GameActivity::class.java)
            startActivity(proximaTela)
            finish()
        }
        binding.btMenu.setOnClickListener {
            var nomeDoJogador = binding.txNomeUsuario.text.toString()
            salvaFirestore(nomeDoJogador,score.toFloat());
            val proximaTela = Intent(this, MainActivity::class.java)
            startActivity(proximaTela)
            finish()
        }
    }

    fun salvaFirestore( nomeUsuario : String, pontos: Float){
        var ranking = Ranking(nomeUsuario, pontos)
        val db = FirebaseFirestore.getInstance()
        db.collection("ranking").add(ranking)
    }
}