package haxe.root;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Lambda extends haxe.lang.HxObject
{
	public    Lambda(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Lambda()
	{
		haxe.root.Lambda.__hx_ctor__Lambda(this);
	}
	
	
	public static   void __hx_ctor__Lambda(haxe.root.Lambda __temp_me5)
	{
		{
		}
		
	}
	
	
	public static  <A> boolean exists(java.lang.Object it, haxe.lang.Function f)
	{
		{
			java.lang.Object __temp_iterator40 = ((java.lang.Object) (haxe.lang.Runtime.callField(it, "iterator", null)) );
			while (haxe.lang.Runtime.toBool(haxe.lang.Runtime.callField(__temp_iterator40, "hasNext", null)))
			{
				A x = ((A) (haxe.lang.Runtime.callField(__temp_iterator40, "next", null)) );
				if (haxe.lang.Runtime.toBool(f.__hx_invoke1_o(0.0, x))) 
				{
					return true;
				}
				
			}
			
		}
		
		return false;
	}
	
	
	public static  <A> haxe.root.List<A> filter(java.lang.Object it, haxe.lang.Function f)
	{
		haxe.root.List<A> l = new haxe.root.List<A>();
		{
			java.lang.Object __temp_iterator41 = ((java.lang.Object) (haxe.lang.Runtime.callField(it, "iterator", null)) );
			while (haxe.lang.Runtime.toBool(haxe.lang.Runtime.callField(__temp_iterator41, "hasNext", null)))
			{
				A x = ((A) (haxe.lang.Runtime.callField(__temp_iterator41, "next", null)) );
				if (haxe.lang.Runtime.toBool(f.__hx_invoke1_o(0.0, x))) 
				{
					l.add(x);
				}
				
			}
			
		}
		
		return l;
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxe.root.Lambda(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxe.root.Lambda();
	}
	
	
}


