package br.edu.fatecpg.myalejandro.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nome_completo") val nome: String,
    @ColumnInfo(name = "telefone_contato") val telefone: String,
)
