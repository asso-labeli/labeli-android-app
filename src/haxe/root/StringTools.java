package haxe.root;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class StringTools extends haxe.lang.HxObject
{
	public    StringTools(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    StringTools()
	{
		haxe.root.StringTools.__hx_ctor__StringTools(this);
	}
	
	
	public static   void __hx_ctor__StringTools(haxe.root.StringTools __temp_me9)
	{
		{
		}
		
	}
	
	
	public static   java.lang.String urlEncode(java.lang.String s)
	{
		try 
		{
			return java.net.URLEncoder.encode(s, "UTF-8");
		}
		catch (java.lang.Throwable __temp_catchallException85)
		{
			java.lang.Object __temp_catchall86 = __temp_catchallException85;
			if (( __temp_catchall86 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall86 = ((haxe.lang.HaxeException) (__temp_catchallException85) ).obj;
			}
			
			{
				java.lang.Object e = __temp_catchall86;
				throw haxe.lang.HaxeException.wrap(e);
			}
			
		}
		
		
	}
	
	
	public static   java.lang.String urlDecode(java.lang.String s)
	{
		try 
		{
			return java.net.URLDecoder.decode(s, "UTF-8");
		}
		catch (java.lang.Throwable __temp_catchallException87)
		{
			java.lang.Object __temp_catchall88 = __temp_catchallException87;
			if (( __temp_catchall88 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall88 = ((haxe.lang.HaxeException) (__temp_catchallException87) ).obj;
			}
			
			{
				java.lang.Object e = __temp_catchall88;
				throw haxe.lang.HaxeException.wrap(e);
			}
			
		}
		
		
	}
	
	
	public static   boolean isSpace(java.lang.String s, int pos)
	{
		java.lang.Object c = haxe.lang.StringExt.charCodeAt(s, pos);
		return ( ( ( haxe.lang.Runtime.compare(c, 8) > 0 ) && ( haxe.lang.Runtime.compare(c, 14) < 0 ) ) || haxe.lang.Runtime.eq(c, 32) );
	}
	
	
	public static   java.lang.String ltrim(java.lang.String s)
	{
		int l = s.length();
		int r = 0;
		while (( ( r < l ) && haxe.root.StringTools.isSpace(s, r) ))
		{
			r++;
		}
		
		if (( r > 0 )) 
		{
			return haxe.lang.StringExt.substr(s, r, ( l - r ));
		}
		 else 
		{
			return s;
		}
		
	}
	
	
	public static   java.lang.String rtrim(java.lang.String s)
	{
		int l = s.length();
		int r = 0;
		while (( ( r < l ) && haxe.root.StringTools.isSpace(s, ( ( l - r ) - 1 )) ))
		{
			r++;
		}
		
		if (( r > 0 )) 
		{
			return haxe.lang.StringExt.substr(s, 0, ( l - r ));
		}
		 else 
		{
			return s;
		}
		
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxe.root.StringTools(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxe.root.StringTools();
	}
	
	
}


