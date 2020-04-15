package es.aulanosa.ejemplos.modelo.dao

import androidx.room.*
import es.aulanosa.ejemplos.modelo.entidades.Director


@Dao
interface DirectoresDao {
    @Insert
    fun anhadirDirector(director: Director)

    @Query("select * from directores where id = :idDirector" )
    fun obtenerDirector(idDirector: Long) :Director?

    @Delete
    fun eliminarDirector(director: Director)

    @Update
    fun modificarDirector(director: Director)
}

