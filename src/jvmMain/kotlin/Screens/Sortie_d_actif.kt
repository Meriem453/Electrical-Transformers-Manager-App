package Screens

import Models.Transformateur
import Screens.Components.*
import VIewModels.transfo
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Sortie_d_actif() {
    Column(modifier = Modifier.fillMaxSize()) {
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
        }

        val horizontal_state = rememberScrollState()
        val vertical_state = rememberScrollState()

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxSize()
                .background(Color.White)
                .horizontalScroll(horizontal_state)
        ) {
            val list = listOf(
                "Marque",
                "N° série",
                "Tension",
                "Puissance",
                "A. fabrication",
                "Fournisseur",
                "Prix d'aquisition",
                "Id bien",
                "Date du reforme",
                "Date du vente",
                "Résolution CA",
                "Résolution du vente"
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                list.forEachIndexed { position, item ->
                    Column {
                        Text(item, fontSize = 17.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
                        Column(modifier = Modifier.verticalScroll(vertical_state)) {
                            transfo.forEachIndexed { pos, transfo ->
                                Box(
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        when (position) {
                                            0 -> transfo.marque
                                            1 -> transfo.n_serie
                                            2 -> transfo.tension
                                            3 -> transfo.puissance
                                            4 -> transfo.a_fabrication
                                            5 -> transfo.fournisseur
                                            6 -> "100000 DA"
                                            7->"123456"
                                            8 -> "4/5/2024"
                                            9 -> "4/5/2024"
                                            10 -> "zerty.pdf"
                                            11 -> "zerty.pdf"
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
        HorizontalScrollbar(rememberScrollbarAdapter(horizontal_state))
    }
}