package labeli;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class ApiAsyncProxy extends labeli.Async_Api
{
	public    ApiAsyncProxy(haxe.lang.EmptyObject empty)
	{
		super(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public    ApiAsyncProxy(haxe.remoting.AsyncConnection c)
	{
		super(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
		labeli.ApiAsyncProxy.__hx_ctor_labeli_ApiAsyncProxy(this, c);
	}
	
	
	public static   void __hx_ctor_labeli_ApiAsyncProxy(labeli.ApiAsyncProxy __temp_me37, haxe.remoting.AsyncConnection c)
	{
		labeli.Async_Api.__hx_ctor_labeli_Async_Api(__temp_me37, c);
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new labeli.ApiAsyncProxy(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new labeli.ApiAsyncProxy(((haxe.remoting.AsyncConnection) (arr.__get(0)) ));
	}
	
	
}


