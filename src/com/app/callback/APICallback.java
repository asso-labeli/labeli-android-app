package com.app.callback;

import android.util.Log;

public class APICallback extends haxe.lang.Function
{
	
	private haxe.root.Array array;
	
    public APICallback()
    {
        super(0,0);
    }
    
    

    public haxe.root.Array getArray() {
		return array;
	}



	public void setArray(haxe.root.Array array) {
		this.array = array;
	}



	@Override public java.lang.Object __hx_invoke1_o(double _, java.lang.Object result)
    {
        if(result instanceof haxe.root.Array)
        {
            haxe.root.Array eventArray = (haxe.root.Array) result;

            
            this.array = eventArray;
        }
        return null;
    }
}