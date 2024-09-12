module.exports = (sequelize, DataTypes) => {

    const District = sequelize.define("district", {
        centre: {
            type: DataTypes.STRING,
            allowNull: false
        },
        district: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true
        },
        init: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true
        },
        code_agence: {
            type: DataTypes.STRING,
            allowNull: false,
            unique:true
        },
        code_centre: {
            type: DataTypes.STRING,
            allowNull: false,
            unique:true
        },

    })
    return District
}
