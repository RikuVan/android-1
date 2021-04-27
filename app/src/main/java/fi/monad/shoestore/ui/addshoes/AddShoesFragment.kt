package fi.monad.shoestore.ui.addshoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import fi.monad.shoestore.R
import fi.monad.shoestore.databinding.FragmentAddShoesBinding
import fi.monad.shoestore.databinding.FragmentShoeDetailsBinding
import fi.monad.shoestore.databinding.FragmentWelcomeBinding
import fi.monad.shoestore.ui.shoes.ShoeListViewModel
import fi.monad.shoestore.ui.shoes.ShoeListViewModelFactory


class AddShoesFragment: Fragment() {
    private lateinit var binding: FragmentAddShoesBinding
    private val viewModel: ShoeListViewModel by activityViewModels { ShoeListViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val goToList = { Navigation.findNavController(view).navigate(R.id.action_addShoesFragment_to_shoeListFragment) }
        binding.addShoesViewModel = viewModel
        binding.addShoesSubmitBtn.setOnClickListener {
            viewModel.addShoes()
            goToList()
        }
        binding.addShoesDismissBtn.setOnClickListener {
            viewModel.clearShoes()
            goToList()
        }
    }

}