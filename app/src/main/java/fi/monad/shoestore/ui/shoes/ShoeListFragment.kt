package fi.monad.shoestore.ui.shoes

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import fi.monad.shoestore.R
import fi.monad.shoestore.databinding.FragmentShoeListBinding
import fi.monad.shoestore.databinding.RowShoeItemBinding


class ShoeListFragment : Fragment() {
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shoeListViewModel = viewModel

        viewModel.shoes.observe(viewLifecycleOwner, {
            it?.let { list ->
                list.forEach { shoe ->
                    val rowBinding = RowShoeItemBinding.inflate(layoutInflater)
                    rowBinding.shoeVM = shoe
                    binding.shoeListParent.addView(rowBinding.root)
                    rowBinding.cardView.setOnClickListener(View.OnClickListener {
                        viewModel.setCurrent(shoe)
                        findNavController(view).navigate(R.id.navigation_shoe_details)
                    })
                }
            }
        })

        binding.addShoesBtn.setOnClickListener {
            findNavController(view).navigate(R.id.action_shoeListFragment_to_addShoesFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}