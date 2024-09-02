package Screens

import Components.*
import Models.Mouvement
import Theme
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.style.TextAlign
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
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
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
        var closeConfirm by remember { mutableStateOf(false) }

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
                if(addMvt){
                    Window(onCloseRequest = {
                        closeConfirm=true
                    },
                        resizable = false,
                        state = rememberWindowState(
                            position = WindowPosition(250.dp,0.dp),
                            size = DpSize(1250.dp,700.dp)
                        ),icon = painterResource("images/sonelgaz.png"), title = "Saisir un bon de mouvement"
                    ) {
                        var destPlat by remember { mutableStateOf(false) }
                        var destExploi by remember { mutableStateOf(false) }
                        var destAutre by remember { mutableStateOf(false) }
                        var motifAvar by remember { mutableStateOf(false) }
                        var motifEntr by remember { mutableStateOf(false) }
                        var motifTransfertInterDD by remember { mutableStateOf(false) }
                        var motifTransfertDDversGDC by remember { mutableStateOf(false) }
                        var motifVente by remember { mutableStateOf(false) }

                        val verticalScroll = rememberScrollState()

                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Theme.BACKGROUND)
                                    .padding(20.dp)
                                    .verticalScroll(verticalScroll)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        "Saisir un bon de mouvement",
                                        fontSize = 26.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Box(contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .padding(horizontal = 5.dp)
                                            .shadow(2.dp, RoundedCornerShape(20.dp))
                                            .background(Color(0xff0073FF))
                                            .clickable {
                                                findTransfo = false
                                                addMvt = true
                                            }
                                    ) {
                                        Text(
                                            "Valider",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(50.dp))
                                Text(
                                    "Transfo: 123456985 Marque:EI Provenance:Stock",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    color = Theme.MAIN_BLUE,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(50.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    DropDown(
                                        listOf("El Harrach", "Rouiba"),
                                        "Emetteur (District)",
                                        {},
                                        true,
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    EmptyTextField(
                                        "N° du bon de mvt",
                                        "",
                                        10,
                                        {},
                                        Modifier
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    EmptyTextField(
                                        "Date du bon de mvt",
                                        "",
                                        10,
                                        {},
                                        Modifier
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    EmptyTextField(
                                        "Date de mvt",
                                        "",
                                        10,
                                        {},
                                        Modifier
                                    )
                                }
                                Spacer(modifier = Modifier.height(30.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    DropDown(
                                        listOf(
                                            "Avarie",
                                            "Augment puissance",
                                            "Dimunit puissance",
                                            "Stock de sécurité",
                                            "Désaffection poste",
                                            "Entretien Préventif",
                                            "Cession par client",
                                            "Location pour client",
                                            "Poste noeuf",
                                            "Changement de tension",
                                            "Permutation",
                                            "Poste remplacé",
                                            "Vente",
                                            "Transfert inter DD",
                                            "Transfert DD vers GDC"
                                        ),
                                        "Motif",
                                        {
                                            when (it) {
                                                0 -> {
                                                    motifAvar = true
                                                    motifEntr = false
                                                    motifVente = false
                                                    motifTransfertInterDD = false
                                                    motifTransfertDDversGDC = false
                                                }

                                                5 -> {
                                                    motifAvar = false
                                                    motifEntr = true
                                                    motifVente = false
                                                    motifTransfertInterDD = false
                                                    motifTransfertDDversGDC = false
                                                }

                                                12 -> {
                                                    motifAvar = false
                                                    motifEntr = false
                                                    motifVente = true
                                                    motifTransfertInterDD = false
                                                    motifTransfertDDversGDC = false
                                                }

                                                13 -> {
                                                    motifAvar = false
                                                    motifEntr = false
                                                    motifVente = false
                                                    motifTransfertInterDD = true
                                                    motifTransfertDDversGDC = false
                                                }

                                                14 -> {
                                                    motifAvar = false
                                                    motifEntr = false
                                                    motifVente = false
                                                    motifTransfertInterDD = false
                                                    motifTransfertDDversGDC = true
                                                }

                                                else -> {
                                                    motifAvar = false
                                                    motifEntr = false
                                                    motifVente = false
                                                    motifTransfertInterDD = false
                                                    motifTransfertDDversGDC = false
                                                }
                                            }
                                        },
                                        true,
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Column {
                                        DropDown(
                                            listOf("Stock", "Exploitation", "Platform DD", "Platform GDC", "Autre"),
                                            "Destination",
                                            {
                                                when (it) {
                                                    1 -> {
                                                        destAutre = false
                                                        destExploi = true
                                                        destPlat = false
                                                    }

                                                    3 -> {
                                                        destAutre = false
                                                        destExploi = false
                                                        destPlat = true
                                                    }

                                                    4 -> {
                                                        destAutre = true
                                                        destExploi = false
                                                        destPlat = false
                                                    }

                                                    else -> {
                                                        destAutre = false
                                                        destExploi = false
                                                        destPlat = false
                                                    }
                                                }
                                            },
                                            true,
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        DropDown(
                                            listOf(),
                                            "Préciser",
                                            {},
                                            destAutre,
                                        )
                                    }
                                    if (destPlat) {
                                        Spacer(modifier = Modifier.width(20.dp))
                                        EmptyTextField(
                                            "Date d'entrée",
                                            "",
                                            10,
                                            {},
                                            Modifier
                                        )
                                    }
                                }

                                if (destExploi) {
                                    Spacer(modifier = Modifier.height(50.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        DropDown(
                                            listOf("1024", "2543", "52136", "522148"),
                                            "N° poste",
                                            {},
                                            true,
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        EmptyTextField(
                                            "Nature",
                                            "",
                                            10,
                                            {},
                                            Modifier
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        EmptyTextField(
                                            "Designation",
                                            "",
                                            25,
                                            {},
                                            Modifier
                                        )
                                    }
                                }
                                if (motifAvar) {
                                    Spacer(modifier = Modifier.height(50.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        DropDown(
                                            listOf(
                                                "Acte Malveillance",
                                                "Court circuit",
                                                "Court circuit disjinct BT",
                                                "Court circuit fermé par tiers",
                                                "Court circuit interne",
                                                "Court circuit MT",
                                                "Court circuit BT",
                                                "Désiquilibre",
                                                "Foudre",
                                                "Fuite d'huile",
                                                "Incendie",
                                                "Inconnue",
                                                "Mauvaise terre",
                                                "Poupée BT coupé",
                                                "Surcharge",
                                                "Tableau BT brulé",
                                                "Défaut de réparation",
                                                "Sinistre"
                                            ),
                                            "Cause d'avarie",
                                            {},
                                            true,
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        EmptyTextField(
                                            "Date d'avarie",
                                            "",
                                            10,
                                            {},
                                            Modifier
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        FileSection(
                                            "Fiche d'avarie",
                                            "",
                                            {},
                                            Modifier
                                        )
                                    }
                                }
                                if (motifEntr) {
                                    Spacer(modifier = Modifier.height(50.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        DropDown(
                                            listOf(),
                                            "Cause d'avarie",
                                            {},
                                            true,
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        EmptyTextField(
                                            "Date d'avarie",
                                            "",
                                            10,
                                            {},
                                            Modifier
                                        )
                                    }
                                }
                                if (motifTransfertInterDD) {
                                    Spacer(modifier = Modifier.height(50.dp))
                                    FileSection(
                                        "Bon de transfert",
                                        "",
                                        {},
                                        Modifier.fillMaxWidth()
                                    )
                                }
                                if (motifTransfertDDversGDC) {
                                    Spacer(modifier = Modifier.height(50.dp))
                                    FileSection(
                                        "Bon de commande",
                                        "",
                                        {},
                                        Modifier.fillMaxWidth()
                                    )
                                }
                                if (motifVente) {
                                    Spacer(modifier = Modifier.height(50.dp))
                                    FileSection(
                                        "PV CPR",
                                        "",
                                        {},
                                        Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(30.dp))
                                    FileSection(
                                        "Résolution du vente",
                                        "",
                                        {},
                                        Modifier.fillMaxWidth()
                                    )
                                }

                                Spacer(modifier = Modifier.height(50.dp))
                                FileSection(
                                    "Bon du mouvement",
                                    "",
                                    {},
                                    Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(50.dp))
                                var observation by remember { mutableStateOf("") }
                                Column {
                                    Text(
                                        "Observation",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(bottom = 10.dp)
                                    )
                                    TextField(
                                        observation,
                                        maxLines = 4,
                                        onValueChange = {
                                            if (observation.length < 200) {
                                                observation = it
                                            }
                                        },
                                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp))
                                            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(10.dp)),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.White,
                                            cursorColor = Color(0xff0073FF),
                                            focusedIndicatorColor = Color(0xff0073FF)
                                        ),
                                    )
                                }
                            }
                            VerticalScrollbar(rememberScrollbarAdapter(verticalScroll))
                        }
                        }
                    }
        if(closeConfirm){

            Window(onCloseRequest = {
                closeConfirm=false
            },
                resizable = false,
                state = rememberWindowState(
                    position = WindowPosition(500.dp,250.dp),
                    size = DpSize(500.dp,250.dp)
                ),icon = painterResource("images/sonelgaz.png"), title = "Confirmation"
            ){
                Column {
                    Text("Confirmation", fontSize = 25.sp, fontWeight = FontWeight.Medium, color = Theme.MAIN_BLUE, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Voulez vous vraiment quitter ?", fontSize = 18.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Row (verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .padding(horizontal = 5.dp)
                                .shadow(2.dp, RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .clickable {
                                    closeConfirm=false
                                }
                        ){
                            Text("Continuer", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Theme.MAIN_BLUE, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                        }
                        Spacer(modifier = Modifier.width(50.dp))
                        Row (verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .padding(horizontal = 5.dp)
                                .shadow(2.dp, RoundedCornerShape(10.dp))
                                .background(Color(0xff0073FF))
                                .clickable {
                                    addMvt=false
                                    closeConfirm=false
                                    window.isEnabled=true
                                }
                        ){
                            Text("Quitter", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                        }
                    }
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