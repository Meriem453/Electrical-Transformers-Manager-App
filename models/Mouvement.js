module.exports = (sequelize, DataTypes) => {

    const Mouvement = sequelize.define("mouvement", {
        n_bon: {
            type: DataTypes.STRING,
            allowNull: false
        },
        date_mvt: {
            type: DataTypes.STRING,
        },
        date_saisie: {
            type: DataTypes.STRING,
        },
        date_bon: {
            type: DataTypes.STRING,
        },
        motif: {
            type: DataTypes.STRING,
            allowNull: false
        },
        distination: {
            type: DataTypes.STRING,
            allowNull: false
        },
        observation: {
            type: DataTypes.STRING,
            allowNull: false
        },
        // transformateur: {
        //     type: DataTypes.INT,
        //     allowNull: false
        // },
        preciser: {
            type: DataTypes.STRING,
        },
        // poste:{
        //     type:DataTypes.STRING
        // },
        date_d_entree: {
            type: DataTypes.STRING,
        },
        // avarie: {
        //     type: DataTypes.INT,
        // },
        // entretien: {
        //     type: DataTypes.STRING,
        // },
        bon_mvt: {
            type: DataTypes.STRING,
            allowNull: false
        },
        bon_de_transfert: {
            type: DataTypes.STRING,
        },
        bon_de_commande: {
            type: DataTypes.STRING,
        },
    })
    return Mouvement
}

