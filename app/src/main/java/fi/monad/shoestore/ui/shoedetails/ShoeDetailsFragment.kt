package fi.monad.shoestore.ui.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import fi.monad.shoestore.R
import fi.monad.shoestore.databinding.FragmentShoeDetailsBinding
import fi.monad.shoestore.ui.shoes.ShoeListViewModel
import fi.monad.shoestore.ui.shoes.ShoeListViewModelFactory

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private val viewModel: ShoeListViewModel by activityViewModels { ShoeListViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        binding.button.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.navigation_shoe_list)
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // viewModel used in Activity scope since it is shared with ShoeDetails
        binding.shoeListViewModel = viewModel
    }
}