module.exports = (sequelize, DataTypes) => {

    const Avarie = sequelize.define("avarie", {
        cause: {
            type: DataTypes.STRING,
            allowNull: false
        },
        date: {
            type: DataTypes.STRING,
            allowNull: false
        },
        fiche: {
            type: DataTypes.STRING,
            allowNull: false
        },
    })
    return Avarie
}