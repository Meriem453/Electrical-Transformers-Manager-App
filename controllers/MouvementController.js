const db = require('../models')



// create main Model
const Mouvement = db.mouvement
const Avarie = db.avarie
const Entreien = db.entretien
const Vente = db.vente



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
            const { n_bon,date_mvt,date_saisie,date_bon,motif,distination,observation,preciser,poste,date_d_entree,cause_avarie,date_avarie,cause_entretien,date_entretien,transformateur } = req.body;
            const { bon_mvt, bon_de_transfert, bon_de_commande,fiche_avarie,pv_cpr,resolution_du_vente } = req.files;

            // Extract file paths (or set to null if not provided)
            const filePathbon_mvt = bon_mvt ? bon_mvt[0].path : null;
            const filePathbon_de_transfert = bon_de_transfert ? bon_de_transfert[0].path : null;
            const filePathbon_de_commande = bon_de_commande ? bon_de_commande[0].path : null;
            const filePathfiche_avarie = fiche_avarie ? fiche_avarie[0].path : null;
            const filePathpv_cpr = pv_cpr ? pv_cpr[0].path : null;
            const filePathresolution_du_vente = resolution_du_vente ? resolution_du_vente[0].path : null;



            switch (motif) {
                case "Avarie":
                    let newAvarie = await Avarie.create({
                        cause_avarie,
                        date_avarie,
                        filePathfiche_avarie
                    });
                    // Create Transformateur record
                    let newMouvement1 = await Mouvement.create({
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
                        bon_de_commande: filePathbon_de_commande,
                        transformateur: transformateur,
                        avarie: newAvarie.id,

                    });

                    if (!newMouvement1 || !newAvarie) {
                        return res.status(401).send('Error creating mvmnt.');
                    }
                    break;
                case "Entretien":
                    let newEntretien = await Entreien.create({
                        filePathpv_cpr,
                        filePathresolution_du_vente,
                    });
                    // Create Transformateur record
                    let newMouvement2 = await Mouvement.create({
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
                        bon_de_commande: filePathbon_de_commande,
                        transformateur: transformateur,
                        entretien: newEntretien.id
                    });

                    if (!newMouvement2 || !newEntretien) {
                        return res.status(401).send('Error creating mvmnt.');
                    }
                    break;
                case "Vente":
                    let newVente = await Vente.create({
                        cause_entretien,
                        date_entretien,
                    });
                    // Create Transformateur record
                    let newMouvement3 = await Mouvement.create({
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
                        transformateur: transformateur,
                        bon_de_transfert: filePathbon_de_transfert,
                        bon_de_commande: filePathbon_de_commande,
                        vente: newVente.id
                    });

                    if (!newMouvement3 || !newVente) {
                        return res.status(401).send('Error creating mvmnt.');
                    }
                    break;
                default:
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
                        bon_de_commande: filePathbon_de_commande,
                        transformateur: transformateur,
                    });
            }
        });
    } catch (error) {
        console.error('Error creating mvmnt:', error);
        res.status(500).send('Internal Server Error');
    }
};


const getAllMvt = async (req, res) => {
    try {
        let allMvt= await Mouvement.findAll()
        if(!allMvt){
            res.status(401).send('Error getting mvt')
        }else{
            res.status(200).json(allMvt);
        }

    }
    catch (error) {
        console.error('Error getting mvt:', error);
        res.status(500).send('Internal Server Error');
    }
};
module.exports = {
    addMouvement,
    getAllMvt
}
