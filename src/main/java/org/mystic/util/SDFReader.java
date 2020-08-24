package org.mystic.util;

import com.epam.indigo.Indigo;
import com.epam.indigo.IndigoObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class SDFReader {

    public static void main(String[] args) throws IOException {
        Indigo indigo = new Indigo();
        FileWriter fileWriter = new FileWriter(Paths.get("src", "test", "resources", "smiles.csv").toFile());
        for (IndigoObject structure : indigo.iterateSDFile(Paths.get("src", "test", "resources", "Compound_146000001_146500000.sdf.gz").toString())) {
            fileWriter.write(structure.smiles() + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
