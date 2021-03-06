package primes.quadratic ;

import java.math.BigInteger ;
import primes.Item;
import primes.Bidimensional;

class Filter extends primes.Filter<Token> implements Bidimensional<Token> {
	Matrix column ;
	
	
// costruttori
Filter(Item<Token> tail, BigInteger p, Token r) {
	super(tail,p) ;
	
	BigInteger exp = BigInteger.ZERO ;
	
	while(test(r)) {
		exp = exp.add(BigInteger.ONE);
		System.out.println("dividing "+r.value2()+" by "+this.value()+"-> exponent "+exp);
		r.Set(r.value().divide(this.value()));
		//tok.SetPrimality(false) ;
	}
	
	
	try {
		tmp = ((Filter)tail).column();
		System.out.println(" reference to next object (step): "+tmp);
		this.column = new Matrix(tmp , this.setzerocolumn(tmp), exp);
	}
	catch (ClassCastException e) {
		
		tmp = null ;
		
		System.out.println(" reference to next object (2): "+tmp);
		this.column = new Matrix(tmp , tmp, exp);
		
	};
	
	
}
	
Filter(Item<Token> tail, BigInteger p) {
		super(tail,p) ;
		
		Item<Token> tmp;
		System.out.println(" new quadratic Filter "+" is token value = 2 ? "+p.compareTo(new BigInteger("2")));
		
		/*if( p.compareTo(new BigInteger("2")) == 0 )
				{
					tmp = null ;
			
					System.out.println(" reference to next object (2): "+tmp);
					this.column = new Matrix(tmp , tmp, BigInteger.ONE);
				}
			else*/
				try {	
					tmp = ((Filter)tail).column();
					System.out.println(" reference to next object (step): "+tmp);
					this.column = new Matrix(tmp , this.setzerocolumn(tmp), BigInteger.ONE);	
				}
				catch (ClassCastException e) {
					
				tmp = null ;
					
					System.out.println(" reference to next object (2): "+tmp);
					this.column = new Matrix(tmp , tmp, BigInteger.ONE);			

					};
		
	

		//this.column = new Matrix(tmp , tmp, BigInteger.ONE);
		
		System.out.println(" fine creazione filtro ");
	}

private Item<Token> setzerocolumn(Item r) {
		if (((Matrix)r).column() != null)
			return (new Matrix(((Matrix)r).column(),this.setzerocolumn(((Matrix)r).column()),BigInteger.ZERO));
		else
			return null ;
	}

public boolean test(Token t) {
		return (t.value().mod(this.value()).compareTo(BigInteger.ZERO) == 0) ;
	}
	
	
private Token  factorize(Token tok)  {
		Item<Token> tmp;
		
		BigInteger exp = BigInteger.ZERO ;
		
		while(test(tok)) {
			exp = exp.add(BigInteger.ONE);
			System.out.println("dividing "+tok.value2()+" by "+this.value()+"-> exponent "+exp);
			tok.Set(tok.value().divide(this.value()));
			//tok.SetPrimality(false) ;
		}
				
		/*if( this.value().compareTo(new BigInteger("2")) == 0 )
		{
			tmp = null ;
	
			//System.out.println(" reference to next object (2): "+tmp);
			this.column = new Matrix(tmp , this.column(), exp);
		}
	else*/
		try	{	
			tmp = ((Filter)this.next()).column();
			//System.out.println(" reference to next object (step): "+tmp);
			this.column = new Matrix(tmp , this.column(), exp);
		}
		catch (ClassCastException e) {
	
			tmp = null ;
			this.column = new Matrix(tmp , this.column(), exp);
		};

		
		//this.column = new Matrix( ((Filter) this.next()).column(), this.column(), exp ) ;
	return tok;
	}
	

/**
 
 */
public Token get() {
		Token token;
		token= this.next().get() ;
		
			token = factorize(token);
		
			return token;
		
	}
	
	
public Item<Token> column () {
		
		return this.column ;
	}

}










