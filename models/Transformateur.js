module.exports = (sequelize, DataTypes) => {

    const Transformateur = sequelize.define("transformateur", {
        marque: {
            type: DataTypes.STRING,
            allowNull: false
        },
        n_serie: {
            type: DataTypes.STRING,
            allowNull: false
        },
        tension: {
            type: DataTypes.STRING,
            allowNull: false
        },
        puissance: {
            type: DataTypes.STRING,
            allowNull: false
        }
        ,
        a_fabrication: {
            type: DataTypes.STRING,
        },
        fournisseur: {
            type: DataTypes.STRING,
        },
        // district: {
        //     type: DataTypes.STRING,
        //     allowNull:false,
        // },
        lieu_actuel: {
            type: DataTypes.STRING,
            allowNull:false,
        },
        // poste: {
        //     type: DataTypes.STRING,
        // },
        fiche_garantie: {
            type: DataTypes.STRING,
        },
        pv_d_essaie: {
            type: DataTypes.STRING,
        },
        plaque_signalitique: {
            type: DataTypes.STRING,
        }

    })

    return Transformateur
}