package VIewModels

import Auth
import Models.User

object LoginVM {
    val users= listOf(
        User("admin@gmail.com","123456","Admin"),
        User("gestion_transfo@gmail.com","123456","Gestionnaire de transformateurs"),
        User("visiteur@gmail.com","123456","Visiteur"),
    )
    fun login(email:String,password:String){
        Auth.currentUser=users.find { email==it.email && password==it.password }

    }
}