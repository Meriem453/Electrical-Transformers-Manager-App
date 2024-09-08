package VIewModels

import Models.Compte
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
val comptes= listOf(
    Compte("El harrach","Mehdi","RDA","Bourmad","Salim","Tech sup","bourmad@gmail.com"),
    Compte("El harrach","Mehdi","RDA","Bourmad","Salim","Tech sup","bourmad@gmail.com"),
    Compte("El harrach","Mehdi","RDA","Bourmad","Salim","Tech sup","bourmad@gmail.com"),
    Compte("El harrach","Mehdi","RDA","Bourmad","Salim","Tech sup","bourmad@gmail.com"),
    Compte("El harrach","Mehdi","RDA","Bourmad","Salim","Tech sup","bourmad@gmail.com"),
)

object ComptesVM {
    private var allComptes by mutableStateOf(emptyList<Compte>())
    var filteredComptes by mutableStateOf(allComptes)
    fun filterComptes(username: String){
        filteredComptes=if(username=="") allComptes else allComptes.filter {
            it.Nom_utilisateur.contains(username)
        }

    }
    fun getAllComptes(){
        allComptes= comptes
        filteredComptes= comptes
    }
    init {
        getAllComptes()
    }
}