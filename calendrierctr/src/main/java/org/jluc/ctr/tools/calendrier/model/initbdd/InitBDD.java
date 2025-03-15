package org.jluc.ctr.tools.calendrier.model.initbdd;

public class InitBDD {

    public static String initDemandeurs() {
        StringBuilder strB = new StringBuilder();
        strB.append("INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('798256e1-5468-4e46-a4be-dbac41be9455', 'CTD 22', '03 22 00 00');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('3165e2bb-7c30-4f3c-a9d8-bcdc6e34f23c', 'CTD 29', '03 29 00 00');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('07cae1dc-2b97-4841-b29f-5195454b4dc0', 'CTD 35', '03 35 00 00');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('d96a36b2-24fd-4a9a-a6c7-e65af3a693ee', 'CTD 44', '03 44 00 00');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('f988c03e-84ab-4334-a9fd-4efc192d1325', 'CTD 49', '03 49 00 00');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('23c9ae92-a232-4f3f-af91-ad392dff6570', 'CTD 53', '03 53 00 00');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('a27d4e00-c929-47b7-8ae5-8a66eb1b2f51', 'CTD 56', '03 56 00 00');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('e1a934f5-c8a1-41e1-bbb7-f68df31cf4d5', 'CTD 72', '03 72 00 00');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('95715dc9-cfdf-49da-ba4f-43cd931555ec', 'CTD 85', '03 85 00 00');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('064fe066-030e-453f-8ea5-9b5317989343', 'CAP', '03 22 01 35');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('767b7d39-dcf4-417f-bf17-cd8fa462b9e6', 'CIP GLENANS', '03 29 00 07');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('8c066929-3fba-49d5-86d4-6fbd28922628', 'CAMARET', '03 29 01 41');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('00187198-10da-4bf2-a69b-c15827c1f6d2', 'SUBEVASION', '03 85 023 C');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('fc18a85e-2d7a-4462-9642-36aabd94d052', 'MAITAI', '03 49 055 C');"
                + "INSERT INTO Demandeurs (UUID, Name, NumeroStructure) VALUES ('5628b08d-4a2b-497c-9069-b8bf6f4e64e1', 'CTR', '03 00 00 00');");
        return strB.toString();
    }

    public static String initTypesEvenements() {
        StringBuilder strB = new StringBuilder();
        strB.append("INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('b78a75e9-da16-48db-ad63-83fb81e2ba92','Stage final GP',1,'Stage GP-N4 (Information)');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('6dd89a9e-6042-49e8-965c-0ac5e5d58072','Stage Initial Initiateur',2,'Déclaration de stage initial Initiateur');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('9e353bf2-ca63-4596-9afc-2dc00bb17c71','TSI',3,'Déclaration de stage TSI');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('b5798ee6-6478-4159-a23c-aa661f32de08','Examen Initiateur',2,'Déclaration d''examen Initiateur');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('d46e537b-66b4-4a7a-99b1-0046de1a613e','Examen GP',1,'Déclaration d''examen GP-N4');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('8e721101-6bd1-49c3-a47c-fc7f33ac504a','Module 6-20',2,'Demande de module complémentaire 6-20m');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('cd94785b-7c9b-472c-bd65-4c9440c0cf45','Module 20-40',2,'Demande de module complémentaire 20-40m');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('ab153104-b6de-4511-a20d-543c89db3a8a','Stage Initial MF1',4,'Stage Initial MF1 (CTR-Délégation)');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('b9e1e075-62c8-408b-94ca-c069b7f3e753','Stage final MF1',4,'Stage Final MF1 (CTR)');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('d9ce67af-abc8-4cf2-8bb9-f120bed69888','Examen MF1',4,'Examen MF1 (CTR)');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('ca6ec215-41fb-4e01-a719-8ae05c6d8fd9','TIV Initial',6,'TIV');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('57cca574-481a-4c14-ac73-8ef0d21994f2','TIV Recyclage',6,'TIV Recyclage');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('eefb4eec-4124-4a83-89ab-175fc2708970','ANTEOR',7,'ANTEOR');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('d04834ab-73ab-4225-86cf-abeff5ee284f','RIFAP',7,'RIFAP (Information)');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('2da57ce2-b14a-4c69-9c23-aa3314f73bc7','THA',1,'Théorie Anticipée (CTR)');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('739c2fd2-668f-45ef-9501-6c7165517f49','HANDI PESH 6',8,'Handi PESH 6');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('7ea4e7da-d06a-4cb7-89e3-89b62bc51e9d','HANDI PESH 12',8,'Handi PESH 12');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('43d51248-5d2f-4239-865d-03e38060f952','HANDI PESH 20',8,'Handi PESH 20');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('061eac89-b907-4c9a-9620-17bc1146c8a7','HANDI PESH 40',8,'Handi PESH 40');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('00df7cb1-b592-45c1-b11f-1bed3825f775','HANDI EH1',8,'Handi EH1');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('aa0d64d8-f285-4187-9ec3-d512af7faad8','HANDI EH2',8,'Handi EH2');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('bfdb374d-1622-4a78-afd9-d60b8466b30d','HANDI MFEH1',8,'Handi MF EH1');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('5b2e6f9c-62a7-4b19-af3a-927f71fe527d','HANDI MFEH2',8,'Handi MF EH2');"
                + "INSERT INTO TypesEvenements (UUID, Name, Type, Valeur) VALUES ('5734fbd1-1a23-4c49-971f-0394e8cbb222','HANDI MPC',8,'Handi Module MPC');");
        return strB.toString();
    }

    public static String initMoniteurs() {
        StringBuilder strB = new StringBuilder();
        strB.append("INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('53edb9f0-827b-465f-b4bf-2778d127c36c','REDUREAU','Jean-Yves',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('86e3668b-398a-43c8-b24b-1f0116493f31','KERSALE','Jean-Yves',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('1e3cebd9-a930-415d-a6a1-f072587d5084','BOYER','Laurent',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('f6610855-9566-4456-8825-305543fa03df','CIREFICE','Christian',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('394809e9-0549-4118-bba3-88d9f68c0d5a','CORBE','Anne',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('a50df459-1d93-4296-90b2-ac77d17d36de','DENAIS','Michel',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('155d9da0-6dc5-437b-a1a5-620337a48665','GAILLOT','Anne-Claire',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('2a781ad7-5c32-4729-9b9c-1243afe5c564','LAMBERT','Patrick',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('7e50e2af-74ac-43ef-93b7-0798cbaa9129','LE CORGUILLE','Jo',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('327d3458-ab53-44a4-b685-079181d46c49','LE PEUTREC','Yvonnic',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('d3ec7d0e-f052-4ec2-856e-5fbb6bb2fdbf','MARTEAU','Jean-Paul',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('77cd6be4-b026-4522-9609-b5973e663fa1','MONESTIEZ','Pascal',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('1fa3f6e8-c5a1-431d-afb3-742b3984f00b','OLLIVIER','Claude',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('a887bc4a-30a1-44fe-a75b-90e5ad8de874','PEPIN','Cédric',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('5a8011ef-7170-4a8b-948b-780983ffbcbe','RESPINGER','Alix',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('8bcc7d93-77b7-4083-97d1-5d8abd2c8c83','TERRIER','Paul',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('f2cd2d35-21a1-47cd-8a12-5a2edef6de94','QUEMENER','Stéphanie',3);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('3f718bed-074b-43d1-a3b3-2402eac48242','BADAJOZ','Javier',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('d40a944e-3de2-4ba9-b3ad-625c4917e4e7','BERGOT','Gabriel',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('a61a082d-5abd-43e0-a6dc-e43faea3d561','LE NEURES','Caroline',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('32b4e312-9ecc-4001-8be5-34a3d2a6efdb','RENOT','Philippe',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('f066d733-8963-4345-a6d9-57b85da4fcbf','BERRIET','Frédérique',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('2d773dce-dff0-4ac1-90d0-5ebf7e7dcb2d','SEMERARO','Yann',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('ce3a8e4a-f664-44e6-bb29-f341ebb40579','BERTAUX','Cyrille',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('92a3917a-14f9-4c53-a4d4-6089b77430ed','BERTHOU','Patrick',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('4d940aa5-8175-4623-ac02-d00e2a7821ca','BOUAKAZE','Jean-Pierre',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('074710ec-5757-49dd-b939-2a8e9ef8db6e','BOUTIN','Roland',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('86690898-7be4-4d26-a503-e68c460df004','COCHARD','Gilles',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('9d0cab68-b39b-410d-9fb4-8f8332a162dd','COGNEZ','Jean-François',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('3590a849-258f-4e19-bda8-2cfd39b46b02','DUTIN','Thierry',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('ac598be3-195c-43fe-a157-b1f38e0604ea','GUERIF','Christophe',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('5d27188b-af45-4523-a2c3-d85a8392ab6a','KERNEIS','Jean-Yves',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('f55809d2-ed6a-4b4a-9307-2ba46e256032','LE SAUX','Frédéric',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('3d333d2e-5dd1-4760-aa9b-72229d4ccf45','LE TULZO','Daniel',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('3aafd5dd-45f2-4a40-827d-aabe6337b039','LEFAURE','Pierre',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('1da5287b-a352-4a49-98c4-27592127efd5','LHOTELLIER','Eric',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('df91833d-9b6d-4a85-9dcc-e17aa1040a10','MOITHEY','Yohann',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('4e037627-aa02-453a-8c51-4e282b65d0bd','PASCUAL','Claudio',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('80eb217f-fc9d-486a-a840-82fad3fee04e','PHILIPPE','Bernard',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('3ae8f273-7232-478f-b310-536ba4a37788','PICART','Christine',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('0351c295-cc96-4d70-9361-ce7e6bfdc760','ROTA','Dominique',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('ed7ec233-b5d2-476b-82a8-debd51efa53f','ROUX','Stéphane',2);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('379aaa5a-228e-457e-b99e-fe2152ba3898','LEQUITTE','Yannick',4);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('7df7e904-1c8e-458c-942e-0f5078b9de74','EON','Yannick',6);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('5974cb57-6769-4f9e-8e79-4e4e4907097e','MIGNOT','Dominique',6);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('8e590cb7-c957-40aa-afcc-307a87e55221','PROVOST','Sébastien',1);"
                + "INSERT INTO Moniteurs (UUID,LastName,FirstName,Niveau) VALUES ('6a2a9b64-3f2d-435c-b8ec-212027af2247','LANDREIGNE','JC',1);");
        return strB.toString();
    }
}
