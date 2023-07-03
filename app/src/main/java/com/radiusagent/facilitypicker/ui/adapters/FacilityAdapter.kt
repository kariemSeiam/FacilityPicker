package com.radiusagent.facilitypicker.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiusagent.facilitypicker.data.local.models.ExclusionOptions
import com.radiusagent.facilitypicker.data.local.models.Facility
import com.radiusagent.facilitypicker.data.local.models.Option
import com.radiusagent.facilitypicker.databinding.ItemFacilityBinding
import com.radiusagent.facilitypicker.ui.facilities.ExclusionList

class FacilityAdapter : RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>() {

    private val facilities: MutableList<Facility> = mutableListOf()
    private var selectedOptions: MutableMap<String, Option> = mutableMapOf()
    private lateinit var excludedFacilityIds: List<List<ExclusionOptions>>
    private lateinit var exclusionList: ExclusionList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFacilityBinding.inflate(inflater, parent, false)
        return FacilityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.bind(facility)
    }

    override fun getItemCount(): Int = facilities.size


    @SuppressLint("NotifyDataSetChanged")
    fun setData(facilities: List<Facility>, exclusionOptions: List<List<ExclusionOptions>>) {
        this.facilities.clear()
        this.facilities.addAll(facilities)
        this.excludedFacilityIds = exclusionOptions
        notifyDataSetChanged()
    }


    inner class FacilityViewHolder(private val binding: ItemFacilityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(facility: Facility) {
            binding.facilityName = facility.name
            setupOptionsChips(facility)
        }

        private fun setupOptionsChips(facility: Facility) {
            binding.chipGroup.removeAllViews()
            for (option in facility.options) {
                val chipAdapter = ChipAdapter(
                    facility = facility,
                    options = listOf(option),
                    onChipSelected = { selectedOption ->
                        selectedOptions[option.id] = selectedOption
                        notifyOptionsChanged(facility.facilityId)
                    }, context = binding.root.context
                    , exclusionOptions = excludedFacilityIds
                )

                val chipViewHolder = chipAdapter.onCreateViewHolder(binding.chipGroup, 0)
                val chip = chipViewHolder.createChip(option)
                binding.chipGroup.addView(chip)

            }
        }


        private fun notifyOptionsChanged(updatedFacilityId: String) {
            facilities.forEach { facility ->
                if (facility.facilityId != updatedFacilityId) {
                    val position = facilities.indexOf(facility)
                    notifyItemChanged(position)
                }
            }
        }

    }
}
