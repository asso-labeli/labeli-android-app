package haxe;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Http_customRequest_502__Fun extends haxe.lang.Function
{
	public    Http_customRequest_502__Fun()
	{
		super(1, 0);
	}
	
	
	public static  haxe.Http_customRequest_502__Fun __hx_current;
	
	@Override public   java.lang.Object __hx_invoke1_o(double __fn_float1, java.lang.Object __fn_dyn1)
	{
		java.lang.Object h = ( (( __fn_dyn1 == haxe.lang.Runtime.undefined )) ? (((java.lang.Object) (__fn_float1) )) : (((java.lang.Object) (__fn_dyn1) )) );
		return haxe.lang.Runtime.valEq(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(h, "header", true)), "Content-Type");
	}
	
	
}


