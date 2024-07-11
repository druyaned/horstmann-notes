package com.github.druyaned.horstmann.corejava.vol2.ch02;

import com.github.druyaned.horstmann.corejava.Chapter;
import com.github.druyaned.horstmann.corejava.vol2.ch02.src.EmployeeData;
import com.github.druyaned.horstmann.corejava.vol2.ch02.src.P01TextIO;
import com.github.druyaned.horstmann.corejava.vol2.ch02.src.P02BinData;
import com.github.druyaned.horstmann.corejava.vol2.ch02.src.P03RandomAccess;
import com.github.druyaned.horstmann.corejava.vol2.ch02.src.P04Zip;
import com.github.druyaned.horstmann.corejava.vol2.ch02.src.P05ObjectIO;
import com.github.druyaned.horstmann.corejava.vol2.ch02.src.P06FileManipulation;
import com.github.druyaned.horstmann.corejava.vol2.ch02.src.P07FileMap;
import com.github.druyaned.horstmann.corejava.vol2.ch02.src.P08RegEx;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Practice implementation of the Chapter#02: Input-Output.
 * @author druyaned
 */
public class InputOutput extends Chapter {
    
    /**
     * Creates the Chapter#02: Input-Output.
     * @param volDataDir the path to the volume's data-directory
     */
    public InputOutput(Path volDataDir) {
        super(volDataDir, 2);
    }
    
    @Override public String getTitle() {
        return "Input-Output";
    }
    
    @Override public void run() {
        EmployeeData data = new EmployeeData(getDataDir());
        List<Runnable> parts = new ArrayList<>();
        parts.add(new P01TextIO(getDataDir()));
        parts.add(new P02BinData(data));
        parts.add(new P03RandomAccess(data));
        parts.add(new P04Zip(getDataDir(), data));
        parts.add(new P05ObjectIO(getDataDir(), data));
        parts.add(new P06FileManipulation(getDataDir(), data));
        parts.add(new P07FileMap(getDataDir()));
        parts.add(new P08RegEx());
        choosePartAndRun(parts);
    }
    
}
