package eu.ablonczy.csaba.variometer;

import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;

/**
 * Created by Csabi on 2015.10.03..
 */
public class SoapSerializationEnvelopeMod extends SoapSerializationEnvelope {

    //Vector multiRef;
    //public Element[] headerOut;

    public SoapSerializationEnvelopeMod(int ver) {
        super(ver);
    }
/*
    protected void writeElement(XmlSerializer var1, Object var2, PropertyInfo var3, Object var4) throws IOException {
        if(var4 != null) {
            ((Marshal)var4).writeInstance(var1, var2);
        } else if(var2 instanceof KvmSerializable) {
            this.writeObjectBody(var1, (KvmSerializable)var2);
        } else {
            if(!(var2 instanceof Vector)) {
                throw new RuntimeException("Cannot serialize: " + var2);
            }

            this.writeVectorBody(var1, (Vector)var2, var3.elementType);
        }
    }
*/
    @Override
    public void write(XmlSerializer writer) throws IOException {
        //writer.setPrefix("i", this.xsi);
        //writer.setPrefix("d", this.xsd);
        //writer.setPrefix("c", this.enc);
        writer.setPrefix("s", this.env);
        writer.startTag(this.env, "Envelope");
        writer.startTag(this.env, "Header");
        this.writeHeader(writer);
        writer.endTag(this.env, "Header");
        writer.startTag(this.env, "Body");
        this.writeBody(writer);
        writer.endTag(this.env, "Body");
        writer.endTag(this.env, "Envelope");
    }
/*
    @Override
    public void writeBody(XmlSerializer var1) throws IOException {
        this.multiRef = new Vector();
        this.multiRef.addElement(this.bodyOut);
        Object[] var2 = this.getInfo((Object)null, this.bodyOut);
        var1.startTag(this.dotNet ? "" : (String) var2[0], (String) var2[1]);
        if(this.dotNet) {
            var1.attribute((String)null, "xmlns", (String)var2[0]);
        }

        //var1.attribute((String) null, "id", var2[2] == null ? "o0" : (String) var2[2]);
        //var1.attribute(this.enc, "root", "1");
        this.writeElement(var1, this.bodyOut, (PropertyInfo) null, var2[3]);
        var1.endTag(this.dotNet?"":(String)var2[0], (String)var2[1]);
    }
*/
    @Override
    public void writeHeader(XmlSerializer writer) throws IOException {
        if(this.headerOut != null) {
            for(int i = 0; i < this.headerOut.length; ++i) {
                this.headerOut[i].write(writer);
            }
        }
    }
}
