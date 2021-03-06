package Model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaAttuatori extends GenericInfo{
    private List<ModalitaOperative> listModalitaOperative = new ArrayList<>();
    private List<Attuatori> listaAttuatori = new ArrayList<>();
    private List<String> listaNomiAttuatori = new ArrayList<>();
    private List<ModalitaOperative> listaModalitaOperativeParametriche = new ArrayList<>();
    private boolean isAttivo=true;

    public CategoriaAttuatori(String nome, String descrizione, List<ModalitaOperative> listModalitaOperative){
        this.descrizione=descrizione;
        this.nome=nome;
        this.listModalitaOperative=listModalitaOperative;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getDescrizione() {
        return super.getDescrizione();
    }

    public List<ModalitaOperative> getModalitaOperativa() {
        return listModalitaOperative;
    }

    public void aggiungiAttuatore(Attuatori attuatore) {
        listaAttuatori.add(attuatore);
    }

    public void aggiungiNomeAttuatore(String attuatore) {
        listaNomiAttuatori.add(attuatore);
    }

    public List<Attuatori> getListaAttuatori() {
        return listaAttuatori;
    }

    public List<String> getListaNomiAttuatori() {
        return listaNomiAttuatori;
    }

    public List<ModalitaOperative> getListModalitaOperative() {
        return listModalitaOperative;
    }

    public static void printListModalitaOperative(List<ModalitaOperative> listModalita){
        int index = 1;
        int indexP = 1;
        System.out.println("MODALITA' OPERATIVE");
        for (ModalitaOperative mod : listModalita
        ) {
            System.out.println(index + ") " + mod.getNome());
            for (String param : mod.getListaParametri()
            ) {
                System.out.println("    parametro " + indexP + " ---> " + param);
                indexP++;
            }
            indexP = 1;
            index++;
        }
        System.out.println();
    }

    public static void printListCategoriaAttuatori(List<CategoriaAttuatori> listaCategoriaAttuatori){
        int index = 1;
        System.out.println("CATEGORIE ATTUATORI");
        for (CategoriaAttuatori att : listaCategoriaAttuatori
        ) {
            System.out.println(index + ") " + att.getNome() + ": " + att.getDescrizione());
            index++;
        }
        System.out.println();
    }

    public static void printListCategoriaAttuatoriRimanenti(List<CategoriaAttuatori> listaCategoriaAttuatori){
        int index = 1;
        System.out.println("CATEGORIE ATTUATORI DISPONIBILI");
        for (CategoriaAttuatori att : listaCategoriaAttuatori
        ) {
            System.out.println(index + ") " + att.getNome() + ": " + att.getDescrizione());
            index++;
        }
        System.out.println();
    }

    public static boolean checkCategorieAttuatori(){
        if (listaCategoriaAttuatori.size() > 0) return true;
        else {
            System.out.println("ATTENZIONE: la lista delle categorie attuatori e' vuota!!!");
            System.out.println();
            return false;
        }
    }

    public List<ModalitaOperative> getListaModalitaOperativeParametriche() {
        return listaModalitaOperativeParametriche;
    }
}
