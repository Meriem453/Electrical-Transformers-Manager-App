package VIewModels

import Models.Transformateur
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

val transfo= listOf(
    Transformateur("EI","1","10","1000","2003","kkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("W","11","1000","1000","2003","kkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("ENIE","1111","1000","1000","2003","kk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("ENIE","123456","1000","1000","2003","k","Stock","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("ENIE","123456789","1000","1000","2003","kkkkkkkkkk","Stock","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("EI","458752","1000","1000","2003","kkkkkkkkkk","Stock","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("EI","54213","1000","1000","2003","kkkkkkkkkk","Platform DD","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("W","54213","1000","1000","2003","kkkkkkkkkk","Platform DD","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
)
object TransfoVM {

    private var allTransfo by mutableStateOf(emptyList<Transformateur>())
    var filteredTransfo by mutableStateOf(allTransfo)
    var etat by mutableStateOf("Exploitation")
    //var  by mutableStateOf(allTransfo)
        fun filterTransfo(transfo:Transformateur){
            filteredTransfo=allTransfo.filter {
                        (transfo.marque.isEmpty() || it.marque == transfo.marque) &&
                        (transfo.n_serie.isEmpty() || it.n_serie == transfo.n_serie) &&
                        (transfo.tension.isEmpty() || it.tension == transfo.tension) &&
                        (transfo.puissance.isEmpty() || it.puissance == transfo.puissance) &&
                        (transfo.a_fabrication.isEmpty() || it.a_fabrication == transfo.a_fabrication) &&
                        (transfo.district.isEmpty() || it.district == transfo.district) &&
                        (transfo.poste.isEmpty() || it.poste == transfo.poste) &&
                        (transfo.lieu_actuel.isEmpty() || it.lieu_actuel == transfo.lieu_actuel)
            }

        }
    fun getAllTransfo(){
        allTransfo= transfo
        filteredTransfo= transfo
    }
init {
    getAllTransfo()
}

}



