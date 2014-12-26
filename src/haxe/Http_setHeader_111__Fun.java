package haxe;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Http_setHeader_111__Fun extends haxe.lang.Function
{
	public    Http_setHeader_111__Fun(haxe.root.Array<java.lang.String> header1)
	{
		super(1, 0);
		this.header1 = header1;
	}
	
	
	@Override public   java.lang.Object __hx_invoke1_o(double __fn_float1, java.lang.Object __fn_dyn1)
	{
		java.lang.Object h = ( (( __fn_dyn1 == haxe.lang.Runtime.undefined )) ? (((java.lang.Object) (__fn_float1) )) : (((java.lang.Object) (__fn_dyn1) )) );
		return  ! (haxe.lang.Runtime.valEq(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(h, "header", true)), this.header1.__get(0))) ;
	}
	
	
	public  haxe.root.Array<java.lang.String> header1;
	
}


