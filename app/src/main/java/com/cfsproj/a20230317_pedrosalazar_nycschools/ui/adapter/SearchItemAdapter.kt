package com.cfsproj.a20230317_pedrosalazar_nycschools.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.cfsproj.a20230317_pedrosalazar_nycschools.databinding.LayoutSearchItemBinding
import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.SchoolModel
import com.cfsproj.a20230317_pedrosalazar_nycschools.viewmodel.SchoolViewModel
import com.cfsproj.a20230317_pedrosalazar_nycschools.ui.ScoreFragment

private const val TAG = "SearchItemAdapter"

class SearchItemAdapter(
    val activity: AppCompatActivity,
    val viewModel: SchoolViewModel,
    var schools: List<SchoolModel>
) :
    RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val binding =
            LayoutSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return schools.size
    }

    fun changeData(newData: List<SchoolModel>) {
        schools = newData
        notifyDataSetChanged()
    }

    inner class SearchItemViewHolder(val binding: LayoutSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            Log.d(TAG, "bind: ${schools[position]}")
            val school = schools[position]
            binding.tvSchoolName.text = school.schoolName
            binding.tvSchoolAddress.text = school.addressString
            binding.tvNumberStudents.text = school.totalStudents
            binding.clTop.setOnClickListener {
                //will launch a dialog fragment by our context
                viewModel.select(schools[position])
                ScoreFragment.newInstance()
                    .show(activity.supportFragmentManager, "SchoolDetailDialogFragment")


            }
        }
    }
}