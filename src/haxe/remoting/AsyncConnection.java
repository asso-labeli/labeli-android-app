package haxe.remoting;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  interface AsyncConnection extends haxe.lang.IHxObject
{
	   haxe.remoting.AsyncConnection resolve(java.lang.String name);
	
	   void call(haxe.root.Array params, haxe.lang.Function result);
	
}


