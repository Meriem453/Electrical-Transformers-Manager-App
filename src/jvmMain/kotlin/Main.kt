import Screens.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

data class Item(
    val name:String,
    val icon:String,
    val desc:String
)

val items= listOf(
    Item("Transfomateurs","icons/flash_on.svg","ifjisnhvgivnrnvb"),
    Item("Transfo. réformés","icons/wifi_protected_setup.svg",""),
    Item("Mouvements","icons/compare_arrows.svg",""),
    Item("Districts","icons/account_balance.svg",""),
    Item("Postes","icons/charging_station.svg",""),

)
@Composable
@Preview
fun App(window: ComposeWindow) {
    Row(modifier = Modifier.background(Color(0xffF9F9F9))) {
        var selectedItem by remember {
            mutableStateOf(0)
        }
        MaterialTheme {
            NavigationRail(backgroundColor = Color.White,header = {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 50.dp, horizontal = 30.dp)
                ) {
                    Image(painterResource("images/sonelgaz.png"), "")
                    Text("Sonelgaz", fontSize = 26.sp, fontWeight = FontWeight.Medium)
                }
            }) {
                items.forEachIndexed { index, item ->
                    NavigationRailItem(
                        label = {
                            Text(
                                text = item.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (selectedItem == index) Color.White else Color.Black,


                                )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(item.icon), "",
                                tint = if (selectedItem == index) Color.White else Color.Black
                            )
                        },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        modifier = Modifier.fillMaxWidth(.1f).padding(5.dp).clip(RoundedCornerShape(20.dp)).background(
                            Color(
                                if (selectedItem == index) 0xff0073FF else 0xffffffff
                            )
                        ),
                        selectedContentColor = Color(0xff0073FF),

                        )
                }
            }
            Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                    Column(modifier = Modifier.fillMaxWidth(.6f)) {
                        Text(items[selectedItem].name, fontSize = 26.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 20.dp))
                        Text(items[selectedItem].desc, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        , horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                        ) {
                        Column (modifier = Modifier.padding(30.dp)){
                            Text("Name", fontSize = 16.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 10.dp))
                            Text("Role", fontSize = 14.sp, color = Color.Gray)
                        }

                        Row (modifier = Modifier.padding(30.dp).clip(RoundedCornerShape(20.dp)).clickable {  }, verticalAlignment = Alignment.CenterVertically){
                            Icon(painterResource("icons/login.svg"),"", tint = Color.Gray, modifier = Modifier.padding(20.dp))
                            Text("Deconnexion", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Gray,modifier = Modifier.padding(end = 20.dp))
                        }
                    }
                }
                Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
                    when (selectedItem) {
                        0 -> Transfo(window)
                        1 -> Reforme()
                        2 -> Mouvements(window)
                        3 -> Districts(window)
                        4 -> Postes(window)
                    }
                }
            }
        }
    }
}

fun main() = application {
    val state = rememberWindowState()
    Window(onCloseRequest = ::exitApplication, icon = painterResource("images/sonelgaz.png"), title = "Sonelgaz", state = state,) {

        App(window)
    }
}
