package Screens

import Components.Button
import Components.CheckGrp
import Components.DropDown
import Components.SearchBar
import Models.Mouvement
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val mvts = listOf(
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
    Mouvement("123456","4/5/2024","4/5/2024","Avarie","EI","123456","2000","2000","2024","lqdkfvosjo","Exploitation","Rouiba","20144","xfhgjh.pdf"),
)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Mouvements(){
    Column (modifier = Modifier.fillMaxSize()){
        var search by remember {
            mutableStateOf("")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            SearchBar(
                hint = "N° de série transfo",
                text = search,
                onTextChanged = {search=it},
                modifier = Modifier.weight(1f)
            )
            Button(
                icon = "icons/print.svg",
                text = "Imprimer",
                tintColor = Color(0xff0073FF),
                background = Color.White,
                {}
            )
            Button(
                icon = "icons/Group.svg",
                text = "Filtrer",
                tintColor = Color(0xff0073FF),
                background = Color.White,
                {}
            )
            Button(
                icon = "icons/add.svg",
                text = "Nouveau",
                tintColor = Color.White,
                background = Color(0xff0073FF),
                {}
            )
        }

        val horizontal_state= rememberScrollState()
        val vertical_state= rememberScrollState()
        var hoveredTransfoPos:Int? by remember { mutableStateOf(null) }

        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .horizontalScroll(horizontal_state)
        ) {
            val list= listOf(
                "N° Bon","Date mvt","Date saisie","Motif","Marque","N° série transfo","Puissance","Tension","Année de fab","Fournisseur","Destination","District","Poste","Bon mvt"
            )

            Row(modifier = Modifier.fillMaxWidth()){
                list.forEachIndexed{position,item->
                    Column {
                        Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
                        Column(modifier = Modifier.verticalScroll(vertical_state)) {
                            var hover by remember { mutableStateOf(false) }
                            mvts.forEachIndexed{pos, mvt->
                                Box(modifier = Modifier .background(
                                    if(pos==hoveredTransfoPos) Color(0xffE5F1FF) else Color.White
                                ).onPointerEvent(
                                    PointerEventType.Enter,
                                    onEvent = {
                                        hover = true
                                        hoveredTransfoPos = pos
                                    },
                                ).onPointerEvent(
                                    PointerEventType.Exit,
                                    onEvent = {
                                        hover = false
                                        hoveredTransfoPos = null

                                    }).fillMaxWidth()) {
                                    Text(
                                        when(position){
                                            0->mvt.n_bon
                                            1-> mvt.date_mvt
                                            2->mvt.date_saisie
                                            3->mvt.motif
                                            4->mvt.marque
                                            5->mvt.n_serie_transfo
                                            6->mvt.puissance
                                            7->mvt.tension
                                            8->mvt.annee_de_fab
                                            9->mvt.fournisseur
                                            10->mvt.destination
                                            11->mvt.district
                                            12->mvt.poste
                                            13->mvt.bon_mvt
                                            else ->""
                                        }
                                        , fontSize = 15.sp, modifier = Modifier.padding(20.dp)
                                    )}
                            }
                        }
                    }
                }
            }
        }
        HorizontalScrollbar(rememberScrollbarAdapter(horizontal_state))
    }
}