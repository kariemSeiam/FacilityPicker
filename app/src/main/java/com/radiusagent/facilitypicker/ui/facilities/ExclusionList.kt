package com.radiusagent.facilitypicker.ui.facilities

import com.radiusagent.facilitypicker.data.local.models.ExclusionOptions

class ExclusionList {
    private var exclusions: MutableList<List<ExclusionOptions>> = mutableListOf()
    private var selectedOptions: MutableMap<String, String> = mutableMapOf()

    fun addExclusions(exclusions: List<List<ExclusionOptions>>) {
        this.exclusions.addAll(exclusions)
    }

    fun addSelectedOption(facilityId: String, optionId: String) {
        selectedOptions[facilityId] = optionId
    }

    fun isSelectedOptionValid(): Boolean {
        return exclusions.none { exclusionList ->
            val selectedOptionsInExclusion = exclusionList.map { exclusion ->
                selectedOptions[exclusion.facility_id]
            }
            selectedOptionsInExclusion.size > 1 && selectedOptionsInExclusion.distinct().size < selectedOptionsInExclusion.size
        }
    }
}
