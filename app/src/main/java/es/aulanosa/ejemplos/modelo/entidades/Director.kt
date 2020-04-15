package es.aulanosa.ejemplos.modelo.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "directores")
class Director(
        @PrimaryKey(autoGenerate = true) var id : Long,
        var nombre : String,
        var telefono : String)
