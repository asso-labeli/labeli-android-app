package sys.net;

@SuppressWarnings(value={"rawtypes"})
public  class Socket extends haxe.lang.HxObject
{
	public    Socket(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Socket()
	{
		sys.net.Socket.__hx_ctor_sys_net_Socket(this);
	}
	
	
	public static   void __hx_ctor_sys_net_Socket(sys.net.Socket __temp_me34)
	{
		__temp_me34.create();
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new sys.net.Socket(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new sys.net.Socket();
	}
	
	
	public  haxe.io.Input input;
	
	public  haxe.io.Output output;
	
	public  java.net.Socket sock;
	
	public  java.net.ServerSocket server;
	
	public   void create()
	{
		this.sock = new java.net.Socket();
		try 
		{
			this.server = new java.net.ServerSocket();
		}
		catch (java.lang.Throwable __temp_catchallException248)
		{
			java.lang.Object __temp_catchall249 = __temp_catchallException248;
			if (( __temp_catchall249 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall249 = ((haxe.lang.HaxeException) (__temp_catchallException248) ).obj;
			}
			
			{
				java.lang.Object e = __temp_catchall249;
				throw haxe.lang.HaxeException.wrap(e);
			}
			
		}
		
		
	}
	
	
	public   void close()
	{
		try 
		{
			if (( this.sock != null )) 
			{
				this.sock.close();
			}
			
			if (( this.server != null )) 
			{
				this.server.close();
			}
			
		}
		catch (java.lang.Throwable __temp_catchallException250)
		{
			java.lang.Object __temp_catchall251 = __temp_catchallException250;
			if (( __temp_catchall251 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall251 = ((haxe.lang.HaxeException) (__temp_catchallException250) ).obj;
			}
			
			{
				java.lang.Object e = __temp_catchall251;
				throw haxe.lang.HaxeException.wrap(e);
			}
			
		}
		
		
	}
	
	
	public   void write(java.lang.String content)
	{
		this.output.writeString(content);
	}
	
	
	public   void connect(sys.net.Host host, int port)
	{
		try 
		{
			this.sock.connect(((java.net.SocketAddress) (new java.net.InetSocketAddress(((java.net.InetAddress) (host.wrapped) ), ((int) (port) ))) ));
			this.output = new haxe.java.io.NativeOutput(((java.io.OutputStream) (this.sock.getOutputStream()) ));
			this.input = new haxe.java.io.NativeInput(((java.io.InputStream) (this.sock.getInputStream()) ));
		}
		catch (java.lang.Throwable __temp_catchallException252)
		{
			java.lang.Object __temp_catchall253 = __temp_catchallException252;
			if (( __temp_catchall253 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall253 = ((haxe.lang.HaxeException) (__temp_catchallException252) ).obj;
			}
			
			{
				java.lang.Object e = __temp_catchall253;
				throw haxe.lang.HaxeException.wrap(e);
			}
			
		}
		
		
	}
	
	
	public   void shutdown(boolean read, boolean write)
	{
		try 
		{
			if (read) 
			{
				this.sock.shutdownInput();
			}
			
			if (write) 
			{
				this.sock.shutdownOutput();
			}
			
		}
		catch (java.lang.Throwable __temp_catchallException254)
		{
			java.lang.Object __temp_catchall255 = __temp_catchallException254;
			if (( __temp_catchall255 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall255 = ((haxe.lang.HaxeException) (__temp_catchallException254) ).obj;
			}
			
			{
				java.lang.Object e = __temp_catchall255;
				throw haxe.lang.HaxeException.wrap(e);
			}
			
		}
		
		
	}
	
	
	public   void setTimeout(double timeout)
	{
		try 
		{
			this.sock.setSoTimeout(((int) (( timeout * 1000 )) ));
		}
		catch (java.lang.Throwable __temp_catchallException256)
		{
			java.lang.Object __temp_catchall257 = __temp_catchallException256;
			if (( __temp_catchall257 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall257 = ((haxe.lang.HaxeException) (__temp_catchallException256) ).obj;
			}
			
			{
				java.lang.Object e = __temp_catchall257;
				throw haxe.lang.HaxeException.wrap(e);
			}
			
		}
		
		
	}
	
	
	@Override public   java.lang.Object __hx_setField(java.lang.String field, java.lang.Object value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef258 = true;
			switch (field.hashCode())
			{
				case -905826493:
				{
					if (field.equals("server")) 
					{
						__temp_executeDef258 = false;
						this.server = ((java.net.ServerSocket) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 100358090:
				{
					if (field.equals("input")) 
					{
						__temp_executeDef258 = false;
						this.input = ((haxe.io.Input) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3535812:
				{
					if (field.equals("sock")) 
					{
						__temp_executeDef258 = false;
						this.sock = ((java.net.Socket) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1005512447:
				{
					if (field.equals("output")) 
					{
						__temp_executeDef258 = false;
						this.output = ((haxe.io.Output) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef258) 
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
			boolean __temp_executeDef259 = true;
			switch (field.hashCode())
			{
				case 1659754143:
				{
					if (field.equals("setTimeout")) 
					{
						__temp_executeDef259 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("setTimeout"))) );
					}
					
					break;
				}
				
				
				case 100358090:
				{
					if (field.equals("input")) 
					{
						__temp_executeDef259 = false;
						return this.input;
					}
					
					break;
				}
				
				
				case -169343402:
				{
					if (field.equals("shutdown")) 
					{
						__temp_executeDef259 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("shutdown"))) );
					}
					
					break;
				}
				
				
				case -1005512447:
				{
					if (field.equals("output")) 
					{
						__temp_executeDef259 = false;
						return this.output;
					}
					
					break;
				}
				
				
				case 951351530:
				{
					if (field.equals("connect")) 
					{
						__temp_executeDef259 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("connect"))) );
					}
					
					break;
				}
				
				
				case 3535812:
				{
					if (field.equals("sock")) 
					{
						__temp_executeDef259 = false;
						return this.sock;
					}
					
					break;
				}
				
				
				case 113399775:
				{
					if (field.equals("write")) 
					{
						__temp_executeDef259 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("write"))) );
					}
					
					break;
				}
				
				
				case -905826493:
				{
					if (field.equals("server")) 
					{
						__temp_executeDef259 = false;
						return this.server;
					}
					
					break;
				}
				
				
				case 94756344:
				{
					if (field.equals("close")) 
					{
						__temp_executeDef259 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("close"))) );
					}
					
					break;
				}
				
				
				case -1352294148:
				{
					if (field.equals("create")) 
					{
						__temp_executeDef259 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("create"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef259) 
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
			boolean __temp_executeDef260 = true;
			switch (field.hashCode())
			{
				case 1659754143:
				{
					if (field.equals("setTimeout")) 
					{
						__temp_executeDef260 = false;
						this.setTimeout(((double) (haxe.lang.Runtime.toDouble(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case -1352294148:
				{
					if (field.equals("create")) 
					{
						__temp_executeDef260 = false;
						this.create();
					}
					
					break;
				}
				
				
				case -169343402:
				{
					if (field.equals("shutdown")) 
					{
						__temp_executeDef260 = false;
						this.shutdown(haxe.lang.Runtime.toBool(dynargs.__get(0)), haxe.lang.Runtime.toBool(dynargs.__get(1)));
					}
					
					break;
				}
				
				
				case 94756344:
				{
					if (field.equals("close")) 
					{
						__temp_executeDef260 = false;
						this.close();
					}
					
					break;
				}
				
				
				case 951351530:
				{
					if (field.equals("connect")) 
					{
						__temp_executeDef260 = false;
						this.connect(((sys.net.Host) (dynargs.__get(0)) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case 113399775:
				{
					if (field.equals("write")) 
					{
						__temp_executeDef260 = false;
						this.write(haxe.lang.Runtime.toString(dynargs.__get(0)));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef260) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("server");
		baseArr.push("sock");
		baseArr.push("output");
		baseArr.push("input");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


