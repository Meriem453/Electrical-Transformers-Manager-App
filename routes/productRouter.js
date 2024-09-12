// import controllers review, products
const productController = require('../controllers/TransformateurController.js')



// router
const router = require('express').Router()


// use routers
router.post('/addProduct',  productController.addProduct)

router.get('/allProducts', productController.getAllProducts)

router.post('/editProduct', productController.updateProduct)

router.delete('/deleteProduct', productController.deleteProduct)




module.exports = router
