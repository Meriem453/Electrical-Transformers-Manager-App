module.exports = (sequelize, DataTypes) => {

    const Entretien = sequelize.define("entretien", {
        cause: {
            type: DataTypes.STRING,
            allowNull: false
        },
        date: {
            type: DataTypes.STRING,
            allowNull: false
        },
    })
    return Entretien
}