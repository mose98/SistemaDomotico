import Control.CreatorRemove.ObjectRemoval;
import Control.FileSaver;
import Control.ImportClass;
import Control.Regole;
import View.StaticVariables;
import Model.*;
import View.VistaParametri;
import mylib.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static Control.CreatorRemove.ObjectCreator.*;

public class MainVersion implements StaticVariables {
    public static void main(String[] args) throws IOException {
        tipologieUnitaImmobiliari.add("Privato");
        tipologieUnitaImmobiliari.add("Commerciale");

        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String folderName = "SistemaDomotico_" + sdf.format(date);
        String folderUnitaName = null;
        FileSaver.createFolder(folderName);
        FileSaver.createFolder(folderName + File.separator + "CategorieSensori");
        FileSaver.createFolder(folderName + File.separator + "CategorieAttuatori");
        FileSaver.createFile(folderName + "/CategorieSensori.txt");
        FileSaver.createFile(folderName + "/CategorieAttuatori.txt");

        ObjectRemoval removeController = new ObjectRemoval();
        SistemaDomotico sistemaDomotico = new SistemaDomotico();
        int scelta, sceltaManut, sceltaFruit, sceltaModOperativa;
        MyMenu menuIniziale;
        MyMenu menuManutentore;
        MyMenu menuFruitore;
        char confirm;

        menuIniziale = new MyMenu(titolo, vociMenuI);

        menuIniziale.stampaMenu();
        scelta = menuIniziale.scegli();
        boolean finishedManut = false;
        boolean finished = false;
        boolean finishedFruit = false;
        MENU:
        do {
            switch (scelta) {
                case 0:
                    finished = true;
                    menuIniziale.stampaMenu();
                    scelta = menuIniziale.scegli();
                    continue MENU;
                case 1:
                    menuManutentore = new MyMenu(titoloManut, vociMenuManut);
                    menuManutentore.stampaMenu();
                    sceltaManut = menuManutentore.scegli();
                    do {
                        switch (sceltaManut) {
                            case 0:
                                finishedManut = true;
                                menuIniziale.stampaMenu();
                                scelta = menuIniziale.scegli();
                                continue MENU;

                                //creazione unita'  immobiliare
                            case 1:
                                printNewLineAndTitle(titNewUnita);

                                do {
                                    UnitaImmobiliari unita = createUnitaImmobiliare();

                                    sistemaDomotico.getListUnitaImmobiliari().add(unita);
                                    printNewLineAndTitle(titEndNewUnita);

                                    folderUnitaName = folderName + File.separator + unita.getNome();
                                    FileSaver.createFolder(folderUnitaName);
                                    FileSaver.createFile(folderUnitaName + "/Stanze.txt");
                                    FileSaver.createFile(folderUnitaName + "/ArtefattiEsterni.txt");
                                    FileSaver.createFile(folderUnitaName + "/AttuatoriEsterni.txt");
                                    FileSaver.createFile(folderUnitaName + "/SensoriEsterni.txt");

                                    confirm = chooseInsert("Vuoi inserire una nuova unita' immobiliare?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                } while (confirm == 'y');
                                break;
                            //crea nuova categoria sensori
                            case 2:
                                printNewLineAndTitle(titNewCatSens);

                                do {
                                    CategoriaSensori categoriaSensore = createCategoriaSensore(folderName + File.separator + "CategorieSensori");

                                    printNewLineAndTitle(titEndNewCatSens);

                                    CategoriaSensori.printListCategoriaSensori(listaCategoriaSensori);

                                    FileSaver.newCatSensFile(wPath + File.separator + folderName + File.separator + "CategorieSensori.txt", categoriaSensore.getNome(), categoriaSensore.getDescrizione());

                                    confirm = chooseInsert("Vuoi inserire una nuova categoria di sensori?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                } while (confirm == 'y');
                                break;
                            //crea nuova categoria attuatori
                            case 3:
                                printNewLineAndTitle(titNewCatAtt);

                                do {
                                    CategoriaAttuatori categoriaAttuatore = createCategoriaAttuatore(folderName + File.separator + "CategorieAttuatori");

                                    printNewLineAndTitle(titEndNewCatAtt);

                                    CategoriaAttuatori.printListCategoriaAttuatori(listaCategoriaAttuatori);

                                    FileSaver.newCatAttFile(wPath + File.separator + folderName + File.separator + "CategorieAttuatori.txt", categoriaAttuatore.getNome(), categoriaAttuatore.getDescrizione());

                                    confirm = chooseInsert("Vuoi inserire una nuova categoria di attuatori?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                } while (confirm == 'y');
                                break;
                            //crea nuova stanza
                            case 4:
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titNewStanza);

                                    do {
                                        printNewLineAndTitle(titEndNewStanza);

                                        confirm = chooseInsert("Vuoi creare una nuova stanza?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    } while (confirm == 'y');
                                }
                                break;
                            //crea nuovo artefatto
                            case 5:
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titNewArtefatto);

                                    do {
                                        Artefatti artefatto = createArtefatto(folderName);

                                        printNewLineAndTitle(titEndNewArtefatto);

                                        confirm = chooseInsert("Vuoi creare un nuovo artefatto?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    } while (confirm == 'y');
                                }
                                break;
                            //crea nuovo sensore
                            case 6:
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titNewSensore);

                                    do {
                                        Sensori sensore = createSensore(folderName);

                                        printNewLineAndTitle(titEndNewSensore);

                                        confirm = chooseInsert("Vuoi creare un nuovo sensore?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    } while (confirm == 'y');
                                }
                                break;
                            //crea nuovo attuatore
                            case 7:
                                if (sistemaDomotico.checkUnita()) {
                                    printNewLineAndTitle(titNewAttuatore);

                                    do {
                                        Attuatori attuatore = createAttuatore(folderName);

                                        printNewLineAndTitle(titEndNewAttuatore);

                                        confirm = chooseInsert("Vuoi creare un nuovo attuatore?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    } while (confirm == 'y');
                                }
                                break;
                            //rimuovi stanze
                            case 8:
                                if (sistemaDomotico.checkUnita() && listaStanze.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelStanza);

                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Selezionare l'unita' immobiliare dalla quale si vuole eliminare la stanza?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printStanze();
                                    Stanze stanzaSel = unitaSel.getListastanza().get(chooseFromList("Selezionare la stanza da rimuovere > ", unitaSel.getListastanza()));
                                    removeController.deleteObject(unitaSel, stanzaSel);

                                    printNewLineAndTitle(titEndDelStanza);
                                }
                                break;
                            //rimuovi artefatto
                            case 9:
                                if (sistemaDomotico.checkUnita() && listaArtefatti.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelArtefatto);

                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Selezionare l'unita' immobiliare dalla quale si vuole eliminare l'artefatto?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printArtefatti();
                                    Artefatti artefattoSel = unitaSel.getListaArtefattiUnita().get(chooseFromList("Selezionare l'artefatto da rimuovere > ", unitaSel.getListaArtefattiUnita()));
                                    removeController.deleteObject(unitaSel, artefattoSel);

                                    printNewLineAndTitle(titEndDelArtefatto);
                                }
                                break;
                            //rimuovi sensore
                            case 10:
                                if (sistemaDomotico.checkUnita() && listaSensori.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelSensore);

                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Selezionare l'unita' immobiliare dalla quale si vuole eliminare il sensore?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printSensori(unitaSel.getListaSensoriUnita());
                                    Sensori sensoreSel = unitaSel.getListaSensoriUnita().get(chooseFromList("Selezionare il sensore da rimuovere > ", unitaSel.getListaSensoriUnita()));
                                    removeController.deleteObject(unitaSel, sensoreSel);

                                    printNewLineAndTitle(titEndDelSensore);
                                }
                                break;
                            //rimuovi attuatore
                            case 11:
                                if (sistemaDomotico.checkUnita() && listaAttuatori.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelAttuatore);

                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Selezionare l'unita' immobiliare dalla quale si vuole eliminare l'attuatore?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printSensori(unitaSel.getListaSensoriUnita());
                                    Attuatori attuatoreSel = unitaSel.getListaAttuatoriUnita().get(chooseFromList("Selezionare l'attuatore da rimuovere > ", unitaSel.getListaAttuatoriUnita()));
                                    removeController.deleteObject(unitaSel, attuatoreSel);

                                    printNewLineAndTitle(titEndDelAttuatore);
                                }
                                break;
                            //rimuovi categoria sensore
                            case 12:
                                if (sistemaDomotico.checkUnita() && listaCategoriaSensori.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelCatSens);

                                    CategoriaSensori.printListCategoriaSensori(listaCategoriaSensori);
                                    CategoriaSensori catSensoreSel = listaCategoriaSensori.get(chooseFromList("Selezionare la categoria da rimuovere > ", listaCategoriaSensori));
                                    removeController.deleteObject(catSensoreSel);

                                    printNewLineAndTitle(titEndDelCatSens);
                                }
                                break;
                            //rimuovi categoria attuatore
                            case 13:
                                if (sistemaDomotico.checkUnita() && listaCategoriaAttuatori.size() > 0) {
                                    confirm = chooseInsert("Sei sicuro di voler proseguire con la rimozione?\npremere 'y' per confermare\npremere 'n' per tornare al menu precedente", 'y', 'n');
                                    if (confirm == 'n') break;

                                    printNewLineAndTitle(titDelCatAtt);

                                    CategoriaAttuatori.printListCategoriaAttuatori(listaCategoriaAttuatori);
                                    CategoriaAttuatori catAttuatoreSel = listaCategoriaAttuatori.get(chooseFromList("Selezionare la categoria da rimuovere > ", listaCategoriaAttuatori));
                                    removeController.deleteObject(catAttuatoreSel);

                                    printNewLineAndTitle(titEndDelCatAtt);
                                }
                                break;
                            //visualizza categorie sensori
                            case 14:
                                printNewLineAndTitle(titViewCatSens);
                                if (CategoriaSensori.checkCategorieSensori())
                                    CategoriaSensori.printListCategoriaSensori(listaCategoriaSensori);
                                break;
                            //visualizza categorie attuatori
                            case 15:
                                printNewLineAndTitle(titViewCatAtt);
                                if (CategoriaAttuatori.checkCategorieAttuatori())
                                    CategoriaAttuatori.printListCategoriaAttuatori(listaCategoriaAttuatori);
                                break;
                            //visualizza unita immobiliare
                            case 16:
                                int index = 1;
                                if (sistemaDomotico.checkUnita()) {
                                    for (UnitaImmobiliari unita : sistemaDomotico.getListUnitaImmobiliari()
                                    ) {
                                        System.out.println(index + ") " + unita.getNome().toUpperCase() + "     " + unita.getDescrizione());
                                        unita.printStanze();
                                        unita.printArtefatti();
                                        UnitaImmobiliari.printSensori(unita.getListaSensoriUnita());
                                        UnitaImmobiliari.printAttuatori(unita.getListaAttuatoriUnita());

                                        System.out.println("---------------------------------------------------------------------------");
                                    }
                                }
                                //visualizza sensori
                            case 17:
                                if (listaSensori.size() > 0) {
                                    printNewLineAndTitle(titViewSens);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare i sensori?", listaUnitaImmobiliari) - 1);

                                    UnitaImmobiliari.printSensori(unitaSel.getListaSensoriUnita());
                                }
                                break;
                            //visualizza attuatori
                            case 18:
                                if (listaAttuatori.size() > 0) {
                                    printNewLineAndTitle(titViewAtt);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare gli attuatori?", listaUnitaImmobiliari) - 1);

                                    UnitaImmobiliari.printAttuatori(unitaSel.getListaAttuatoriUnita());
                                }
                                break;
                            //visualizza artefatti
                            case 19:
                                if (listaArtefatti.size() > 0) {
                                    printNewLineAndTitle(titViewListArt);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare gli artefatti?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printArtefatti();
                                }
                                break;
                            //visualizza stanze
                            case 20:
                                if (listaStanze.size() > 0) {
                                    printNewLineAndTitle(titViewListStanze);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare le stanze?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printStanze();
                                }
                                break;
                            //importa categoria sensore
                            case 21:
                                listaCategoriaSensori.add(ImportClass.importCatSens(folderName));
                                break;
                            //importa categoria attuatore
                            case 22:
                                listaCategoriaAttuatori.add(ImportClass.importCatAtt(folderName));
                                break;
                            //importa unità immobiliare
                            case 23:
                                listaUnitaImmobiliari.add(ImportClass.importUnita(folderName));
                                break;
                            //importa regole
                            case 24:
                                break;
                        }
                        break;
                    } while (finishedManut);
                    break;

                case 2:
                    menuFruitore = new MyMenu(titoloFruit, vociMenuFruit);
                    menuFruitore.stampaMenu();
                    sceltaFruit = menuFruitore.scegli();
                    do {
                        switch (sceltaFruit) {
                            case 0:
                                int index = 0;
                                finishedFruit = true;
                                menuIniziale.stampaMenu();
                                scelta = menuIniziale.scegli();
                                continue MENU;
                                //visualizza categorie sensori
                            case 1:
                                printNewLineAndTitle(titViewCatSens);
                                if (CategoriaSensori.checkCategorieSensori())
                                    CategoriaSensori.printListCategoriaSensori(listaCategoriaSensori);
                                break;
                            //visualizza categorie attuatori
                            case 2:
                                printNewLineAndTitle(titViewCatAtt);
                                if (CategoriaAttuatori.checkCategorieAttuatori())
                                    CategoriaAttuatori.printListCategoriaAttuatori(listaCategoriaAttuatori);
                                break;
                            //visualizza unita immobiliare
                            case 3:
                                index = 1;
                                if (sistemaDomotico.checkUnita()) {
                                    for (UnitaImmobiliari unita : sistemaDomotico.getListUnitaImmobiliari()
                                    ) {
                                        System.out.println(index + ") " + unita.getNome().toUpperCase() + "     " + unita.getDescrizione());
                                        unita.printStanze();
                                        unita.printArtefatti();
                                        UnitaImmobiliari.printSensori(unita.getListaSensoriUnita());
                                        UnitaImmobiliari.printAttuatori(unita.getListaAttuatoriUnita());

                                        System.out.println("---------------------------------------------------------------------------");
                                    }
                                }
                                //visualizza sensori
                            case 4:
                                if (listaSensori.size() > 0) {
                                    printNewLineAndTitle(titViewSens);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare i sensori?", listaUnitaImmobiliari) - 1);

                                    UnitaImmobiliari.printSensori(unitaSel.getListaSensoriUnita());
                                }
                                break;
                            //visualizza attuatori
                            case 5:
                                if (listaAttuatori.size() > 0) {
                                    printNewLineAndTitle(titViewAtt);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare gli attuatori?", listaUnitaImmobiliari) - 1);

                                    UnitaImmobiliari.printAttuatori(unitaSel.getListaAttuatoriUnita());
                                }
                                break;
                            //visualizza artefatti
                            case 6:
                                if (listaArtefatti.size() > 0) {
                                    printNewLineAndTitle(titViewListArt);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare gli artefatti?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printArtefatti();
                                }
                                break;
                            //visualizza stanze
                            case 7:
                                if (listaStanze.size() > 0) {
                                    printNewLineAndTitle(titViewListStanze);
                                    SistemaDomotico.printListUnita(listaUnitaImmobiliari);
                                    UnitaImmobiliari unitaSel = listaUnitaImmobiliari.get(chooseFromList("Di che unita' immobiliare si desidera visualizzare le stanze?", listaUnitaImmobiliari) - 1);

                                    unitaSel.printStanze();
                                }
                                break;
                            //visualizza valori sensori
                            case 8:
                                if(listaSensori.size()>0){
                                    VistaParametri.ValoriSensori();
                                }
                                break;
                            //assegna parametri modalità operative
                            case 9:
                                if(listaAttuatori.size()>0){
                                    VistaParametri.AssegnaParametri();
                                }
                                break;
                            //creazione regole
                            case 10:
                                break;
                            //visualizza regole applicate
                            case 11:
                                if(listaRegoleApplicate.size()>0){
                                    printTitleAndList("Regole applicate", listaRegoleApplicate);
                                }
                                break;
                            //visualizza regole create
                            case 12:
                                if(listaRegole.size()>0){
                                    printTitleAndList("Lista regole create", listaNomiRegole);
                                }
                                break;
                            //creazione regole tempo-dipendenti
                            case 13:
                                break;
                            //modifica stato sensore
                            case 14:
                                if(listaSensori.size()>0){
                                    VistaParametri.AttivazioneSensori();
                                }
                                break;
                            //modifica stato attuatore
                            case 15:
                                if(listaAttuatori.size()>0){
                                    VistaParametri.AttivazioneAttuatori();
                                }
                                break;
                            //modifica stato regole
                            case 16:
                                if(listaRegole.size()>0){
                                    VistaParametri.AttivazioneRegole();
                                }
                                break;
                        }
                        break;
                    } while (!finishedFruit);
            }
        } while (!finished);
    }
}