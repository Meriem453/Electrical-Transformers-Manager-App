package Screens
import Components.*
import Models.Transformateur
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState

val transfo= listOf(
    Transformateur("WWWWWWWWWW","1","10","1000","2003","kkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("W","111111111111111111111111111111","1000","1000","2003","kkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWW","11111111111111111111","1000","1000","2003","kk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWW","1111111","1000","1000","2003","k","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
    Transformateur("WWWWWWWWWW","111111111111111111111111111111","1000","1000","2003","kkkkkkkkkk","Exploitation","El-Harrach","2222222222","gggggggggg.pdf","gggggggggg.pdf","manquante"),
)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Transfo(window: ComposeWindow) {
    var filterTransfo by remember { mutableStateOf(false) }
    var addTransfo by remember { mutableStateOf(false) }
    var editTransfo by remember { mutableStateOf(false) }
    var currentTransfo: Transformateur? by remember { mutableStateOf(null) }

Column (modifier = Modifier.fillMaxSize()){
    var search by remember {
        mutableStateOf("")
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        SearchBar(
            hint = "N° de série",
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
            filterTransfo = true
            window.isEnabled = false
        }
        Button(
            icon = "icons/add.svg",
            text = "Nouveau",
            tintColor = Color.White,
            background = Color(0xff0073FF)
        ) {
            addTransfo = true
            window.isEnabled = false
        }
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
    var checkedItem by remember {
        mutableStateOf(0)
    }
CheckGrp(
    isChecked = checkedItem==0,
    onChecked = {checkedItem=0},
    "Exploitation"
)
        DropDown(
            listOf("El-Harrach","Rouiba"),
            "District",
            {},
            checkedItem==0
        )
        CheckGrp(
            isChecked = checkedItem==1,
            onChecked = {checkedItem=1},
            "Stock"
        )
        CheckGrp(
            isChecked = checkedItem==2,
            onChecked = {checkedItem=2},
            "Platform DD"
        )
        CheckGrp(
            isChecked = checkedItem==3,
            onChecked = {checkedItem=3},
            "Atelier de réparation"
        )
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
        val list= listOf(
            "Marque","N° série","Tension","Puissance","A. fabrication","Fournisseur","Lieu actuel","District","Poste","Fiche garantie","PV d'éssaie","Plaque signalitique"
        )

        Row(modifier = Modifier.fillMaxWidth()){
list.forEachIndexed{position,item->
    Column {
        Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
        Column(modifier = Modifier.verticalScroll(vertical_state)) {
            var hover by remember { mutableStateOf(false) }
            transfo.forEachIndexed{pos, transfo->
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
                        currentTransfo=transfo
                        editTransfo=true
                        window.isEnabled=false
                    }
                ) {
                Text(
                    when(position){
                        0->transfo.marque
                        1-> transfo.n_serie
                        2->transfo.tension
                        3->transfo.puissance
                        4->transfo.a_fabrication
                        5->transfo.fournisseur
                        6->transfo.lieu_actuel
                        7->transfo.district
                        8->transfo.poste
                        9->transfo.fiche_garantie
                        10->transfo.pv_d_essaie
                        11->transfo.plaque_signalitique
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
    if(editTransfo){
        Window(onCloseRequest = {
            window.isEnabled=true
            editTransfo=false},
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp,200.dp),
                size = DpSize(1000.dp,700.dp)
            ),icon = painterResource("images/sonelgaz.png"), title = "Modifier un transformateur"
            ){
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
                   Text("Modifier un transformateur", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                   Box(contentAlignment = Alignment.Center,
                       modifier = Modifier
                           .clip(RoundedCornerShape(10.dp))
                           .padding(horizontal = 5.dp)
                           .shadow(2.dp, RoundedCornerShape(20.dp))
                           .background(Color(0xff0073FF))
                           .clickable { editTransfo = true }
                   ) {
                       Text(
                           "Sauvgarder",
                           fontSize = 18.sp,
                           fontWeight = FontWeight.Medium,
                           color = Color.White,
                           modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)
                       )
                   }
               }
               Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                   EmptyTextField(
                       "Marque",
                       currentTransfo!!.marque,
                       10,
                       {},
                       Modifier.fillMaxWidth(.4f).padding(end = 10.dp)
                   )
                   EmptyTextField(
                       "N° série",
                       currentTransfo!!.n_serie,
                       25,
                       {},
                       Modifier.fillMaxWidth()
                   )
               }
               Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                   EmptyTextField(
                       "Tension",
                       currentTransfo!!.tension,
                       10,
                       {},
                       Modifier.padding(end = 10.dp)
                   )
                   EmptyTextField(
                       "Puissance",
                       currentTransfo!!.puissance,
                       10,
                       {},
                       Modifier.padding(end = 10.dp)
                   )
                   EmptyTextField(
                       "Année de fabrication",
                       currentTransfo!!.a_fabrication,
                       4,
                       {},
                       Modifier
                   )
               }
               Spacer(modifier = Modifier.height(20.dp))
               EmptyTextField(
                   "Fournisseur",
                   currentTransfo!!.fournisseur,
                   30,
                   {},
                   Modifier.fillMaxWidth()
               )
               Spacer(modifier = Modifier.height(20.dp))
               FileSection(
                   "Fiche garantie",
                   currentTransfo!!.fiche_garantie,
                   {},
                   Modifier.fillMaxWidth()
               )
               Spacer(modifier = Modifier.height(20.dp))
               FileSection(
                   "Rapport PV",
                   currentTransfo!!.pv_d_essaie,
                   {},
                   Modifier.fillMaxWidth()
               )
               Spacer(modifier = Modifier.height(20.dp))
               FileSection(
                   "Plaque signalitique",
                   currentTransfo!!.plaque_signalitique,
                   {},
                   Modifier.fillMaxWidth()
               )
               Spacer(modifier = Modifier.height(50.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .padding(horizontal = 5.dp)
                            .shadow(2.dp, RoundedCornerShape(10.dp))
                            .background(Color(0xff0073FF))
                            .clickable { editTransfo = true }
                    ) {
                        Text(
                            "Historique des mouvements",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)
                        )
                    }
                }
           }
                VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
           }
        }
    }
    if(addTransfo){
        Window(onCloseRequest = {
            window.isEnabled=true
            addTransfo=false},
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp,200.dp),
                size = DpSize(1000.dp,700.dp)
            ),icon = painterResource("images/sonelgaz.png"), title = "Créer un transformateur"
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
                        Text("Créer un transformateur", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 5.dp)
                                .shadow(2.dp, RoundedCornerShape(20.dp))
                                .background(Color(0xff0073FF))
                                .clickable { editTransfo = true }
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
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                        EmptyTextField(
                            "Marque",
                            "",
                            10,
                            {},
                            Modifier.fillMaxWidth(.4f).padding(end = 10.dp)
                        )
                        EmptyTextField(
                            "N° série",
                            "",
                            25,
                            {},
                            Modifier.fillMaxWidth()
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                        EmptyTextField(
                            "Tension",
                            "",
                            10,
                            {},
                            Modifier.padding(end = 10.dp)
                        )
                        EmptyTextField(
                            "Puissance",
                            "",
                            10,
                            {},
                            Modifier.padding(end = 10.dp)
                        )
                        EmptyTextField(
                            "Année de fabrication",
                            "",
                            4,
                            {},
                            Modifier
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    EmptyTextField(
                        "Fournisseur",
                       "",
                        30,
                        {},
                        Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    FileSection(
                        "Fiche garantie",
                        "",
                        {},
                        Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    FileSection(
                        "Rapport PV",
                        "",
                        {},
                        Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    FileSection(
                        "Plaque signalitique",
                        "",
                        {},
                        Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                }
                VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
            }
        }
            }
    if(filterTransfo){
        Window(onCloseRequest = {
            window.isEnabled=true
            filterTransfo=false},
            resizable = false,
            state = rememberWindowState(
                position = WindowPosition(500.dp,200.dp),
                size = DpSize(1000.dp,500.dp)
            ),icon = painterResource("images/sonelgaz.png"), title = "Filtrer les transformateurs"
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
                        Text("Filtrer les transformateurs", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 5.dp)
                                .shadow(2.dp, RoundedCornerShape(20.dp))
                                .background(Color(0xff0073FF))
                                .clickable { editTransfo = true }
                        ) {
                            Text(
                                "Filtrer",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                            )
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                        EmptyTextField(
                            "Marque",
                            "",
                            10,
                            {},
                            Modifier.fillMaxWidth(.4f).padding(end = 10.dp)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        EmptyTextField(
                            "Tension",
                            "",
                            10,
                            {},
                            Modifier.padding(end = 10.dp)
                        )
                        EmptyTextField(
                            "Puissance",
                            "",
                            10,
                            {},
                            Modifier.padding(end = 10.dp)
                        )
                        EmptyTextField(
                            "Année de fabrication",
                            "",
                            4,
                            {},
                            Modifier
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)){
                        DropDown(
                            listOf("El-Harrach","Rouiba"),
                            "District",
                            {},
                            true,
                            Modifier.padding(top = 28.dp)
                        )
                        EmptyTextField(
                            "Poste",
                            "",
                            10,
                            {},
                            Modifier.padding(start = 10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(50.dp))
                }
                VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
            }
        }
    }
    }



