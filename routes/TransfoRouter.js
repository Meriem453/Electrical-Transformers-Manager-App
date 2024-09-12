// import controllers review, products
const transfoController = require('../controllers/TransformateurController.js')



// router
const router = require('express').Router()


// use routers
router.post('/transfo',  transfoController.addTransformateur)
router.get('/transfo',  transfoController.getAllTransfo)
router.put('/transfo',  transfoController.updateTransfo)






module.exports = router
