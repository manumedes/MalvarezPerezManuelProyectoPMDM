package es.aulanosa.ejemplos

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.aulanosa.ejemplos.modelo.bbdd.PeliculasBBDD
import es.aulanosa.ejemplos.modelo.entidades.Director
import es.aulanosa.ejemplos.modelo.entidades.Pelicula
import kotlinx.android.synthetic.main.activity_anhadirpelis.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Long.parseLong
import java.lang.NumberFormatException

class NuevaPeliculaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anhadirpelis)

        val db = PeliculasBBDD.getDatabase(this@NuevaPeliculaActivity)
        val agregarPelicula = findViewById<Button>(R.id.btnAnhadir)

        agregarPelicula.setOnClickListener {
            val anhoString = etAnhoAnhadir.text.toString()
            val titulo = etTituloAnhadir.text.toString()
            val duracionString = etDuracionAnhadir.text.toString()
            val genero = etGeneroAnhadir.text.toString()
            val pais = etPaisAnhadir.text.toString()
            val sinopsis = etSinopsisAnhadir.text.toString()
            val directr = etDirectorAnhadir.text.toString()
            val telefono = etTelefonoAnhadir.text.toString()

            var anho: Long? = null
            var duracion: Long? = null

            try {
                anho = parseLong(anhoString)
            } catch (e:NumberFormatException){
                Toast.makeText(applicationContext, "El año debe ser numerico", Toast.LENGTH_SHORT).show()
            }

            try {
                duracion = parseLong(duracionString)
            } catch (e:NumberFormatException){
                Toast.makeText(applicationContext, "La duracion debe ser numerica", Toast.LENGTH_SHORT).show()
            }

            GlobalScope.launch(Dispatchers.IO) {
                if (anho != null && duracion != null) {
                    val peli = Pelicula( id = 0, nombre = titulo, anho = anho, duracion = duracion, genero = genero, pais = pais, sinopsis = sinopsis, directorId = 1 )
                    val director = Director( id = 0, nombre = directr, telefono = telefono)

                    val anhadirPeli = db?.getPeliculasDao()
                    val anhadirDirec = db?.getDirectoresDao()
                    anhadirPeli?.anhadirPelicula(peli)
                    anhadirDirec?.anhadirDirector(director)
                }

                finish()
          }
        }
    }
}