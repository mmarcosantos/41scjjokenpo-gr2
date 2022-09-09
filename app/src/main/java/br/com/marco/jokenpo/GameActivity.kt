package br.com.marco.jokenpo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import br.com.marco.jokenpo.databinding.ActivityGameBinding
import br.com.marco.jokenpo.databinding.ActivityMainBinding
import java.util.*

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private var score = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgTesoura.setOnClickListener {
            binding.imgEscolhaUser.setImageResource(R.drawable.tesoura)
            jogadaRobo(1)
        }
        binding.imgPapel.setOnClickListener {
            binding.imgEscolhaUser.setImageResource(R.drawable.papel)
            jogadaRobo(2)
        }
        binding.imgPedra.setOnClickListener {
            binding.imgEscolhaUser.setImageResource(R.drawable.pedra)
            jogadaRobo(3)
        }
    }
    fun jogadaRobo( escolhaJogador : Int) {
        var numeroRobo : Int;
        numeroRobo = (1..3).random();
        when (numeroRobo) {
            1 -> binding.imgEscolhaRobo.setImageResource(R.drawable.tesoura)
            2 -> binding.imgEscolhaRobo.setImageResource(R.drawable.papel)
            3 -> binding.imgEscolhaRobo.setImageResource(R.drawable.pedra)
        }


        if( (numeroRobo == 1 && escolhaJogador == 2) ||
            (numeroRobo == 2 && escolhaJogador == 3) ||
            (numeroRobo == 3 && escolhaJogador == 1)
        ){
            val proximaTela = Intent(this, GameOverActivity::class.java)
            proximaTela.putExtra("score",score)
            startActivity(proximaTela)
            finish()
        }else if( (escolhaJogador == 1 && numeroRobo == 2) ||
            (escolhaJogador == 2 && numeroRobo == 3) ||
            (escolhaJogador == 3 && numeroRobo == 1)
        ){
            score = score +2;
            binding.txPontos.text = score.toString()
        }
        else {
            score++;
            binding.txPontos.text = score.toString()
        }
    }
}