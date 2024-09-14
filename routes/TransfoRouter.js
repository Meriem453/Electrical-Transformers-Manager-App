const transfoController = require('../controllers/TransformateurController.js')



const transfoRouter = require('express').Router()


transfoRouter.post('/',  transfoController.addTransformateur)
transfoRouter.get('/',  transfoController.getAllTransfo)
transfoRouter.put('/',  transfoController.updateTransfo)






module.exports = transfoRouter
