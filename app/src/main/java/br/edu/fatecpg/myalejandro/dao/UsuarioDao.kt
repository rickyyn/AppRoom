package br.edu.fatecpg.myalejandro.dao

import androidx.room.Insert
import androidx.room.Query
import br.edu.fatecpg.myalejandro.model.Usuario

interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    fun buscarTodosUsuarios():List<Usuario>
    @Insert
    suspend fun inserirUsuario(vararg usuario: Usuario)
}