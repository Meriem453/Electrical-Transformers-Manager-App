package Screens

import Models.DD
import Models.Transformateur
import Screens.Components.*
import VIewModels.PostesVM
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

@Composable
fun DD(window: ComposeWindow){
   var currentDD:DD? by remember { mutableStateOf(null) }
    var addDD by remember { mutableStateOf(false) }

    Column (modifier = Modifier.fillMaxSize()){
        if(Auth.currentUser!!.role=="Admin") {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {

                Button(
                    icon = "icons/add.svg",
                    text = "Nouveau",
                    tintColor = Color.White,
                    background = Color(0xff0073FF)
                ) {

                    addDD=true
                    window.isEnabled = false
                }
            }
        }

        val vertical_state= rememberScrollState()

        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .fillMaxWidth()
        ) {
            val list= listOf(
                "DD","Code",""
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                list.forEachIndexed{position,item->
                    Column {
                        Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
                        Column(modifier = Modifier.verticalScroll(vertical_state)) {

                            listOf(DD("El Harrach","251"),DD("Dar El Baida","251")).forEachIndexed{ pos, dd->
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        when(position){
                                            0->dd.DD
                                            1-> dd.code
                                            else ->""
                                        }
                                        , fontSize = 15.sp, modifier = Modifier.padding(20.dp)
                                    )
                                   if(position==2) {
                                       Row (verticalAlignment = Alignment.CenterVertically,
                                           modifier = Modifier
                                               .clip(RoundedCornerShape(20.dp))
                                               .padding(horizontal = 5.dp)
                                               .shadow(2.dp, RoundedCornerShape(10.dp))
                                               .background(Color(0xff0073FF))
                                               .clickable {
                                                   currentDD=dd
                                                   window.isEnabled = false
                                                   addDD = true
                                               }
                                       ){
                                           Text("Modifier", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                                       }
                                   }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    if(addDD) {
        Window(
            onCloseRequest = {
                currentDD=null
                window.isEnabled = true
                addDD = false
            },
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp, 200.dp),
                size = DpSize(800.dp, 300.dp)
            ), icon = painterResource("images/sonelgaz.png"), title = "Ajouter/Modifier"
        ) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color(0xffF8F8F8)),
                contentAlignment = Alignment.TopEnd
            ) {
                var empty by remember { mutableStateOf(false) }
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "${if (currentDD == null) "Créer" else "Modfier"} une DD",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        if (empty) Text(
                            "Vous devez remplir toutes les informations",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xffb70007)
                        )
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 5.dp)
                                .shadow(2.dp, RoundedCornerShape(20.dp))
                                .background(Color(0xff0073FF))
                                .clickable {
                                    if (
                                        true
                                    ) {
                                        currentDD=null
                                        window.isEnabled = true
                                        addDD = false

                                    } else empty = true
                                }
                        ) {
                            Text(
                                if (currentDD == null) "Créer" else "Modfier",
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
                            "DD",
                            if (currentDD == null) "" else currentDD!!.DD,
                            10,
                            {
                            },
                            Modifier.fillMaxWidth(.5f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        EmptyTextField(
                            "Code",
                            if (currentDD == null) "" else currentDD!!.code,
                            10,
                            {
                            },
                            Modifier.fillMaxWidth()
                        )
                    }


                }
            }
        }
    }
}