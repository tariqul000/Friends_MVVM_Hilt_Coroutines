package com.tariqul.friends.business.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.tariqul.friends.R
import com.tariqul.friends.business.view.adopter.UsersListAdapter
import com.tariqul.friends.business.viewmodel.MainActivityViewModel
import com.tariqul.friends.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//
//@AndroidEntryPoint
//class UserListFragment : Fragment() {
//    private lateinit var viewModel: MainActivityViewModel
//
//    @Inject
//    lateinit var adapter: UsersListAdapter
//
//    private var _binding: FragmentUserListBinding? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_user_list, container, false
//        )
////        binding.viewModel = viewModel
////        binding.lifecycleOwner = viewLifecycleOwner
////        binding.recyclerView.layoutManager = GridLayoutManager(context,2)
////        binding.recyclerView.adapter = adapter
//
//        return binding.root
//    }
//
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////        viewModel.data.observe(viewLifecycleOwner, {
////            adapter.submitList(it)
////        })
//////        // pass argument to the next fragment
//////        adapter.clickListener.onItemClick = {
//////            findNavController().navigate(UserListFragmentDirections.actionUsersListToUserDetails(it.id))
//////        }
////    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//       // binding.recyclerView.adapter = null
//        _binding = null
//    }
//
//}