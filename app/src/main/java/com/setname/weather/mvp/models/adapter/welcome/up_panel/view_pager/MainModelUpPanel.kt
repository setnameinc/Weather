package com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager

import android.os.Parcelable
import com.setname.weather.mvp.models.database.weather.smart_request.ModelUpPanel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainModelUpPanel(
    val id_dt: Long,
    val temp: Float,
    val desc: String
) : Parcelable {

    constructor(modelUpPanel: ModelUpPanel) : this(modelUpPanel.id_dt, modelUpPanel.temp, modelUpPanel.model_up_panel.description)

}