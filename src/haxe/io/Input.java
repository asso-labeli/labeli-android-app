package haxe.io;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Input extends haxe.lang.HxObject
{
	public    Input(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Input()
	{
		haxe.io.Input.__hx_ctor_haxe_io_Input(this);
	}
	
	
	public static   void __hx_ctor_haxe_io_Input(haxe.io.Input __temp_me20)
	{
		{
		}
		
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxe.io.Input(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxe.io.Input();
	}
	
	
	public   int readByte()
	{
		throw haxe.lang.HaxeException.wrap("Not implemented");
	}
	
	
	public   int readBytes(haxe.io.Bytes s, int pos, int len)
	{
		int k = len;
		byte[] b = s.b;
		if (( ( ( pos < 0 ) || ( len < 0 ) ) || ( ( pos + len ) > s.length ) )) 
		{
			throw haxe.lang.HaxeException.wrap(haxe.io.Error.OutsideBounds);
		}
		
		while (( k > 0 ))
		{
			b[pos] = ((byte) (this.readByte()) );
			pos++;
			k--;
		}
		
		return len;
	}
	
	
	@Override public   java.lang.Object __hx_getField(java.lang.String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		{
			boolean __temp_executeDef212 = true;
			switch (field.hashCode())
			{
				case -1140063115:
				{
					if (field.equals("readBytes")) 
					{
						__temp_executeDef212 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("readBytes"))) );
					}
					
					break;
				}
				
				
				case -868060226:
				{
					if (field.equals("readByte")) 
					{
						__temp_executeDef212 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("readByte"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef212) 
			{
				return super.__hx_getField(field, throwErrors, isCheck, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_invokeField(java.lang.String field, haxe.root.Array dynargs)
	{
		{
			boolean __temp_executeDef213 = true;
			switch (field.hashCode())
			{
				case -1140063115:
				{
					if (field.equals("readBytes")) 
					{
						__temp_executeDef213 = false;
						return this.readBytes(((haxe.io.Bytes) (dynargs.__get(0)) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(2))) ));
					}
					
					break;
				}
				
				
				case -868060226:
				{
					if (field.equals("readByte")) 
					{
						__temp_executeDef213 = false;
						return this.readByte();
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef213) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
}


