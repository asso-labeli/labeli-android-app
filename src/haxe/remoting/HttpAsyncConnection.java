package haxe.remoting;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class HttpAsyncConnection extends haxe.lang.DynamicObject implements haxe.remoting.AsyncConnection
{
	public    HttpAsyncConnection(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    HttpAsyncConnection(java.lang.Object data, haxe.root.Array<java.lang.String> path)
	{
		haxe.remoting.HttpAsyncConnection.__hx_ctor_haxe_remoting_HttpAsyncConnection(this, data, path);
	}
	
	
	public static   void __hx_ctor_haxe_remoting_HttpAsyncConnection(haxe.remoting.HttpAsyncConnection __temp_me24, java.lang.Object data, haxe.root.Array<java.lang.String> path)
	{
		__temp_me24.__data = data;
		__temp_me24.__path = path;
	}
	
	
	public static   haxe.remoting.HttpAsyncConnection urlConnect(java.lang.String url)
	{
		java.lang.Object __temp_stmt228 = null;
		{
			haxe.lang.Function __temp_odecl227 = ( (( haxe.remoting.HttpAsyncConnection_urlConnect_74__Fun.__hx_current != null )) ? (haxe.remoting.HttpAsyncConnection_urlConnect_74__Fun.__hx_current) : (haxe.remoting.HttpAsyncConnection_urlConnect_74__Fun.__hx_current = ((haxe.remoting.HttpAsyncConnection_urlConnect_74__Fun) (new haxe.remoting.HttpAsyncConnection_urlConnect_74__Fun()) )) );
			__temp_stmt228 = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{"error", "url"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{__temp_odecl227, url}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}));
		}
		
		return new haxe.remoting.HttpAsyncConnection(((java.lang.Object) (__temp_stmt228) ), ((haxe.root.Array<java.lang.String>) (new haxe.root.Array<java.lang.String>(new java.lang.String[]{})) ));
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxe.remoting.HttpAsyncConnection(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxe.remoting.HttpAsyncConnection(((java.lang.Object) (arr.__get(0)) ), ((haxe.root.Array<java.lang.String>) (arr.__get(1)) ));
	}
	
	
	public  java.lang.Object __data;
	
	public  haxe.root.Array<java.lang.String> __path;
	
	public   haxe.remoting.AsyncConnection resolve(java.lang.String name)
	{
		haxe.remoting.HttpAsyncConnection c = new haxe.remoting.HttpAsyncConnection(((java.lang.Object) (this.__data) ), ((haxe.root.Array<java.lang.String>) (this.__path.copy()) ));
		c.__path.push(name);
		return c;
	}
	
	
	public   void setErrorHandler(haxe.lang.Function h)
	{
		haxe.lang.Runtime.setField(this.__data, "error", h);
	}
	
	
	public   void call(haxe.root.Array params, haxe.lang.Function onResult)
	{
		haxe.root.Array<haxe.lang.Function> onResult1 = new haxe.root.Array<haxe.lang.Function>(new haxe.lang.Function[]{onResult});
		haxe.Http h = new haxe.Http(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(this.__data, "url", true)));
		haxe.Serializer s = new haxe.Serializer();
		s.serialize(this.__path);
		s.serialize(params);
		h.setHeader("X-Haxe-Remoting", "1");
		h.setParameter("__x", s.toString());
		haxe.root.Array<haxe.lang.Function> error = new haxe.root.Array<haxe.lang.Function>(new haxe.lang.Function[]{((haxe.lang.Function) (haxe.lang.Runtime.getField(this.__data, "error", true)) )});
		h.onData = new haxe.remoting.HttpAsyncConnection_call_55__Fun(((haxe.root.Array<haxe.lang.Function>) (error) ), ((haxe.root.Array<haxe.lang.Function>) (onResult1) ));
		h.onError = error.__get(0);
		h.request(true);
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef222 = true;
			switch (field.hashCode())
			{
				case -1484387446:
				{
					if (field.equals("__data")) 
					{
						__temp_executeDef222 = false;
						this.__data = ((java.lang.Object) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef222) 
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
			boolean __temp_executeDef223 = true;
			switch (field.hashCode())
			{
				case -1484029947:
				{
					if (field.equals("__path")) 
					{
						__temp_executeDef223 = false;
						this.__path = ((haxe.root.Array<java.lang.String>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1484387446:
				{
					if (field.equals("__data")) 
					{
						__temp_executeDef223 = false;
						this.__data = ((java.lang.Object) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef223) 
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
			boolean __temp_executeDef224 = true;
			switch (field.hashCode())
			{
				case 3045982:
				{
					if (field.equals("call")) 
					{
						__temp_executeDef224 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("call"))) );
					}
					
					break;
				}
				
				
				case -1484387446:
				{
					if (field.equals("__data")) 
					{
						__temp_executeDef224 = false;
						return this.__data;
					}
					
					break;
				}
				
				
				case -816590428:
				{
					if (field.equals("setErrorHandler")) 
					{
						__temp_executeDef224 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("setErrorHandler"))) );
					}
					
					break;
				}
				
				
				case -1484029947:
				{
					if (field.equals("__path")) 
					{
						__temp_executeDef224 = false;
						return this.__path;
					}
					
					break;
				}
				
				
				case 1097368044:
				{
					if (field.equals("resolve")) 
					{
						__temp_executeDef224 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("resolve"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef224) 
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
			boolean __temp_executeDef225 = true;
			switch (field.hashCode())
			{
				case -1484387446:
				{
					if (field.equals("__data")) 
					{
						__temp_executeDef225 = false;
						return ((double) (haxe.lang.Runtime.toDouble(this.__data)) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef225) 
			{
				return super.__hx_getField_f(field, throwErrors, handleProperties);
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
			boolean __temp_executeDef226 = true;
			switch (field.hashCode())
			{
				case 3045982:
				{
					if (field.equals("call")) 
					{
						__temp_executeDef226 = false;
						this.call(((haxe.root.Array) (dynargs.__get(0)) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 1097368044:
				{
					if (field.equals("resolve")) 
					{
						__temp_executeDef226 = false;
						return this.resolve(haxe.lang.Runtime.toString(dynargs.__get(0)));
					}
					
					break;
				}
				
				
				case -816590428:
				{
					if (field.equals("setErrorHandler")) 
					{
						__temp_executeDef226 = false;
						this.setErrorHandler(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef226) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("__path");
		baseArr.push("__data");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


