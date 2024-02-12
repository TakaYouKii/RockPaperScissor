package com.example.rps

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.rockpaperscissor.R
import com.example.rockpaperscissor.databinding.ActivityMainBinding

import kotlin.random.Random

//
//                btnRock.isEnabled = false
//                btnScissors.isEnabled = false
//                btnRock.isEnabled = true
//                btnScissors.isEnabled = true

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var botScore = 0
        var myScore = 0



        val images = arrayListOf(
            binding.imgRandomScissors,
            binding.imgRandomPaper,
            binding.imgRandomRock,
        )



        with(binding) {

            btnPaper.setOnClickListener {
                imgScissors.visibility = View.GONE
                imgPaper.visibility = View.VISIBLE
                imgRock.visibility = View.GONE

                val randomImage = getRandomImage(images)

                val result = getResult(randomImage, 1, 2, 0)

                when (result) {
                    1 -> {
                        myScore++
                        tvMyScore.text = myScore.toString()

                    }

                    -1 -> {
                        botScore++
                        tvBotScore.text = botScore.toString()

                    }


                }

                finalResult(myScore, botScore)
            }



            btnRock.setOnClickListener {

                imgScissors.visibility = View.GONE
                imgPaper.visibility = View.GONE
                imgRock.visibility = View.VISIBLE

                val randomImage = getRandomImage(images)
                val result = getResult(randomImage, 2, 0, 1)


                when (result) {
                    1 -> {
                        myScore++
                        tvMyScore.text = myScore.toString()

                    }

                    -1 -> {
                        botScore++
                        tvBotScore.text = botScore.toString()

                    }

                    0 -> {

                    }
                }
                finalResult(myScore, botScore)
            }



            btnScissors.setOnClickListener {

                imgScissors.visibility = View.VISIBLE
                imgPaper.visibility = View.GONE
                imgRock.visibility = View.GONE
                val randomImage = getRandomImage(images)
                val result = getResult(randomImage, 0, 1, 2)

                when (result) {
                    1 -> {
                        myScore++
                        tvMyScore.text = myScore.toString()

                    }

                    -1 -> {
                        botScore++
                        tvBotScore.text = botScore.toString()


                    }

                    0 -> {


                    }
                }

                finalResult(myScore, botScore)
            }

            btnTryAgain.setOnClickListener {
                viewVisible()
                binding.finalResult.visibility = View.INVISIBLE
                myScore = 0
                botScore = 0
                binding.tvMyScore.text = "0"
                binding.tvBotScore.text = "0"

            }
        }
    }

    private fun getRandomImage(imageList: ArrayList<ImageView>): Int {
        val random = Random.Default
        val randomNumber = random.nextInt(imageList.size)
        for (i in 0 until imageList.size) {
            if (i == randomNumber) {
                imageList[i].visibility = View.VISIBLE
            } else {
                imageList[i].visibility = View.GONE
            }
        }
        return randomNumber
    }


    private fun getResult(randomImage: Int, draw: Int, win: Int, lose: Int): Int {
        var result = 0
        when (randomImage) {
            draw -> {
                result = 0
            }

            win -> {
                result = 1

            }

            lose -> {
                result = -1

            }
        }
        return result
    }

    fun finalResult(myScore: Int, botScore: Int){

        binding.tvMyFinalScore.text = myScore.toString()
        binding.tvBotFinalScore.text = botScore.toString()

        if(myScore == 3 || botScore == 3){
            viewInvisible()
            binding.finalResult.visibility = View.VISIBLE
        }

        if(myScore == 3 && botScore != 3){

            binding.tvFinalResult.text = "Victory"
            val color = ContextCompat.getColor(applicationContext, R.color.green)
            binding.tvFinalResult.setTextColor(color)
        }else if(myScore != 3 && botScore == 3){
            binding.tvFinalResult.text = "Defeat"
            val color = ContextCompat.getColor(applicationContext, R.color.red)
            binding.tvFinalResult.setTextColor(color)
        }
    }


    fun viewVisible(){
        binding.myContainer.visibility = View.VISIBLE
        binding.botsContainer.visibility = View.VISIBLE
        binding.btnContainer.visibility = View.VISIBLE
        binding.me.visibility = View.VISIBLE
        binding.tvMyScore.visibility = View.VISIBLE
        binding.tvBotScore.visibility = View.VISIBLE
        binding.bot.visibility = View.VISIBLE
    }

    fun viewInvisible(){
        binding.myContainer.visibility = View.INVISIBLE
        binding.botsContainer.visibility = View.INVISIBLE

        binding.btnContainer.visibility = View.INVISIBLE
        binding.me.visibility = View.INVISIBLE
        binding.tvMyScore.visibility = View.INVISIBLE
        binding.tvBotScore.visibility = View.INVISIBLE
        binding.bot.visibility = View.INVISIBLE
    }


}
