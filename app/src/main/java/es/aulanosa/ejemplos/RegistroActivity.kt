package es.aulanosa.ejemplos

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)
        val etUsuarioRegistro = findViewById<EditText>(R.id.etUsuarioRegistro)
        val etEmailRegistro = findViewById<EditText>(R.id.etEmailRegistro)
        val etContrasenhaRegistro = findViewById<EditText>(R.id.etContrasenhaRegistro)
        val etRContrasenhaRegisto = findViewById<EditText>(R.id.etRContrasenhaRegistro)
        val preferencias = getSharedPreferences("ficheroPreferences", Context.MODE_PRIVATE)
        val aceptarTerminos = findViewById<CheckBox>(R.id.cbTerminos)


        btnRegistrarse.setOnClickListener {
            val correo = etEmailRegistro.text.toString().trim()
            val correoValido = Patterns.EMAIL_ADDRESS.matcher(correo).matches()

            val contrasenha = etContrasenhaRegistro.text.toString()
            val repetirContrasena = etRContrasenhaRegisto.text.toString()

            if (!correoValido) {
                Toast.makeText(applicationContext, "Email inválido", Toast.LENGTH_SHORT).show()
            } else if (!aceptarTerminos.isChecked) {
                Toast.makeText(applicationContext, "Debe aceptar los términos", Toast.LENGTH_SHORT).show()
            } else if (contrasenha != repetirContrasena) {
                Toast.makeText(applicationContext, "Contrasenas no coinciden", Toast.LENGTH_SHORT).show()
            }else if(contrasenha.length<6 ){
                Toast.makeText(applicationContext, "La contraseña es demasiado pequeña", Toast.LENGTH_SHORT).show()

            } else if(contrasenha.length>16 ){
                Toast.makeText(applicationContext, "La contraseña es demasiado grande", Toast.LENGTH_SHORT).show()

            }else {
                val usuario = etUsuarioRegistro.text.toString()
                val editor = preferencias.edit()

                editor.putString("email", correo)
                editor.putString("contrasenha", contrasenha)
                editor.putString("usuario", usuario)
                editor.clear()
                editor.apply()
                finish()
            }
        }
    }
}