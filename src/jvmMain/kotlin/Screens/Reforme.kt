package Screens

import Auth
import Models.Transformateur
import Screens.Components.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun Reforme(window:ComposeWindow){
    var addReform by remember {
        mutableStateOf(false)
    }
    var addVendu by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        var checkedItem by remember {
            mutableStateOf(0)
        }
        var reforme by remember {
            mutableStateOf(false)
        }
        var vendu by remember {
            mutableStateOf(false)
        }
        var filter by remember {
            mutableStateOf(false)
        }
        var reformList by remember {
            mutableStateOf(ArrayList<Transformateur>())
        }
        var venteList by remember {
            mutableStateOf(ArrayList<Transformateur>())
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            SearchBar(
                hint = "N° de série",
                initText = "",
                onTextChanged = {

                },
                modifier = Modifier.weight(1f)
            )
            Refresh {

            }
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
                background =if(filter) Color(0xffE5F1FF) else Color.White
            ) {
                if(filter){
                    //vm.filterTransfo(Transformateur())
                    filter=false
                }else{
                    filter=true
//                    filterTransfo = true
//                    parentWindow.isEnabled = false
                }

            }
        }
        Row( modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            CheckGrp(
                isChecked = checkedItem == 0,
                onChecked = { checkedItem = 0
                            vendu=false
                            },
                "Proposés à la réforme"
            )

            Box(modifier = Modifier.weight(1f)) {
                CheckGrp(
                    isChecked = checkedItem == 1,
                    onChecked = {
                        checkedItem = 1
                        reforme = false
                    },
                    "Réformés"
                )
            }
            if(reforme) {
                Button(
                    icon = "icons/add.svg",
                    text = "Réformé",
                    tintColor = Color.White,
                    background = Color(0xff0073FF)
                ) {
                    addReform=true
                    window.isEnabled=false
                }
            }
            if(vendu){
                Button(
                    icon = "icons/add.svg",
                    text = "Vendu",
                    tintColor = Color.White,
                    background = Color(0xff0073FF)
                ) {
                    addVendu=true
                    window.isEnabled=false
                }
            }
        }
        if (checkedItem==0)
            Propose{
                reforme=it.isNotEmpty()
                reformList=it
            }
        else
            Reformes{
                vendu=it.isNotEmpty()
                venteList=it
            }

        }
    if(addReform){
        Window(onCloseRequest = {
            window.isEnabled=true
            addReform=false},
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp,200.dp),
                size = DpSize(800.dp,500.dp)
            ),icon = painterResource("images/sonelgaz.png"), title = "Transformateur réformé"
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
                        Text("Transformateur réformé", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
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
                                    ){
                                        window.isEnabled=true
                                        addReform=false
                                    } else empty=true

                                }
                        ) {
                            Text(
                                "Réformer",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                            )
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                        EmptyTextField(
                            "Date",
                            "",
                            10,
                            {

                            },
                            Modifier.fillMaxWidth(.5f).padding(end = 10.dp)
                        )
                        EmptyTextField(
                            "N° de la résolution",
                            "",
                            25,
                            {

                            },
                            Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    var ca by remember { mutableStateOf("") }

                    FileSection(
                        "Résolution CA",
                        ca,
                        {
                            ca= openFileDialog(
                                window,"choisir un fichier"
                            )?:""
                        },
                        Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
    if(addVendu){
        Window(onCloseRequest = {
            window.isEnabled=true
            addVendu=false},
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp,200.dp),
                size = DpSize(800.dp,500.dp)
            ),icon = painterResource("images/sonelgaz.png"), title = "Transformateurs vendus"
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
                        Text("Transformateurs vendus", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
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
                                    ){
                                        window.isEnabled=true
                                        addVendu=false
                                    } else empty=true

                                }
                        ) {
                            Text(
                                "Vendre",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                            )
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                        EmptyTextField(
                            "Date",
                            "",
                            10,
                            {

                            },
                            Modifier.fillMaxWidth(.5f).padding(end = 10.dp)
                        )
                        EmptyTextField(
                            "N° de la résolution",
                            "",
                            25,
                            {

                            },
                            Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    var ca by remember { mutableStateOf("") }

                    FileSection(
                        "Résolution du vente",
                        ca,
                        {
                            ca= openFileDialog(
                                window,"choisir un fichier"
                            )?:""
                        },
                        Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
    }

@Composable
fun Propose(
    isReformeVisible:(list:ArrayList<Transformateur>)->Unit
){
    val vertical_state= rememberScrollState()
    val reformList by remember { mutableStateOf(ArrayList<Transformateur>()) }


    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxSize()
            .background(Color.White)
    ) {
        val list= listOf(
            "Marque","N° série","Tension","Puissance","A. fabrication","Fournisseur","Prix d'aquisition","Id bien",""
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            list.forEachIndexed{position,item->
                Column {
                    Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
                    Column(modifier = Modifier.verticalScroll(vertical_state)) {
                        listOf(
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                        ).forEachIndexed{ pos, transfo->
                            Box(contentAlignment = Alignment.Center, modifier = Modifier

                            ) {
                                Text(
                                    when(position){
                                        0->transfo.marque
                                        1-> transfo.n_serie
                                        2->transfo.tension
                                        3->transfo.puissance
                                        4->transfo.a_fabrication
                                        5->transfo.fournisseur
                                        6->"1000000 DA"
                                        7->"123456"
                                        else ->""
                                    }
                                    , fontSize = 15.sp, modifier = Modifier.padding(20.dp)
                                )
                                if(position==8 && Auth.currentUser!!.role=="Gestionnaire de transformateurs"){
                                    var checkedTrn by remember { mutableStateOf(false) }
                                    CheckGrp(
                                        isChecked = checkedTrn ,
                                        onChecked = {
                                            checkedTrn = !checkedTrn
                                            if(checkedTrn){
                                                reformList.add(transfo)
                                            }
                                            else{
                                                reformList.remove(transfo)
                                            }
                                            isReformeVisible(reformList)

                                                    },
                                        ""
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun Reformes(
    isVenteVisible:(list:ArrayList<Transformateur>)->Unit
){
    val vertical_state= rememberScrollState()
    val venduList by remember { mutableStateOf(ArrayList<Transformateur>()) }


    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxSize()
            .background(Color.White)
    ) {
        val list= listOf(
            "Marque","N° série","Tension","Puissance","A. fabrication","Fournisseur","Prix d'aquisition","Id bien","Date de la reform","Numero de la résolution","Résolution CA",""
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            list.forEachIndexed{position,item->
                Column {
                    Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
                    Column(modifier = Modifier.verticalScroll(vertical_state)) {
                        listOf(
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                            Transformateur("EI","123456","1000","1000","2003","qsdfghjk",),
                        ).forEachIndexed{ pos, transfo->
                            Box(contentAlignment = Alignment.Center, modifier = Modifier

                            ) {
                                Text(
                                    when(position){
                                        0->transfo.marque
                                        1-> transfo.n_serie
                                        2->transfo.tension
                                        3->transfo.puissance
                                        4->transfo.a_fabrication
                                        5->transfo.fournisseur
                                        6->"1000000 DA"
                                        7->"123456"
                                        8->"4/5/2024"
                                        9->"1234560"
                                        10->"pdf"
                                        else ->""
                                    }
                                    , fontSize = 15.sp, modifier = Modifier.padding(20.dp)
                                )
                                if(position==11 && Auth.currentUser!!.role=="Gestionnaire de transformateurs"){
                                    var checkedTrn by remember { mutableStateOf(false) }
                                    CheckGrp(
                                        isChecked = checkedTrn ,
                                        onChecked = {
                                            checkedTrn = !checkedTrn
                                            if(checkedTrn){
                                                venduList.add(transfo)
                                            }
                                            else{
                                                venduList.remove(transfo)
                                            }
                                            isVenteVisible(venduList)

                                        },
                                        ""
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
