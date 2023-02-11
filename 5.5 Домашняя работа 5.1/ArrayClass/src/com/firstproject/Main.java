package com.firstproject;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[][] cross = new String[7][7];
        for (int i = 0; i < cross.length; i++) {
            Arrays.fill(cross[i], " ");
            cross[i][i] = "X";
            cross[i][cross[i].length - 1 - i] ="X";
            for (int j = 0; j < cross[i].length; j++) {
                System.out.print(cross[i][j]);
            }
            System.out.println();
        }

//        String tmpVar = "";
//        String[] colors = "Каждый охотник желает знать, где сидит фазан.".replaceAll("\\.", "").split(",?\s");
//        for (int i = 0; i < colors.length / 2; i++) {
//            tmpVar = colors[colors.length - 1 - i];
//            colors[colors.length - 1 - i] = colors[i];
//            colors[i] = tmpVar;
//        }
//        for (String colorName :
//                colors) {
//            System.out.print(colorName + " ");
//        }
//        final int PATIENTS_AMOUNT = 30;
//        final int MAX_TEMP = 40;
//        final int MIN_TEMP = 32;
//        final float MAX_TEMP_HEALTHY = (float) 36.9;
//        final float MIN_TEMP_HEALTHY = (float) 36.2;
//
//        float[] patients = new float[PATIENTS_AMOUNT];
//        for (int i = 0; i < patients.length; i++) {
//            patients[i] = (float) (MIN_TEMP + Math.random() * (MAX_TEMP - MIN_TEMP));
//            System.out.println(patients[i]);
//        }
//
//        double meanTemp = 0;
//        for (float patientTemp :
//                patients) {
//            meanTemp += patientTemp;
//        }
//        meanTemp /= patients.length;
//        System.out.println("\n" + meanTemp);
//
//        int healthyAmount = 0;
//        for (float patientTemp :
//                patients) {
//            if (patientTemp >= MIN_TEMP_HEALTHY && patientTemp <= MAX_TEMP_HEALTHY) {
//                healthyAmount++;
//            }
//        }
//        System.out.println("\n" + healthyAmount);
    }
}
