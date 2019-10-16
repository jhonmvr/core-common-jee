package com.relative.core.util.main;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Clase utilizaria para gestinar numeros de tipo BigDecimal
 *
 * @author Alex Guamán
 */
public class BigDecimalUtil {
	
	private BigDecimalUtil(){
		
	}

    /**
     * Metodo que redondea los decimales, hacia arriba
     *
     * @param decimal valor a redondear
     * @param cntDecimales numero de decimales a aplicar
     * @return Numero redondeado
     */
    public static BigDecimal roundBigDecimal(BigDecimal decimal, int cntDecimales) {
        return decimal.setScale(cntDecimales, RoundingMode.HALF_UP);
    }

    /**
     * Compara dos numeros para identificar si el primero es mayor que el
     * segundo
     *
     * @param decimal1 primer numero a comprar
     * @param decimal2 segundo numero a comparar.
     * @return verdadero si el primero es mayor que el segundo
     */
    public static boolean esMayor(BigDecimal decimal1, BigDecimal decimal2) {
        return decimal1.compareTo(decimal2) > 0;
    }

    /**
     * Compara dos numeros para identificar si el primero es menor que el
     * segundo
     *
     * @param decimal1 primer numero a comprar
     * @param decimal2 segundo numero a comparar.
     * @return verdadero si el primero es menor que el segundo
     */
    public static boolean esMenor(BigDecimal decimal1, BigDecimal decimal2) {
        return decimal1.compareTo(decimal2) < 0;
    }

    /**
     * Compara dos numeros para identificar si el primero es mayor o igual que
     * el segundo
     *
     * @param decimal1 primer numero a comprar
     * @param decimal2 segundo numero a comparar.
     * @return verdadero si el primero es mayor o igual que el segundo
     */
    public static boolean esMayorIgual(BigDecimal decimal1, BigDecimal decimal2) {
        return decimal1.compareTo(decimal2) >= 0;
    }

    /**
     * Compara dos numeros para identificar si el primero es menor o igual que
     * el segundo
     *
     * @param decimal1 primer numero a comprar
     * @param decimal2 segundo numero a comparar.
     * @return verdadero si el primero es menor o igual que el segundo
     */
    public static boolean esMenorIgual(BigDecimal decimal1, BigDecimal decimal2) {
        return decimal1.compareTo(decimal2) <= 0;
    }

    /**
     * Compara dos numeros para identificar si el primero es igual que el
     * segundo
     *
     * @param decimal1 primer numero a comprar
     * @param decimal2 segundo numero a comparar.
     * @return verdadero si el primero es igual que el segundo
     */
    public static boolean esIgual(BigDecimal decimal1, BigDecimal decimal2) {
        return decimal1.compareTo(decimal2) == 0;
    }

    /**
     * Metodo que divide dos numeros, y devuelve el resultado redondeado
     *
     * @param dividendo
     * @param divisor
     * @param cntDecimales
     * @return Division redondeada
     */
    public static BigDecimal dividir(BigDecimal dividendo, BigDecimal divisor, int cntDecimales) {
        if (!esIgual(divisor, BigDecimal.ZERO)) {
            return dividendo.divide(divisor, cntDecimales);
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Metodo que convierte el decimal en cadena con el formato #,###,##0.00
     *
     * @param valor
     * @return
     */
    public static String transformToString(BigDecimal valor) {
        if (valor != null) {
            BigDecimal newValor = roundBigDecimal(valor, 5);
            DecimalFormat df = new DecimalFormat(Constantes.NUMBER_AS_STRING_FORMAT);
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator(Constantes.NUMBER_AS_STRING_DECIMAL_SEPARATOR);
            decimalFormatSymbols.setGroupingSeparator(Constantes.NUMBER_AS_STRING_GROUP_SEPARATOR);
            df.setDecimalFormatSymbols(decimalFormatSymbols);
            df.setGroupingSize(3);
            return df.format(newValor);
        } else {
            return "";
        }
    }

    /**
     * Devuelve true si la cantidad de números decimales corresponde al indicado en decimalesAceptados
     * @param valor
     * @param decimalesAceptados
     * @return   true si cumple la cantidad de decimales, caso contrario false.
     */
    public static boolean verificarDecimal(BigDecimal valor, int decimalesAceptados) {
        String s = valor.toString();
        int index = s.indexOf('.');
        Integer num = s.length() - 1 - index;
        return num.equals(decimalesAceptados);
    }

}
