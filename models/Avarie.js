module.exports = (sequelize, DataTypes) => {

    const Avarie = sequelize.define("avarie", {
        cause_avarie: {
            type: DataTypes.STRING,
            allowNull: false
        },
        date_avarie: {
            type: DataTypes.STRING,
            allowNull: false
        },
        fiche_avarie: {
            type: DataTypes.STRING,
            allowNull: false
        },
    })
    return Avarie
}