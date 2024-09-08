package VIewModels

import Models.District
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

val dist= listOf(
    District("El Harrach","Rouiba","EHR","55","552")
)
object DistrictVM {
    private var allDistricts by mutableStateOf(emptyList<District>())
    var filteredDistricts by mutableStateOf(allDistricts)
    fun filterDistricts(text: String){
        filteredDistricts=if(text=="") allDistricts else allDistricts.filter {
            it.district.contains(text)
        }

    }
    fun getAllDistricts(){
        allDistricts= dist
        filteredDistricts= dist
    }
    init {
        getAllDistricts()
    }
}