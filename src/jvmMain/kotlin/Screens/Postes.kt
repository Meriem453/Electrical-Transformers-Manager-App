package Screens

import Components.Button
import Components.DropDown
import Components.SearchBar
import Models.Poste
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val postes= listOf(
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
    Poste("El Harrach","qsdfghjklmùaz","12543","Cabine","7410852","EI"),
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Postes(window: ComposeWindow) {
    Column (modifier = Modifier.fillMaxSize()){
        var search by remember {
            mutableStateOf("")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            SearchBar(
                hint = "District",
                text = search,
                onTextChanged = {search=it},
                modifier = Modifier.weight(1f)
            )
            DropDown(
                listOf("El-Harrach","Rouiba"),
                "District",
                {},
                true
            )
            Button(
                icon = "icons/print.svg",
                text = "Imprimer",
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
        var hoveredPostePos:Int? by remember { mutableStateOf(null) }

        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .horizontalScroll(horizontal_state)
                .fillMaxWidth()
        ) {
            val list= listOf(
                "District","Designation","Numero","Nature","N° série transfo","Marque transfo",null
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                list.forEachIndexed{position,item->
                    Column {
                       if(item!=null) Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp)) else Spacer(Modifier)
                        Column(modifier = Modifier.verticalScroll(vertical_state)) {
                            var hover by remember { mutableStateOf(false) }
                            postes.forEachIndexed{pos, poste->
                                Box(modifier = Modifier .background(
                                    if(pos==hoveredPostePos) Color(0xffE5F1FF) else Color.White
                                ).onPointerEvent(
                                    PointerEventType.Enter,
                                    onEvent = {
                                        hover = true
                                        hoveredPostePos = pos
                                    },
                                ).onPointerEvent(
                                    PointerEventType.Exit,
                                    onEvent = {
                                        hover = false
                                        hoveredPostePos = null

                                    }).fillMaxWidth()) {
                                    Text(
                                        when(position){
                                            0->poste.District
                                            1-> poste.Designation
                                            2->poste.Numero
                                            3->poste.Nature
                                            4->poste.n_serie_transfo
                                            5->poste.marque_transfo
                                            else ->""
                                        }
                                        , fontSize = 15.sp, modifier = Modifier.padding(20.dp)
                                    )
                                    if(item==null) Icon(Icons.Default.Delete,"", tint = Color(0xffb70007))
                                }
                            }
                        }
                    }
                }
            }
        }
        HorizontalScrollbar(rememberScrollbarAdapter(horizontal_state))
    }
}