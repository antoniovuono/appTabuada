package com.example.tabuada

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var num1: Int = 0
    var num2:Int = 0
    var placar = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sortear()

        sbValor.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i("TABUADA", "Valor: ${progress}")
                txtResposta.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }

    fun sortear() {
        num1 = Random.nextInt(1, 9)
        num2 = Random.nextInt(1, 9)

        txtNum1.text = num1.toString()
        txtNum2.text = num2.toString()
    }

    fun sortearProximo(View: View) {

        var avaliaResposta = 0
        //Valor atual selecioando pelo usuário seekbar
        if(sbValor.progress == num1 * num2) {
            avaliaResposta = R.string.lblCorreto
            placar++
            if (placar > 5) {
                placar = 0.0f
            }
            rbPlacar.rating = placar
        } else {
            avaliaResposta = R.string.lblErrado

        }

        //Formata a resposta
        val msg = getString(R.string.lblResultado, getString(avaliaResposta))

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage(msg)
        builder.setIcon(android.R.drawable.btn_star)
        builder.setPositiveButton(android.R.string.ok, object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog!!.cancel()
                sortear()
            }

        })

        // de fato existe o alerta
        builder.show()



        sortear()
    }
}