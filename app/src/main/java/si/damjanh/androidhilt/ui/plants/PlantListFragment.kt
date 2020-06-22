package si.damjanh.androidhilt.ui.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_plant_list.*
import si.damjanh.androidhilt.R
import si.damjanh.androidhilt.ui.plants.adapters.PlantAdapter

@AndroidEntryPoint
class PlantListFragment : Fragment() {
    private val plantsViewModel: PlantsListViewModel by viewModels()

    private lateinit var adapter: PlantAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PlantAdapter(requireContext())
        plant_list.adapter = adapter

        plantsViewModel.loading.observe(requireActivity(), Observer { show ->
            spinner.visibility = if (show) View.VISIBLE else View.GONE
        })

        plantsViewModel.snackbar.observe(requireActivity(), Observer { text ->
            text?.let {
                Snackbar.make(plants_root, text, Snackbar.LENGTH_SHORT).show()
                plantsViewModel.onSnackbarShown()
            }
        })

        plantsViewModel.plants.observe(requireActivity(), Observer { plants ->
            adapter.submitList(plants)
        })
    }
}