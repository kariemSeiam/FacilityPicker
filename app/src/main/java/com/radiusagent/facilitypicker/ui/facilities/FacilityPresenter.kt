package com.radiusagent.facilitypicker.ui.facilities

import com.radiusagent.facilitypicker.data.local.models.FacilityResponse
import com.radiusagent.facilitypicker.data.remote.api.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm

class FacilityPresenter(
    private val view: FacilityContract.View,
    private val apiService: ApiService,
    private val realm: Realm
) : FacilityContract.Presenter {

    private var disposable: Disposable? = null
    private lateinit var exclusionList: ExclusionList


    override fun getData() {
        disposable = apiService.getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->

                view.showFacilities(response.facilities, response.exclusionOptions)
                exclusionList.addExclusions(response.exclusionOptions)/*
                 U Can Use any one of them

                 saveFacilityResponse() just once a day as u said

                 getFacilityResponse()
                  we use this as base one we have to ues just this in getData() and just we use the previous one just once daily

                 clearFacilityResponse() we don't need to use it

                 // There is Small Error in saving Data on DB Cause It's my 1st with realm
                */
            }, { error ->
                view.showError(error.message ?: "Unknown error occurred")
            })
    }


    fun saveFacilityResponse(facilityResponse: FacilityResponse) {
        realm.executeTransaction { r ->
            r.copyToRealmOrUpdate(facilityResponse)
        }
        realm.close()
    }

    fun getFacilityResponse(): FacilityResponse? {
        val facilityResponse = realm.where(FacilityResponse::class.java).findFirst()?.let {
            realm.copyFromRealm(it)
        }
        realm.close()
        return facilityResponse
    }

    fun clearFacilityResponse() {
        realm.executeTransaction { r ->
            r.deleteAll()
        }
        realm.close()
    }


}
