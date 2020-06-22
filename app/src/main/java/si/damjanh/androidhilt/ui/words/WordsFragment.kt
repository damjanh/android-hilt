package si.damjanh.androidhilt.ui.words

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_words.*
import si.damjanh.androidhilt.R
import si.damjanh.androidhilt.data.model.Word
import si.damjanh.androidhilt.ui.words.adapters.WordListAdapter
import si.damjanh.androidhilt.ui.new_word.NewWordActivity

@AndroidEntryPoint
class WordsFragment : Fragment() {
    private val wordViewModel: WordViewModel by viewModels()

    private val newWOrdActivityRequestCode = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WordListAdapter(requireContext())
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        wordViewModel.allWords.observe(requireActivity(), Observer { words ->
            words?.let { adapter.setWords(it) }
        })

        fab.setOnClickListener {
            startActivityForResult(
                Intent(context, NewWordActivity::class.java),
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
                activity,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
