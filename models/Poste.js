module.exports = (sequelize, DataTypes) => {

    const Poste = sequelize.define("poste", {
        designation: {
            type: DataTypes.STRING,
            allowNull: false
        },
        numero: {
            type: DataTypes.STRING,
            allowNull: false
        },
        nature: {
            type: DataTypes.STRING,
            allowNull: false
        },
        // district: {
        //     type: DataTypes.STRING,
        //     allowNull: false
        // },

    })
    return Poste
}
