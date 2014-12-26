package haxe.remoting;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class HttpAsyncConnection_call_55__Fun extends haxe.lang.Function
{
	public    HttpAsyncConnection_call_55__Fun(haxe.root.Array<haxe.lang.Function> error, haxe.root.Array<haxe.lang.Function> onResult1)
	{
		super(1, 0);
		this.error = error;
		this.onResult1 = onResult1;
	}
	
	
	@Override public   java.lang.Object __hx_invoke1_o(double __fn_float1, java.lang.Object __fn_dyn1)
	{
		java.lang.String response = ( (( __fn_dyn1 == haxe.lang.Runtime.undefined )) ? (haxe.lang.Runtime.toString(__fn_float1)) : (haxe.lang.Runtime.toString(__fn_dyn1)) );
		boolean ok = true;
		java.lang.Object ret = null;
		try 
		{
			if ( ! (haxe.lang.Runtime.valEq(haxe.lang.StringExt.substr(response, 0, 3), "hxr")) ) 
			{
				throw haxe.lang.HaxeException.wrap(( ( "Invalid response : \'" + response ) + "\'" ));
			}
			
			haxe.Unserializer s1 = new haxe.Unserializer(haxe.lang.Runtime.toString(haxe.lang.StringExt.substr(response, 3, null)));
			ret = s1.unserialize();
		}
		catch (java.lang.Throwable __temp_catchallException229)
		{
			java.lang.Object __temp_catchall230 = __temp_catchallException229;
			if (( __temp_catchall230 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall230 = ((haxe.lang.HaxeException) (__temp_catchallException229) ).obj;
			}
			
			{
				java.lang.Object err = __temp_catchall230;
				ret = null;
				ok = false;
				this.error.__get(0).__hx_invoke1_o(0.0, err);
			}
			
		}
		
		
		if (( ok && ( this.onResult1.__get(0) != null ) )) 
		{
			this.onResult1.__get(0).__hx_invoke1_o(0.0, ret);
		}
		
		return null;
	}
	
	
	public  haxe.root.Array<haxe.lang.Function> error;
	
	public  haxe.root.Array<haxe.lang.Function> onResult1;
	
}


