package es.aulanosa.ejemplos

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import es.aulanosa.ejemplos.modelo.bbdd.PeliculasBBDD
import es.aulanosa.ejemplos.modelo.entidades.Director
import es.aulanosa.ejemplos.modelo.entidades.Pelicula
import kotlinx.android.synthetic.main.activity_editarpelis.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PelisEditarActivity : AppCompatActivity() {
    var peli: Pelicula? = null
    var director: Director? = null
    var db: PeliculasBBDD? = null

    override fun onCreate(savedIstanceState: Bundle?) {
        super.onCreate(savedIstanceState)
        setContentView(R.layout.activity_editarpelis)

        val id: Long = intent.extras!!.getLong("peliculaId")
        db = PeliculasBBDD.getDatabase(this@PelisEditarActivity)

        GlobalScope.launch(Dispatchers.IO) {
            peli = db?.getPeliculasDao()?.obtenerPelicula(id)
            director = db?.getDirectoresDao()?.obtenerDirector(id)

            runOnUiThread {
                etTituloEditar.hint = peli?.nombre
                etAnhoEditar.hint = peli?.anho.toString()
                etDuracionEditar.hint = peli?.duracion.toString()
                etGeneroEditar.hint = peli?.genero
                etPaisEditar.hint = peli?.pais
                etSinopsisEditar.hint = peli?.sinopsis
                etDirectorEditar.hint = director?.nombre
                etTelefonoEditar.hint = director?.telefono
            }

        }


        btnEditarListo.setOnClickListener {
                val titulo = etTituloEditar.text.toString()
                val anho = etAnhoEditar.text.toString()
                val duracion = etDuracionEditar.text.toString()
                val genero = etGeneroEditar.text.toString()
                val pais = etPaisEditar.text.toString()
                val sinopsis = etSinopsisEditar.text.toString()
                val directr = etDirectorEditar.text.toString()
                val telefono = etTelefonoEditar.text.toString()

                val peli = Pelicula(id = id, nombre = titulo, anho = anho.toLong(), duracion = duracion.toLong(), genero = genero, pais = pais, sinopsis = sinopsis, directorId = id)
                var director = Director(id = id, nombre = directr, telefono = telefono)

                val modificarPeli = PeliculasBBDD.getDatabase(this)?.getPeliculasDao()
                val modificarDirec = PeliculasBBDD.getDatabase(this)?.getDirectoresDao()
            GlobalScope.launch(Dispatchers.IO) {
                modificarPeli?.modificarPelicula(peli)
                modificarDirec?.modificarDirector(director)
            }
        }

        btnEliminar.setOnClickListener {
            val modificarPeli = db?.getPeliculasDao()
            val modificarDirec = db?.getDirectoresDao()
            GlobalScope.launch(Dispatchers.IO){
                if (peli != null) {
                    modificarPeli?.eliminarPelicula(peli!!)
                }

                if (director != null) {
                    modificarDirec?.eliminarDirector(director!!)
                }

                finish()
            }
        }


    }


}