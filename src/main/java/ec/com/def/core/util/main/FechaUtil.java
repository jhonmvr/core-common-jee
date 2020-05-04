package ec.com.def.core.util.main;

import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import ec.com.def.core.exception.DefException;

import org.apache.commons.lang.StringUtils;

/**
 * Clase utilitaria para la gestion de fechas
 * 
 * @author Alex Guaman
 *
 */
public class FechaUtil {
	
	private FechaUtil(){
		
	}

    /**
     * *
     * Encera la hora , minutos, segundos y milisegundos a cero, en la fecha
     * indicada.
     *
     * @param fecha
     * @return
     */
    public static Date obtenerInicioDia(Date fecha) {
        LocalDate localDate = new LocalDate(fecha);
        return localDate.toDate();
    }

    /**
     * *
     * Setea la hora a 23 , minutos a 59, segundos a 59 y milisegundos a 999, en
     * la fecha indicada.
     *
     * @param fecha
     * @return
     */
    public static Date obtenerFinDia(Date fecha) {
        DateTime dateTime = new DateTime(fecha);
        dateTime = dateTime.hourOfDay().setCopy(23);
        dateTime = dateTime.minuteOfHour().setCopy(59);
        dateTime = dateTime.secondOfMinute().setCopy(59);
        dateTime = dateTime.millisOfSecond().setCopy(999);

        return dateTime.toDate();
    }

    /**
     * Agrega o resta minutos a la fecha indicada.
     *
     * @param fecha
     * @param minutos
     * @return
     */
    public static Date sumarRestarMinutos(Date fecha, int minutos) {
        DateTime dateTime = new DateTime(fecha);
        dateTime = dateTime.plusMinutes(minutos);
        return dateTime.toDate();

    }

    /**
     * Agrega o resta dias a la fecha indicada.
     *
     * @param fecha
     * @param dias
     * @return
     */
    public static Date sumarRestarDiasFecha(Date fecha, int dias) {
        DateTime dateTime = new DateTime(fecha);
        dateTime = dateTime.plusDays(dias);
        return dateTime.toDate();
    }

    /**
     * Agrega o resta meses a la fecha indicada.
     *
     * @param fecha
     * @param meses
     * @return
     */
    public static Date sumarRestarMesesFecha(Date fecha, int meses) {
        DateTime dateTime = new DateTime(fecha);
        dateTime = dateTime.plusMonths(meses);
        return dateTime.toDate();
    }

    /**
     * Asigna la hora , los minutos y los segundos indicados, a la fecha (date).
     *
     * @param date
     * @param hora
     * @param minutos
     * @param segundos
     * @return
     */
    public static Date asignarHoraMinutoSegundo(Date date, int hora, int minutos, int segundos) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.hourOfDay().setCopy(hora);
        dateTime = dateTime.minuteOfHour().setCopy(minutos);
        dateTime = dateTime.secondOfMinute().setCopy(segundos);
        return dateTime.toDate();
    }

    /**
     * Genera un string a partir de la fecha indicada (date) en base al formato
     * (yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String formatearCadenaFechaCorta(Date date) {
        DateTime dateTime = new DateTime(date);
        return DateTimeFormat.forPattern(Constantes.FORMATO_FECHA_CORTA).print(dateTime);
    }

    /**
     * Genera un string a partir de la fecha indicada (date) en base al formato
     * (yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String formatearCadenaFechaLarga(Date date) {
        DateTime dateTime = new DateTime(date);
        return DateTimeFormat.forPattern(Constantes.FORMATO_FECHA_LARGA).print(dateTime);
    }

    public static String formatearCadenaFechaCompleta(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter dtfmrEs = DateTimeFormat.forPattern(Constantes.FORMATO_FECHA_COMPLETA).withLocale(new Locale("es", "EC"));
        return dtfmrEs.print(dateTime);
    }

    /**
     * Obtiene la diferencia de dos fechas en milisegundos.
     *
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public static Long diferenciarFechas(Date fechaInicio, Date fechaFin) {
        DateTime dateTime1 = new DateTime(fechaInicio);
        DateTime dateTime2 = new DateTime(fechaFin);
        Duration duration = new Duration(dateTime1, dateTime2);

        return duration.getMillis();
    }

    /**
     * Convierte un date en XMLGregorian Calendar utilizada en mesajes XML
     *
     * @param date fecha a transformar
     * @return fecha en formato XMLGregorianCalendar
     * @throws DatatypeConfigurationException
     */
    public static XMLGregorianCalendar convertirXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar dateGC = new GregorianCalendar();
        dateGC.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(dateGC);
    }

    /**
     * Convierte un XMLGregorianCalendar a una fecha simple
     *
     * @param xMLGregorianCalendar fecha XMLGregorianCalendar
     * @return Date
     * @throws ParseException
     */
    public static Date convetirXMLGregorianCalendarADateCorta(XMLGregorianCalendar xMLGregorianCalendar) {
        GregorianCalendar cal = xMLGregorianCalendar.toGregorianCalendar();
        return cal.getTime();
    }

    /**
     * Genera un string a partir de la fecha indicada (date) en base al formato
     * (MM/yyyy)
     * 
     * @param date
     * @return
     */
    
    public static String formatearCadenaFechaMesCenturia(Date date){
        DateTime dateTime = new DateTime(date);
        return DateTimeFormat.forPattern(Constantes.FORMATO_FECHA_MES_CENTURIA).print(dateTime);
    }
    
    /**
	 * Metodo que genera una cadena en formato hh:mi desde una fecha
	 * @param date Fecha a convertir en formato hh:mi
	 * @returnCadena con formato hh:mi
	 * @throws DefException
	 */
    public static String obtnerHoraMinutoComoCadena(Date fecha) throws DefException {
  		try {
  			XMLGregorianCalendar local  =FechaUtil.convertirXMLGregorianCalendar(fecha );
  			return StringUtils.leftPad(String.valueOf(local.getHour()),2) +":" + StringUtils.leftPad(String.valueOf(local.getMinute()),2);
  		} catch (DatatypeConfigurationException e) {
  			throw new DefException("ERROR EN CONVERSION DE FECHA A FORMATO HH:MI " + e.getMessage());
  		}      
    }
}

