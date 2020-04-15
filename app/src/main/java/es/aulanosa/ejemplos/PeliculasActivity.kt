package es.aulanosa.ejemplos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.aulanosa.ejemplos.modelo.adapters.PeliculasAdapter
import es.aulanosa.ejemplos.modelo.bbdd.PeliculasBBDD
import es.aulanosa.ejemplos.modelo.entidades.Pelicula
import kotlinx.android.synthetic.main.activity_peliculas.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PeliculasActivity : AppCompatActivity(), PeliculasAdapter.AdapterListener {
    private lateinit var peliculasAdapter: PeliculasAdapter
    private var db: PeliculasBBDD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas)

        peliculasAdapter = PeliculasAdapter(arrayListOf())
        peliculasAdapter.listener = this;

        db = PeliculasBBDD.getDatabase(context = this)
        val peliculasDao = db?.getPeliculasDao()

        rvLista.apply {
            rvLista.layoutManager = LinearLayoutManager(this@PeliculasActivity)
            rvLista.adapter = peliculasAdapter
        }

        GlobalScope.launch(Dispatchers.IO) {
            val dbPeliculas = peliculasDao?.obtenerPeliculas()
            if (dbPeliculas != null) {
                peliculasAdapter.actualizarDatos(dbPeliculas)
            }
        }

        val anhadirPelicula = findViewById<FloatingActionButton>(R.id.fabAnhadirPelicula)
        anhadirPelicula.setOnClickListener {
            startActivity(Intent(this, NuevaPeliculaActivity::class.java))
        }
    }

    override fun onItemSelected(pelicula: Pelicula?) {
        val intent = Intent(this, PelisDetalleActivity::class.java)
        if (pelicula != null) {
            intent.putExtra("peliculaId", pelicula.id)
        }
        startActivity(intent)
    }
}

