package Screens

import Models.District
import Models.Poste
import Screens.Components.*
import VIewModels.DistrictVM
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
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
fun Districts(window: ComposeWindow) {
    val vm = DistrictVM

    var addDistrict by remember { mutableStateOf(false) }

    Column (modifier = Modifier.fillMaxSize()){

        Row(verticalAlignment = Alignment.CenterVertically) {
            SearchBar(
                hint = "District",
                initText = "",
                onTextChanged = {
                                vm.filterDistricts(it)
                },
                modifier = Modifier.weight(1f)
            )
            Refresh {
                vm.getAllDistricts()
            }
            Button(
                icon = "icons/add.svg",
                text = "Nouveau",
                tintColor = Color.White,
                background = Color(0xff0073FF),
                {window.isEnabled=false
                    addDistrict=true}
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
                            vm.filteredDistricts.forEachIndexed{pos, dist->
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
    if(addDistrict){
        Window(onCloseRequest = {
            window.isEnabled=true
            addDistrict=false},
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp,200.dp),
                size = DpSize(1000.dp,500.dp)
            ),icon = painterResource("images/sonelgaz.png"), title = "Ajouter une district"
        ) {
            var empty by remember { mutableStateOf(false) }
            var district=District()
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
                        Text("Ajouter une district", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                        if(empty) Text("Vous devez remplir toutes les informations", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xffb70007))
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 5.dp)
                                .shadow(2.dp, RoundedCornerShape(20.dp))
                                .background(Color(0xff0073FF))
                                .clickable {
                                    if(
                                        district.district!=""&&
                                        district.centre!=""&&
                                        district.init!=""&&
                                        district.code_centre!=""&&
                                        district.code_agence!=""
                                    ) {
                                        window.isEnabled=true
                                        addDistrict = false
                                        }
                                    else empty=true
                                }
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
                        EmptyTextField(
                            "District",
                            "",
                            10,
                            {
                            district.district=it
                            },
                            Modifier.fillMaxWidth(.3f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        EmptyTextField(
                            "Centre",
                            "",
                            10,
                            {
                            district.centre=it
                            },
                            Modifier.fillMaxWidth(.5f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        EmptyTextField(
                            "Init",
                            "",
                            10,
                            {
                            district.init=it
                            },
                            Modifier.fillMaxWidth()
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                    ) {
                        EmptyTextField(
                            "Code district",
                            "",
                            10,
                            {
                            district.code_agence=it
                            },
                            Modifier.fillMaxWidth(.3f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        EmptyTextField(
                            "Code centre",
                            "",
                            10,
                            {
                            district.code_centre=it
                            },
                            Modifier.fillMaxWidth(.5f)
                        )

                    }
                }
                    VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
            }
        }
    }
}