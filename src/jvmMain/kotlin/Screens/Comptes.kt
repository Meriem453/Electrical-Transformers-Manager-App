package Screens

import Models.Compte
import Screens.Components.*
import VIewModels.ComptesVM
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
fun Comptes(window: ComposeWindow) {
    var addAccount by remember { mutableStateOf(false) }
    val vm = ComptesVM

    Column (modifier = Modifier.fillMaxSize()){

        Row(verticalAlignment = Alignment.CenterVertically) {
            SearchBar(
                hint = "Nom d'utilisateur",
                initText = "",
                onTextChanged = {
                                vm.filterComptes(it)
                },
                modifier = Modifier.weight(1f)
            )
            Refresh {
                vm.getAllComptes()
            }
            Button(
                icon = "icons/add.svg",
                text = "Nouveau",
                tintColor = Color.White,
                background = Color(0xff0073FF),
                {window.isEnabled=false
                    addAccount=true}
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
                "District","Nom d'utilisateur","Type","Nom","Prenom","Fonction","Email",null
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                list.forEachIndexed{position,item->
                    Column {
                        if(item!=null)Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))else Box(Modifier.height(57.dp))
                        Column(modifier = Modifier.verticalScroll(vertical_state)) {
                            var hover by remember { mutableStateOf(false) }
                            vm.filteredComptes.forEachIndexed{pos, compte->
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
                                            0->compte.District
                                            1-> compte.Nom_utilisateur
                                            2->compte.Type
                                            3->compte.Nom
                                            4->compte.Prenom
                                            5->compte.Fonction
                                            6->compte.Email
                                            else ->""
                                        }
                                        , fontSize = 15.sp, modifier = Modifier.padding(20.dp)
                                    )
                                    if(item==null) Icon(painterResource("icons/delete.svg"),"", tint = Color(0xffb70007), modifier = Modifier.padding(11.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
        HorizontalScrollbar(rememberScrollbarAdapter(horizontal_state))
    }
    if(addAccount){
        Window(onCloseRequest = {
            window.isEnabled=true
            addAccount=false},
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp,200.dp),
                size = DpSize(1000.dp,500.dp)
            ),icon = painterResource("images/sonelgaz.png"), title = "Créer un compte"
        ) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color(0xffF8F8F8)),
                contentAlignment = Alignment.TopEnd
            ) {
                var empty by remember { mutableStateOf(false) }
                val compte=Compte()
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(scrollState)
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text("Créer un compte", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                         if(empty) Text("Vous devez remplir toutes les informations", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xffb70007))
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 5.dp)
                                .shadow(2.dp, RoundedCornerShape(20.dp))
                                .background(Color(0xff0073FF))
                                .clickable {
                                    if(
                                        compte.District!="" &&
                                        compte.Email!="" &&
                                        compte.Nom!="" &&
                                        compte.Prenom!="" &&
                                        compte.Nom_utilisateur!="" &&
                                        compte.Type!="" &&
                                        compte.Fonction!=""&&
                                        compte.mdps!=""
                                    )
                                    {window.isEnabled=true
                                        addAccount = false}
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
                            compte.District=it
                            },
                            Modifier.fillMaxWidth(.3f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        EmptyTextField(
                            "Nom d'utilisateur",
                            "",
                            10,
                            {compte.Nom_utilisateur=it},
                            Modifier.fillMaxWidth(.5f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        DropDown(
                            listOf("Gestionnaire des transfo","Visiteur"),
                            "Type",
                            {text,_ ->
                            compte.Type=text
                            },
                            true,
                            Modifier
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                    ) {
                        EmptyTextField(
                            "Nom",
                            "",
                            10,
                            {
                            compte.Nom=it
                            },
                            Modifier.fillMaxWidth(.3f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        EmptyTextField(
                            "Prénom",
                            "",
                            10,
                            {
                            compte.Prenom=it
                            },
                            Modifier.fillMaxWidth(.5f)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        EmptyTextField(
                            "Fonction",
                            "",
                            10,
                            {
                            compte.Fonction=it
                            },
                            Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    EmptyTextField(
                        "Email",
                        "",
                        30,
                        {
                        compte.Email=it
                        },
                        Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    EmptyTextField(
                        "Mot de passe",
                        "",
                        30,
                        {
                        compte.mdps=it
                        },
                        Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                }
                VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
                }
            }
        }
    }
