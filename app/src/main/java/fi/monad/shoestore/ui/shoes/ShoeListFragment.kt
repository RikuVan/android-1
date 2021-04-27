package fi.monad.shoestore.ui.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fi.monad.shoestore.R
import fi.monad.shoestore.databinding.FragmentShoeListBinding

const val SHOE_ID = "show_id"

class ShoeListFragment: Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by activityViewModels { ShoeListViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // viewModel used in Activity scope since it is shared with ShoeDetails
        binding.shoeListViewModel = viewModel

        println(viewModel.newShoes.value?.toString())

        val shoesAdapter = ShoeListAdapter { shoe ->
            viewModel.setCurrent(shoe)
            findNavController(view).navigate(R.id.navigation_shoe_details)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.shoe_list_recycler_view)
        recyclerView.adapter = shoesAdapter

        viewModel.shoes.observe(viewLifecycleOwner, {
            it?.let {
                shoesAdapter.submitList(it)
            }
        })

        binding.addShoesBtn.setOnClickListener {
            findNavController(view).navigate(R.id.action_shoeListFragment_to_addShoesFragment)
        }

    }
}