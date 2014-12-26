package labeli;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Labeli extends haxe.lang.HxObject
{
	public static void main(String[] args)
	{
		main();
	}
	public    Labeli(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Labeli(java.lang.String url)
	{
		labeli.Labeli.__hx_ctor_labeli_Labeli(this, url);
	}
	
	
	public static   void __hx_ctor_labeli_Labeli(labeli.Labeli __temp_me38, java.lang.String url)
	{
		haxe.remoting.HttpAsyncConnection asyncContext = haxe.remoting.HttpAsyncConnection.urlConnect(url);
		asyncContext.setErrorHandler(((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (__temp_me38) ), haxe.lang.Runtime.toString("onError"))) ));
		__temp_me38.async = new labeli.ApiAsyncProxy(((haxe.remoting.AsyncConnection) (asyncContext.resolve("api")) ));
	}
	
	
	public static   void main()
	{
		{
		}
		
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new labeli.Labeli(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new labeli.Labeli(haxe.lang.Runtime.toString(arr.__get(0)));
	}
	
	
	public  labeli.ApiAsyncProxy async;
	
	public   void onError(java.lang.Object e)
	{
		haxe.Log.trace.__hx_invoke2_o(0.0, e, 0.0, new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{"className", "fileName", "methodName"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{"labeli.Labeli", "Labeli.hx", "onError"}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"lineNumber"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (((double) (18) )) )})));
	}
	
	
	@Override public   java.lang.Object __hx_setField(java.lang.String field, java.lang.Object value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef267 = true;
			switch (field.hashCode())
			{
				case 93127292:
				{
					if (field.equals("async")) 
					{
						__temp_executeDef267 = false;
						this.async = ((labeli.ApiAsyncProxy) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef267) 
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
			boolean __temp_executeDef268 = true;
			switch (field.hashCode())
			{
				case -1349867671:
				{
					if (field.equals("onError")) 
					{
						__temp_executeDef268 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("onError"))) );
					}
					
					break;
				}
				
				
				case 93127292:
				{
					if (field.equals("async")) 
					{
						__temp_executeDef268 = false;
						return this.async;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef268) 
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
			boolean __temp_executeDef269 = true;
			switch (field.hashCode())
			{
				case -1349867671:
				{
					if (field.equals("onError")) 
					{
						__temp_executeDef269 = false;
						this.onError(dynargs.__get(0));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef269) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("async");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


