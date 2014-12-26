package haxe;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Http extends haxe.lang.HxObject
{
	static 
	{
		haxe.Http.PROXY = null;
	}
	public    Http(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Http(java.lang.String url)
	{
		haxe.Http.__hx_ctor_haxe_Http(this, url);
	}
	
	
	public static   void __hx_ctor_haxe_Http(haxe.Http __temp_me11, java.lang.String url)
	{
		__temp_me11.onStatus = ( (( haxe.Http___hx_ctor_haxe_Http_785__Fun.__hx_current != null )) ? (haxe.Http___hx_ctor_haxe_Http_785__Fun.__hx_current) : (haxe.Http___hx_ctor_haxe_Http_785__Fun.__hx_current = ((haxe.Http___hx_ctor_haxe_Http_785__Fun) (new haxe.Http___hx_ctor_haxe_Http_785__Fun()) )) );
		__temp_me11.onError = ( (( haxe.Http___hx_ctor_haxe_Http_775__Fun.__hx_current != null )) ? (haxe.Http___hx_ctor_haxe_Http_775__Fun.__hx_current) : (haxe.Http___hx_ctor_haxe_Http_775__Fun.__hx_current = ((haxe.Http___hx_ctor_haxe_Http_775__Fun) (new haxe.Http___hx_ctor_haxe_Http_775__Fun()) )) );
		__temp_me11.onData = ( (( haxe.Http___hx_ctor_haxe_Http_765__Fun.__hx_current != null )) ? (haxe.Http___hx_ctor_haxe_Http_765__Fun.__hx_current) : (haxe.Http___hx_ctor_haxe_Http_765__Fun.__hx_current = ((haxe.Http___hx_ctor_haxe_Http_765__Fun) (new haxe.Http___hx_ctor_haxe_Http_765__Fun()) )) );
		__temp_me11.url = url;
		__temp_me11.headers = new haxe.root.List<java.lang.Object>();
		__temp_me11.params = new haxe.root.List<java.lang.Object>();
		__temp_me11.cnxTimeout = ((double) (10) );
	}
	
	
	public static  java.lang.Object PROXY;
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxe.Http(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxe.Http(haxe.lang.Runtime.toString(arr.__get(0)));
	}
	
	
	public  java.lang.String url;
	
	public  java.lang.String responseData;
	
	public  boolean noShutdown;
	
	public  double cnxTimeout;
	
	public  haxe.ds.StringMap<java.lang.String> responseHeaders;
	
	public  java.lang.Object chunk_size;
	
	public  haxe.io.Bytes chunk_buf;
	
	public  java.lang.Object file;
	
	public  java.lang.String postData;
	
	public  haxe.root.List<java.lang.Object> headers;
	
	public  haxe.root.List<java.lang.Object> params;
	
	public   haxe.Http setHeader(java.lang.String header, java.lang.String value)
	{
		haxe.root.Array<java.lang.String> header1 = new haxe.root.Array<java.lang.String>(new java.lang.String[]{header});
		this.headers = haxe.root.Lambda.filter(this.headers, new haxe.Http_setHeader_111__Fun(((haxe.root.Array<java.lang.String>) (header1) )));
		java.lang.Object __temp_stmt93 = null;
		{
			java.lang.String __temp_odecl92 = header1.__get(0);
			__temp_stmt93 = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{"header", "value"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{__temp_odecl92, value}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}));
		}
		
		this.headers.push(__temp_stmt93);
		return this;
	}
	
	
	public   haxe.Http setParameter(java.lang.String param, java.lang.String value)
	{
		haxe.root.Array<java.lang.String> param1 = new haxe.root.Array<java.lang.String>(new java.lang.String[]{param});
		this.params = haxe.root.Lambda.filter(this.params, new haxe.Http_setParameter_129__Fun(((haxe.root.Array<java.lang.String>) (param1) )));
		java.lang.Object __temp_stmt95 = null;
		{
			java.lang.String __temp_odecl94 = param1.__get(0);
			__temp_stmt95 = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{"param", "value"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{__temp_odecl94, value}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}));
		}
		
		this.params.push(__temp_stmt95);
		return this;
	}
	
	
	public   void request(java.lang.Object post)
	{
		haxe.Http me = this;
		haxe.root.Array<haxe.Http> me1 = new haxe.root.Array<haxe.Http>(new haxe.Http[]{this});
		haxe.root.Array<haxe.io.BytesOutput> output = new haxe.root.Array<haxe.io.BytesOutput>(new haxe.io.BytesOutput[]{new haxe.io.BytesOutput()});
		haxe.root.Array<haxe.lang.Function> old = new haxe.root.Array<haxe.lang.Function>(new haxe.lang.Function[]{this.onError});
		haxe.root.Array<java.lang.Object> err = new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{false});
		this.onError = new haxe.Http_request_367__Fun(((haxe.root.Array<haxe.lang.Function>) (old) ), ((haxe.root.Array<haxe.Http>) (me1) ), ((haxe.root.Array<haxe.io.BytesOutput>) (output) ), ((haxe.root.Array<java.lang.Object>) (err) ));
		this.customRequest(haxe.lang.Runtime.toBool(post), output.__get(0), null, null);
		if ( ! (haxe.lang.Runtime.toBool(err.__get(0))) ) 
		{
			me1.__get(0).onData.__hx_invoke1_o(0.0, me1.__get(0).responseData = output.__get(0).getBytes().toString());
		}
		
	}
	
	
	public   void customRequest(boolean post, haxe.io.Output api, java.lang.Object sock, java.lang.String method)
	{
		this.responseData = null;
		haxe.root.EReg url_regexp = new haxe.root.EReg(haxe.lang.Runtime.toString("^(https?://)?([a-zA-Z\\.0-9-]+)(:[0-9]+)?(.*)$"), haxe.lang.Runtime.toString(""));
		if ( ! (url_regexp.match(this.url)) ) 
		{
			this.onError.__hx_invoke1_o(0.0, "Invalid URL");
			return ;
		}
		
		boolean secure = haxe.lang.Runtime.valEq(url_regexp.matched(1), "https://");
		if (( sock == null )) 
		{
			if (secure) 
			{
				sock = new haxe.java.net.SslSocket();
			}
			 else 
			{
				sock = new sys.net.Socket();
			}
			
		}
		
		java.lang.String host = url_regexp.matched(2);
		java.lang.String portString = url_regexp.matched(3);
		java.lang.String request = url_regexp.matched(4);
		if (haxe.lang.Runtime.valEq(request, "")) 
		{
			request = "/";
		}
		
		java.lang.Object port = null;
		if (( ( portString == null ) || haxe.lang.Runtime.valEq(portString, "") )) 
		{
			if (secure) 
			{
				port = 443;
			}
			 else 
			{
				port = 80;
			}
			
		}
		 else 
		{
			port = haxe.root.Std.parseInt(haxe.lang.StringExt.substr(portString, 1, ( portString.length() - 1 )));
		}
		
		java.lang.Object data = null;
		boolean multipart = ( ! (( this.file == null )) );
		java.lang.String boundary = null;
		java.lang.String uri = null;
		if (multipart) 
		{
			post = true;
			boundary = ( ( ( haxe.root.Std.string(haxe.root.Std.random(1000)) + haxe.root.Std.string(haxe.root.Std.random(1000)) ) + haxe.root.Std.string(haxe.root.Std.random(1000)) ) + haxe.root.Std.string(haxe.root.Std.random(1000)) );
			while (( boundary.length() < 38 ))
			{
				boundary = ( "-" + boundary );
			}
			
			haxe.root.StringBuf b = new haxe.root.StringBuf();
			{
				java.lang.Object __temp_iterator42 = this.params.iterator();
				while (haxe.lang.Runtime.toBool(haxe.lang.Runtime.callField(__temp_iterator42, "hasNext", null)))
				{
					java.lang.Object p = ((java.lang.Object) (haxe.lang.Runtime.callField(__temp_iterator42, "next", null)) );
					b.add("--");
					b.add(boundary);
					b.add("\r\n");
					b.add("Content-Disposition: form-data; name=\"");
					b.add(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(p, "param", true)));
					b.add("\"");
					b.add("\r\n");
					b.add("\r\n");
					b.add(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(p, "value", true)));
					b.add("\r\n");
				}
				
			}
			
			b.add("--");
			b.add(boundary);
			b.add("\r\n");
			b.add("Content-Disposition: form-data; name=\"");
			b.add(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(this.file, "param", true)));
			b.add("\"; filename=\"");
			b.add(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(this.file, "filename", true)));
			b.add("\"");
			b.add("\r\n");
			b.add(( ( ( "Content-Type: " + haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(this.file, "mimeType", true)) ) + "\r\n" ) + "\r\n" ));
			uri = b.toString();
		}
		 else 
		{
			{
				java.lang.Object __temp_iterator43 = this.params.iterator();
				while (haxe.lang.Runtime.toBool(haxe.lang.Runtime.callField(__temp_iterator43, "hasNext", null)))
				{
					java.lang.Object p1 = ((java.lang.Object) (haxe.lang.Runtime.callField(__temp_iterator43, "next", null)) );
					if (( uri == null )) 
					{
						uri = "";
					}
					 else 
					{
						uri += "&";
					}
					
					uri += ( ( haxe.root.StringTools.urlEncode(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(p1, "param", true))) + "=" ) + haxe.root.StringTools.urlEncode(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(p1, "value", true))) );
				}
				
			}
			
		}
		
		haxe.root.StringBuf b1 = new haxe.root.StringBuf();
		if (( method != null )) 
		{
			b1.add(method);
			b1.add(" ");
		}
		 else 
		{
			if (post) 
			{
				b1.add("POST ");
			}
			 else 
			{
				b1.add("GET ");
			}
			
		}
		
		if (( ! (( haxe.Http.PROXY == null )) )) 
		{
			b1.add("http://");
			b1.add(host);
			if (( ! (haxe.lang.Runtime.eq(port, 80)) )) 
			{
				b1.add(":");
				b1.add(port);
			}
			
		}
		
		b1.add(request);
		if ((  ! (post)  && ( uri != null ) )) 
		{
			if (( haxe.lang.StringExt.indexOf(request, "?", 0) >= 0 )) 
			{
				b1.add("&");
			}
			 else 
			{
				b1.add("?");
			}
			
			b1.add(uri);
		}
		
		b1.add(( ( " HTTP/1.1\r\nHost: " + host ) + "\r\n" ));
		if (( this.postData != null )) 
		{
			b1.add(( ( "Content-Length: " + this.postData.length() ) + "\r\n" ));
		}
		 else 
		{
			if (( post && ( uri != null ) )) 
			{
				if (( multipart ||  ! (haxe.root.Lambda.exists(this.headers, ( (( haxe.Http_customRequest_502__Fun.__hx_current != null )) ? (haxe.Http_customRequest_502__Fun.__hx_current) : (haxe.Http_customRequest_502__Fun.__hx_current = ((haxe.Http_customRequest_502__Fun) (new haxe.Http_customRequest_502__Fun()) )) )))  )) 
				{
					b1.add("Content-Type: ");
					if (multipart) 
					{
						b1.add("multipart/form-data");
						b1.add("; boundary=");
						b1.add(boundary);
					}
					 else 
					{
						b1.add("application/x-www-form-urlencoded");
					}
					
					b1.add("\r\n");
				}
				
				if (multipart) 
				{
					b1.add(( ( "Content-Length: " + (( ( ( uri.length() + ((int) (haxe.lang.Runtime.getField_f(this.file, "size", true)) ) ) + boundary.length() ) + 6 )) ) + "\r\n" ));
				}
				 else 
				{
					b1.add(( ( "Content-Length: " + uri.length() ) + "\r\n" ));
				}
				
			}
			
		}
		
		{
			java.lang.Object __temp_iterator44 = this.headers.iterator();
			while (haxe.lang.Runtime.toBool(haxe.lang.Runtime.callField(__temp_iterator44, "hasNext", null)))
			{
				java.lang.Object h1 = ((java.lang.Object) (haxe.lang.Runtime.callField(__temp_iterator44, "next", null)) );
				b1.add(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(h1, "header", true)));
				b1.add(": ");
				b1.add(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(h1, "value", true)));
				b1.add("\r\n");
			}
			
		}
		
		b1.add("\r\n");
		if (( this.postData != null )) 
		{
			b1.add(this.postData);
		}
		 else 
		{
			if (( post && ( uri != null ) )) 
			{
				b1.add(uri);
			}
			
		}
		
		try 
		{
			if (( ! (( haxe.Http.PROXY == null )) )) 
			{
				haxe.lang.Runtime.callField(sock, "connect", new haxe.root.Array(new java.lang.Object[]{new sys.net.Host(haxe.lang.Runtime.toString(haxe.lang.Runtime.getField(haxe.Http.PROXY, "host", true))), ((int) (haxe.lang.Runtime.getField_f(haxe.Http.PROXY, "port", true)) )}));
			}
			 else 
			{
				haxe.lang.Runtime.callField(sock, "connect", new haxe.root.Array(new java.lang.Object[]{new sys.net.Host(haxe.lang.Runtime.toString(host)), port}));
			}
			
			haxe.lang.Runtime.callField(sock, "write", new haxe.root.Array(new java.lang.Object[]{b1.toString()}));
			if (multipart) 
			{
				int bufsize = 4096;
				haxe.io.Bytes buf = haxe.io.Bytes.alloc(bufsize);
				while (( haxe.lang.Runtime.compare(((int) (haxe.lang.Runtime.getField_f(this.file, "size", true)) ), 0) > 0 ))
				{
					int size = 0;
					if (( haxe.lang.Runtime.compare(((int) (haxe.lang.Runtime.getField_f(this.file, "size", true)) ), bufsize) > 0 )) 
					{
						size = bufsize;
					}
					 else 
					{
						size = ((int) (haxe.lang.Runtime.getField_f(this.file, "size", true)) );
					}
					
					int len = 0;
					try 
					{
						len = ((haxe.io.Input) (haxe.lang.Runtime.getField(this.file, "io", true)) ).readBytes(buf, 0, size);
					}
					catch (java.lang.Throwable __temp_catchallException100)
					{
						java.lang.Object __temp_catchall101 = __temp_catchallException100;
						if (( __temp_catchall101 instanceof haxe.lang.HaxeException )) 
						{
							__temp_catchall101 = ((haxe.lang.HaxeException) (__temp_catchallException100) ).obj;
						}
						
						if (( __temp_catchall101 instanceof haxe.io.Eof )) 
						{
							haxe.io.Eof e = ((haxe.io.Eof) (__temp_catchall101) );
							{
								break;
							}
							
						}
						 else 
						{
							throw haxe.lang.HaxeException.wrap(__temp_catchallException100);
						}
						
					}
					
					
					((haxe.io.Output) (haxe.lang.Runtime.getField(sock, "output", true)) ).writeFullBytes(buf, 0, len);
					{
						java.lang.Object __temp_dynop45 = this.file;
						haxe.lang.Runtime.setField_f(__temp_dynop45, "size", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop45, "size", true)) ) - ((int) (len) ) )) ));
					}
					
				}
				
				haxe.lang.Runtime.callField(sock, "write", new haxe.root.Array(new java.lang.Object[]{"\r\n"}));
				haxe.lang.Runtime.callField(sock, "write", new haxe.root.Array(new java.lang.Object[]{"--"}));
				haxe.lang.Runtime.callField(sock, "write", new haxe.root.Array(new java.lang.Object[]{boundary}));
				haxe.lang.Runtime.callField(sock, "write", new haxe.root.Array(new java.lang.Object[]{"--"}));
			}
			
			this.readHttpResponse(api, sock);
			haxe.lang.Runtime.callField(sock, "close", null);
		}
		catch (java.lang.Throwable __temp_catchallException98)
		{
			java.lang.Object __temp_catchall99 = __temp_catchallException98;
			if (( __temp_catchall99 instanceof haxe.lang.HaxeException )) 
			{
				__temp_catchall99 = ((haxe.lang.HaxeException) (__temp_catchallException98) ).obj;
			}
			
			{
				java.lang.Object e1 = __temp_catchall99;
				try 
				{
					haxe.lang.Runtime.callField(sock, "close", null);
				}
				catch (java.lang.Throwable __temp_catchallException96)
				{
					java.lang.Object __temp_catchall97 = __temp_catchallException96;
					if (( __temp_catchall97 instanceof haxe.lang.HaxeException )) 
					{
						__temp_catchall97 = ((haxe.lang.HaxeException) (__temp_catchallException96) ).obj;
					}
					
					{
						java.lang.Object e2 = __temp_catchall97;
						{
						}
						
					}
					
				}
				
				
				this.onError.__hx_invoke1_o(0.0, haxe.root.Std.string(e1));
			}
			
		}
		
		
	}
	
	
	public   void readHttpResponse(haxe.io.Output api, java.lang.Object sock)
	{
		haxe.io.BytesBuffer b = new haxe.io.BytesBuffer();
		int k = 4;
		haxe.io.Bytes s = haxe.io.Bytes.alloc(4);
		haxe.lang.Runtime.callField(sock, "setTimeout", new haxe.root.Array(new java.lang.Object[]{this.cnxTimeout}));
		{
			label1:
			while (true)
			{
				int p = ((haxe.io.Input) (haxe.lang.Runtime.getField(sock, "input", true)) ).readBytes(s, 0, k);
				while (( p != k ))
				{
					p += ((haxe.io.Input) (haxe.lang.Runtime.getField(sock, "input", true)) ).readBytes(s, p, ( k - p ));
				}
				
				{
					if (( ( k < 0 ) || ( k > s.length ) )) 
					{
						throw haxe.lang.HaxeException.wrap(haxe.io.Error.OutsideBounds);
					}
					
					b.b.write(((byte[]) (s.b) ), ((int) (0) ), ((int) (k) ));
				}
				
				switch (k)
				{
					case 1:
					{
						int c = ( s.b[0] & 255 );
						if (( c == 10 )) 
						{
							break label1;
						}
						
						if (( c == 13 )) 
						{
							k = 3;
						}
						 else 
						{
							k = 4;
						}
						
						break;
					}
					
					
					case 2:
					{
						int c1 = ( s.b[1] & 255 );
						if (( c1 == 10 )) 
						{
							if (( (( s.b[0] & 255 )) == 13 )) 
							{
								break label1;
							}
							
							k = 4;
						}
						 else 
						{
							if (( c1 == 13 )) 
							{
								k = 3;
							}
							 else 
							{
								k = 4;
							}
							
						}
						
						break;
					}
					
					
					case 3:
					{
						int c2 = ( s.b[2] & 255 );
						if (( c2 == 10 )) 
						{
							if (( (( s.b[1] & 255 )) != 13 )) 
							{
								k = 4;
							}
							 else 
							{
								if (( (( s.b[0] & 255 )) != 10 )) 
								{
									k = 2;
								}
								 else 
								{
									break label1;
								}
								
							}
							
						}
						 else 
						{
							if (( c2 == 13 )) 
							{
								if (( ( (( s.b[1] & 255 )) != 10 ) || ( (( s.b[0] & 255 )) != 13 ) )) 
								{
									k = 1;
								}
								 else 
								{
									k = 3;
								}
								
							}
							 else 
							{
								k = 4;
							}
							
						}
						
						break;
					}
					
					
					case 4:
					{
						int c3 = ( s.b[3] & 255 );
						if (( c3 == 10 )) 
						{
							if (( (( s.b[2] & 255 )) != 13 )) 
							{
								continue;
							}
							 else 
							{
								if (( ( (( s.b[1] & 255 )) != 10 ) || ( (( s.b[0] & 255 )) != 13 ) )) 
								{
									k = 2;
								}
								 else 
								{
									break label1;
								}
								
							}
							
						}
						 else 
						{
							if (( c3 == 13 )) 
							{
								if (( ( (( s.b[2] & 255 )) != 10 ) || ( (( s.b[1] & 255 )) != 13 ) )) 
								{
									k = 3;
								}
								 else 
								{
									k = 1;
								}
								
							}
							
						}
						
						break;
					}
					
					
				}
				
			}
			
		}
		
		haxe.root.Array<java.lang.String> headers = haxe.lang.StringExt.split(b.getBytes().toString(), "\r\n");
		java.lang.String response = headers.shift();
		haxe.root.Array<java.lang.String> rp = haxe.lang.StringExt.split(response, " ");
		java.lang.Object status = haxe.root.Std.parseInt(rp.__get(1));
		if (( haxe.lang.Runtime.eq(status, 0) || ( status == null ) )) 
		{
			throw haxe.lang.HaxeException.wrap("Response status error");
		}
		
		headers.pop();
		headers.pop();
		this.responseHeaders = new haxe.ds.StringMap<java.lang.String>();
		java.lang.Object size = null;
		boolean chunked = false;
		{
			int _g = 0;
			while (( _g < headers.length ))
			{
				java.lang.String hline = headers.__get(_g);
				 ++ _g;
				haxe.root.Array<java.lang.String> a = haxe.lang.StringExt.split(hline, ": ");
				java.lang.String hname = a.shift();
				java.lang.String hval = null;
				if (( a.length == 1 )) 
				{
					hval = a.__get(0);
				}
				 else 
				{
					hval = a.join(": ");
				}
				
				hval = haxe.root.StringTools.ltrim(haxe.root.StringTools.rtrim(hval));
				this.responseHeaders.set(hname, hval);
				{
					java.lang.String _g1 = hname.toLowerCase();
					{
						java.lang.String __temp_svar106 = (_g1);
						switch (__temp_svar106.hashCode())
						{
							case -1132779846:
							{
								if (__temp_svar106.equals("content-length")) 
								{
									size = haxe.root.Std.parseInt(hval);
								}
								
								break;
							}
							
							
							case 1274458357:
							{
								if (__temp_svar106.equals("transfer-encoding")) 
								{
									chunked = haxe.lang.Runtime.valEq(hval.toLowerCase(), "chunked");
								}
								
								break;
							}
							
							
						}
						
					}
					
				}
				
			}
			
		}
		
		this.onStatus.__hx_invoke1_o(0.0, status);
		haxe.root.EReg chunk_re = new haxe.root.EReg(haxe.lang.Runtime.toString("^([0-9A-Fa-f]+)[ ]*\r\n"), haxe.lang.Runtime.toString("m"));
		this.chunk_size = null;
		this.chunk_buf = null;
		int bufsize = 1024;
		haxe.io.Bytes buf = haxe.io.Bytes.alloc(bufsize);
		if (( size == null )) 
		{
			if ( ! (this.noShutdown) ) 
			{
				haxe.lang.Runtime.callField(sock, "shutdown", new haxe.root.Array(new java.lang.Object[]{false, true}));
			}
			
			try 
			{
				while (true)
				{
					int len = ((haxe.io.Input) (haxe.lang.Runtime.getField(sock, "input", true)) ).readBytes(buf, 0, bufsize);
					if (chunked) 
					{
						if ( ! (this.readChunk(chunk_re, api, buf, len)) ) 
						{
							break;
						}
						
					}
					 else 
					{
						api.writeBytes(buf, 0, len);
					}
					
				}
				
			}
			catch (java.lang.Throwable __temp_catchallException102)
			{
				java.lang.Object __temp_catchall103 = __temp_catchallException102;
				if (( __temp_catchall103 instanceof haxe.lang.HaxeException )) 
				{
					__temp_catchall103 = ((haxe.lang.HaxeException) (__temp_catchallException102) ).obj;
				}
				
				if (( __temp_catchall103 instanceof haxe.io.Eof )) 
				{
					haxe.io.Eof e = ((haxe.io.Eof) (__temp_catchall103) );
					{
						{
						}
						
					}
					
				}
				 else 
				{
					throw haxe.lang.HaxeException.wrap(__temp_catchallException102);
				}
				
			}
			
			
		}
		 else 
		{
			api.prepare(((int) (haxe.lang.Runtime.toInt(size)) ));
			try 
			{
				while (( haxe.lang.Runtime.compare(size, 0) > 0 ))
				{
					int len1 = ((haxe.io.Input) (haxe.lang.Runtime.getField(sock, "input", true)) ).readBytes(buf, 0, ( (( haxe.lang.Runtime.compare(size, bufsize) > 0 )) ? (bufsize) : (((int) (haxe.lang.Runtime.toInt(size)) )) ));
					if (chunked) 
					{
						if ( ! (this.readChunk(chunk_re, api, buf, len1)) ) 
						{
							break;
						}
						
					}
					 else 
					{
						api.writeBytes(buf, 0, len1);
					}
					
					size = ( ((int) (haxe.lang.Runtime.toInt(size)) ) - ((int) (len1) ) );
				}
				
			}
			catch (java.lang.Throwable __temp_catchallException104)
			{
				java.lang.Object __temp_catchall105 = __temp_catchallException104;
				if (( __temp_catchall105 instanceof haxe.lang.HaxeException )) 
				{
					__temp_catchall105 = ((haxe.lang.HaxeException) (__temp_catchallException104) ).obj;
				}
				
				if (( __temp_catchall105 instanceof haxe.io.Eof )) 
				{
					haxe.io.Eof e1 = ((haxe.io.Eof) (__temp_catchall105) );
					{
						throw haxe.lang.HaxeException.wrap("Transfer aborted");
					}
					
				}
				 else 
				{
					throw haxe.lang.HaxeException.wrap(__temp_catchallException104);
				}
				
			}
			
			
		}
		
		if (( chunked && (( ( ! (( this.chunk_size == null )) ) || ( this.chunk_buf != null ) )) )) 
		{
			throw haxe.lang.HaxeException.wrap("Invalid chunk");
		}
		
		if (( ( haxe.lang.Runtime.compare(status, 200) < 0 ) || ( haxe.lang.Runtime.compare(status, 400) >= 0 ) )) 
		{
			throw haxe.lang.HaxeException.wrap(( haxe.lang.Runtime.toString("Http Error #") + haxe.lang.Runtime.toString(status) ));
		}
		
		api.close();
	}
	
	
	public   boolean readChunk(haxe.root.EReg chunk_re, haxe.io.Output api, haxe.io.Bytes buf, int len)
	{
		if (( this.chunk_size == null )) 
		{
			if (( this.chunk_buf != null )) 
			{
				haxe.io.BytesBuffer b = new haxe.io.BytesBuffer();
				{
					haxe.io.Bytes src = this.chunk_buf;
					b.b.write(((byte[]) (src.b) ), ((int) (0) ), ((int) (src.length) ));
				}
				
				{
					if (( ( len < 0 ) || ( len > buf.length ) )) 
					{
						throw haxe.lang.HaxeException.wrap(haxe.io.Error.OutsideBounds);
					}
					
					b.b.write(((byte[]) (buf.b) ), ((int) (0) ), ((int) (len) ));
				}
				
				buf = b.getBytes();
				len += this.chunk_buf.length;
				this.chunk_buf = null;
			}
			
			if (chunk_re.match(buf.toString())) 
			{
				java.lang.Object p = chunk_re.matchedPos();
				if (( haxe.lang.Runtime.compare(((int) (haxe.lang.Runtime.getField_f(p, "len", true)) ), len) <= 0 )) 
				{
					java.lang.String cstr = chunk_re.matched(1);
					this.chunk_size = haxe.root.Std.parseInt(( "0x" + cstr ));
					if (haxe.lang.Runtime.valEq(cstr, "0")) 
					{
						this.chunk_size = null;
						this.chunk_buf = null;
						return false;
					}
					
					len = ( ((int) (len) ) - ((int) (haxe.lang.Runtime.getField_f(p, "len", true)) ) );
					return this.readChunk(chunk_re, api, buf.sub(((int) (haxe.lang.Runtime.getField_f(p, "len", true)) ), len), len);
				}
				
			}
			
			if (( len > 10 )) 
			{
				this.onError.__hx_invoke1_o(0.0, "Invalid chunk");
				return false;
			}
			
			this.chunk_buf = buf.sub(0, len);
			return true;
		}
		
		if (( haxe.lang.Runtime.compare(this.chunk_size, len) > 0 )) 
		{
			{
				haxe.Http __temp_dynop46 = this;
				__temp_dynop46.chunk_size = ( ((int) (haxe.lang.Runtime.toInt(__temp_dynop46.chunk_size)) ) - ((int) (len) ) );
			}
			
			api.writeBytes(buf, 0, len);
			return true;
		}
		
		int end = ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.plus(this.chunk_size, 2))) );
		if (( len >= end )) 
		{
			if (( haxe.lang.Runtime.compare(this.chunk_size, 0) > 0 )) 
			{
				api.writeBytes(buf, 0, ((int) (haxe.lang.Runtime.toInt(this.chunk_size)) ));
			}
			
			len -= end;
			this.chunk_size = null;
			if (( len == 0 )) 
			{
				return true;
			}
			
			return this.readChunk(chunk_re, api, buf.sub(end, len), len);
		}
		
		if (( haxe.lang.Runtime.compare(this.chunk_size, 0) > 0 )) 
		{
			api.writeBytes(buf, 0, ((int) (haxe.lang.Runtime.toInt(this.chunk_size)) ));
		}
		
		{
			haxe.Http __temp_dynop47 = this;
			__temp_dynop47.chunk_size = ( ((int) (haxe.lang.Runtime.toInt(__temp_dynop47.chunk_size)) ) - ((int) (len) ) );
		}
		
		return true;
	}
	
	
	public  haxe.lang.Function onData;
	
	public  haxe.lang.Function onError;
	
	public  haxe.lang.Function onStatus;
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef108 = true;
			switch (field.hashCode())
			{
				case 3143036:
				{
					if (field.equals("file")) 
					{
						__temp_executeDef108 = false;
						this.file = ((java.lang.Object) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -196911308:
				{
					if (field.equals("cnxTimeout")) 
					{
						__temp_executeDef108 = false;
						this.cnxTimeout = ((double) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1525550445:
				{
					if (field.equals("chunk_size")) 
					{
						__temp_executeDef108 = false;
						this.chunk_size = ((java.lang.Object) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef108) 
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
			boolean __temp_executeDef109 = true;
			switch (field.hashCode())
			{
				case 1505928881:
				{
					if (field.equals("onStatus")) 
					{
						__temp_executeDef109 = false;
						this.onStatus = ((haxe.lang.Function) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 116079:
				{
					if (field.equals("url")) 
					{
						__temp_executeDef109 = false;
						this.url = haxe.lang.Runtime.toString(value);
						return value;
					}
					
					break;
				}
				
				
				case -1349867671:
				{
					if (field.equals("onError")) 
					{
						__temp_executeDef109 = false;
						this.onError = ((haxe.lang.Function) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1438740363:
				{
					if (field.equals("responseData")) 
					{
						__temp_executeDef109 = false;
						this.responseData = haxe.lang.Runtime.toString(value);
						return value;
					}
					
					break;
				}
				
				
				case -1013421527:
				{
					if (field.equals("onData")) 
					{
						__temp_executeDef109 = false;
						this.onData = ((haxe.lang.Function) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 989669175:
				{
					if (field.equals("noShutdown")) 
					{
						__temp_executeDef109 = false;
						this.noShutdown = haxe.lang.Runtime.toBool(value);
						return value;
					}
					
					break;
				}
				
				
				case -995427962:
				{
					if (field.equals("params")) 
					{
						__temp_executeDef109 = false;
						this.params = ((haxe.root.List<java.lang.Object>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -196911308:
				{
					if (field.equals("cnxTimeout")) 
					{
						__temp_executeDef109 = false;
						this.cnxTimeout = ((double) (haxe.lang.Runtime.toDouble(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 795307910:
				{
					if (field.equals("headers")) 
					{
						__temp_executeDef109 = false;
						this.headers = ((haxe.root.List<java.lang.Object>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1387714565:
				{
					if (field.equals("responseHeaders")) 
					{
						__temp_executeDef109 = false;
						this.responseHeaders = ((haxe.ds.StringMap<java.lang.String>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 756526186:
				{
					if (field.equals("postData")) 
					{
						__temp_executeDef109 = false;
						this.postData = haxe.lang.Runtime.toString(value);
						return value;
					}
					
					break;
				}
				
				
				case -1525550445:
				{
					if (field.equals("chunk_size")) 
					{
						__temp_executeDef109 = false;
						this.chunk_size = ((java.lang.Object) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3143036:
				{
					if (field.equals("file")) 
					{
						__temp_executeDef109 = false;
						this.file = ((java.lang.Object) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 2028982689:
				{
					if (field.equals("chunk_buf")) 
					{
						__temp_executeDef109 = false;
						this.chunk_buf = ((haxe.io.Bytes) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef109) 
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
			boolean __temp_executeDef110 = true;
			switch (field.hashCode())
			{
				case 1505928881:
				{
					if (field.equals("onStatus")) 
					{
						__temp_executeDef110 = false;
						return this.onStatus;
					}
					
					break;
				}
				
				
				case 116079:
				{
					if (field.equals("url")) 
					{
						__temp_executeDef110 = false;
						return this.url;
					}
					
					break;
				}
				
				
				case -1349867671:
				{
					if (field.equals("onError")) 
					{
						__temp_executeDef110 = false;
						return this.onError;
					}
					
					break;
				}
				
				
				case 1438740363:
				{
					if (field.equals("responseData")) 
					{
						__temp_executeDef110 = false;
						return this.responseData;
					}
					
					break;
				}
				
				
				case -1013421527:
				{
					if (field.equals("onData")) 
					{
						__temp_executeDef110 = false;
						return this.onData;
					}
					
					break;
				}
				
				
				case 989669175:
				{
					if (field.equals("noShutdown")) 
					{
						__temp_executeDef110 = false;
						return this.noShutdown;
					}
					
					break;
				}
				
				
				case -1139644809:
				{
					if (field.equals("readChunk")) 
					{
						__temp_executeDef110 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("readChunk"))) );
					}
					
					break;
				}
				
				
				case -196911308:
				{
					if (field.equals("cnxTimeout")) 
					{
						__temp_executeDef110 = false;
						return this.cnxTimeout;
					}
					
					break;
				}
				
				
				case -954305121:
				{
					if (field.equals("readHttpResponse")) 
					{
						__temp_executeDef110 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("readHttpResponse"))) );
					}
					
					break;
				}
				
				
				case 1387714565:
				{
					if (field.equals("responseHeaders")) 
					{
						__temp_executeDef110 = false;
						return this.responseHeaders;
					}
					
					break;
				}
				
				
				case 2113105374:
				{
					if (field.equals("customRequest")) 
					{
						__temp_executeDef110 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("customRequest"))) );
					}
					
					break;
				}
				
				
				case -1525550445:
				{
					if (field.equals("chunk_size")) 
					{
						__temp_executeDef110 = false;
						return this.chunk_size;
					}
					
					break;
				}
				
				
				case 1095692943:
				{
					if (field.equals("request")) 
					{
						__temp_executeDef110 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("request"))) );
					}
					
					break;
				}
				
				
				case 2028982689:
				{
					if (field.equals("chunk_buf")) 
					{
						__temp_executeDef110 = false;
						return this.chunk_buf;
					}
					
					break;
				}
				
				
				case -801118873:
				{
					if (field.equals("setParameter")) 
					{
						__temp_executeDef110 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("setParameter"))) );
					}
					
					break;
				}
				
				
				case 3143036:
				{
					if (field.equals("file")) 
					{
						__temp_executeDef110 = false;
						return this.file;
					}
					
					break;
				}
				
				
				case 260127119:
				{
					if (field.equals("setHeader")) 
					{
						__temp_executeDef110 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("setHeader"))) );
					}
					
					break;
				}
				
				
				case 756526186:
				{
					if (field.equals("postData")) 
					{
						__temp_executeDef110 = false;
						return this.postData;
					}
					
					break;
				}
				
				
				case -995427962:
				{
					if (field.equals("params")) 
					{
						__temp_executeDef110 = false;
						return this.params;
					}
					
					break;
				}
				
				
				case 795307910:
				{
					if (field.equals("headers")) 
					{
						__temp_executeDef110 = false;
						return this.headers;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef110) 
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
			boolean __temp_executeDef111 = true;
			switch (field.hashCode())
			{
				case 3143036:
				{
					if (field.equals("file")) 
					{
						__temp_executeDef111 = false;
						return ((double) (haxe.lang.Runtime.toDouble(this.file)) );
					}
					
					break;
				}
				
				
				case -196911308:
				{
					if (field.equals("cnxTimeout")) 
					{
						__temp_executeDef111 = false;
						return this.cnxTimeout;
					}
					
					break;
				}
				
				
				case -1525550445:
				{
					if (field.equals("chunk_size")) 
					{
						__temp_executeDef111 = false;
						return ((double) (haxe.lang.Runtime.toDouble(this.chunk_size)) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef111) 
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
			boolean __temp_executeDef112 = true;
			switch (field.hashCode())
			{
				case -1139644809:
				{
					if (field.equals("readChunk")) 
					{
						__temp_executeDef112 = false;
						return this.readChunk(((haxe.root.EReg) (dynargs.__get(0)) ), ((haxe.io.Output) (dynargs.__get(1)) ), ((haxe.io.Bytes) (dynargs.__get(2)) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(3))) ));
					}
					
					break;
				}
				
				
				case 260127119:
				{
					if (field.equals("setHeader")) 
					{
						__temp_executeDef112 = false;
						return this.setHeader(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)));
					}
					
					break;
				}
				
				
				case -954305121:
				{
					if (field.equals("readHttpResponse")) 
					{
						__temp_executeDef112 = false;
						this.readHttpResponse(((haxe.io.Output) (dynargs.__get(0)) ), dynargs.__get(1));
					}
					
					break;
				}
				
				
				case -801118873:
				{
					if (field.equals("setParameter")) 
					{
						__temp_executeDef112 = false;
						return this.setParameter(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)));
					}
					
					break;
				}
				
				
				case 2113105374:
				{
					if (field.equals("customRequest")) 
					{
						__temp_executeDef112 = false;
						this.customRequest(haxe.lang.Runtime.toBool(dynargs.__get(0)), ((haxe.io.Output) (dynargs.__get(1)) ), dynargs.__get(2), haxe.lang.Runtime.toString(dynargs.__get(3)));
					}
					
					break;
				}
				
				
				case 1095692943:
				{
					if (field.equals("request")) 
					{
						__temp_executeDef112 = false;
						this.request(dynargs.__get(0));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef112) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("onStatus");
		baseArr.push("onError");
		baseArr.push("onData");
		baseArr.push("params");
		baseArr.push("headers");
		baseArr.push("postData");
		baseArr.push("file");
		baseArr.push("chunk_buf");
		baseArr.push("chunk_size");
		baseArr.push("responseHeaders");
		baseArr.push("cnxTimeout");
		baseArr.push("noShutdown");
		baseArr.push("responseData");
		baseArr.push("url");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


