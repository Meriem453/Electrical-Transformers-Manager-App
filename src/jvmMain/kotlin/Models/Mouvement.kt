package Models

data class Mouvement(
    val n_bon:String,
    val date_mvt:String,
    val date_saisie:String,
    val motif:String,
    val marque:String,
    val n_serie_transfo:String,
    val puissance:String,
    val tension:String,
    val annee_de_fab:String,
    val fournisseur:String,
    val destination:String,
    val district:String,
    val poste:String,
    val bon_mvt:String

)
