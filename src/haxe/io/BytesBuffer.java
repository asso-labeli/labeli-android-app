package haxe.io;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class BytesBuffer extends haxe.lang.HxObject
{
	public    BytesBuffer(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    BytesBuffer()
	{
		haxe.io.BytesBuffer.__hx_ctor_haxe_io_BytesBuffer(this);
	}
	
	
	public static   void __hx_ctor_haxe_io_BytesBuffer(haxe.io.BytesBuffer __temp_me19)
	{
		__temp_me19.b = new java.io.ByteArrayOutputStream();
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxe.io.BytesBuffer(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxe.io.BytesBuffer();
	}
	
	
	public  java.io.ByteArrayOutputStream b;
	
	public   haxe.io.Bytes getBytes()
	{
		byte[] buf = this.b.toByteArray();
		haxe.io.Bytes bytes = new haxe.io.Bytes(((int) (buf.length) ), ((byte[]) (buf) ));
		this.b = null;
		return bytes;
	}
	
	
	@Override public   java.lang.Object __hx_setField(java.lang.String field, java.lang.Object value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef209 = true;
			switch (field.hashCode())
			{
				case 98:
				{
					if (field.equals("b")) 
					{
						__temp_executeDef209 = false;
						this.b = ((java.io.ByteArrayOutputStream) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef209) 
			{
				return super.__hx_setField(field, value, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_getField(java.lang.String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		{
			boolean __temp_executeDef210 = true;
			switch (field.hashCode())
			{
				case 1950049973:
				{
					if (field.equals("getBytes")) 
					{
						__temp_executeDef210 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getBytes"))) );
					}
					
					break;
				}
				
				
				case 98:
				{
					if (field.equals("b")) 
					{
						__temp_executeDef210 = false;
						return this.b;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef210) 
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
			boolean __temp_executeDef211 = true;
			switch (field.hashCode())
			{
				case 1950049973:
				{
					if (field.equals("getBytes")) 
					{
						__temp_executeDef211 = false;
						return this.getBytes();
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef211) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("b");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


