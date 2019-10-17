package com.wql.utils.security;

import org.bouncycastle.util.encoders.Base64Encoder;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;


public class KeyPairGenUtil {
    public static void main(String[] args) {
         KeyPairGenUtil.createKeyPairText();
    }

    private static void createKeyPair() {
        ObjectOutputStream streamOne = null;
        ObjectOutputStream streamTwo = null;
        try {

            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);

            KeyPair keyPair = generator.generateKeyPair();
            Key publicKey = keyPair.getPublic();
            Key privateKey = keyPair.getPrivate();


            streamOne = new ObjectOutputStream(new FileOutputStream("PublicKey"));
            streamTwo = new ObjectOutputStream(new FileOutputStream("PrivateKey"));
            streamOne.writeObject(publicKey);
            streamTwo.writeObject(privateKey);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            try {
                streamOne.close();
                streamTwo.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }



    private static void createKeyPairText() {

        try {
            SecureRandom random = new SecureRandom();
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024,random);

            KeyPair keyPair = generator.generateKeyPair();
            Key publicKey = keyPair.getPublic();
            Key privateKey = keyPair.getPrivate();
            byte[] publicKeyBytes = publicKey.getEncoded();
            byte[] privateKeyBytes = privateKey.getEncoded();
            String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKeyBytes);
            String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKeyBytes);

            System.out.println("publicKey:"+publicKeyBase64);
            System.out.println("privareKey:"+privateKeyBase64);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
