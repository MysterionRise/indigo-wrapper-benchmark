package org.mystic.jna;

public class IndigoJNA {

    public static void main(String[] args) {
        IndigoAPI indigoAPI = new IndigoAPI();
        IndigoObject indigoObject = indigoAPI.loadMolecule("C1=CC=CC=C1");
//        EXPECTED C1C=CC=CC=1
        System.out.println(indigoObject.canonicalSmiles());
    }


}
