package grsoft.com.br.nybooks.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import grsoft.com.br.nybooks.databinding.FragmentBookDetailsBinding
import java.util.*

class BookDetailsFragment : Fragment() {

    private var _binding: FragmentBookDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = BookDetailsFragmentArgs.fromBundle(it)
            binding.textViewTitle.text = safeArgs.title
            binding.textViewDescription.text = safeArgs.description
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}