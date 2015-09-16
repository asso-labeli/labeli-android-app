package net.tools;

import java.io.File;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

import com.app.labeli.Survey;
import com.app.labeli.SurveyItem;
import com.app.labeli.interfaces.DataWithPicture;
import com.app.labeli.member.Member;
import com.app.labeli.project.Message;
import com.app.labeli.project.Project;
import com.app.labeli.project.ProjectUser;
import com.app.labeli.Vote;
import com.tools.DateTools;

import net.tools.RequestSender;

/**
 * > @APIConnection
 *
 * Connector to Label[i] API
 * Reference : {@link http://api.labeli.org}
 * Git API : {@link https://github.com/asso-labeli/labeli-api}
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public abstract class APIConnection {

	// URLs
	public static String apiUrl = "http://192.168.1.3:9010/";
	private static String urlUsers = apiUrl + "users";
	private static String urlAuth = apiUrl + "auth";
	private static String urlProjects = apiUrl + "projects";
	private static String urlProjectUser = apiUrl + "projectUser";
	private static String urlProjectUsers = urlProjectUser + "s";
	private static String urlMessage = apiUrl + "message";
	private static String urlMessages = urlMessage + "s";
	private static String urlSurveys = apiUrl + "surveys";
	private static String urlSurveyItem = apiUrl + "surveyItem";
	private static String urlSurveyItems = urlSurveyItem + "s";
	private static String urlVotes = apiUrl + "votes";
	private static String urlResetPassword = apiUrl + "resetPassword";

	// HTTP
	private static String GET = "GET";
	private static String POST = "POST";
	private static String DELETE = "DELETE";
	private static String PUT = "PUT";

	// Users JSON Tags
	private static String tagUserFirstName = "firstName";
	private static String tagUserLastName = "lastName";
	private static String tagUserUsername = "username";
	private static String tagUserEmail = "email";
	private static String tagUserRole = "role";
	private static String tagUserUniversityGroup = "universityGroup";
	private static String tagUserDescription = "description";
	private static String tagUserPicture = "picture";
	private static String tagUserCreated = "created";
	private static String tagUserBirthday = "birthday";
	private static String tagUserLastEdited= "lastEdited";
	private static String tagUserId = "_id";
	private static String tagUserLevel = "level";
	private static String tagUserWebsite = "website";

	// Projects JSON Tags
	private static String tagProjectAuthor = "author";
	private static String tagProjectName = "name";
	private static String tagProjectDescription = "description";
	private static String tagProjectPicture = "picture";
	private static String tagProjectCreated = "created";
	private static String tagProjectLastEdited = "lastEdited";
	private static String tagProjectStatus = "status";
	private static String tagProjectType = "type";
	private static String tagProjectId = "_id";

	// SurveyItem JSON Tags
	private static String tagSurveyItemName = "name";
	private static String tagSurveyItemCreated = "created";
	private static String tagSurveyItemLastEdited = "lastEdited";
	private static String tagSurveyItemId = "_id";

	// Surveys JSON Tags
	private static String tagSurveyDescription = "description";
	private static String tagSurveyName = "name";
	private static String tagSurveyState = "state";
	private static String tagSurveyNumberChoices = "numberChoices";
	private static String tagSurveyCreated = "created";
	private static String tagSurveyLastEdited = "lastEdited";
	private static String tagSurveyAuthor = "author";
	private static String tagSurveyId = "_id";

	// Vote JSON Tags
	private static String tagVoteNegative = "negative";
	private static String tagVoteNeutral = "neutral";
	private static String tagVotePositive = "positive";
	private static String tagVoteTotal = "total";

	// Messages JSON Tags

	private static String tagMessageContent = "content";
	private static String tagMessageProject = "project";
	private static String tagMessageAuthor = "author";
	private static String tagMessageLastEdited = "lastEdited";
	private static String tagMessageCreated = "created";
	private static String tagMessageId = "_id";

	// Votes JSON Tags

	public static final int BOOLEAN_TRUE = 0;
	public static final int BOOLEAN_FALSE = 1;
	public static final int ERROR_VALUE = -1;

	private static Member loggedUser = null;

	private static RequestSender jParser = new RequestSender();

	/**
	 * Send HTTP Request with params (x-url-encoded)
	 * @param url
	 * @param method - HTTP method (GET, POST, PUT, DELETE, ...)
	 * @param urlParameters - parameters send in URL
	 * @param bodyParameters - parameters send in body (encoded)
	 * @return JSONObject returned by the server
	 */
	private static JSONObject makeHttpRequest(String url, 
			String method, HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		return jParser.makeHttpRequest(url, method, urlParameters, bodyParameters);
	}

	/**
	 * Send a file to a server
	 * @param f - the file to send
	 * @param fileName - new name of file on the server
	 * @return true if upload is successful
	 */
	public static boolean makeFileRequest(File f, String fileName){
		return jParser.postPicture(f, fileName);
	}

	/**
	 * Load an image from a URL
	 * @param a - activity where the image must be load
	 * @param d - a data with a picture URL
	 * @param width - width of the screen
	 * @return true if loading is successful
	 */
	public static boolean loadImage(Activity a, DataWithPicture d, int width){
		return jParser.loadImage(a, d, width);
	}

	/**
	 * Get the connected user to the API
	 * @return the connected user
	 */
	public static Member getLoggedUser(){
		return loggedUser;
	}

	/**
	 * Check if this APIConnection is connected
	 * @return true if is connected / a user is logged
	 */
	public static boolean isLogged(){
		return loggedUser != null;
	}

	/**
	 * Check if logged user is a member
	 * @return true if is at least a member
	 */
	public static boolean loggedUserIsMember(){
		if (isLogged())
			return loggedUser.getLevel() >= Member.LEVEL_MEMBER;

			return false;
	}

	/**
	 * Check if logged user is an admin
	 * @return true if is an admin
	 */
	public static boolean loggedUserIsAdmin(){
		if (isLogged())
			return loggedUser.getLevel() >= Member.LEVEL_ADMIN;

			return false;
	}
	
	/* ******************************************************
	 * 
	 * HTTP Request Methods
	 * 
	 ********************************************************/

	/**
	 * Send a GET request and create a list with returned JSON datas.
	 * The JSONObject returned by server must have :
	 * - success : int - 1 if request is successful
	 * - data : JSONArray - contains datas which will be parsed
	 * @param url
	 * @param parseMethod - name of the method used to parse an object in data
	 * @param urlParameters - parameters send in URL
	 * @param bodyParameters - parameters send in body (encoded)
	 * @return the list of parsed elements
	 */
	private static <T> ArrayList<T> getItems(String url, String parseMethod, 
			HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		ArrayList<T> list = new ArrayList<T>();
		
		// Get parseMethod
		Class<?>[] cArg = new Class[1];
		cArg[0] = JSONObject.class;

		Method parse = null;
		try {
			parse = APIConnection.class.getMethod(parseMethod, cArg);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
			return null;
		}

		// Do the request
		JSONObject json = makeHttpRequest(url, GET, urlParameters, bodyParameters);

		if (json == null)
			return null;
		try {
			int success = json.getInt("success");
			// Parse if successful
			if (success == 1){
				JSONArray data = json.getJSONArray("data");
				for (int i = 0; i < data.length(); i++){
					@SuppressWarnings("unchecked")
					T tmp = (T) parse.invoke(APIConnection.class, data.getJSONObject(i));
					list.add(tmp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}
	
	/**
	 * Send a HTTP request and parse the returned element.
	 * The JSONObject returned by server must have :
	 * - success : int - 1 if request is successful
	 * - data : JSONObject - element which will be parsed
	 * @param url
	 * @param method - HTTP method (GET, PUT, ...)
	 * @param parseMethod - name of the method used to parse an object in data
	 * @param urlParameters - parameters send in URL
	 * @param bodyParameters - parameters send in body (encoded)
	 * @return the list of parsed elements
	 */
	@SuppressWarnings("unchecked")
	private static <T> T doHTTPRequestAndParse(String url, String method, String parseMethod, 
			HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		// Get parseMethod
		Class<?>[] cArg = new Class[1];
		cArg[0] = JSONObject.class;

		Method parse = null;
		try {
			parse = APIConnection.class.getMethod(parseMethod, cArg);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
			return null;
		}

		// Do the request
		JSONObject json = makeHttpRequest(url, method, urlParameters, bodyParameters);

		if (json == null)
			return null;
		try {
			int success = json.getInt("success");
			// Parse if successful
			if (success == 1){
				return (T) parse.invoke(APIConnection.class, json.getJSONObject("data"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 

		return null;
	}
	
	/**
	 * Send a HTTP request and check the returned element.
	 * The JSONObject returned by server must have :
	 * - success : int - 1 if request is successful
	 * @param url
	 * @param method - HTTP method (GET, PUT, ...)
	 * @param parseMethod - name of the method used to parse an object in data
	 * @param urlParameters - parameters send in URL
	 * @param bodyParameters - parameters send in body (encoded)
	 * @return the list of parsed elements
	 */
	public static boolean doHTTPRequest(String url, String method,
			HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		JSONObject json = makeHttpRequest(url, method, urlParameters, bodyParameters);

		if (json == null)
			return false;
		try {
			int success = json.getInt("success");
			// Parse if successful
			if (success == 1){
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		} 

		return false;
	}

	/**
	 * Send a GET request and parse the returned element.
	 * The JSONObject returned by server must have :
	 * - success : int - 1 if request is successful
	 * - data : JSONObject - element which will be parsed
	 * @param url
	 * @param parseMethod - name of the method used to parse an object in data
	 * @param urlParameters - parameters send in URL
	 * @param bodyParameters - parameters send in body (encoded)
	 * @return the list of parsed elements
	 */
	private static <T> T getItem(String url, String parseMethod, 
			HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		return APIConnection.<T>doHTTPRequestAndParse(url, GET, parseMethod, urlParameters, bodyParameters);
	}

	/**
	 * Send a POST request and parse the returned element.
	 * The JSONObject returned by server must have :
	 * - success : int - 1 if request is successful
	 * - data : JSONObject - element which will be parsed
	 * @param url
	 * @param parseMethod - name of the method used to parse an object in data
	 * @param urlParameters - parameters send in URL
	 * @param bodyParameters - parameters send in body (encoded)
	 * @return the list of parsed elements
	 */
	private static <T> T createItem(String url, String parseMethod, 
			HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		return APIConnection.<T>doHTTPRequestAndParse(url, POST, parseMethod, urlParameters, bodyParameters);
	}

	/**
	 * Send a PUT request and parse the returned element.
	 * The JSONObject returned by server must have :
	 * - success : int - 1 if request is successful
	 * - data : JSONObject - element which will be parsed
	 * @param url
	 * @param parseMethod - name of the method used to parse an object in data
	 * @param urlParameters - parameters send in URL
	 * @param bodyParameters - parameters send in body (encoded)
	 * @return the list of parsed elements
	 */
	private static <T> T editItem(String url, String parseMethod, 
			HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		return APIConnection.<T>doHTTPRequestAndParse(url, PUT, parseMethod, urlParameters, bodyParameters);
	}

	/**
	 * Send a DELETE request.
	 * @param url
	 * @param urlParameters - parameters send in URL
	 * @param bodyParameters - parameters send in body (encoded)
	 * @return the list of parsed elements
	 */
	public static boolean deleteItem(String url,
			HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		return APIConnection.doHTTPRequest(url, DELETE, urlParameters, bodyParameters);
	}

	/* ******************************************************
	 * 
	 * API Module : Authentification
	 * 
	 ********************************************************/

	/**
	 * Send a request to get the logged user (server vision)
	 * @return the logged user, or null if not logged
	 */
	public static Member getCurrentUser(){
		return APIConnection.<Member>getItem(urlAuth, GET, null, null);
	}

	/**
	 * Send a request to login
	 * @param username 
	 * @param password
	 * @return true if the connection is successful
	 */
	public static boolean login(String username, String password){
		HashMap<String, String> nameValuePairs = new HashMap<String, String>(2);
		nameValuePairs.put("username", username);
		nameValuePairs.put("password", password);

		JSONObject json = makeHttpRequest(urlAuth, POST, null, nameValuePairs);

		try {
			if (json.getInt("success") == 1){
				loggedUser = parseMember(json.getJSONObject("data"));
				return true;
			}
		} catch (JSONException e) {
			Log.w("APIConnection", "Error during parsing JSON");
		} catch (NullPointerException e){
			Log.w("APIConnection", "Error during getting response");
		}

		return false;
	}

	/**
	 * Send a request to logout
	 * @return true if successful
	 */
	public static boolean logout(){
		JSONObject json = makeHttpRequest(urlAuth, DELETE, null, null);

		try {
			if (json.getInt("success") == 1){
				loggedUser = null;
				return true;
			}
		} catch (JSONException e) {
			Log.w("APIConnection", "Error during parsing JSON");
		} catch (NullPointerException e){
			Log.w("APIConnection", "Error during getting response");
		}

		return false;
	}
	
	/**
	 * Send a request to reset password of user
	 * @param userId - id of user 
	 * @return true if successful
	 */
	public static boolean resetPassword(String userId){
		if (!isLogged()) return false;
		
		return APIConnection.doHTTPRequest(urlResetPassword + '/' + userId, POST, null, null);
	}

	/* ******************************************************
	 * 
	 * API Module : Users
	 * 
	 ********************************************************/

	/**
	 * Send a request to get the list of users
	 * @return the list of users
	 */
	public static ArrayList<Member> getUsers(){
		return APIConnection.<Member>getItems(urlUsers, "parseMember", null, null);
	}

	/**
	 * Send a request to create a user
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @return the created user
	 */
	public static Member createUser(String firstName, String lastName, 
			String email){
		if (!isLogged()) return null;
		
		HashMap<String, String> params = new HashMap<String, String>(3);
		params.put("firstName", firstName);
		params.put("lastName", lastName);
		params.put("email", email);

		return APIConnection.<Member>createItem(urlUsers, "parseMember", null, params);
	}

	/**
	 * Send a request to get user
	 * @param userID - id of user to get
	 * @return the user with the ID
	 */
	public static Member getUser(String userID){
		return APIConnection.<Member>getItem(urlUsers + "/" + userID, "parseMember", null, null);
	}

	/**
	 * Send a request to edit a user
	 * @param m - member object with updated informations
	 * @param password - the new password, or null if no new password
	 * @return the edited user
	 */
	public static Member editUser(Member m, String password){
		if (!isLogged()) return null;
		
		HashMap<String, String> params = new HashMap<String, String>(11);
		params.put("firstName", m.getFirstName());
		params.put("lastName", m.getLastName());
		params.put("email", m.getEmail());
		params.put("website", m.getWebsite());
		params.put("universityGroup", m.getUniversityGroup());
		params.put("birthday", String.valueOf(m.getBirthday().getTime()));
		params.put("picture", m.getPictureURL());
		params.put("role", m.getRole());
		params.put("level", String.valueOf(m.getLevel()));
		params.put("description", m.getDescription());
		if (password != null) params.put("password", password);
		
		return APIConnection.<Member>editItem(urlUsers + "/" + m.getId(), "parseMember", null, params);
	}

	/**
	 * Send a request to delete a user
	 * @param userID - the id of user to delete
	 * @return true if the user is deleted
	 */
	public static boolean deleteUser(String userID){
		if (!isLogged()) return false;
		
		return APIConnection.deleteItem(urlUsers + "/" + userID, null, null);
	}

	/* ******************************************************
	 * 
	 * API Module : Projects
	 * 
	 ********************************************************/

	/**
	 * Send a request to create a project
	 * @param name
	 * @param type
	 * @param authorUsername
	 * @return the created project
	 */
	public static Project createProject(String name, String type, 
			String authorUsername){
		if (!isLogged()) return null;
		
		HashMap<String, String> params = new HashMap<String, String>(3);
		params.put("name", name);
		params.put("type", type);
		params.put("authorUsername", authorUsername);

		return APIConnection.<Project>createItem(urlProjects, "parseProject", null, params);
	}

	/**
	 * Send a request to delete a project
	 * @param projectID
	 * @return true if the project is deleted
	 */
	public static boolean deleteProject(String projectID){
		if (!isLogged()) return false;
		
		return APIConnection.deleteItem(urlProjects + "/" + projectID, null, null);
	}

	/**
	 * Send a request to edit a project
	 * @param p - a project with updated informations
	 * @return the edited project
	 */
	public static Project editProject(Project p){
		if (!isLogged()) return null;
		
		HashMap<String, String> params = new HashMap<String, String>(6);
		params.put("name", p.getName());
		params.put("status", String.valueOf(p.getStatus()));
		params.put("description", p.getDescription());
		params.put("type", String.valueOf(p.getType()));
		params.put("authorUsername", p.getAuthor().getUsername());
		if (p.getPictureURL() != null) params.put("picture", p.getPictureURL());

		return APIConnection.<Project>editItem(urlProjects + "/" + p.getId(), "parseProject", null, params);
	}

	/**
	 * Send a request to get all projects
	 * @return the list of projects
	 */
	public static ArrayList<Project> getProjects(){
		return APIConnection.<Project>getItems(urlProjects, "parseProject", null, null);
	}

	/**
	 * Send a request to get a project
	 * @param projectID
	 * @return the project with the good ID
	 */
	public static Project getProject(String projectID){
		return APIConnection.<Project>getItem(urlProjects + "/" + projectID, "parseProject", null, null);
	}

	/* ******************************************************
	 * 
	 * API Module : ProjectUsers
	 * 
	 ********************************************************/

	public static boolean createOrEditProjectUser(String projectID, 
			String level, String username){
		// TODO createOrEditProjectUser
		return false;
	}

	public static boolean deleteProjectUser(String projectUserID){
		// TODO deleteProjectUser
		return false;
	}

	public static ProjectUser getProjectUser(String projectUserID){
		// TODO getProjectUser
		return null;
	}

	public static ArrayList<ProjectUser> getProjectUsers(String projectID){
		// TODO getProjectUsers
		return null;
	}
	
	/* ******************************************************
	 * 
	 * API Module : Surveys
	 * 
	 ********************************************************/
	
	/**
	 * Send a request to create a survey
	 * @param name - name of the survey
	 * @param description - [optional] description of the survey
	 * @param state - [optional] 0 : close / 1 : open
	 * @return the created survey
	 */
	public static Survey createSurvey(String name, String description, 
			String state){
		if (!isLogged()) return null;
		
		HashMap<String, String> bodyParameters = new HashMap<String, String>(3);
		bodyParameters.put("name", name);
		if (description != null) bodyParameters.put("description", description);
		if (state != null) bodyParameters.put("state", state);
		
		return APIConnection.<Survey>createItem(urlSurveys, "parseSurvey", null, bodyParameters);
	}
	
	/**
	 * Send a request to delete a survey
	 * @param surveyId - id of the survey to delete
	 * @return true if successful
	 */
	public static boolean deleteSurvey(String surveyId){
		if (!isLogged()) return false;
		
		return APIConnection.deleteItem(urlSurveys + '/' + surveyId, null, null);
	}
	
	/**
	 * Send a request to edit a survey
	 * @param s - the survey with updated datas
	 * @return the edited survey
	 */
	public static Survey editSurvey(Survey s){
		if (!isLogged()) return null;
		
		HashMap<String, String> bodyParameters = new HashMap<String, String>(3);
		bodyParameters.put("name", s.getName());
		bodyParameters.put("description", s.getDescription());
		bodyParameters.put("state", String.valueOf(s.getState()));
		
		return APIConnection.<Survey>editItem(urlSurveys + '/' + s.getId(), 
				"parseSurvey", null, bodyParameters);
	}
	
	/**
	 * Send a request to get a specifif survey
	 * @param id - id of survey to get
	 * @return resulted survey
	 */
	public static Survey getSurvey(String id){
		return APIConnection.<Survey>getItem(urlSurveys, "parseSurvey", null, null);
	}
	
	/**
	 * Send a request to get all surveys
	 * @return ArrayList with all surveys
	 */
	public static ArrayList<Survey> getSurveys(){
		return APIConnection.<Survey>getItems(urlSurveys, "parseSurvey", null, null);
	}
	
	/* ******************************************************
	 * 
	 * API Module : SurveyItems
	 * 
	 ********************************************************/
	
	/**
	 * Send a request to create a SurveyItem
	 * @param parentSurverId - id of survey which must be add the irem
	 * @param name - name of item
	 * @return parsed SurveyItem
	 */
	public static SurveyItem createSurveyItem(String parentSurverId, String name){
		if (!isLogged()) return null;
		
		HashMap<String, String> bodyParameters = new HashMap<String, String>(1);
		bodyParameters.put("name", name);
		
		return APIConnection.<SurveyItem>createItem(urlSurveyItems, "parseSurveyItem", null, bodyParameters);
	}
	
	/**
	 * Send a request to delete a SurveyItem
	 * @param surveyItemId - id of surveyItem to delete
	 * @return true if successful
	 */
	public static boolean deleteSurveyItem(String surveyItemId){
		if (!isLogged()) return false;
		
		return APIConnection.deleteItem(urlSurveyItem + '/' + surveyItemId, null, null);
	}
	
	/**
	 * Send a request to edit a SurveyItem
	 * @param s - surveyItem with updated values
	 * @return the edited surveyItem
	 */
	public static SurveyItem editSurveyItem(SurveyItem s){
		if (!isLogged()) return null;
		
		HashMap<String, String> bodyParameters = new HashMap<String, String>(1);
		bodyParameters.put("name", s.getName());
		
		return APIConnection.<SurveyItem>editItem(urlSurveyItem + '/' + s.getId(), 
				"parseSurveyItem", null, bodyParameters);
	}
	
	/**
	 * Send a request to get a specific SurveyItem
	 * @param surveyItemId - id of surveyItem to get
	 * @return the surveyItem with the good id
	 */
	public static SurveyItem getSurveyItem(String surveyItemId){
		return APIConnection.<SurveyItem>getItem(urlSurveyItem + '/' + surveyItemId, 
				"parseSurveyItem", null, null);
	}
	
	/**
	 * Send a request to get all surveyItems
	 * @return the list of SurveyItems
	 */
	public static ArrayList<SurveyItem> getSurveyItems(){
		return APIConnection.<SurveyItem>getItems(urlSurveyItems, 
				"parseSurveyItem", null, null);
	}

	/* ******************************************************
	 * 
	 * API Module : Messages
	 * 
	 ********************************************************/

	/**
	 * Send a request to create a message
	 * @param projectID - project where the message must be add
	 * @param content
	 * @return the created message
	 */
	public static Message createMessage(String projectID, String content){
		if (!isLogged()) return null;

		HashMap<String, String> params = new HashMap<String, String>(1);
		params.put("content", content);
		return APIConnection.<Message>createItem(urlMessages + "/" + projectID, "parseMessage", null, params);
	}

	/**
	 * Send a request to delete a message
	 * @param messageID
	 * @return true if the message is deleted
	 */
	public static boolean deleteMessage(String messageID){
		if (!isLogged()) return false;

		return APIConnection.deleteItem(urlMessage + "/" + messageID, null, null);
	}

	/**
	 * Send a request to edit a message
	 * @param messageID
	 * @param content
	 * @return the edited message
	 */
	public static Message editMessage(String messageID, String content){
		if (!isLogged()) return null;

		HashMap<String, String> params = new HashMap<String, String>(1);
		params.put("content", content);
		return APIConnection.<Message>editItem(urlMessage + "/" + messageID, "parseMessage", null, params);
	}

	/**
	 * Send a request to get a message
	 * @param messageID
	 * @return the message with the good ID
	 */
	public static Message getMessage(String messageID){
		return APIConnection.<Message>getItem(urlMessage + "/" + messageID , "parseMessage", null, null);
	}

	/**
	 * Send a request to get messages of a project
	 * @param projectID
	 * @return the list of messages for a project
	 */
	public static ArrayList<Message> getMessages(String projectID) {
		return APIConnection.<Message>getItems(urlMessages + "/" + projectID, "parseMessage", null, null);
	}

	/* ******************************************************
	 * 
	 * API Module : Votes
	 * 
	 ********************************************************/

	public static Vote getVote(String id){
		return null;
	}

	public static boolean createVote(String id){
		return false;
	}

	public static ArrayList<SurveyItem> getSurveyItems(String id){
		return null;
	}
	
	/* ******************************************************
	 * 
	 * Parsing Methods
	 * 
	 ********************************************************/

	/**
	 * Parse a JSONObject to a Member object
	 * @param o
	 * @return the parsed Member
	 * @throws JSONException
	 */
	public static Member parseMember(JSONObject o) throws JSONException{
		String lastName = o.getString(tagUserLastName);
		String firstName = o.getString(tagUserFirstName);
		String username = o.getString(tagUserUsername);
		String email = o.getString(tagUserEmail);
		String picture = o.getString(tagUserPicture);
		String role = o.getString(tagUserRole);
		int level = o.getInt(tagUserLevel);
		String description = o.getString(tagUserDescription);
		Date created = null;
		Date birthday = null;
		Date lastEdited = null;
		try {
			created = DateTools.parse(o.getString(tagUserCreated));
			lastEdited = DateTools.parse(o.getString(tagUserLastEdited));
			if (!o.getString(tagUserBirthday).equals("null"))
				birthday = DateTools.parse(o.getString(tagUserBirthday));
			else
				birthday = new Date(0);
		} catch (ParseException e) {
			e.printStackTrace();
			created = new Date(0);
			birthday = new Date(0);
			lastEdited = new Date(0);
		}
		String universityGroup = o.getString(tagUserUniversityGroup);
		String id = o.getString(tagUserId);
		String website = o.getString(tagUserWebsite);

		return new Member(firstName, lastName, username, email, role, website, universityGroup,
				description, picture, created, birthday, lastEdited, id, level);
	}

	/**
	 * Parse a JSONObject to a Project object
	 * @param o
	 * @return the parsed project
	 * @throws JSONException
	 */
	public static Project parseProject(JSONObject o) throws JSONException{
		Member author = getUser(o.getString(tagProjectAuthor));
		String name = o.getString(tagProjectName);
		int type = o.getInt(tagProjectType);
		String description = o.getString(tagProjectDescription);
		Date created = null;
		Date lastEdited = null;
		try {
			created = DateTools.parse(o.getString(tagProjectCreated));
			lastEdited = DateTools.parse(o.getString(tagProjectLastEdited));
		} catch (ParseException e) {
			created = new Date(0);
			lastEdited = new Date(0);
			e.printStackTrace();
		}
		String picture = o.getString(tagProjectPicture);
		int status = o.getInt(tagProjectStatus);
		String id = o.getString(tagProjectId);

		return new Project(author, name, description, picture, created, lastEdited, status, type, id);
	}

	/**
	 * Parse a JSONObject to a SurveyItem object
	 * @param o
	 * @return the parsed surveyItem
	 * @throws JSONException
	 */
	public static SurveyItem parseSurveyItem(JSONObject o) throws JSONException{
		String name = o.getString(tagSurveyItemName);
		Date created = null;
		Date lastEdited = null;
		try {
			created = DateTools.parse(o.getString(tagSurveyItemCreated));
			lastEdited = DateTools.parse(o.getString(tagSurveyItemLastEdited));
		} catch (ParseException e) {
			e.printStackTrace();
			created = new Date(0);
			lastEdited = new Date(0);
		}
		String id = o.getString(tagSurveyItemId);

		return new SurveyItem(name, created, lastEdited, id);
	}

	/**
	 * Parse a JSONObject to a Vote object
	 * @param o
	 * @return the parsed vote
	 * @throws JSONException
	 */
	public static Vote parseVote(JSONObject o) throws JSONException {
		int negative = o.getInt(tagVoteNegative);
		int neutral = o.getInt(tagVoteNeutral);
		int positive = o.getInt(tagVotePositive);
		int total = o.getInt(tagVoteTotal);

		return new Vote(negative, neutral, positive, total);
	}

	/**
	 * Parse a JSONObject to a Survey object
	 * @param oSurvey
	 * @return the parsed survey
	 * @throws JSONException
	 */
	public static Survey parseSurvey(JSONObject oSurvey) throws JSONException {
		String description = oSurvey.getString(tagSurveyDescription);
		String name = oSurvey.getString(tagSurveyName);
		int state = oSurvey.getInt(tagSurveyState);
		int numberChoices = oSurvey.getInt(tagSurveyNumberChoices);
		Date created = null;
		Date lastEdited = null;
		try {
			created = DateTools.parse(oSurvey.getString(tagSurveyItemCreated));
			lastEdited = DateTools.parse(oSurvey.getString(tagSurveyItemLastEdited));
		} catch (ParseException e) {
			e.printStackTrace();
			created = new Date(0);
			lastEdited = new Date(0);
		}
		Member author = getUser(oSurvey.getString(tagSurveyAuthor));
		String id = oSurvey.getString(tagSurveyId);

		Vote v = getVote(id);
		ArrayList<SurveyItem> items = getSurveyItems(id);

		return new Survey(description, name, state, numberChoices, created, lastEdited, author, id, v, items);
	}

	/**
	 * Parse a JSONObject to a Message object
	 * @param o
	 * @return the parsed message
	 * @throws JSONException
	 */
	public static Message parseMessage(JSONObject o) throws JSONException{
		String content = o.getString(tagMessageContent);
		Project project = getProject(o.getString(tagMessageProject));
		Member author = getUser(o.getString(tagMessageAuthor));
		Date created = null;
		Date lastEdited = null;
		try {
			created = DateTools.parse(o.getString(tagMessageCreated));
			lastEdited = DateTools.parse(o.getString(tagMessageLastEdited));
		} catch (ParseException e) {
			e.printStackTrace();
			created = new Date(0);
			lastEdited = new Date(0);
		}
		String id = o.getString(tagMessageId);

		return new Message(project, author, created, lastEdited, id, content);
	}
}
