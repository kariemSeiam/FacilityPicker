package com.radiusagent.facilitypicker.ui.facilities

import com.radiusagent.facilitypicker.data.local.models.ExclusionOptions
import com.radiusagent.facilitypicker.data.local.models.Option


// i need on this an fun that gives the 3th Options Selected And I need It to be checked if there is tow of them on ant one of exclusions
class ExclusionList {
    private var excludedOptions: MutableList<List<ExclusionOptions>> = mutableListOf()
    private var selectedOptions: MutableMap<String, String> = mutableMapOf()
    private var optionName: MutableMap<String, String> = mutableMapOf()
    private var excludedList: MutableList<ExclusionOptions> = mutableListOf()

    fun addExclusions(exclusions: List<List<ExclusionOptions>>) {
        // Its just 2 facilities with two options so i need to save them in one
        // to check what on the one is valid or not then go to 2 until we finish the number of facilities


        // If I have 2 dimnshal list have
        // 0 -> 0,1 every one of them have facilityId and OptionId i need
        // 1 -> 0,1 every one of them have facilityId and OptionId i need
        // 2 -> 0,1 every one of them have facilityId and OptionId i need
    }

    fun addSelectedOption(facilityId: String, option: Option) {
        selectedOptions[facilityId] = option.id
        optionName[option.id] = option.name
    }

    fun isSelectedOptionValid(): String {

        excludedOptions.forEach { exclusionList ->
            var exclusionFound = 0
            var excludedOptions: MutableMap<String, String> = mutableMapOf()
            exclusionList.forEach { exclusion ->
                val selectedOptionId = selectedOptions[exclusion.facility_id]

                if (selectedOptionId != null && selectedOptionId == exclusion.options_id) {
                    excludedList[exclusionFound] = (exclusion)

                    // i need here to get the name of the option
                    /*
                                        excludedOptions["$exclusionFound"] = optionName[exclusion.options_id]
                    */

                    exclusionFound++
                }
            }

            if (exclusionFound >= 2) {
                return "You Can't Select ${excludedOptions["0"]} with ${excludedOptions["1"]}."
            }
        }
        return "Saved"
    }
}
