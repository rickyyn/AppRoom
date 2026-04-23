package br.edu.fatecpg.myalejandro.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import br.edu.fatecpg.myalejandro.R
import br.edu.fatecpg.myalejandro.dao.UsuarioDao
import br.edu.fatecpg.myalejandro.databinding.ActivityMainBinding
import br.edu.fatecpg.myalejandro.db.AppDataBase
import br.edu.fatecpg.myalejandro.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioDao: UsuarioDao
    private lateinit var db: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "db_contatos"
        ).fallbackToDestructiveMigration().build()
        usuarioDao = db.usuarioDao()
        binding.btnCadastrar.setOnClickListener {
            val nome = binding.edtNome.text.toString()
            val telefone = binding.edtTelefone.text.toString()
            val usuario = Usuario(0, nome, telefone)
            lifecycleScope.launch {
                usuarioDao.inserirUsuario(usuario)
            }
            binding.edtNome.text.clear()
            binding.edtTelefone.text.clear()
            Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show()

        }
        binding.txvCadastrados.setOnClickListener {
            lifecycleScope.launch {
                val list = withContext(Dispatchers.IO) {
                    usuarioDao.buscarTodosUsuarios()
                }
                list.forEach {
                    Log.i("CONTATO", "${it.nome} - ${it.telefone}")
                }
            }
            Toast.makeText(this, "Verifique o Logcat", Toast.LENGTH_SHORT).show()
        }
    }
}
