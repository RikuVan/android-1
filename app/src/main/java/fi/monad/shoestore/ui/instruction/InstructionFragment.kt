package fi.monad.shoestore.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import fi.monad.shoestore.databinding.FragmentInstructionBinding

import fi.monad.shoestore.R


class InstructionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)
        binding.shoeListNavBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.navigation_shoe_list)
        )
        return binding.root
    }
}
