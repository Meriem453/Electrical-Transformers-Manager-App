package Screens

import Auth
import Models.Poste
import Screens.Components.*
import VIewModels.PostesVM
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Postes(window: ComposeWindow) {

    val vm = PostesVM


    Column (modifier = Modifier.fillMaxSize()){

        Row(verticalAlignment = Alignment.CenterVertically) {
            SearchBar(
                hint = "Numéro du poste",
                initText = "",
                onTextChanged = {
                                vm.filterPostes(it)
                },
                modifier = Modifier.weight(1f)
            )
            Refresh {
                vm.getAllPostes()
            }
            DropDown(
                listOf("El-Harrach","Rouiba"),
                "District",
                {_,pos ->

                },
                true
            )
            Button(
                icon = "icons/print.svg",
                text = "Imprimer",
                tintColor = Color(0xff0073FF),
                background = Color.White,
                {}
            )
        }
        val vertical_state= rememberScrollState()
        var hoveredPostePos:Int? by remember { mutableStateOf(null) }

        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .fillMaxWidth()
        ) {
            val list= listOf(
                "District","Addresse","Commune","Numero","Nature","N° série transfo","Marque transfo",null
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                list.forEachIndexed{position,item->
                    Column {
                       if(item!=null) Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp)) else if(Auth.currentUser!!.role=="Gestionnaire de transformateurs") Box(Modifier.height(57.dp))
                        Column(modifier = Modifier.verticalScroll(vertical_state)) {
                            var hover by remember { mutableStateOf(false) }
                            vm.filteredPostes.forEachIndexed{pos, poste->
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

                                    })) {
                                    Text(
                                        when(position){
                                            0->poste.District
                                            1-> poste.Designation
                                            2->"Commune"
                                            3->poste.Numero
                                            4->poste.Nature
                                            5->poste.n_serie_transfo
                                            6->poste.marque_transfo
                                            else ->""
                                        }
                                        , fontSize = 15.sp, modifier = Modifier.padding(20.dp)
                                    )
                                    if(item==null && Auth.currentUser!!.role=="Gestionnaire de transformateurs") Icon(painterResource("icons/delete.svg"),"", tint = Color(0xffb70007), modifier = Modifier.padding(11.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}