# Systeme d'emprunt de velos avec equilibrage de stock
Description
Ce projet simule un système de partage de vélos avec plusieurs sites où des clients peuvent emprunter et déposer des vélos. Un camion d'équilibrage se déplace régulièrement pour redistribuer les vélos entre les sites afin de maintenir des niveaux de stock équilibrés. Les clients et le camion agissent de manière concurrente, gérant la disponibilité des vélos via des opérations synchronisées.
# Fonctionnalités principales
# Clients : Les clients empruntent des vélos à un site de départ et les déposent à un site d'arrivée.
# Camion d'équilibrage : Le camion parcourt les sites dans un ordre cyclique pour équilibrer les stocks, récupérant les vélos excédentaires et déposant des vélos sur les sites qui en manquent.
# Synchronisation : Le système utilise des mécanismes de synchronisation (synchronized, wait(), notifyAll()) pour gérer les accès concurrents aux sites.
