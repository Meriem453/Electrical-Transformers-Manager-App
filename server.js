const express = require('express')
const cors = require('cors')


const app = express()

// middleware

app.use(express.json())

app.use(express.urlencoded({ extended: true }))


// routers
const transfoRouter = require('./routes/TransfoRouter.js')
const mvtRouter=require('./routes/MvtRouter.js')
app.use('/transfo', transfoRouter)
app.use('/mvt', mvtRouter)


//port

const PORT = process.env.PORT || 8080

//server

app.listen(PORT, () => {
    console.log(`server is running on port ${PORT}`)
})