package com.agibank.challenge.util;

import com.agibank.challenge.util.enums.Path;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtil {

    public static String readFile(String name){
        return Path.INPUT.path.concat(name);
    }

    public static String writeFile(String name){
        return Path.OUTPUT.path.concat(name).replace("txt","done.txt");
    }

}
