package VIewModels

import Models.Mouvement
import Models.Transformateur
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate
import java.time.format.DateTimeFormatter



val mvts = listOf(
    Mouvement("12","4/5/2024","4/5/2024","04/01/2003","Avarie","EI","123","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("12345655","4/5/2024","4/5/2024", "05/02/2004","Avarie","EI","12","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("12345644","4/5/2024","4/5/2024", "06/03/2005","Avarie","EI","1234","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("12345677","4/5/2024","4/5/2024","07/04/2006","Avarie","EI","123","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf", ),
    Mouvement("12345698","4/5/2024","4/5/2024","08/05/2007","Avarie","EI","123","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf", ),
    Mouvement("12345610","4/5/2024","4/5/2024","10/07/2009","Avarie","EI","123","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf",  ),
    Mouvement("12345614","4/5/2024","4/5/2024","09/06/2008","Avarie","EI","1234","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf", ),
    Mouvement("123456","4/5/2024","4/5/2024","11/08/2010","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf",  ),
    Mouvement("123456","4/5/2024","4/5/2024","12/09/2011","Avarie","EI","1","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf", ),
    Mouvement("123456","4/5/2024","4/5/2024","13/10/2012","Avarie","EI","1","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf",  ),
    Mouvement("123456","4/5/2024","4/5/2024","14/11/2013","Avarie","EI","1","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf",  ),
    Mouvement("123456","4/5/2024","4/5/2024","15/12/2014","Avarie","EI","1","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf", ),
)
object MouvmntVM {

    private var allMvt by mutableStateOf(emptyList<Mouvement>())
    private var allTransfo by mutableStateOf(emptyList<Transformateur>())
    var mvtOfTransfo by mutableStateOf(emptyList<Mouvement>())
    var filteredMvt by mutableStateOf(allMvt)
    var suggestedTransfo by mutableStateOf(emptyList<Transformateur>())
    fun filterMvt(mvt: Mouvement,debut:String,fin:String){
        filteredMvt= allMvt.filter {
            (mvt.n_bon.isEmpty() || it.n_bon == mvt.n_bon) &&
                    (mvt.date_mvt.isEmpty() || it.date_mvt == mvt.date_mvt) &&
                    (mvt.date_saisie.isEmpty() || it.date_saisie == mvt.date_saisie) &&
                    (mvt.motif.isEmpty() || it.motif == mvt.motif) &&
                    (mvt.n_serie_transfo.isEmpty() || it.n_serie_transfo == mvt.n_serie_transfo) &&
                    (mvt.marque.isEmpty() || it.marque == mvt.marque) &&
                    (mvt.destination.isEmpty() || it.destination == mvt.destination) &&
                    (mvt.poste.isEmpty() || it.poste == mvt.poste)
        }
        if (debut!="" && fin!=""){
            try {
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val startDate = LocalDate.parse(debut, formatter)
                val endDate = LocalDate.parse(fin, formatter)

                filteredMvt= filteredMvt.filter {
                    val date = LocalDate.parse(it.date_bon, formatter)
                    date.isEqual(startDate) || date.isEqual(endDate) || (date.isAfter(startDate) && date.isBefore(endDate))
                }
            }catch (e:Exception){
                println(e.localizedMessage)
            }

        }
    }

    fun suggestTransfo(text:String){
        getAllTransfo()
        suggestedTransfo= allTransfo.filter {
            it.n_serie.contains(text)
        }
    }

    fun findTransfoMouvment(transfo: Transformateur){
        mvtOfTransfo= allMvt.filter {
            it.n_serie_transfo==transfo.n_serie && it.marque==transfo.marque
        }
    }
    fun getAllMvts(){
        allMvt= mvts
        filteredMvt= mvts
    }
private fun getAllTransfo(){
    allTransfo= transfo
}
    init {
        getAllMvts()
    }

}