package eu.ablonczy.csaba.variometer;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.transport.Transport;
import org.kxml2.io.KXmlSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Csabi on 2015.10.14..
 */
public abstract class TransportMod extends Transport {

    private int bufferLength = 262144;
    private String xmlVersionTag = "";
    private HashMap prefixes = new HashMap();

    public TransportMod() {
        super();
    }

    public TransportMod(String url) {
        super(url);
    }

    public TransportMod(String url, int timeout) {
        super(url, timeout);
    }

    public TransportMod(String url, int timeout, int bufferLength) {
        super(url, timeout, bufferLength);
    }

    public TransportMod(Proxy proxy, String url) {
        super(proxy, url);
    }

    public TransportMod(Proxy proxy, String url, int timeout) {
        super(proxy, url, timeout);
    }

    public TransportMod(Proxy proxy, String url, int timeout, int bufferLength) {
        super(proxy, url, timeout, bufferLength);
    }

    @Override
    protected byte[] createRequestData(SoapEnvelope envelope, String encoding) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(this.bufferLength);
        Object result = null;
        bos.write(this.xmlVersionTag.getBytes());
        KXmlSerializer xw = new KXmlSerializer();
        Iterator keysIter = this.prefixes.keySet().iterator();
        xw.setOutput(bos, encoding);

        while(keysIter.hasNext()) {
            String key = (String)keysIter.next();
            xw.setPrefix(key, (String)this.prefixes.get(key));
        }

        envelope.write(xw);
        xw.flush();
        bos.write(13);
        bos.write(10);
        bos.flush();
        String res1 = bos.toString();

        //res1 = res1.replaceAll("n0:", "");
        //res1 = res1.replaceAll(":n0", "");
        //res1 = res1.replaceAll(" mustUnderstand=", " s:mustUnderstand=");
        res1 = res1.replaceAll(".000Z", "");
        res1 = res1.replaceFirst("<GetFlyingByPlaceAndDate xmlns=\"http://tempuri.org/\">", "<GetFlyingByPlaceAndDate xmlns=\"http://tempuri.org/\"><qConditions xmlns:d4p1=\"http://schemas.datacontract.org/2004/07/\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">");
        res1 = res1.replaceAll("FlyingDate>", "d4p1:FlyingDate>");
        res1 = res1.replaceAll("PlaceID>", "d4p1:PlaceID>");
        res1 = res1.replaceFirst("</d4p1:PlaceID>", "</d4p1:PlaceID></qConditions>");

        byte[] result1 = res1.getBytes(); //bos.toByteArray();
        xw = null;
        bos = null;
        return result1;
    }

}
