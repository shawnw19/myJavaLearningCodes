package LanguageFeaturesRelated.PointMoving_HotDeployment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PointClient {
    static Point pt;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pt = PointServer.createPoint(null);
        System.out.println(pt);
        while (true){
            System.out.println("MOVE1, RELOAD, or EXIT");
            String cmd = null;
            try {
                cmd = br.readLine().toUpperCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(cmd.equals("EXIT")){
                return;
            }else if(cmd.equals("RELOAD")){
                PointServer.reloadIMP();
                pt = PointServer.createPoint(pt);
                System.out.println(pt);
            }else if(cmd.equals("MOVE1")){
                pt.move(1,1);
                System.out.println(pt);
            }
        }
    }
}

