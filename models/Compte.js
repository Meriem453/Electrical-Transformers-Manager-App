module.exports = (sequelize, DataTypes) => {

    const Compte = sequelize.define("compte", {
        district: {
            type: DataTypes.STRING,
            allowNull: false
        },
        nom_utilisateur: {
            type: DataTypes.STRING,
            allowNull: false
        },
        type: {
            type: DataTypes.STRING,
            allowNull: false
        },
        nom: {
            type: DataTypes.STRING,
            allowNull: false
        },
        prenom: {
            type: DataTypes.STRING,
            allowNull: false
        },
        fonction: {
            type: DataTypes.STRING,
            allowNull: false
        },
        email: {
            type: DataTypes.STRING,
            allowNull: false
        },
        mdps: {
            type: DataTypes.STRING,
            allowNull: false
        },
    })
    return Compte
}