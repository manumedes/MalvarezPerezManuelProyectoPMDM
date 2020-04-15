package es.aulanosa.ejemplos.modelo.bbdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.aulanosa.ejemplos.modelo.dao.DirectoresDao
import es.aulanosa.ejemplos.modelo.dao.PeliculasDao
import es.aulanosa.ejemplos.modelo.dao.UsuariosDao
import es.aulanosa.ejemplos.modelo.entidades.Director
import es.aulanosa.ejemplos.modelo.entidades.Pelicula
import es.aulanosa.ejemplos.modelo.entidades.Usuario

@Database(entities = [Usuario ::class, Pelicula::class, Director::class], version = 1 )
abstract class PeliculasBBDD : RoomDatabase() {

    abstract fun getUsuariosDao(): UsuariosDao
    abstract fun getPeliculasDao(): PeliculasDao
    abstract fun getDirectoresDao(): DirectoresDao

    companion object {

        private var database: PeliculasBBDD? = null

        fun getDatabase(context: Context): PeliculasBBDD? {

            database ?: synchronized(this) {
                database = Room.databaseBuilder(
                        context,
                        PeliculasBBDD::class.java,
                        "appPeliculas_BBDD"
                ).build()
            }

            return database
        }
    }

}