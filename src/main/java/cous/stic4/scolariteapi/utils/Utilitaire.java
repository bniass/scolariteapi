package cous.stic4.scolariteapi.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utilitaire {
    public static String anneeacademique(){
        switch (LocalDate.now().getMonth().getValue()){
            case 9:
            case 10:
            case 11:
            case 12:return LocalDate.now().getYear()+"-"+LocalDate.now().plusYears(1).getYear();
            default:return LocalDate.now().plusYears(1).getYear() +"-"+LocalDate.now().getYear();
        }
    }
    public static List<String>  anneeacademiquelist(){
        String[] annee=anneeacademique().split("-");
        List<String> elts=new ArrayList<>();
        int an1=Integer.parseInt(annee[0])-4;
        int an2=Integer.parseInt(annee[1])-4;
        for (int i = 0; i <5 ; i++) {
            elts.add(an1+"-"+an2);
            an1++;
            an2++;
        }

       return elts;
    }
}
