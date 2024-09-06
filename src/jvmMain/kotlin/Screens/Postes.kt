package Screens

import Components.Button
import Components.DropDown
import Components.EmptyTextField
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

    var addPoste by remember { mutableStateOf(false) }

    Column (modifier = Modifier.fillMaxSize()){

        Row(verticalAlignment = Alignment.CenterVertically) {
            SearchBar(
                hint = "District",
                initText = "",
                onTextChanged = {},
                modifier = Modifier.weight(1f)
            )
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
            Button(
                icon = "icons/add.svg",
                text = "Nouveau",
                tintColor = Color.White,
                background = Color(0xff0073FF),
                {addPoste=true}
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

    if(addPoste){
        Window(onCloseRequest = {
            window.isEnabled=true
            addPoste=false},
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp,200.dp),
                size = DpSize(1000.dp,500.dp)
            ),icon = painterResource("images/sonelgaz.png"), title = "Ajouter un poste"
        ) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color(0xffF8F8F8)),
                contentAlignment = Alignment.TopEnd
            ) {
                var scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(scrollState)
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text("Ajouter un poste", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 5.dp)
                                .shadow(2.dp, RoundedCornerShape(20.dp))
                                .background(Color(0xff0073FF))
                                .clickable { addPoste = false }
                        ) {
                            Text(
                                "Créer",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DropDown(
                            listOf("El Harrach","Rouiba"),
                            "District",
                            {_,pos ->

                            },
                            true,
                            Modifier.fillMaxWidth(.3f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        EmptyTextField(
                            "Numero",
                            "",
                            10,
                            {},
                            Modifier.fillMaxWidth(.5f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        DropDown(
                            listOf("NP"),
                            "Nature",
                            {_,pos ->

                            },
                            true,
                            Modifier.fillMaxWidth()
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                    ) {
                        EmptyTextField(
                            "Désignation",
                            "",
                            10,
                            {},
                            Modifier.fillMaxWidth()
                        )
                    }
                }
                VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
            }
        }
    }

}