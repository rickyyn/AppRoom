package br.edu.fatecpg.myalejandro.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.edu.fatecpg.myalejandro.model.Usuario

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    suspend fun buscarTodosUsuarios(): List<Usuario>

    @Insert
    suspend fun inserirUsuario(usuario: Usuario)
}