module.exports = (sequelize, DataTypes) => {

    const Entretien = sequelize.define("entretien", {
        cause_entretien: {
            type: DataTypes.STRING,
            allowNull: false
        },
        date_entretien: {
            type: DataTypes.STRING,
            allowNull: false
        },
    })
    return Entretien
}