package VIewModels

import Models.Poste
import Models.Transformateur
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

val postes= listOf(
    Poste("551 El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("551 El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
)
object PostesVM {
    private var allPostes by mutableStateOf(emptyList<Poste>())
    var filteredPostes by mutableStateOf(allPostes)
    fun filterPostes(number: String){
        filteredPostes=if(number=="") allPostes else allPostes.filter {
           it.Numero.contains(number)
        }

    }
    fun getAllPostes(){
        allPostes= postes
        filteredPostes= postes
    }
    init {
        getAllPostes()
    }
}