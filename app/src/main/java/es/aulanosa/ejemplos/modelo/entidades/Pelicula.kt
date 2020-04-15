package es.aulanosa.ejemplos.modelo.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas")
class Pelicula(
        @PrimaryKey(autoGenerate = true) var id: Long,
        var nombre: String,
        var anho: Long,
        var duracion: Long,
        var genero: String,
        var pais: String,
        var sinopsis: String,
        var directorId: Long) {

    override fun toString(): String {
        return "Id: $id Nombre: $nombre, anho $anho \n"
    }

}