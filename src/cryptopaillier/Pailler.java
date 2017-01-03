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
public class Pailler {
    BigInteger N;
    BigInteger phi_N;
    
    public Pailler(){
        keyGen();
    }

    public BigInteger getN() {
        return N;
    }

    public BigInteger getPhi_N() {
        return phi_N;
    }
    
    public void keyGen() {
        //clé publique : N
        //clé privé : phi_N
        boolean ok = false;
        
        while (!ok) {
            BigInteger p = new BigInteger(512, 2, new Random());
            while (!fermat(p)) {
                p = p.nextProbablePrime();
            }
            BigInteger q = new BigInteger(512, 2, new Random());
            while (!fermat(q)) {
                q = q.nextProbablePrime();
            }
            N = p.multiply(q);
            phi_N = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
            if (N.gcd(phi_N).equals(new BigInteger("1"))) {
                ok = true;
            }
        }
       
        
    }
    
    public BigInteger encrypt(BigInteger m){
        BigInteger r = new BigInteger(512, 2, new Random());
        while(!(r.compareTo(N)==-1)){
            r=r.nextProbablePrime();
        }
                
        BigInteger c=N.add(new BigInteger("1")).modPow(m, N.multiply(N)).multiply(r.modPow(N, N.multiply(N)));
        return c;
    }
    
    public BigInteger decrypt(BigInteger c){
        BigInteger r = c.modPow(N.modInverse(phi_N),N);
        BigInteger m = c.multiply(r.modInverse(N.multiply(N))).modPow(N,N.multiply(N)).subtract(new BigInteger("1")).divide(N);
        return m;
    }
    
    

    public boolean fermat(BigInteger m) {
        BigInteger nn = new BigInteger("2");
        return nn.modPow(m.subtract(new BigInteger("1")), m).equals(new BigInteger("1"));
    }
}
