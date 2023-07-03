package com.radiusagent.facilitypicker.ui.adapters


import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.radiusagent.facilitypicker.data.local.models.ExclusionOptions
import com.radiusagent.facilitypicker.data.local.models.Facility
import com.radiusagent.facilitypicker.data.local.models.Option
import com.radiusagent.facilitypicker.ui.facilities.ExclusionList

class ChipAdapter(
    private val facility: Facility,
    private val options: List<Option>,
    private val exclusionOptions: List<List<ExclusionOptions>>,
    private val onChipSelected: (Option) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<ChipAdapter.ChipViewHolder>() {

    private lateinit var exclusionList: ExclusionList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val chipGroup = ChipGroup(parent.context)
        return ChipViewHolder(chipGroup)
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        val option = options[position]
        holder.bind(option)
    }

    override fun getItemCount(): Int = options.size

    inner class ChipViewHolder(private val chipGroup: ChipGroup) :
        RecyclerView.ViewHolder(chipGroup) {

        fun bind(option: Option) {
            val chip = createChip(option)
            chipGroup.addView(chip)
            chip.setOnClickListener {
                onChipSelected.invoke(option)
            }
        }

        fun createChip(option: Option): Chip {
            val chip = Chip(context).apply {
                text = option.name
                isClickable = true
                isCheckable = true
            }

            val iconResourceId = getIconResourceId(option.icon)
            if (iconResourceId != 0) {
                val chipIcon = ContextCompat.getDrawable(context, iconResourceId)
                chip.chipIcon = chipIcon
            }

            chip.setOnClickListener {
                if (!isExclusionSelected(option)) {
                    saveSelectedOption(facility, option)
                } else {
                    Toast.makeText(context, "Exclusion selected!", Toast.LENGTH_SHORT).show()
                }
            }
            return chip
        }

        private fun isExclusionSelected(option: Option): Boolean {
            return exclusionOptions.any { exclusion ->
                //not complete
                exclusion[0].facility_id == facility.facilityId && exclusion[0].options_id == option.id
            }
        }

        private fun saveSelectedOption(facility: Facility, option: Option) {
            exclusionList.addSelectedOption(facility.facilityId,option)
        }

        private fun getIconResourceId(icon: String): Int {
            var resourceName = icon
            if (icon == "no-room") {
                resourceName = "noroom"
            }
            return context.resources.getIdentifier(
                resourceName, "drawable", context.packageName
            )
        }
    }
}
