package com.doditugas3pam.app.ui.skill

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doditugas3pam.app.databinding.FragmentSkillBinding
import com.doditugas3pam.app.R
import java.util.Locale

class SkillFragment : Fragment() {

    private var _binding: FragmentSkillBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var data = ArrayList<itemsViewModel>()
    val adapter = CustomAdapter(data)
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val skillViewModel =
            ViewModelProvider(this).get(SkillViewModel::class.java)

        _binding = FragmentSkillBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textSkill

        val dataSkill = resources.getStringArray(R.array.daftar_skill)
        val pengalamanSkill = resources.getIntArray(R.array.daftar_pengalaman)

        val recyclerview : RecyclerView = root.findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        for(i in 0 until dataSkill.size){
            val data_skill = dataSkill.get(i)
            val data_pengalaman = pengalamanSkill.get(i).toString()
            data.add(itemsViewModel("$data_skill", "$data_pengalaman Tahun"))
        }
        recyclerview.adapter = adapter
        val searchViewID : SearchView = root.findViewById(R.id.searchViewID)
        searchViewID.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(msg)
                return false
            }
        })
        skillViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun filter(text: String){
        val filteredlist : ArrayList<itemsViewModel> = ArrayList()
        for(item in data){
            if(item.nama.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))){
                filteredlist.add(item)
            }
            if(filteredlist.isEmpty()){
                Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "Data found", Toast.LENGTH_SHORT).show()
                activity?.runOnUiThread{}
                adapter.filterList(filteredlist)
            }
        }
    }

}