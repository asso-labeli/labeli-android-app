package labeli;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Async_Api extends haxe.lang.HxObject
{
	public    Async_Api(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Async_Api(haxe.remoting.AsyncConnection c)
	{
		labeli.Async_Api.__hx_ctor_labeli_Async_Api(this, c);
	}
	
	
	public static   void __hx_ctor_labeli_Async_Api(labeli.Async_Api __temp_me36, haxe.remoting.AsyncConnection c)
	{
		__temp_me36.__cnx = c;
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new labeli.Async_Api(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new labeli.Async_Api(((haxe.remoting.AsyncConnection) (arr.__get(0)) ));
	}
	
	
	public  haxe.remoting.AsyncConnection __cnx;
	
	public   void login(java.lang.String username, java.lang.String password, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("login").call(new haxe.root.Array(new java.lang.Object[]{username, password}), __callb);
	}
	
	
	public   void logout(haxe.lang.Function __callb)
	{
		this.__cnx.resolve("logout").call(new haxe.root.Array(new java.lang.Object[]{}), __callb);
	}
	
	
	public   void getCurrentUser(haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getCurrentUser").call(new haxe.root.Array(new java.lang.Object[]{}), __callb);
	}
	
	
	public   void createUser(java.lang.String firstName, java.lang.String lastName, java.lang.String email, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("createUser").call(new haxe.root.Array(new java.lang.Object[]{firstName, lastName, email}), __callb);
	}
	
	
	public   void editUser(int userId, java.lang.String role, java.lang.String universityGroup, int birthday, java.lang.String description, java.lang.String picture, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("editUser").call(new haxe.root.Array(new java.lang.Object[]{userId, role, universityGroup, birthday, description, picture}), __callb);
	}
	
	
	public   void getUser(int userId, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getUser").call(new haxe.root.Array(new java.lang.Object[]{userId}), __callb);
	}
	
	
	public   void getUsers(haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getUsers").call(new haxe.root.Array(new java.lang.Object[]{}), __callb);
	}
	
	
	public   void getOldUsers(haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getOldUsers").call(new haxe.root.Array(new java.lang.Object[]{}), __callb);
	}
	
	
	public   void createGroup(java.lang.String name, int userId, java.lang.String type, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("createGroup").call(new haxe.root.Array(new java.lang.Object[]{name, userId, type}), __callb);
	}
	
	
	public   void leaveGroup(int groupId, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("leaveGroup").call(new haxe.root.Array(new java.lang.Object[]{groupId}), __callb);
	}
	
	
	public   void joinGroup(int groupId, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("joinGroup").call(new haxe.root.Array(new java.lang.Object[]{groupId}), __callb);
	}
	
	
	public   void editGroup(int groupId, java.lang.String name, java.lang.String description, java.lang.String picture, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("editGroup").call(new haxe.root.Array(new java.lang.Object[]{groupId, name, description, picture}), __callb);
	}
	
	
	public   void validateGroup(int groupId, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("validateGroup").call(new haxe.root.Array(new java.lang.Object[]{groupId}), __callb);
	}
	
	
	public   void unvalidateGroup(int groupId, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("unvalidateGroup").call(new haxe.root.Array(new java.lang.Object[]{groupId}), __callb);
	}
	
	
	public   void deleteGroup(int groupId, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("deleteGroup").call(new haxe.root.Array(new java.lang.Object[]{groupId}), __callb);
	}
	
	
	public   void addUserToGroup(int groupId, int userId, java.lang.String label, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("addUserToGroup").call(new haxe.root.Array(new java.lang.Object[]{groupId, userId, label}), __callb);
	}
	
	
	public   void submitVote(int groupId, int value, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("submitVote").call(new haxe.root.Array(new java.lang.Object[]{groupId, value}), __callb);
	}
	
	
	public   void getVotes(haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getVotes").call(new haxe.root.Array(new java.lang.Object[]{}), __callb);
	}
	
	
	public   void getEvents(haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getEvents").call(new haxe.root.Array(new java.lang.Object[]{}), __callb);
	}
	
	
	public   void getEvent(int eventId, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getEvent").call(new haxe.root.Array(new java.lang.Object[]{eventId}), __callb);
	}
	
	
	public   void addMessage(java.lang.String thread, java.lang.String message, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("addMessage").call(new haxe.root.Array(new java.lang.Object[]{thread, message}), __callb);
	}
	
	
	public   void getMessages(java.lang.String thread, double since, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getMessages").call(new haxe.root.Array(new java.lang.Object[]{thread, since}), __callb);
	}
	
	
	public   void getProjects(haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getProjects").call(new haxe.root.Array(new java.lang.Object[]{}), __callb);
	}
	
	
	public   void getProject(int projectId, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getProject").call(new haxe.root.Array(new java.lang.Object[]{projectId}), __callb);
	}
	
	
	public   void getTeams(haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getTeams").call(new haxe.root.Array(new java.lang.Object[]{}), __callb);
	}
	
	
	public   void getTeam(int teamId, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getTeam").call(new haxe.root.Array(new java.lang.Object[]{teamId}), __callb);
	}
	
	
	public   void getSurveyData(java.lang.String surveyName, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("getSurveyData").call(new haxe.root.Array(new java.lang.Object[]{surveyName}), __callb);
	}
	
	
	public   void voteForSurvey(java.lang.String surveyName, java.lang.String surveyItem, boolean value, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("voteForSurvey").call(new haxe.root.Array(new java.lang.Object[]{surveyName, surveyItem, value}), __callb);
	}
	
	
	public   void sendMail(java.lang.String title, java.lang.String content, int recipientId, boolean preformat, boolean sendAsAdmin, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("sendMail").call(new haxe.root.Array(new java.lang.Object[]{title, content, recipientId, preformat, sendAsAdmin}), __callb);
	}
	
	
	public   void previewMail(java.lang.String title, java.lang.String content, boolean preformat, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("previewMail").call(new haxe.root.Array(new java.lang.Object[]{title, content, preformat}), __callb);
	}
	
	
	public   void isHouseOpened(haxe.lang.Function __callb)
	{
		this.__cnx.resolve("isHouseOpened").call(new haxe.root.Array(new java.lang.Object[]{}), __callb);
	}
	
	
	public   void setHouseOpened(boolean opened, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("setHouseOpened").call(new haxe.root.Array(new java.lang.Object[]{opened}), __callb);
	}
	
	
	public   void askResetPassword(java.lang.String email, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("askResetPassword").call(new haxe.root.Array(new java.lang.Object[]{email}), __callb);
	}
	
	
	public   void resetPassword(java.lang.String username, java.lang.String key, java.lang.String password, haxe.lang.Function __callb)
	{
		this.__cnx.resolve("resetPassword").call(new haxe.root.Array(new java.lang.Object[]{username, key, password}), __callb);
	}
	
	
	@Override public   java.lang.Object __hx_setField(java.lang.String field, java.lang.Object value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef264 = true;
			switch (field.hashCode())
			{
				case 90663309:
				{
					if (field.equals("__cnx")) 
					{
						__temp_executeDef264 = false;
						this.__cnx = ((haxe.remoting.AsyncConnection) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef264) 
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
			boolean __temp_executeDef265 = true;
			switch (field.hashCode())
			{
				case -24412918:
				{
					if (field.equals("resetPassword")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("resetPassword"))) );
					}
					
					break;
				}
				
				
				case 90663309:
				{
					if (field.equals("__cnx")) 
					{
						__temp_executeDef265 = false;
						return this.__cnx;
					}
					
					break;
				}
				
				
				case 1806941969:
				{
					if (field.equals("askResetPassword")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("askResetPassword"))) );
					}
					
					break;
				}
				
				
				case 103149417:
				{
					if (field.equals("login")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("login"))) );
					}
					
					break;
				}
				
				
				case -1301783065:
				{
					if (field.equals("setHouseOpened")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("setHouseOpened"))) );
					}
					
					break;
				}
				
				
				case -1097329270:
				{
					if (field.equals("logout")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("logout"))) );
					}
					
					break;
				}
				
				
				case -299810785:
				{
					if (field.equals("isHouseOpened")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("isHouseOpened"))) );
					}
					
					break;
				}
				
				
				case -38994002:
				{
					if (field.equals("getCurrentUser")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getCurrentUser"))) );
					}
					
					break;
				}
				
				
				case -1291437665:
				{
					if (field.equals("previewMail")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("previewMail"))) );
					}
					
					break;
				}
				
				
				case 1369252583:
				{
					if (field.equals("createUser")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("createUser"))) );
					}
					
					break;
				}
				
				
				case 1247233375:
				{
					if (field.equals("sendMail")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("sendMail"))) );
					}
					
					break;
				}
				
				
				case 1602074869:
				{
					if (field.equals("editUser")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("editUser"))) );
					}
					
					break;
				}
				
				
				case -684844615:
				{
					if (field.equals("voteForSurvey")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("voteForSurvey"))) );
					}
					
					break;
				}
				
				
				case -75082687:
				{
					if (field.equals("getUser")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getUser"))) );
					}
					
					break;
				}
				
				
				case -972192230:
				{
					if (field.equals("getSurveyData")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getSurveyData"))) );
					}
					
					break;
				}
				
				
				case 1967404114:
				{
					if (field.equals("getUsers")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getUsers"))) );
					}
					
					break;
				}
				
				
				case -75126061:
				{
					if (field.equals("getTeam")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getTeam"))) );
					}
					
					break;
				}
				
				
				case -2027557481:
				{
					if (field.equals("getOldUsers")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getOldUsers"))) );
					}
					
					break;
				}
				
				
				case 1966059520:
				{
					if (field.equals("getTeams")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getTeams"))) );
					}
					
					break;
				}
				
				
				case -515792157:
				{
					if (field.equals("createGroup")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("createGroup"))) );
					}
					
					break;
				}
				
				
				case 727549667:
				{
					if (field.equals("getProject")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getProject"))) );
					}
					
					break;
				}
				
				
				case -52145656:
				{
					if (field.equals("leaveGroup")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("leaveGroup"))) );
					}
					
					break;
				}
				
				
				case 1079203312:
				{
					if (field.equals("getProjects")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getProjects"))) );
					}
					
					break;
				}
				
				
				case -516304011:
				{
					if (field.equals("joinGroup")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("joinGroup"))) );
					}
					
					break;
				}
				
				
				case 1615806146:
				{
					if (field.equals("getMessages")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getMessages"))) );
					}
					
					break;
				}
				
				
				case -1888235883:
				{
					if (field.equals("editGroup")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("editGroup"))) );
					}
					
					break;
				}
				
				
				case -1431807962:
				{
					if (field.equals("addMessage")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("addMessage"))) );
					}
					
					break;
				}
				
				
				case -853071095:
				{
					if (field.equals("validateGroup")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("validateGroup"))) );
					}
					
					break;
				}
				
				
				case 1952717028:
				{
					if (field.equals("getEvent")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getEvent"))) );
					}
					
					break;
				}
				
				
				case -340042480:
				{
					if (field.equals("unvalidateGroup")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("unvalidateGroup"))) );
					}
					
					break;
				}
				
				
				case 404685839:
				{
					if (field.equals("getEvents")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getEvents"))) );
					}
					
					break;
				}
				
				
				case -1144040556:
				{
					if (field.equals("deleteGroup")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("deleteGroup"))) );
					}
					
					break;
				}
				
				
				case 1968222483:
				{
					if (field.equals("getVotes")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getVotes"))) );
					}
					
					break;
				}
				
				
				case -857546984:
				{
					if (field.equals("addUserToGroup")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("addUserToGroup"))) );
					}
					
					break;
				}
				
				
				case -2076755166:
				{
					if (field.equals("submitVote")) 
					{
						__temp_executeDef265 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("submitVote"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef265) 
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
			boolean __temp_executeDef266 = true;
			switch (field.hashCode())
			{
				case -24412918:
				{
					if (field.equals("resetPassword")) 
					{
						__temp_executeDef266 = false;
						this.resetPassword(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)), haxe.lang.Runtime.toString(dynargs.__get(2)), ((haxe.lang.Function) (dynargs.__get(3)) ));
					}
					
					break;
				}
				
				
				case 103149417:
				{
					if (field.equals("login")) 
					{
						__temp_executeDef266 = false;
						this.login(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)), ((haxe.lang.Function) (dynargs.__get(2)) ));
					}
					
					break;
				}
				
				
				case 1806941969:
				{
					if (field.equals("askResetPassword")) 
					{
						__temp_executeDef266 = false;
						this.askResetPassword(haxe.lang.Runtime.toString(dynargs.__get(0)), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -1097329270:
				{
					if (field.equals("logout")) 
					{
						__temp_executeDef266 = false;
						this.logout(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -1301783065:
				{
					if (field.equals("setHouseOpened")) 
					{
						__temp_executeDef266 = false;
						this.setHouseOpened(haxe.lang.Runtime.toBool(dynargs.__get(0)), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -38994002:
				{
					if (field.equals("getCurrentUser")) 
					{
						__temp_executeDef266 = false;
						this.getCurrentUser(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -299810785:
				{
					if (field.equals("isHouseOpened")) 
					{
						__temp_executeDef266 = false;
						this.isHouseOpened(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 1369252583:
				{
					if (field.equals("createUser")) 
					{
						__temp_executeDef266 = false;
						this.createUser(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)), haxe.lang.Runtime.toString(dynargs.__get(2)), ((haxe.lang.Function) (dynargs.__get(3)) ));
					}
					
					break;
				}
				
				
				case -1291437665:
				{
					if (field.equals("previewMail")) 
					{
						__temp_executeDef266 = false;
						this.previewMail(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)), haxe.lang.Runtime.toBool(dynargs.__get(2)), ((haxe.lang.Function) (dynargs.__get(3)) ));
					}
					
					break;
				}
				
				
				case 1602074869:
				{
					if (field.equals("editUser")) 
					{
						__temp_executeDef266 = false;
						this.editUser(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), haxe.lang.Runtime.toString(dynargs.__get(1)), haxe.lang.Runtime.toString(dynargs.__get(2)), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(3))) ), haxe.lang.Runtime.toString(dynargs.__get(4)), haxe.lang.Runtime.toString(dynargs.__get(5)), ((haxe.lang.Function) (dynargs.__get(6)) ));
					}
					
					break;
				}
				
				
				case 1247233375:
				{
					if (field.equals("sendMail")) 
					{
						__temp_executeDef266 = false;
						this.sendMail(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(2))) ), haxe.lang.Runtime.toBool(dynargs.__get(3)), haxe.lang.Runtime.toBool(dynargs.__get(4)), ((haxe.lang.Function) (dynargs.__get(5)) ));
					}
					
					break;
				}
				
				
				case -75082687:
				{
					if (field.equals("getUser")) 
					{
						__temp_executeDef266 = false;
						this.getUser(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -684844615:
				{
					if (field.equals("voteForSurvey")) 
					{
						__temp_executeDef266 = false;
						this.voteForSurvey(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)), haxe.lang.Runtime.toBool(dynargs.__get(2)), ((haxe.lang.Function) (dynargs.__get(3)) ));
					}
					
					break;
				}
				
				
				case 1967404114:
				{
					if (field.equals("getUsers")) 
					{
						__temp_executeDef266 = false;
						this.getUsers(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -972192230:
				{
					if (field.equals("getSurveyData")) 
					{
						__temp_executeDef266 = false;
						this.getSurveyData(haxe.lang.Runtime.toString(dynargs.__get(0)), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -2027557481:
				{
					if (field.equals("getOldUsers")) 
					{
						__temp_executeDef266 = false;
						this.getOldUsers(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -75126061:
				{
					if (field.equals("getTeam")) 
					{
						__temp_executeDef266 = false;
						this.getTeam(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -515792157:
				{
					if (field.equals("createGroup")) 
					{
						__temp_executeDef266 = false;
						this.createGroup(haxe.lang.Runtime.toString(dynargs.__get(0)), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), haxe.lang.Runtime.toString(dynargs.__get(2)), ((haxe.lang.Function) (dynargs.__get(3)) ));
					}
					
					break;
				}
				
				
				case 1966059520:
				{
					if (field.equals("getTeams")) 
					{
						__temp_executeDef266 = false;
						this.getTeams(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -52145656:
				{
					if (field.equals("leaveGroup")) 
					{
						__temp_executeDef266 = false;
						this.leaveGroup(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 727549667:
				{
					if (field.equals("getProject")) 
					{
						__temp_executeDef266 = false;
						this.getProject(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -516304011:
				{
					if (field.equals("joinGroup")) 
					{
						__temp_executeDef266 = false;
						this.joinGroup(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 1079203312:
				{
					if (field.equals("getProjects")) 
					{
						__temp_executeDef266 = false;
						this.getProjects(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -1888235883:
				{
					if (field.equals("editGroup")) 
					{
						__temp_executeDef266 = false;
						this.editGroup(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), haxe.lang.Runtime.toString(dynargs.__get(1)), haxe.lang.Runtime.toString(dynargs.__get(2)), haxe.lang.Runtime.toString(dynargs.__get(3)), ((haxe.lang.Function) (dynargs.__get(4)) ));
					}
					
					break;
				}
				
				
				case 1615806146:
				{
					if (field.equals("getMessages")) 
					{
						__temp_executeDef266 = false;
						this.getMessages(haxe.lang.Runtime.toString(dynargs.__get(0)), ((double) (haxe.lang.Runtime.toDouble(dynargs.__get(1))) ), ((haxe.lang.Function) (dynargs.__get(2)) ));
					}
					
					break;
				}
				
				
				case -853071095:
				{
					if (field.equals("validateGroup")) 
					{
						__temp_executeDef266 = false;
						this.validateGroup(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -1431807962:
				{
					if (field.equals("addMessage")) 
					{
						__temp_executeDef266 = false;
						this.addMessage(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)), ((haxe.lang.Function) (dynargs.__get(2)) ));
					}
					
					break;
				}
				
				
				case -340042480:
				{
					if (field.equals("unvalidateGroup")) 
					{
						__temp_executeDef266 = false;
						this.unvalidateGroup(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 1952717028:
				{
					if (field.equals("getEvent")) 
					{
						__temp_executeDef266 = false;
						this.getEvent(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -1144040556:
				{
					if (field.equals("deleteGroup")) 
					{
						__temp_executeDef266 = false;
						this.deleteGroup(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 404685839:
				{
					if (field.equals("getEvents")) 
					{
						__temp_executeDef266 = false;
						this.getEvents(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -857546984:
				{
					if (field.equals("addUserToGroup")) 
					{
						__temp_executeDef266 = false;
						this.addUserToGroup(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), haxe.lang.Runtime.toString(dynargs.__get(2)), ((haxe.lang.Function) (dynargs.__get(3)) ));
					}
					
					break;
				}
				
				
				case 1968222483:
				{
					if (field.equals("getVotes")) 
					{
						__temp_executeDef266 = false;
						this.getVotes(((haxe.lang.Function) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -2076755166:
				{
					if (field.equals("submitVote")) 
					{
						__temp_executeDef266 = false;
						this.submitVote(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), ((haxe.lang.Function) (dynargs.__get(2)) ));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef266) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("__cnx");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


