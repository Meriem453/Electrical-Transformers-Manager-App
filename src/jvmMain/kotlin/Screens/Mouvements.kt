package Screens

import Components.Button
import Components.SearchBar
import Models.Mouvement
import Theme
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
val list= listOf(
    "N° Bon","Date mvt","Date saisie","Motif","Marque","N° série transfo","Puissance","Tension","Année de fab","Fournisseur","Destination","District","Poste","Bon mvt"
)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Mouvements(window: ComposeWindow,transfo:Int?=null) {
    Column (modifier = Modifier.fillMaxSize()){
        var search by remember {
            mutableStateOf("")
        }
        var findTransfo by remember { mutableStateOf(false) }
        var addMvt by remember { mutableStateOf(false) }
        var filterMvt by remember { mutableStateOf(false) }
        var mvtDetails by remember { mutableStateOf(false) }
        var currentMvt:Mouvement? by remember { mutableStateOf(null) }
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
                background = Color.White
            ) {
                window.isEnabled = false
                filterMvt = true
            }
            Button(
                icon = "icons/add.svg",
                text = "Nouveau",
                tintColor = Color.White,
                background = Color(0xff0073FF)
            ) {
                window.isEnabled = false
                findTransfo = true
            }
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

                                    }).fillMaxWidth()
                                    .clickable {
                                        currentMvt=mvt
                                        window.isEnabled = false
                                        mvtDetails = true
                                    }
                                ) {
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
if(findTransfo){
    Window(onCloseRequest = {
        window.isEnabled=true
        findTransfo=false},
        resizable = false,
        state = rememberWindowState(
            position = WindowPosition(500.dp,0.dp),
            size = DpSize(1000.dp,1000.dp)
        ),icon = painterResource("images/sonelgaz.png"), title = "Saisir un bon de mouvement"
    ){
        var n_serie by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("Saisir un bon de mouvement", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .padding(horizontal = 5.dp)
                        .shadow(2.dp, RoundedCornerShape(20.dp))
                        .background(Color(0xff0073FF))
                        .clickable {
                           findTransfo=false
                            addMvt=true
                        }
                ) {
                    Text(
                        "Saisir",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text("Information du transformateur", fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = Theme.MAIN_BLUE)
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(
                hint = "N° série",
                text = n_serie,
                {
                    n_serie=it
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                TextItem("Marque","EI")
                TextItem("Année de fabrication","2024")
                TextItem("Fournisseur","EI")
                TextItem("District","EI")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                TextItem("Tension","EI")
                TextItem("Puissance","2024")
                TextItem("Lieu actuel","EI")
                TextItem("Poste","EI")
                TextItem("Nature","EI")
            }
            Spacer(modifier = Modifier.height(40.dp))
            TextItem("Designation","qsdfghjklmùwxcvbn,;:azertyuiop^$")
            Spacer(modifier = Modifier.height(40.dp))
            Text("Historique des mouvements", fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = Theme.MAIN_BLUE)
            Spacer(modifier = Modifier.height(40.dp))

            val horizontal_state2= rememberScrollState()
            val vertical_state2= rememberScrollState()
            var hoveredTransfoPos2:Int? by remember { mutableStateOf(null) }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .horizontalScroll(horizontal_state2)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    list.forEachIndexed { position, item ->
                        Column {
                            Text(
                                item,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(20.dp)
                            )
                            Column(modifier = Modifier.verticalScroll(vertical_state2)) {
                                var hover by remember { mutableStateOf(false) }
                                mvts.forEachIndexed { pos, mvt ->
                                    Box(modifier = Modifier.background(
                                        if (pos == hoveredTransfoPos2) Color(0xffE5F1FF) else Color.White
                                    ).onPointerEvent(
                                        PointerEventType.Enter,
                                        onEvent = {
                                            hover = true
                                            hoveredTransfoPos2 = pos
                                        },
                                    ).onPointerEvent(
                                        PointerEventType.Exit,
                                        onEvent = {
                                            hover = false
                                            hoveredTransfoPos2 = null

                                        }).fillMaxWidth()
                                    ) {
                                        Text(
                                            when (position) {
                                                0 -> mvt.n_bon
                                                1 -> mvt.date_mvt
                                                2 -> mvt.date_saisie
                                                3 -> mvt.motif
                                                4 -> mvt.marque
                                                5 -> mvt.n_serie_transfo
                                                6 -> mvt.puissance
                                                7 -> mvt.tension
                                                8 -> mvt.annee_de_fab
                                                9 -> mvt.fournisseur
                                                10 -> mvt.destination
                                                11 -> mvt.district
                                                12 -> mvt.poste
                                                13 -> mvt.bon_mvt
                                                else -> ""
                                            }, fontSize = 15.sp, modifier = Modifier.padding(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            HorizontalScrollbar(rememberScrollbarAdapter(horizontal_state2))
        }
    }
}

    }
}

@Composable
fun TextItem(
    title:String,
    text:String
){
    Column {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text, fontSize = 18.sp)

    }
}