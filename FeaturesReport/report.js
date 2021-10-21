$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/reservation.feature");
formatter.feature({
  "name": "Réservation de voiture",
  "description": "  L\u0027application permet de réserver une voiture sur une période donnée.",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "achat crédit",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "je suis authentifié en tant que \"\u003cprenom_client\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "que le solde de mon compte est de \"\u003csolde_avant\u003e\" crédits",
  "keyword": "And "
});
formatter.step({
  "name": "j\u0027achète \"\u003cnb_credit\u003e\" crédits",
  "keyword": "When "
});
formatter.step({
  "name": "le solde de mon compte est de \"\u003csolde_après\u003e\" crédits",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "prenom_client",
        "solde_avant",
        "nb_credit",
        "solde_après"
      ]
    },
    {
      "cells": [
        "Benjamin",
        "0",
        "5",
        "5"
      ]
    },
    {
      "cells": [
        "Jean-Michel",
        "10",
        "2",
        "12"
      ]
    },
    {
      "cells": [
        "Patrick",
        "4",
        "50",
        "54"
      ]
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "des catégories existent:",
  "rows": [
    {
      "cells": [
        "id",
        "libelle"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000001",
        "citadine"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000002",
        "berline"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000003",
        "monospace"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "CategorieStepdefs.java:14"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "des voitures existent:",
  "rows": [
    {
      "cells": [
        "id",
        "marque",
        "modele",
        "nombre_place",
        "categorie"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000001",
        "DACIA",
        "Stepway",
        "5",
        "10000000-0000-0000-0000-000000000001"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000002",
        "PEUGEOT",
        "5008",
        "5",
        "10000000-0000-0000-0000-000000000003"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000003",
        "CITROEN",
        "DS4",
        "5",
        "10000000-0000-0000-0000-000000000002"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "VoitureStepdefs.java:19"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "achat crédit",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "je suis authentifié en tant que \"Benjamin\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "que le solde de mon compte est de \"0\" crédits",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "j\u0027achète \"5\" crédits",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "le solde de mon compte est de \"5\" crédits",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "des catégories existent:",
  "rows": [
    {
      "cells": [
        "id",
        "libelle"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000001",
        "citadine"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000002",
        "berline"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000003",
        "monospace"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "CategorieStepdefs.java:14"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "des voitures existent:",
  "rows": [
    {
      "cells": [
        "id",
        "marque",
        "modele",
        "nombre_place",
        "categorie"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000001",
        "DACIA",
        "Stepway",
        "5",
        "10000000-0000-0000-0000-000000000001"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000002",
        "PEUGEOT",
        "5008",
        "5",
        "10000000-0000-0000-0000-000000000003"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000003",
        "CITROEN",
        "DS4",
        "5",
        "10000000-0000-0000-0000-000000000002"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "VoitureStepdefs.java:19"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "achat crédit",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "je suis authentifié en tant que \"Jean-Michel\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "que le solde de mon compte est de \"10\" crédits",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "j\u0027achète \"2\" crédits",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "le solde de mon compte est de \"12\" crédits",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "des catégories existent:",
  "rows": [
    {
      "cells": [
        "id",
        "libelle"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000001",
        "citadine"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000002",
        "berline"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000003",
        "monospace"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "CategorieStepdefs.java:14"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "des voitures existent:",
  "rows": [
    {
      "cells": [
        "id",
        "marque",
        "modele",
        "nombre_place",
        "categorie"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000001",
        "DACIA",
        "Stepway",
        "5",
        "10000000-0000-0000-0000-000000000001"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000002",
        "PEUGEOT",
        "5008",
        "5",
        "10000000-0000-0000-0000-000000000003"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000003",
        "CITROEN",
        "DS4",
        "5",
        "10000000-0000-0000-0000-000000000002"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "VoitureStepdefs.java:19"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "achat crédit",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "je suis authentifié en tant que \"Patrick\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "que le solde de mon compte est de \"4\" crédits",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "j\u0027achète \"50\" crédits",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "le solde de mon compte est de \"54\" crédits",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenarioOutline({
  "name": "achat d\u0027un film avec suffisamment de crédits et l\u0027âge légal",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "je suis authentifié en tant que \"\u003cprenom_client\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "que le solde de mon compte est de \"\u003csolde_avant\u003e\" crédits",
  "keyword": "And "
});
formatter.step({
  "name": "que mon utilisateur à l\u0027âge minimum pour acheter le film \"\u003ctitre_film\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "je tente d\u0027acheter le film \"\u003ctitre_film\u003e\" qui coute \"\u003cprix_film\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "le film est ajouté dans mon catalogue de film acheté",
  "keyword": "Then "
});
formatter.step({
  "name": "le solde de mon compte est de \"\u003csolde_après\u003e\" crédits",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "prenom_client",
        "solde_avant",
        "solde_après",
        "titre_film",
        "prix_film"
      ]
    },
    {
      "cells": [
        "Jean-Michel",
        "10",
        "5",
        "The Simpsons",
        "5"
      ]
    },
    {
      "cells": [
        "Patrick",
        "26",
        "22",
        "Chucky",
        "4"
      ]
    },
    {
      "cells": [
        "Benjamin",
        "29",
        "24",
        "The Simpsons",
        "5"
      ]
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "des catégories existent:",
  "rows": [
    {
      "cells": [
        "id",
        "libelle"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000001",
        "citadine"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000002",
        "berline"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000003",
        "monospace"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "CategorieStepdefs.java:14"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "des voitures existent:",
  "rows": [
    {
      "cells": [
        "id",
        "marque",
        "modele",
        "nombre_place",
        "categorie"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000001",
        "DACIA",
        "Stepway",
        "5",
        "10000000-0000-0000-0000-000000000001"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000002",
        "PEUGEOT",
        "5008",
        "5",
        "10000000-0000-0000-0000-000000000003"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000003",
        "CITROEN",
        "DS4",
        "5",
        "10000000-0000-0000-0000-000000000002"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "VoitureStepdefs.java:19"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "achat d\u0027un film avec suffisamment de crédits et l\u0027âge légal",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "je suis authentifié en tant que \"Jean-Michel\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "que le solde de mon compte est de \"10\" crédits",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "que mon utilisateur à l\u0027âge minimum pour acheter le film \"The Simpsons\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "je tente d\u0027acheter le film \"The Simpsons\" qui coute \"5\"",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "le film est ajouté dans mon catalogue de film acheté",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "le solde de mon compte est de \"5\" crédits",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "des catégories existent:",
  "rows": [
    {
      "cells": [
        "id",
        "libelle"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000001",
        "citadine"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000002",
        "berline"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000003",
        "monospace"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "CategorieStepdefs.java:14"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "des voitures existent:",
  "rows": [
    {
      "cells": [
        "id",
        "marque",
        "modele",
        "nombre_place",
        "categorie"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000001",
        "DACIA",
        "Stepway",
        "5",
        "10000000-0000-0000-0000-000000000001"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000002",
        "PEUGEOT",
        "5008",
        "5",
        "10000000-0000-0000-0000-000000000003"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000003",
        "CITROEN",
        "DS4",
        "5",
        "10000000-0000-0000-0000-000000000002"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "VoitureStepdefs.java:19"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "achat d\u0027un film avec suffisamment de crédits et l\u0027âge légal",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "je suis authentifié en tant que \"Patrick\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "que le solde de mon compte est de \"26\" crédits",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "que mon utilisateur à l\u0027âge minimum pour acheter le film \"Chucky\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "je tente d\u0027acheter le film \"Chucky\" qui coute \"4\"",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "le film est ajouté dans mon catalogue de film acheté",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "le solde de mon compte est de \"22\" crédits",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "des catégories existent:",
  "rows": [
    {
      "cells": [
        "id",
        "libelle"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000001",
        "citadine"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000002",
        "berline"
      ]
    },
    {
      "cells": [
        "10000000-0000-0000-0000-000000000003",
        "monospace"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "CategorieStepdefs.java:14"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "des voitures existent:",
  "rows": [
    {
      "cells": [
        "id",
        "marque",
        "modele",
        "nombre_place",
        "categorie"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000001",
        "DACIA",
        "Stepway",
        "5",
        "10000000-0000-0000-0000-000000000001"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000002",
        "PEUGEOT",
        "5008",
        "5",
        "10000000-0000-0000-0000-000000000003"
      ]
    },
    {
      "cells": [
        "00000000-0000-0000-0000-000000000003",
        "CITROEN",
        "DS4",
        "5",
        "10000000-0000-0000-0000-000000000002"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "VoitureStepdefs.java:19"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "achat d\u0027un film avec suffisamment de crédits et l\u0027âge légal",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "je suis authentifié en tant que \"Benjamin\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "que le solde de mon compte est de \"29\" crédits",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "que mon utilisateur à l\u0027âge minimum pour acheter le film \"The Simpsons\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "je tente d\u0027acheter le film \"The Simpsons\" qui coute \"5\"",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "le film est ajouté dans mon catalogue de film acheté",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "le solde de mon compte est de \"24\" crédits",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});