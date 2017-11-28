/**
 * @file FlyingResponseComp.java
 *
 * @brief      Comparator osztály a FlyingResponse osztályhoz.
 * @details    A Collections.max() metódus használja FlyingResponse típusú vektor elemeihez.
 */
package eu.ablonczy.csaba.variometer;

import java.util.Comparator;

/**
 * Comparator oszt&aacute;ly a FlyingResponse oszt&aacute;lyhoz.
 * A Collections.max() met&oacute;dus haszn&aacute;lja FlyingResponse t&iacute;pus&uacute; vektor elemeihez az altitude tagv&aacute;ltoz&oacute; alapj&aacute;n.
 *
 * P&eacute;lda k&oacute;d:
 * @code
 *     Integer maxAlt = Collections.max(flyingResponse, new FlyingResponseComp()).altitude;
 * @endcode
 */
public class FlyingResponseComp implements Comparator<MainActivity.FlyingResponse> {

    @Override
    public int compare(MainActivity.FlyingResponse e1, MainActivity.FlyingResponse e2) {
        return e1.altitude.compareTo(e2.altitude);
    }
}
