// import controllers review, products
const mvtController = require('../controllers/MouvementController.js')



// router
const mvtRouter = require('express').Router()


// use routers
mvtRouter.post('/',  mvtController.addMouvement)
mvtRouter.get('/',  mvtController.getAllMvt)







module.exports = mvtRouter