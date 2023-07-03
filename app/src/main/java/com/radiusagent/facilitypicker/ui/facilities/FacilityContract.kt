    package com.radiusagent.facilitypicker.ui.facilities

    import com.radiusagent.facilitypicker.data.local.models.ExclusionOptions
    import com.radiusagent.facilitypicker.data.local.models.Facility


    interface FacilityContract {
        interface View {
            fun showFacilities(facilities: List<Facility>, exclusionOptions: List<List<ExclusionOptions>>)
            fun showError(message: String)

        }

        interface Presenter {
            fun getData()
        }




    }
