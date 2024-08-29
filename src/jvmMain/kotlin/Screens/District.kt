package Screens

import Components.Button
import Components.SearchBar
import Models.District
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

val dist= listOf(
    District("El Harrach","Rouiba","EHR","55","552")
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Districts(){
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
        var hoveredDistPos:Int? by remember { mutableStateOf(null) }

        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .horizontalScroll(horizontal_state)
                .fillMaxWidth()
        ) {
            val list= listOf(
"Centre","District","Init","Code agence","Code centre"
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                list.forEachIndexed{position,item->
                    Column {
                        Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
                        Column(modifier = Modifier.verticalScroll(vertical_state)) {
                            var hover by remember { mutableStateOf(false) }
                            dist.forEachIndexed{pos, dist->
                                Box(modifier = Modifier .background(
                                    if(pos==hoveredDistPos) Color(0xffE5F1FF) else Color.White
                                ).onPointerEvent(
                                    PointerEventType.Enter,
                                    onEvent = {
                                        hover = true
                                        hoveredDistPos = pos
                                    },
                                ).onPointerEvent(
                                    PointerEventType.Exit,
                                    onEvent = {
                                        hover = false
                                        hoveredDistPos = null

                                    }).fillMaxWidth()) {
                                    Text(
                                        when(position){
                                            0->dist.centre
                                            1-> dist.district
                                            2->dist.init
                                            3->dist.code_agence
                                            4->dist.code_centre
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