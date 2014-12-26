package com.app.callback;


public class APICallback extends haxe.lang.Function
{
	
	@SuppressWarnings("rawtypes")
	private haxe.root.Array array;
	
    public APICallback()
    {
        super(0,0);
    }
    
    

    @SuppressWarnings("rawtypes")
	public haxe.root.Array getArray() {
		return array;
	}



	public void setArray(@SuppressWarnings("rawtypes") haxe.root.Array array) {
		this.array = array;
	}



	@Override public java.lang.Object __hx_invoke1_o(double _, java.lang.Object result)
    {
        if(result instanceof haxe.root.Array)
        {
            @SuppressWarnings("rawtypes")
			haxe.root.Array eventArray = (haxe.root.Array) result;

            
            this.array = eventArray;
        }
        return null;
    }
}