const db = require('../models')



// create main Model
const Product = db.products
const Category=db.category
const Command=db.command
const ExpDate=db.expdate
const ProductGroup=db.productgroup
const Supplier=db.supplier

const { sequelize } = require('../models'); // Import the Sequelize instance
const { QueryTypes } = require('sequelize');
// main work

// 1. create product



const addProduct = async (req, res) => {
    try {
        const { name, price, quantity, benefit,barcode,supplier} = req.body;

        const query = `
            INSERT INTO products (name, price, quantity, benefit,barcode,supplier)
            VALUES (:name, :price, :quantity, :benefit,:barcode,:supplier)
        `;

        const values = {name, price, quantity, benefit,barcode,supplier };

        // Execute the raw SQL query
        const [product] = await sequelize.query(query, {
            replacements: values,
            type: QueryTypes.INSERT,
            raw: true,
        });

        console.log('Product:', product);

        res.status(200).json({ product }); // Send the product data as JSON
    } catch (error) {
        console.error('Error adding product:', error);
        res.status(500).send('Internal Server Error');
    }
};







// 2. get all products

const getAllProducts = async (req, res) => {
    try {
        const query = 'SELECT * FROM products';
        const products = await sequelize.query(query, { type: sequelize.QueryTypes.SELECT });

        res.status(200).send(products);
        console.log(products)
    } catch (error) {
        console.error('Error fetching products:', error);
        res.status(500).send('Internal Server Error');
    }
};

// 3. get single product
const getProductOfSupp = async (req, res) => {
    try {
        const id = req.query.id;
        const query = 'SELECT * FROM products WHERE supplier = :id';
        const product = await sequelize.query(query, {
            type: sequelize.QueryTypes.SELECT,
            replacements: { id: id },
        });

        if (product.length === 0) {
            res.status(404).send('Product not found');
        } else {
            res.status(200).send(product);
        }
    } catch (error) {
        console.error('Error fetching product:', error);
        res.status(500).send('Internal Server Error');
    }

}

const getOneProduct = async (req, res) => {

    try {
        const id = req.params.id;
        const query = 'SELECT * FROM products WHERE id = :id';
        const product = await sequelize.query(query, {
            type: sequelize.QueryTypes.SELECT,
            replacements: { id: id },
        });

        if (product.length === 0) {
            res.status(404).send('Product not found');
        } else {
            res.status(200).send(product[0]);
        }
    } catch (error) {
        console.error('Error fetching product:', error);
        res.status(500).send('Internal Server Error');
    }

}

// 4. update Product

const updateProduct = async (req, res) => {
    try {

        const updatedData = req.body;

        const query = `
            UPDATE products
            SET name = ?, price = ?, quantity = ?, benefit = ?,barecode = ?
            WHERE id = ?
        `;

        const [rowsUpdated, _] = await sequelize.query(query, {
            replacements: [updatedData.name, updatedData.price, updatedData.quantity, updatedData.benefit,updatedData.barecode, updatedData.id],
            type: sequelize.QueryTypes.UPDATE,
            returning: true,
        });

        if (rowsUpdated === 0) {
            res.status(404).send('Product not found');
        } else {
            res.status(200).send('Product updated successfully');
        }
    } catch (error) {
        console.error('Error updating product:', error);
        res.status(500).send('Internal Server Error');
    }
}

// 5. delete product by id

const deleteProduct = async (req, res) => {

    try {
        const id = req.query.id;


        const deleteProductsQuery = 'DELETE FROM products WHERE id = ?';
        const rowsDeleted=await sequelize.query(deleteProductsQuery, {
            replacements: [id],
            type: sequelize.QueryTypes.DELETE,
        });

        if (rowsDeleted === 0) {
            res.status(404).send('Product not found');
        } else {
            res.status(200).send('Product is deleted!');
        }
    } catch (error) {
        console.error('Error deleting product:', error);
        res.status(500).send('Internal Server Error');
    }
}

module.exports = {
    getProductOfSupp,
    addProduct,
    getAllProducts,
    updateProduct,
    deleteProduct,
}