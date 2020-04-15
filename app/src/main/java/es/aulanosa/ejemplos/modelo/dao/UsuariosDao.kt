package es.aulanosa.ejemplos.modelo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.aulanosa.ejemplos.modelo.entidades.Usuario
@Dao

interface UsuariosDao {

    @Insert
    fun guardarUsuario (usuario : Usuario)

    @Query("select * from usuarios where id = :idUsuario")
    fun obtenerUsuario (idUsuario : Long): Usuario?
}