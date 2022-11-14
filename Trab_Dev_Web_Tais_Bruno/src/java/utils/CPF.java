package utils;

import java.util.InputMismatchException;

public class CPF {
    public static boolean IsCPF(String CPF) {
        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");

        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || CPF.length() != 11) {
            return(false);
        }

        char dig10, dig11;
        int sum, index, remainder, number, p;

        try {
            sum = 0;
            number = 0;
            p = 10;
            for (index = 0; index < 9; index++) {
                number = (int)(CPF.charAt(index) - 48);
                sum = sum + (number * p);
                p = p - 1;
            }

            remainder = 11 - (sum % 11);
            if ((remainder == 10) || (remainder == 11)) {
                 dig10 = '0';
            } else {
                dig10 = (char)(remainder + 48);
            }

            sum = 0;
            p = 11;
            for(index = 0; index < 10; index++) {
                number = (int)(CPF.charAt(index) - 48);
                sum = sum + (number * p);
                p = p - 1;
            }

            remainder = 11 - (sum % 11);
            if ((remainder == 10) || (remainder == 11)) {
                 dig11 = '0';
            } else {
                dig11 = (char)(remainder + 48);
            }

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                 return(true);
            } else {
                return(false);
            }
            
        } catch (InputMismatchException e) {
            return(false);
        }
    }
}
