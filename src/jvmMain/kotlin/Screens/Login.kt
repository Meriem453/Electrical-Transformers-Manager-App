package Screens

import Auth
import Theme
import VIewModels.LoginVM
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
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

@Composable
fun Login(window: ComposeWindow,login:()->Unit){
    var user_name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val vm=LoginVM
    var error by remember { mutableStateOf(false) }
    Row (
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.MAIN_BLUE)
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(.6f)
                .background(Theme.BACKGROUND)
                .padding(50.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Image(painter = painterResource("images/sonelgaz.png"),"")
                Text("Sonelgaz", fontWeight = FontWeight.Medium, fontSize = 20.sp, modifier = Modifier.padding(horizontal = 20.dp))
            }
Column {
    OutlinedTextField(
        user_name,
        {user_name=it},
        label = {
            Text("Nom d'utilisateur", fontSize = 14.sp, modifier = Modifier.padding(horizontal = 20.dp))
        },
        trailingIcon = {
            Icon(painterResource("icons/person.svg"),"", tint = Theme.MAIN_BLUE)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Theme.MAIN_BLUE,
            unfocusedIndicatorColor = Color.Gray,
            focusedLabelColor = Theme.MAIN_BLUE,
            unfocusedLabelColor = Color.Gray
        ),
        maxLines = 1
    )
    Spacer(modifier = Modifier.height(50.dp))
    OutlinedTextField(
        password,
        {password=it},
        label = {
            Text("Mot de passe", fontSize = 14.sp, modifier = Modifier.padding(horizontal = 20.dp))
        },
        trailingIcon = {
            Icon(painterResource("icons/lock.svg"),"", tint = Theme.MAIN_BLUE)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Theme.MAIN_BLUE,
            unfocusedIndicatorColor = Color.Gray,
            focusedLabelColor = Theme.MAIN_BLUE,
            unfocusedLabelColor = Color.Gray
        ),
        maxLines = 1
    )
}


            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(bottom = 50.dp)
                    .shadow(2.dp, RoundedCornerShape(20.dp))
                    .background(Color(0xff0073FF))
                    .clickable {
                        vm.login(email = user_name,password)
                        if(Auth.currentUser!=null)
                        login()
                        else error=true
                    }
            ) {
                Text(
                    "Connexion",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)
                )
            }
            if(error) Text("Veuillez réessayer", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xffb70007))

        }
    }
}