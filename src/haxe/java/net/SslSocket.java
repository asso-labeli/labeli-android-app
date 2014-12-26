package haxe.java.net;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class SslSocket extends sys.net.Socket
{
	public    SslSocket(haxe.lang.EmptyObject empty)
	{
		super(haxe.lang.EmptyObject.EMPTY);
	}
	
	
	public    SslSocket()
	{
		super(haxe.lang.EmptyObject.EMPTY);
		haxe.java.net.SslSocket.__hx_ctor_haxe_java_net_SslSocket(this);
	}
	
	
	public static   void __hx_ctor_haxe_java_net_SslSocket(haxe.java.net.SslSocket __temp_me35)
	{
		sys.net.Socket.__hx_ctor_sys_net_Socket(__temp_me35);
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxe.java.net.SslSocket(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxe.java.net.SslSocket();
	}
	
	
	@Override public   void create()
	{
		try 
		{
			this.sock = javax.net.ssl.SSLSocketFactory.getDefault().createSocket();
			this.server = javax.net.ssl.SSLServerSocketFactory.getDefault().createServerSocket();
		}
		catch (java.lang.Throwable __temp_catchallException261)
		{
			java.lang.Object __temp_catchall262 = __temp_catchallException261;
			if (( __temp_catchall262 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall262 = ((haxe.lang.HaxeException) (__temp_catchallException261) ).obj;
			}
			
			{
				java.lang.Object e = __temp_catchall262;
				throw haxe.lang.HaxeException.wrap(e);
			}
			
		}
		
		
	}
	
	
	@Override public   java.lang.Object __hx_getField(java.lang.String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		{
			boolean __temp_executeDef263 = true;
			switch (field.hashCode())
			{
				case -1352294148:
				{
					if (field.equals("create")) 
					{
						__temp_executeDef263 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("create"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef263) 
			{
				return super.__hx_getField(field, throwErrors, isCheck, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
}


