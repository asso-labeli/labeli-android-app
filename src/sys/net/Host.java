package sys.net;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Host extends haxe.lang.HxObject
{
	public    Host(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Host(java.lang.String name)
	{
		sys.net.Host.__hx_ctor_sys_net_Host(this, name);
	}
	
	
	public static   void __hx_ctor_sys_net_Host(sys.net.Host __temp_me39, java.lang.String name)
	{
		try 
		{
			__temp_me39.wrapped = java.net.InetAddress.getByName(haxe.lang.Runtime.toString(name));
		}
		catch (java.lang.Throwable __temp_catchallException274)
		{
			java.lang.Object __temp_catchall275 = __temp_catchallException274;
			if (( __temp_catchall275 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall275 = ((haxe.lang.HaxeException) (__temp_catchallException274) ).obj;
			}
			
			{
				java.lang.Object e = __temp_catchall275;
				throw haxe.lang.HaxeException.wrap(e);
			}
			
		}
		
		
		byte[] rawIp = __temp_me39.wrapped.getAddress();
		__temp_me39.ip = ( ( ( ((int) (rawIp[3]) ) | ( ((int) (rawIp[2]) ) << 8 ) ) | ( ((int) (rawIp[1]) ) << 16 ) ) | ( ((int) (rawIp[0]) ) << 24 ) );
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new sys.net.Host(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new sys.net.Host(haxe.lang.Runtime.toString(arr.__get(0)));
	}
	
	
	public  int ip;
	
	public  java.net.InetAddress wrapped;
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef270 = true;
			switch (field.hashCode())
			{
				case 3367:
				{
					if (field.equals("ip")) 
					{
						__temp_executeDef270 = false;
						this.ip = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef270) 
			{
				return super.__hx_setField_f(field, value, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_setField(java.lang.String field, java.lang.Object value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef271 = true;
			switch (field.hashCode())
			{
				case 1595507845:
				{
					if (field.equals("wrapped")) 
					{
						__temp_executeDef271 = false;
						this.wrapped = ((java.net.InetAddress) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3367:
				{
					if (field.equals("ip")) 
					{
						__temp_executeDef271 = false;
						this.ip = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef271) 
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
			boolean __temp_executeDef272 = true;
			switch (field.hashCode())
			{
				case 1595507845:
				{
					if (field.equals("wrapped")) 
					{
						__temp_executeDef272 = false;
						return this.wrapped;
					}
					
					break;
				}
				
				
				case 3367:
				{
					if (field.equals("ip")) 
					{
						__temp_executeDef272 = false;
						return this.ip;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef272) 
			{
				return super.__hx_getField(field, throwErrors, isCheck, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   double __hx_getField_f(java.lang.String field, boolean throwErrors, boolean handleProperties)
	{
		{
			boolean __temp_executeDef273 = true;
			switch (field.hashCode())
			{
				case 3367:
				{
					if (field.equals("ip")) 
					{
						__temp_executeDef273 = false;
						return ((double) (this.ip) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef273) 
			{
				return super.__hx_getField_f(field, throwErrors, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("wrapped");
		baseArr.push("ip");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


