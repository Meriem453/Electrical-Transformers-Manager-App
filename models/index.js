const dbConfig = require('../config/dbConfig.js');

const {Sequelize, DataTypes} = require('sequelize');

const sequelize = new Sequelize(
    dbConfig.DB,
    dbConfig.USER,
    dbConfig.PASSWORD, {
        host: dbConfig.HOST,
        dialect: dbConfig.dialect,
        operatorsAliases: false,

        pool: {
            max: dbConfig.pool.max,
            min: dbConfig.pool.min,
            acquire: dbConfig.pool.acquire,
            idle: dbConfig.pool.idle

        }
    }
)

sequelize.authenticate()
.then(() => {
    console.log('connected..')
})
.catch(err => {
    console.log('Error'+ err)
})

const db = {}

db.Sequelize = Sequelize
db.sequelize = sequelize

db.transformateur = require('./Transformateur.js')(sequelize, DataTypes)
db.avarie = require('./Avarie.js')(sequelize, DataTypes)
db.compte = require('./Compte.js')(sequelize, DataTypes)
db.district = require('./DIstrict.js')(sequelize, DataTypes)
db.entretien = require('./Entretien.js')(sequelize, DataTypes)
db.mouvement = require('./Mouvement.js')(sequelize, DataTypes)
db.poste = require('./Poste.js')(sequelize, DataTypes)
db.vente = require('./Vente.js')(sequelize, DataTypes)



db.sequelize.sync({ force: false })
.then(() => {
    console.log('yes re-sync done!')
})





db.mouvement.belongsTo(db.transformateur,{
    foreignKey:'transformateur',
    as:'transformateur_',
    allowNull:false,
    onDelete:"CASCADE",
    onUpdate:"CASCADE",
})

db.mouvement.belongsTo(db.avarie,{
    foreignKey:'avarie',
    as:'avarie_',
    allowNull:false,
    onDelete:"SET NULL",
    onUpdate:"CASCADE",
})

db.mouvement.belongsTo(db.entretien,{
    foreignKey:'entretien',
    as:'entretien_',
    allowNull:false,
    onDelete:"SET NULL",
    onUpdate:"CASCADE",
})

module.exports = db