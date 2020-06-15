import java.math.BigInteger;

public class DiffieHellman {
    BigInteger p, g; 
    public DiffieHellman(){}

    public void generatePrimeAndPrimitiveRoot(){
        this.p = BigInteger.valueOf(new PrimeNumberGenerator().getPrimeNumber()); 
        this.g = BigInteger.valueOf(new PrimitiveRootGenerator(this.p.intValue()).getPrimitiveRoot());
    }

    public BigInteger getP(){
        return this.p;
    }

    public BigInteger getG(){
        return this.g;
    }

    public BigInteger getAliceMessage(BigInteger aliceSecretNumber){
        return this.g.modPow(aliceSecretNumber, this.p);
    }

    public BigInteger getBobMessage(BigInteger bobSecretNumber){
        return this.g.modPow(bobSecretNumber, this.p);
    }

    public BigInteger aliceCalculationOfKey(BigInteger bobMessage, BigInteger aliceSecretNumber){
        return bobMessage.modPow(aliceSecretNumber, this.p);
    }

    public BigInteger bobCalculationOfKey(BigInteger aliceMessage, BigInteger bobSecretNumber){
        return aliceMessage.modPow(bobSecretNumber, this.p);
    }
}