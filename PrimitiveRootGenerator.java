import java.math.BigInteger;
import java.util.Random;
import java.util.Vector;

public class PrimitiveRootGenerator{
    long pr, p, phi;
    public PrimitiveRootGenerator(long p){
        this.p = p;
        this.phi = p-1;

        Vector<Long> primitiveRoots = this.getPrimitiveRoots(this.p, this.phi);
        this.pr = primitiveRoots.get(new Random().nextInt(primitiveRoots.size()));
    }

    public long getPrimitiveRoot(){
        return pr;
    }
    
    private Vector<Long> getPrimitiveRoots(long p, long phi){
        Vector<Long> primeFactors = this.getPrimeFactorsList(phi); 
        Vector<Long> primitiveRoots = new Vector<>(); 

        for(long i=2; i<p; i++){
            boolean flag=false; 

            for(long l: primeFactors){
                BigInteger iBig = BigInteger.valueOf(i); 
                BigInteger pBig = BigInteger.valueOf(p);
                BigInteger phiBig = BigInteger.valueOf(phi/l);
                
                BigInteger pRootBig = iBig.modPow(phiBig, pBig); 
                if(pRootBig.compareTo(BigInteger.valueOf(1))==0){
                    flag=true; 
                    break;
                }
            }

            if(!flag) primitiveRoots.add((long) i); 
        }
        return primitiveRoots;
    }

    private Vector<Long> getPrimeFactorsList(long phi){
        Vector<Long> primeFactors = new Vector<>();
        while(phi%2 == 0){
            primeFactors.add((long)2); 
            phi /= 2;
        }

        for(long i=3; i*i <= Math.sqrt(phi); i+=2)
            while(phi%i == 0){
                primeFactors.add((long) i); 
                phi /= i; 
            }

        if(phi > 2) primeFactors.add((long) phi); 
        return primeFactors;
    }
}