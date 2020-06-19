package si.damjanh.androidhilt.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import si.damjanh.androidhilt.R
import si.damjanh.androidhilt.data.model.Word
import si.damjanh.androidhilt.ui.main.adapters.WordListAdapter
import si.damjanh.androidhilt.ui.WordViewModel
import si.damjanh.androidhilt.ui.new_word.NewWordActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val wordViewModel: WordViewModel by viewModels()

    private val newWOrdActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = WordListAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.setWords(it) }
        })

        fab.setOnClickListener {
            startActivityForResult(
                Intent(this@MainActivity, NewWordActivity::class.java),
                newWOrdActivityRequestCode
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWOrdActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                wordViewModel.insert(Word(it))
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
