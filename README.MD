# Elevator – JavaFX Heissimulasjon
En JavaFX-applikasjon som simulerer en heis med 3 etasjer, animerte dører og statusvisning i sanntid.

## Forutsetninger
- Java 17+

## Prosjektstruktur
```
src/
  App.java              # Inngangspunkt – starter JavaFX-applikasjonen
  controller/
    BindingsHub.java    # Kobler modell og visning med JavaFX bindings
    EventHub.java       # Håndterer brukerinteraksjon og hendelser
  model/
    ElevatorModel.java  # Tilstand for heisen (posisjon, status, kø)
    FloorModel.java     # Etasjeinformasjon
    DoorModel.java      # Tilstand for dørene
    ElevatorStatus.java # Enum for heisstatus
    DoorStatusEnum.java # Enum for dørstatus
  service/
    ElevatorService.java  # Logikk for heisbevegelse
    DoorService.java      # Logikk for åpning/lukking av dører
    MoveElevator.java     # Animasjon og forflytning
  view/
    MainView.java         # UI-komponenter og layout
```

## Bruk
Trykk på en av knappene **1**, **2** eller **3** for å sende heisen til ønsket etasje. Dørene åpnes automatisk når heisen ankommer, og lukkes igjen før neste forflytning. Status for heis og dører vises løpende i grensesnittet.
