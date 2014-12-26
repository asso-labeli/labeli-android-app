package haxe;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Http_request_367__Fun extends haxe.lang.Function
{
	public    Http_request_367__Fun(haxe.root.Array<haxe.lang.Function> old, haxe.root.Array<haxe.Http> me1, haxe.root.Array<haxe.io.BytesOutput> output, haxe.root.Array<java.lang.Object> err)
	{
		super(1, 0);
		this.old = old;
		this.me1 = me1;
		this.output = output;
		this.err = err;
	}
	
	
	@Override public   java.lang.Object __hx_invoke1_o(double __fn_float1, java.lang.Object __fn_dyn1)
	{
		java.lang.String e = ( (( __fn_dyn1 == haxe.lang.Runtime.undefined )) ? (haxe.lang.Runtime.toString(__fn_float1)) : (haxe.lang.Runtime.toString(__fn_dyn1)) );
		this.me1.__get(0).responseData = this.output.__get(0).getBytes().toString();
		this.err.__set(0, true);
		this.old.__get(0).__hx_invoke1_o(0.0, e);
		return null;
	}
	
	
	public  haxe.root.Array<haxe.lang.Function> old;
	
	public  haxe.root.Array<haxe.Http> me1;
	
	public  haxe.root.Array<haxe.io.BytesOutput> output;
	
	public  haxe.root.Array<java.lang.Object> err;
	
}


