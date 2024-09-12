const db = require('../models')



// create main Model
const Transformateur = db.transformateur


const { sequelize } = require('../models'); // Import the Sequelize instance
const { QueryTypes } = require('sequelize');
const {upload} = require("../middlewares/filesMiddleware");
const fs = require('fs');


const uploadFiles = upload.fields([
    { name: 'fiche_garantie', maxCount: 1 },
    { name: 'pv_d_essaie', maxCount: 1 },
    { name: 'plaque_signalitique', maxCount: 1 }
]);

const addTransformateur = async (req, res) => {
    try {
        // Middleware to handle file uploads
        uploadFiles(req, res, async (err) => {
            if (err) {
                return res.status(400).send('Error uploading files: ' + err.message);
            }

            // Extract form fields and files
            const { marque, n_serie, tension, puissance, a_fabrication, fournisseur, district, lieu_actuel, poste } = req.body;
            const { fiche_garantie, pv_d_essaie, plaque_signalitique } = req.files;

            // Extract file paths (or set to null if not provided)
            const filePathFicheGarantie = fiche_garantie ? fiche_garantie[0].path : null;
            const filePathPvDEssaie = pv_d_essaie ? pv_d_essaie[0].path : null;
            const filePathPlaqueSignalitique = plaque_signalitique ? plaque_signalitique[0].path : null;

            // Create Transformateur record
            let newTransfo = await Transformateur.create({
                marque,
                n_serie,
                tension,
                puissance,
                a_fabrication,
                fournisseur,
                district,
                lieu_actuel,
                poste,
                fiche_garantie: filePathFicheGarantie,
                pv_d_essaie: filePathPvDEssaie,
                plaque_signalitique: filePathPlaqueSignalitique
            });

            if (!newTransfo) {
                return res.status(401).send('Error creating transfo.');
            }

            res.status(200).json(newTransfo);
        });
    } catch (error) {
        console.error('Error creating transfo:', error);
        res.status(500).send('Internal Server Error');
    }
};

const getAllTransfo = async (req, res) => {
    try {
        let allTransfos= await Transformateur.findAll()
        if(!allTransfos){
            res.status(401).send('Error getting transfos')
        }else{
            res.status(200).json(allTransfos);
        }

    }
    catch (error) {
        console.error('Error getting transfos:', error);
        res.status(500).send('Internal Server Error');
    }
};
const updateTransfo = async (req, res) => {
    try {
        uploadFiles(req, res, async (err) => {
            if (err) {
                return res.status(400).send('Error uploading files: ' + err.message);
            }


            const {
                id,
                marque,
                n_serie,
                tension,
                puissance,
                a_fabrication,
                fournisseur,
                district,
                lieu_actuel,
                poste
            } = req.body;
            const {fiche_garantie, pv_d_essaie, plaque_signalitique} = req.files;


            const transfo = await Transformateur.findByPk(id);
            if (!transfo) {
                return res.status(404).send('Transfo not found');
            }

            // Delete old files if new files are provided
            const deleteFile = (filePath) => {
                if (filePath && fs.existsSync(filePath)) {
                    fs.unlinkSync(filePath);
                }
            };

            if (fiche_garantie) {
                deleteFile(transfo.fiche_garantie);
            }
            if (pv_d_essaie) {
                deleteFile(transfo.pv_d_essaie);
            }
            if (plaque_signalitique) {
                deleteFile(transfo.plaque_signalitique);
            }

            const filePathFicheGarantie = fiche_garantie ? fiche_garantie[0].path : null;
            const filePathPvDEssaie = pv_d_essaie ? pv_d_essaie[0].path : null;
            const filePathPlaqueSignalitique = plaque_signalitique ? plaque_signalitique[0].path : null;

            const [affectedRows] = await Transformateur.update({
                marque: marque,
                n_serie: n_serie,
                tension: tension,
                puissance: puissance,
                a_fabrication: a_fabrication,
                fournisseur: fournisseur,
                lieu_actuel: lieu_actuel,
                district: district,
                poste: poste,
                fiche_garantie: filePathFicheGarantie,
                pv_d_essaie: filePathPvDEssaie,
                plaque_signalitique: filePathPlaqueSignalitique
            }, {
                where: {id: id}
            });


            if (affectedRows > 0) {
                res.status(200).send('Transfo updated successfully');
            } else {
                res.status(404).send('Transfo not found');
            }
        });
    } catch (error) {
        console.error('Error updating transfo:', error);
        res.status(500).send('Internal Server Error');
    }
}



module.exports = {
    addTransformateur,
    getAllTransfo,
    updateTransfo
}