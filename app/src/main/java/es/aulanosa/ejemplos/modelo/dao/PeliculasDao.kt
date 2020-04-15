package es.aulanosa.ejemplos.modelo.dao

import androidx.room.*
import es.aulanosa.ejemplos.modelo.entidades.Pelicula
@Dao
interface PeliculasDao {

    @Insert
    fun anhadirPelicula(pelicula: Pelicula)

    @Query("select * from peliculas where id = :idPelicula")
    fun obtenerPelicula(idPelicula : Long): Pelicula?

    @Query("select * from peliculas")
    fun obtenerPeliculas(): Array<Pelicula>

    @Delete
    fun eliminarPelicula(pelicula: Pelicula)

    @Update
    fun modificarPelicula(pelicula: Pelicula)
}