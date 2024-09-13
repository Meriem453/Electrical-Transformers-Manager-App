const db = require('../models')



// create main Model
const Mouvement = db.mouvement


const { sequelize } = require('../models'); // Import the Sequelize instance
const { QueryTypes } = require('sequelize');
const {upload} = require("../middlewares/filesMiddleware");
const fs = require('fs');


const uploadFiles = upload.fields([
    { name: 'bon_mvt', maxCount: 1 },
    { name: 'bon_de_transfert', maxCount: 1 },
    { name: 'bon_de_commande', maxCount: 1 },
    { name: 'fiche_avarie', maxCount: 1 },
    { name: 'pv_cpr', maxCount: 1 },
    { name: 'resolution_du_vente', maxCount: 1 },
]);

const addMouvement = async (req, res) => {
    try {
        // Middleware to handle file uploads
        uploadFiles(req, res, async (err) => {
            if (err) {
                return res.status(400).send('Error uploading files: ' + err.message);
            }

            // Extract form fields and files
            const { n_bon,date_mvt,date_saisie,date_bon,motif,distination,observation,preciser,poste,date_d_entree } = req.body;
            const { bon_mvt, bon_de_transfert, bon_de_commande,fiche_avarie,pv_cpr,resolution_du_vente } = req.files;

            // Extract file paths (or set to null if not provided)
            const filePathbon_mvt = bon_mvt ? bon_mvt[0].path : null;
            const filePathbon_de_transfert = bon_de_transfert ? bon_de_transfert[0].path : null;
            const filePathbon_de_commande = bon_de_commande ? bon_de_commande[0].path : null;
            const filePathfiche_avarie = fiche_avarie ? fiche_avarie[0].path : null;
            const filePathpv_cpr = pv_cpr ? pv_cpr[0].path : null;
            const filePathresolution_du_vente = resolution_du_vente ? resolution_du_vente[0].path : null;


            // Create Transformateur record
            let newMouvement = await Mouvement.create({
                n_bon,
                date_mvt,
                date_saisie,
                date_bon,
                motif,
                distination,
                observation,
                preciser,
                poste,
                date_d_entree,
                bon_mvt: filePathbon_mvt,
                bon_de_transfert: filePathbon_de_transfert,
                bon_de_commande: filePathbon_de_commande
            });

            if (!newMouvement) {
                return res.status(401).send('Error creating mvmnt.');
            }

            res.status(200).json(newMouvement);

            switch (motif) {
                case "Avarie":
                    //TODO("here")
                    break;
                case value2:
                    // code to be executed if expression === value2
                    break;
                // more cases...
                default:
                // code to be executed if expression doesn't match any case
            }
        });
    } catch (error) {
        console.error('Error creating mvmnt:', error);
        res.status(500).send('Internal Server Error');
    }
};
