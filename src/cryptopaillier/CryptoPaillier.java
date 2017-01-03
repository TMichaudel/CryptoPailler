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
        keyGen();
    }

    public static void keyGen() {
        //clé publique : (n,e)
        //clé privé : 
        boolean ok = false;
        BigInteger n = null;
        BigInteger phi_n = null;
        
        while (!ok) {
            BigInteger p = new BigInteger(512, 2, new Random());
            while (!fermat(p)) {
                p = p.nextProbablePrime();
            }
            BigInteger q = new BigInteger(512, 2, new Random());
            while (!fermat(q)) {
                q = q.nextProbablePrime();
            }
            n = p.multiply(q);
            phi_n = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
            if (n.gcd(phi_n).equals(new BigInteger("1"))) {
                ok = true;
            }
        }

        BigInteger e = new BigInteger(16, 2, new Random());
        while (!fermat(e)) {
            e = e.nextProbablePrime();
        }

        while (!phi_n.gcd(e).equals(new BigInteger("1"))) {
            e = e.nextProbablePrime();
        }
        
        BigInteger d = e.modInverse(phi_n);
    }

    public static boolean fermat(BigInteger m) {
        BigInteger nn = new BigInteger("2");
        return nn.modPow(m.subtract(new BigInteger("1")), m).equals(new BigInteger("1"));
    }
}
