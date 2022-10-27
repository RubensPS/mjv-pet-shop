package com.school.mjvpetshop.utilities;


public class PetShopFieldValidator {

    public static boolean checkCpf(String cpf) {
        return cpf.length() == 11;
    }

    public static String formatCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }
}
