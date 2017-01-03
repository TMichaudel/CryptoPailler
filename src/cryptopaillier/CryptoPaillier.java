/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptopaillier;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author thiba
 */
public class CryptoPaillier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pailler paillier = new Pailler();
        System.out.println(new BigInteger("42"));
        BigInteger c = paillier.encrypt(new BigInteger("42"));
        System.out.println(paillier.decrypt(c));
    }
}
