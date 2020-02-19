import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class GestorCifrado {
    KeyPair claves;
    KeyPairGenerator generadorClaves;
    Cipher cifrador;
    Key publicKey;

    public GestorCifrado() throws NoSuchAlgorithmException, NoSuchPaddingException {
        generadorClaves = KeyPairGenerator.getInstance("RSA");
        /*
         * Usaremos una longitud de clave de 1024 bits
         */
        generadorClaves.initialize(1024);
        claves = generadorClaves.generateKeyPair();
        cifrador = Cipher.getInstance("RSA");
    }

    public PublicKey getPublica() {
        return claves.getPublic();
    }

    public PrivateKey getPrivada() {
        return claves.getPrivate();
    }

    public byte[] cifrar(byte[] paraCifrar, Key claveCifrado)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] resultado;
        /* Se pone el cifrador en modo cifrado */
        cifrador.init(Cipher.ENCRYPT_MODE, claveCifrado);
        resultado = cifrador.doFinal(paraCifrar);
        return resultado;
    }

    public byte[] descifrar(byte[] paraDescifrar, Key claveDescifrado)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] resultado;
        /* Se pone el cifrador en modo descifrado */
        cifrador.init(Cipher.DECRYPT_MODE, claveDescifrado);
        resultado = cifrador.doFinal(paraDescifrar);
        return resultado;
    }

    public void sendPublicKey(OutputStream stream) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(getPublica().getEncoded().length);
        stream.write(bb.array());
        stream.write(getPublica().getEncoded());
        stream.flush();
    }

    public void receivePublicKey(InputStream stream) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] lenb = new byte[4];
        stream.read(lenb,0,4);
        ByteBuffer bb2 = ByteBuffer.wrap(lenb);
        int len = bb2.getInt();
        byte[] servPubKeyBytes = new byte[len];
        stream.read(servPubKeyBytes);
        X509EncodedKeySpec ks = new X509EncodedKeySpec(servPubKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        publicKey = kf.generatePublic(ks);
    }

    public void sendEncryptedMessage(byte[] paraCifrar, OutputStream stream) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] mensajeCifrado = cifrar(paraCifrar, publicKey);
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(mensajeCifrado.length);
        stream.write(bb.array());
        stream.write(mensajeCifrado);
        stream.flush();
    }

    public String receiveEncryptedMessage(InputStream stream) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] lenCadenaCifrada = new byte[4];
        stream.read(lenCadenaCifrada, 0, 4);
        ByteBuffer byteBufferLenCadenaCifrada = ByteBuffer.wrap(lenCadenaCifrada);
        int lenCadenaCifradaRecibida = byteBufferLenCadenaCifrada.getInt();
        byte[] cadenaCifradaRecibidaBytes = new byte[lenCadenaCifradaRecibida];
        stream.read(cadenaCifradaRecibidaBytes,0, lenCadenaCifradaRecibida);
        byte[] cadenaDescifrada = descifrar(cadenaCifradaRecibidaBytes, getPrivada());
        return new String(cadenaDescifrada);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {
        GestorCifrado gestorCifrado = new GestorCifrado();
    }
}