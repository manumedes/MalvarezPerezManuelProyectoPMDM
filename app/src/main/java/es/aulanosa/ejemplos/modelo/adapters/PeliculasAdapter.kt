package es.aulanosa.ejemplos.modelo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.aulanosa.ejemplos.R
import es.aulanosa.ejemplos.modelo.entidades.Pelicula
import kotlinx.android.synthetic.main.itemlista.view.*


class PeliculasAdapter(private val list: MutableList<Pelicula>) : RecyclerView.Adapter<PeliculasAdapter.ViewHolder>() {
    interface AdapterListener {
        fun onItemSelected(position: Pelicula?)
    }

    var listener: AdapterListener? = null

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlista, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTituloPeliculaCarta?.text = list[position].nombre
        holder.tvGeneroNombre?.text = list[position].genero
        holder.tvDirectorNombre?.text = list[position].directorId.toString()
        holder.bind(list[position])
    }

     fun actualizarDatos(data:Array<Pelicula>) {
         list.clear()
         list.addAll(data)
         notifyDataSetChanged()
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private var pelicula: Pelicula? = null
        val tvTituloPeliculaCarta = view.tvTituloPeliculaCarta
        val tvGeneroNombre = view.tvGeneroNombre
        val tvDirectorNombre = view.tvDirectorNombre

        init {
            view.setOnClickListener {
                listener?.onItemSelected(pelicula)
            }
        }

        fun bind(pelicula: Pelicula) {
            this.pelicula = pelicula
        }
    }
}


