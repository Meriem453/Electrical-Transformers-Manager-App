module.exports = (sequelize, DataTypes) => {

    const Vente = sequelize.define("vente", {
        pv_cpr: {
            type: DataTypes.STRING,
            allowNull: false
        },
        resolution_du_vente: {
            type: DataTypes.STRING,
            allowNull: false
        }
    })
    return Vente
}