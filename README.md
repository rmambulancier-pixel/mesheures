# MesHeures - Application Hybride (Ambulancier)

Application de gestion, de suivi horaire et de contrôle de paie dédiée à l'activité d'ambulancier, combinant une interface Web ergonomique et un conteneur natif Android (WebView).

## Architecture du Projet

* **`index.html`** : Cœur de l'application (Interface utilisateur, moteurs de calculs, parsing de fichiers ROMI1 et bulletins de paie, gestion du stockage local).
* **`/app`** : Module natif Android pour l'encapsulation de l'interface en WebView avec gestion des accès aux fichiers et sélecteurs natifs.
* **`manifest.json`** : Configuration pour la prise en charge en Progressive Web App (PWA).

## Fonctionnalités principales

* Suivi journalier et mensuel des temps de travail (TTE, amplitudes, heures supplémentaires).
* Importation et rapprochement automatique avec les données officielles (ROMI1 et Bulletins de paie).
* Calculs sécurisés intégrant la gestion des paniers repas (IR, IRU), des indemnités (IDAJ) et des repos compensateurs.
