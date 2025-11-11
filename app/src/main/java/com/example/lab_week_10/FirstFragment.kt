package com.example.lab_week_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel

class FirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViewModel()
    }

    private fun updateText(total: Int) {
        // 'view' adalah properti dari Fragment, jadi Anda bisa mengaksesnya di sini
        view?.findViewById<TextView>(R.id.text_total)?.text =
            getString(R.string.text_total, total)
    }

    // 4. PINDAHKAN FUNGSI INI KE DALAM CLASS
    private fun prepareViewModel() {
        val viewModel =
            ViewModelProvider(requireActivity()).get(TotalViewModel::class.java)

        // Observe the LiveData object
        viewModel.total.observe(viewLifecycleOwner) { total ->
            // Whenever the value changes, update the text
            updateText(total)
        }
    }

    // 5. PINDAHKAN COMPANION OBJECT KE DALAM CLASS
    companion object {
        /**
         * Gunakan factory method ini untuk membuat instance baru dari
         * fragment ini menggunakan parameter yang disediakan.
         */
        // TODO: Ganti parameter jika Anda perlukan
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment()
    }
}