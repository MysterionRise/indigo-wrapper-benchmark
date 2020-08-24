package org.mystic.indigo;


import com.epam.indigo.Indigo;
import com.epam.indigo.IndigoObject;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IndigoTest {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Indigo indigoAPI = new Indigo();
        List<String> smilesList = new ArrayList<>();
        Scanner in = new Scanner(Paths.get("src", "test", "resources", "smiles.csv"));
        while (in.hasNext()) {
            String smile = in.nextLine();
            IndigoObject indigoObject = indigoAPI.loadMolecule(smile);
            indigoObject.layout();
            indigoObject.aromatize();
            smilesList.add(indigoObject.canonicalSmiles());
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
