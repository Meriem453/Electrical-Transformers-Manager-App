package Screens.Components

import Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Refresh(
    onClick:()->Unit
){
    Box(
        modifier = Modifier
            .size(90.dp)
            .padding(20.dp)
            .clip(CircleShape)
            .shadow(1.dp, CircleShape)
            .background(Color.White)
            .clickable {
                       onClick()
            }

        ,
        contentAlignment = Alignment.Center

    ){
        Icon(painter = painterResource("icons/refresh.svg"),"", tint = Theme.MAIN_BLUE, modifier = Modifier.padding(10.dp))
    }
}