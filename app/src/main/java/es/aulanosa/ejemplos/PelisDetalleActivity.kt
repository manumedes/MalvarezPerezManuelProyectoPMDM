package es.aulanosa.ejemplos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import es.aulanosa.ejemplos.modelo.bbdd.PeliculasBBDD
import es.aulanosa.ejemplos.modelo.entidades.Pelicula
import kotlinx.android.synthetic.main.activity_pelisdetalle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PelisDetalleActivity : AppCompatActivity() {

    var peli: Pelicula? = null

    override fun onCreate(savedIstanceState: Bundle?){
        super.onCreate(savedIstanceState)
        setContentView(R.layout.activity_pelisdetalle)

        val valor: Long = intent.extras!!.getLong("peliculaId")

        GlobalScope.launch(Dispatchers.IO) {
            peli = PeliculasBBDD.getDatabase(this@PelisDetalleActivity)?.getPeliculasDao()?.obtenerPelicula(valor)
            val directr = PeliculasBBDD.getDatabase(this@PelisDetalleActivity)?.getDirectoresDao()?.obtenerDirector(valor)


            runOnUiThread {
                tvTituloDetalle.text = peli?.nombre
                tvDescripcionDetalle.text = peli?.sinopsis
                tvAnhoDetalle.text = peli?.anho.toString()
                tvDuracionDetalle.text = peli?.duracion.toString()
                tvPaisDetalle.text = peli?.pais
                tvDirectorDetalle.text = directr?.nombre
                btnTelefonoDetalle.text = directr?.telefono

            }
        }

        btnEditarPelicula.setOnClickListener {
            val intent = Intent(this, PelisEditarActivity::class.java)
            if (peli != null) {
                intent.putExtra("peliculaId", peli!!.id)
            }
            startActivity(intent)
        }
    }
}