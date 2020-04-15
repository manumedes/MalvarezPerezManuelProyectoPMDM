package es.aulanosa.ejemplos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val abrirRegistroBtn = findViewById<Button>(R.id.btnIrAlRegistro)
        val btnAcceder = findViewById<Button>(R.id.btnAcceder)
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginContraseña = findViewById<EditText>(R.id.etLoginContrasenha)


        abrirRegistroBtn.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }


        btnAcceder.setOnClickListener {

            val correoLogin = etLoginEmail.text.toString()
            val contrasenhaLogin = etLoginContraseña.text.toString()

            val comprobarDatos = getSharedPreferences("ficheroPreferences", Context.MODE_PRIVATE)
            val emailGuardado = comprobarDatos.getString("email", "")
            val contrasenhaGuardada = comprobarDatos.getString("contrasenha", "")

            if (emailGuardado == correoLogin && contrasenhaGuardada == contrasenhaLogin && correoLogin != "" && contrasenhaLogin != ""){
                startActivity(Intent(this, PeliculasActivity::class.java))
            } else {
                val text = "Error de login"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "Estoy en el onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "Estoy en el onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "Estoy en el onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "Estoy en el onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "Estoy en el onDestroy: ")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("MainActivity", "He pulsado el botón atrás. onBackPressed: ")

    }
}