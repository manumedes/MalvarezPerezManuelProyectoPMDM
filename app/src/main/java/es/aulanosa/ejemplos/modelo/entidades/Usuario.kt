package es.aulanosa.ejemplos.modelo.entidades

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
class Usuario(
        @PrimaryKey(autoGenerate = true) var id : Long,
        var email: String,
        var contrasenha : String ) {
} 