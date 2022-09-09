package br.com.marco.jokenpo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.marco.jokenpo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btPlay.setOnClickListener {
            val proximaTela = Intent(this, GameActivity::class.java)
            startActivity(proximaTela)
            finish()
        }
        binding.btRanking.setOnClickListener {
            val proximaTela = Intent(this, RankingActivity::class.java)
            startActivity(proximaTela)
            finish()
        }
        binding.btSair.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        binding.btSobre.setOnClickListener {
            val proximaTela = Intent(this, SobreActivity::class.java)
            startActivity(proximaTela)
            finish()
        }
    }
}