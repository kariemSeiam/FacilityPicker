package com.radiusagent.facilitypicker.ui.facilities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiusagent.facilitypicker.R
import com.radiusagent.facilitypicker.data.local.models.ExclusionOptions
import com.radiusagent.facilitypicker.data.local.models.Facility
import com.radiusagent.facilitypicker.data.local.realm.FacilityRealmManager
import com.radiusagent.facilitypicker.data.remote.RetrofitClient
import com.radiusagent.facilitypicker.databinding.FragmentFacilityBinding
import com.radiusagent.facilitypicker.ui.adapters.FacilityAdapter
import com.radiusagent.facilitypicker.ui.adapters.SavedFacilitiesAdapter
import io.realm.Realm


class FacilityFragment : Fragment(), FacilityContract.View {

    private lateinit var binding: FragmentFacilityBinding
    private lateinit var presenter: FacilityContract.Presenter
    private lateinit var realm: Realm
    private lateinit var facilityAdapter: FacilityAdapter
    private lateinit var facilitiesAdapter: SavedFacilitiesAdapter
    private lateinit var exclusionList: ExclusionList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFacilityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupPresenter()
        presenter.getData()
    }

    private fun setupRecyclerView() {
        facilityAdapter = FacilityAdapter()
        facilitiesAdapter = SavedFacilitiesAdapter()
        binding.recyclerFacilities.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = facilityAdapter
        }
        binding.recyclerSavedFacilities.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = facilitiesAdapter
        }
    }

    private fun setupPresenter() {
        FacilityRealmManager.initRealm()
        realm = FacilityRealmManager.getRealmInstance()
        val apiService = RetrofitClient.create()
        presenter = FacilityPresenter(this, apiService, realm)
    }


    override fun showFacilities(
        facilities: List<Facility>, exclusionOptions: List<List<ExclusionOptions>>
    ) {
        facilityAdapter.setData(facilities, exclusionOptions)
        facilitiesAdapter.setData(facilities, exclusionOptions)
        binding.title = getString(R.string.facility_title)

        binding.btnAddFacility.setOnClickListener {
            /*if(exclusionList.isSelectedOptionValid()=="Saved"){
                // WE Have to save the data of this selection on the facilities on realm
            }*/
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()

        }

    }

    override fun showError(message: String) {
        Log.e("error", message + "")
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}
