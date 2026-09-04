# 🚑 MesHeures

Suivi des heures pour ambulancier — décompte en **quatorzaine**, heures
supplémentaires, repos compensateur, paniers repas et contrôle du bulletin de paie.

Application **100 % locale** : aucune donnée n'est envoyée sur internet.
Tout reste sur le téléphone.

---

## Installation

### Option A — Application web (PWA), 5 minutes

1. Sur GitHub : **Settings → Pages**
2. Source : *Deploy from a branch* · Branch : **main** · dossier **/ (root)** → *Save*
3. Attendre 1 à 2 minutes
4. Ouvrir `https://TON-PSEUDO.github.io/mesheures/` dans **Chrome**
5. Menu ⋮ → **Installer l'application**

Icône sur l'écran d'accueil, plein écran, fonctionne hors ligne.

### Option B — APK Android

1. Onglet **Actions** du dépôt
2. Workflow **Build APK** → **Run workflow**
3. Attendre ~5 min, puis télécharger l'artefact **mesheures-apk**
4. Décompresser le `.zip`, transférer l'`.apk` sur le téléphone, l'ouvrir
5. Autoriser « Installer des applications inconnues » si demandé

> APK de debug non signé : normal pour un usage personnel.

---

## Premier démarrage

1. Onglet **Réglages** → vérifier taux horaire, base hebdo, ancrage de quatorzaine
2. **Ancrage quatorzaine** : n'importe quel lundi de début de quatorzaine connu.
   Toutes les autres sont calculées à partir de là.
3. Importer l'historique (voir ci-dessous)
4. **Exporter** une sauvegarde

---

## Importer l'historique depuis AmbuTrack

La sauvegarde JSON d'AmbuTrack est **chiffrée** (le fichier commence par
`Salted__`) : elle n'est lisible que par AmbuTrack. On passe donc par les PDF.

1. Dans AmbuTrack, se placer sur une période
2. Générer le **PDF récapitulatif**
3. Ouvrir le PDF, **sélectionner tout le texte**, copier
4. Dans MesHeures : **Réglages → Importer depuis AmbuTrack** → coller
5. Répéter pour chaque période — **on peut coller plusieurs PDF à la suite**
   dans la même zone, ils seront tous importés d'un coup
6. **Analyser et importer**

L'import récupère : type de journée, horaires, pauses ENT/EXT, paniers,
et **coche automatiquement les jours fériés travaillés**.

---

## Les onglets

| Onglet | Rôle |
|---|---|
| **Jour** | Saisie quotidienne + progression de la quatorzaine en cours |
| **Mois** | Calendrier couleur, récap par semaine |
| **Paie** | Une période de paie : HS, brut, RC, comparaison fiche employeur |
| **Audit** | Toutes les périodes : écarts, fériés, anomalies, cumuls |
| **Réglages** | Paramètres, import, sauvegarde |

---

## Contrôles automatiques

- ⏱️ Pause de 20 min due après 6 h sans pause (art. L3121-16)
- 🛏️ Repos quotidien inférieur à 11 h entre deux services
- 📊 Dépassement des 48 h hebdomadaires
- 🚩 Journée marquée « repos » mais contenant des horaires
- ☀️ Jour férié travaillé sans majoration cochée
- 🔄 Écart entre le RC calculé et le RC acquis du bulletin

---

## Règles de calcul appliquées

| Élément | Règle |
|---|---|
| Amplitude | (fin − début) + habillage |
| TTE | (fin − début) − pauses |
| Quatorzaine | 70 h normales, puis 16 h à 25 %, puis 50 % |
| RC | **exclu** du TTE et des seuils, mais valorisé |
| IDAJ | amplitude au-delà du seuil (habillage inclus) |
| Panier | dû si le service couvre toute la plage repas |
| HS → RC | (heures dues − payées) × 1,25 ou × 1,50 |

Les congés payés alimentent les seuils. RC, maladie et AT non.

---

## Sauvegarde — à lire

Les données vivent dans le stockage local du navigateur. Elles survivent aux
redémarrages et aux mises à jour, **mais disparaissent** si :

- on vide les données de Chrome
- on désinstalle l'application
- on change de téléphone

👉 **Exporter le JSON une fois par mois** et se l'envoyer par mail.
Cela donne en plus une sauvegarde horodatée par un tiers.

---

## Limites

- Le brut affiché ne correspondra pas au bulletin : le bulletin mensualise
  151,67 h et convertit une partie des HS en RC. **Se fier aux heures.**
- Estimation indicative, sans valeur juridique.
- Continuer à noter ses horaires à la main : un carnet a une valeur probante
  qu'un fichier modifiable n'aura jamais.
