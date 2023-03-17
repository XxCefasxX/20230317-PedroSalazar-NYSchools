package com.cfsproj.a20230317_pedrosalazar_nycschools.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.cfsproj.a20230317_pedrosalazar_nycschools.databinding.FragmentScoreBinding
import com.cfsproj.a20230317_pedrosalazar_nycschools.domain.HighSchoolResponse
import com.cfsproj.a20230317_pedrosalazar_nycschools.viewmodel.SchoolViewModel

class ScoreFragment : DialogFragment() {

    private lateinit var binding: FragmentScoreBinding
    private lateinit var viewModel: SchoolViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NORMAL,
            android.R.style.Theme_Material_Light_NoActionBar_Fullscreen
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SchoolViewModel::class.java)
        val currentSelection = viewModel.lCurrentSelection.value
        binding.schoolName.text = currentSelection.schoolName
        binding.schoolAddress.text = currentSelection.addressString
        binding.phone.text = currentSelection.phone
        binding.website.text = currentSelection.website
        binding.email.text = currentSelection.email

        binding.mathScore.text = currentSelection.satScores?.satMathAvgScore ?: "N/A"
        binding.criticalReadingScore.text =
            currentSelection.satScores?.satCriticalReadingAvgScore ?: "N/A"
        binding.writingScore.text = currentSelection.satScores?.satWritingAvgScore ?: "N/A"

        binding.numTaskTaker.text = currentSelection.satScores?.numOfSatTestTakers ?: "N/A"
        binding.tvOverviewParagraph.text = currentSelection.overviewParagraph
        binding.tvOpportinities.text = currentSelection.opportunities

        //set map location
        binding.schoolAddress.setOnClickListener {
            //will open map
            val lat = currentSelection.school?.latitude ?: "0.0"
            val lng = currentSelection.school?.longitude ?: "0.0"
            val label = currentSelection.schoolName
            val uri = "geo:$lat,$lng?q=$lat,$lng($label)"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }

        binding.lyEmailInfo.setOnClickListener {
            val email = currentSelection.emailUrl
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = email
            startActivity(intent)
        }
        binding.lyPhoneInfo.setOnClickListener {
            val phone = currentSelection.phoneURL
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = phone
            startActivity(intent)
        }
        binding.lyWebInfo.setOnClickListener {
            val website = currentSelection.webSiteUrl
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = website
            startActivity(intent)
        }

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            ScoreFragment()
    }
}