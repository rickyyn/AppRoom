package br.edu.fatecpg.myalejandro.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.fatecpg.myalejandro.dao.UsuarioDao
import br.edu.fatecpg.myalejandro.model.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
        abstract fun usuarioDao(): UsuarioDao
    }
