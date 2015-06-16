/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.util;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author ilja
 */
public class ListCleaner {
    
    public static List cleanListFromEqualObjects(List list){
        if (list == null || list.isEmpty()) {
            return list;
        }
        ArrayList in = new ArrayList();
        for (Object object : list) {
            if (in.contains(object)) {
                System.out.println("Contains");
                continue;
            }
            in.add(object);
        }
        return in;
    }
}
