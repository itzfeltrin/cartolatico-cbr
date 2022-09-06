package cartola.gamer.web.utils;

import java.math.BigInteger;


public class Decodificador{
    public Decodificador() {
        // TODO Auto-generated constructor stub
    }

    public BigInteger encodeString(String texto){
        return new BigInteger(texto.getBytes());
    }

    public String decodeBigInt(BigInteger bigInt){
        return new String(bigInt.toByteArray());
    }

    public static void main(String[] args) {
        String dataTime    = "2017-09-28 17:46:00";
        String url         = "GET /search?q=0 HTTP/1.1";
        String urlLonga    = "http://multi11.cpd.ufsm.br/asm/logs/Rename/Login.php?sslchannel=true&sessionid=1wcTbJxOfjPQ8qeuqG4v82oSGnOj7yW9592hSA57TVY2ldxMTCi2EGUk3JEbiBknKnECXKKRFJT1WrOQ";
        String hostname    = "pfsense1-wan1.cpd.ufsm.br";
        String ip          = "104.244.14.252";
        String port        = "50663";
        String desc        = "servidor wifi pfsense captive portal";
        String originalUrl = "https://www.google.co.nz/?gfe_rd=cr&ei=dzbFV&gws_rd=ssl#q=java";

        Decodificador res = new Decodificador();
        BigInteger urlEnco = res.encodeString(url);
        System.out.println("Codificado: " + urlEnco);
        String urlDeco = res.decodeBigInt(urlEnco);
        System.out.println("Decodificado: " + urlDeco);
        System.out.println("========================");
        BigInteger urlLongaEnco = res.encodeString(urlLonga);
        System.out.println("Codificado: " + urlLongaEnco);
        String urlLongaDeco = res.decodeBigInt(urlLongaEnco);
        System.out.println("Decodificado: " + urlLongaDeco);
        System.out.println("========================");
        BigInteger ipEnco = res.encodeString(ip);
        System.out.println("Codificado: " + ipEnco);
        String ipDeco = res.decodeBigInt(ipEnco);
        System.out.println("Decodificado: " + ipDeco);
    }
}
