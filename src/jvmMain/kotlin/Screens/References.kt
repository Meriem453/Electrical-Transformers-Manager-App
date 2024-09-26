package Screens

import Screens.Components.Button
import Screens.Components.EmptyTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState

@Composable
fun References(window: ComposeWindow){
    var addMarque by remember { mutableStateOf(false) }
    var addTension by remember { mutableStateOf(false) }
    var addPuissance by remember { mutableStateOf(false) }
Row (
    Modifier
        .fillMaxSize(),
    horizontalArrangement = Arrangement.SpaceBetween
){
    Column(
        modifier = Modifier
            .weight(.3f)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
            .background(Color.White)
    ) {
    Row(modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Marque", fontSize = 26.sp)
        if(Auth.currentUser!!.role=="Admin") {
            Button(
                icon = "icons/add.svg",
                text = "Nouvel",
                tintColor = Color.White,
                background = Color(0xff0073FF)
            ) {
                window.isEnabled = false
                addMarque = true
            }
        }
    }

        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
        ) {
            itemsIndexed(listOf("Al Suweidi","EI")){pos,item->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(item, fontSize = 16.sp, modifier = Modifier.weight(1f).padding(vertical = 10.dp))
                    if(Auth.currentUser!!.role=="Admin") Icon(painter = painterResource("icons/delete.svg"),"", tint = Color.Gray)
                }

            }
        }
    }
    Column(
        modifier = Modifier
            .weight(.3f)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
            .background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Tension", fontSize = 26.sp)
            if(Auth.currentUser!!.role=="Admin") {
                Button(
                    icon = "icons/add.svg",
                    text = "Nouvel",
                    tintColor = Color.White,
                    background = Color(0xff0073FF)
                ) {
                    window.isEnabled = false
                    addTension = true
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
        ) {
            itemsIndexed(listOf("1220","2005")){pos,item->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(item, fontSize = 16.sp, modifier = Modifier.weight(1f).padding(vertical = 10.dp))
                    if(Auth.currentUser!!.role=="Admin") Icon(painter = painterResource("icons/delete.svg"),"", tint = Color.Gray)
                }

            }
        }
    }
    Column(
        modifier = Modifier
            .weight(.3f)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
            .background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Puissance", fontSize = 26.sp)
            if(Auth.currentUser!!.role=="Admin") {
                Button(
                    icon = "icons/add.svg",
                    text = "Nouvel",
                    tintColor = Color.White,
                    background = Color(0xff0073FF)
                ) {
                    window.isEnabled = false
                    addPuissance = true
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
        ) {
            itemsIndexed(listOf("1254","1254")){pos,item->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(item, fontSize = 16.sp, modifier = Modifier.weight(1f).padding(vertical = 10.dp))
                    if(Auth.currentUser!!.role=="Admin") Icon(painter = painterResource("icons/delete.svg"),"", tint = Color.Gray)
                }

            }
        }
    }
}
    if(addMarque || addPuissance || addTension){
        Window(onCloseRequest = {
            window.isEnabled=true
            addMarque=false
            addPuissance=false
            addTension=false
                                },
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp,200.dp),
                size = DpSize(800.dp,300.dp)
            ),icon = painterResource("images/sonelgaz.png"), title = "Ajouter"
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
                        Text("Ajouter une ${if(addMarque) "Marque" else if(addTension) "Tension" else "Puissance"}", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                        if(empty) Text("Vous devez remplir toutes les informations", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xffb70007))
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 5.dp)
                                .shadow(2.dp, RoundedCornerShape(20.dp))
                                .background(Color(0xff0073FF))
                                .clickable {
                                    if(
                                        true
                                    )
                                    {
                                        window.isEnabled=true
                                        addMarque=false
                                        addPuissance=false
                                        addTension=false

                                    }
                                    else empty=true
                                }
                        ) {
                            Text(
                                "Ajouter",
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
                            if(addMarque) "Marque" else if(addTension) "Tension" else "Puissance",
                            "",
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