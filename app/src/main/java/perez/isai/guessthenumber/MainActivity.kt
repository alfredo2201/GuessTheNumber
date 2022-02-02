package perez.isai.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.NumberFormatException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var maxNumber = 100
    var minNumber: Int = 0
    var num: Int = 0
    var aux: Int?= null
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessing: TextView = findViewById(R.id.guessing)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener{
            num = Random.nextInt(minNumber,maxNumber)
            guessing.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }
        up.setOnClickListener{
            minNumber = num
            if (checkingLimits()) {
                num = Random.nextInt(minNumber, maxNumber)
                guessing.setText(num.toString())
            }else{
                guessing.setText("No way :( You won me")
            }
        }
        down.setOnClickListener {
            maxNumber = num
            if (checkingLimits()) {
                num = Random.nextInt(minNumber, maxNumber)
                guessing.setText(num.toString())
            }else{
                guessing.setText("No way :( You won me")
            }
        }

        guessed.setOnClickListener{
            if(!won) {
                guessing.setText("I guessed, your number is: " + num)
                guessed.setText("Play again")
                won = true
            }else{
                generate.visibility = View.VISIBLE
                guessing.setText("Tap on generate to start")
                guessed.setText("Guessed")
                guessed.visibility = View.GONE
                resetValues()
            }
        }

    }
    fun resetValues(){
        minNumber = 0
        maxNumber = 100
        num = 0
        won = false
    }
    fun checkingLimits():Boolean{
        return minNumber != maxNumber
    }
}