package net.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.app.labeli.Survey;
import com.app.labeli.SurveyItem;
import com.app.labeli.event.Event;
import com.app.labeli.member.Member;
import com.app.labeli.project.Message;
import com.app.labeli.project.Project;
import com.app.labeli.project.ProjectUser;
import com.app.labeli.Vote;
import com.tools.DateTools;

import net.tools.JSONParser;

public abstract class APIConnection {

	/**
	 * Reference : https://github.com/eolhing/labeli-api
	 */

	// URLs
	public static String apiUrl = "http://192.168.1.11:9010/";
	private static String urlUsers = apiUrl + "users";
	private static String urlAuth = apiUrl + "auth";
	private static String urlProjects = apiUrl + "projects";
	private static String urlMessages = apiUrl + "messages";
	private static String urlMessage = apiUrl + "message";
	private static String urlVotes = apiUrl + "votes";

	// HTTP
	private static String GET = "GET";
	private static String POST = "POST";
	private static String DELETE = "DELETE";
	private static String PUT = "PUT";

	// API Parameters Name
	private static String paramAuthUsername = "username";
	private static String paramAuthPassword = "password";
	private static String paramProjectsName = "name";
	private static String paramProjectsType = "type";
	private static String paramUserFirstName = "firstName";
	private static String paramUserLastName = "lastName";
	private static String paramUserEmail = "email";
	private static String paramUserPassword = "password";
	private static String paramMessagesContent = "content";

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

	private static JSONParser jParser = new JSONParser();

	private static JSONObject makeHttpRequest(String url, 
			String method, List<NameValuePair> params){
		return jParser.makeHttpRequest(url, method, params);
	}

	private String getProjectUrl(int id){
		return urlProjects + "/" + id;
	}

	private String getUserUrl(int id){
		return urlUsers + "/" + id;
	}

	private String getMessageUrl(int id){
		return urlMessages + "/" + id;
	}

	private String getVoteUrl(int id){
		return urlVotes + "/" + id;
	}

	public static Member getLoggedUser(){
		return loggedUser;
	}
	
	public static boolean isLogged(){
		return loggedUser != null;
	}
	
	public static boolean loggedUserIsMember(){
		if (isLogged())
			return loggedUser.getLevel() >= Member.LEVEL_MEMBER;
		return false;
	}
	
	public static boolean loggedUserIsAdmin(){
		if (isLogged())
			return loggedUser.getLevel() >= Member.LEVEL_ADMIN;
		return false;
	}

	/**
	 * Vérifie si la chaîne est un booléen. Elle supprime tout les espaces et les \n de la chaine avant la vérification.
	 * @param bool
	 * @return
	 */
	private static boolean convertToBoolean(String bool){
		return Boolean.valueOf(bool.replace(" ", "").replace("\n", ""));
	}

	private static <T> ArrayList<T> getItems(String url, String parseMethod){
		Class<?>[] cArg = new Class[1];
		cArg[0] = JSONObject.class;

		Method parse = null;
		try {
			parse = APIConnection.class.getMethod(parseMethod, cArg);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
			return null;
		}

		ArrayList<T> list = new ArrayList<T>();

		// Make request
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONObject json = makeHttpRequest(url, GET, params);

		if (json == null)
			return null;
		try {
			int success = json.getInt("success");
			// Parse if successfull
			if (success == 1){
				JSONArray data = json.getJSONArray("data");
				for (int i = 0; i < data.length(); i++){
					@SuppressWarnings("unchecked")
					T tmp = (T) parse.invoke(APIConnection.class, data.getJSONObject(i));
					list.add(tmp);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	private static <T> T getItem(String url, String parseMethod){
		Class<?>[] cArg = new Class[1];
		cArg[0] = JSONObject.class;

		Method parse = null;
		try {
			parse = APIConnection.class.getMethod(parseMethod, cArg);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
			return null;
		}

		// Make request
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONObject json = makeHttpRequest(url, GET, params);

		if (json == null)
			return null;
		try {
			int success = json.getInt("success");
			// Parse if successfull
			if (success == 1){
				return (T) parse.invoke(APIConnection.class, json.getJSONObject("data"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T createItem(String url, String parseMethod, List<NameValuePair> params){
		Class<?>[] cArg = new Class[1];
		cArg[0] = JSONObject.class;

		Method parse = null;
		try {
			parse = APIConnection.class.getMethod(parseMethod, cArg);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
			return null;
		}

		JSONObject json = makeHttpRequest(url, POST, params);
		
		Log.i("Coucou", json.toString());

		if (json == null)
			return null;
		try {
			int success = json.getInt("success");
			// Parse if successfull
			if (success == 1){
				return (T) parse.invoke(APIConnection.class, json.getJSONObject("data"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}
	@SuppressWarnings("unchecked")
	public static <T> T editItem(String url, String parseMethod, List<NameValuePair> params){
		Class<?>[] cArg = new Class[1];
		cArg[0] = JSONObject.class;

		Method parse = null;
		try {
			parse = APIConnection.class.getMethod(parseMethod, cArg);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
			return null;
		}

		Log.i("Test", url);
		JSONObject json = makeHttpRequest(url, PUT, params);
		Log.i("Coucou", json.toString());

		if (json == null)
			return null;
		try {
			int success = json.getInt("success");
			// Parse if successfull
			if (success == 1){
				return (T) parse.invoke(APIConnection.class, json.getJSONObject("data"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/*
	 * AUTHENTIFICATION
	 */

	public static Member getCurrentUser(){
		// TODO getCurrentUser
		return null;
	}

	public static boolean login(String username, String password){
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("username", username));
		nameValuePairs.add(new BasicNameValuePair("password", password));

		JSONObject json = makeHttpRequest(urlAuth, POST, nameValuePairs);

		try {
			if (json.getInt("success") == 1){
				loggedUser = parseMember(json.getJSONObject("data"));
				return true;
			}
		} catch (JSONException e) {
			Log.w("APIConnection", "Error during parsing JSON");
		}

		return false;
	}

	public static boolean logout(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONObject json = makeHttpRequest(urlAuth, DELETE, params);

		try {
			if (json.getInt("success") == 1){
				loggedUser = null;
				return true;
			}
		} catch (JSONException e) {
			Log.w("APIConnection", "Error during parsing JSON");
		}

		return false;
	}

	/*
	 * USERS
	 */

	public static ArrayList<Member> getUsers(){
		return APIConnection.<Member>getItems(urlUsers, "parseMember");
	}

	public static Member createUser(String firstName, String lastName, 
			String email){
		List<NameValuePair> params = new ArrayList<NameValuePair>(3);
		params.add(new BasicNameValuePair("firstName", firstName));
		params.add(new BasicNameValuePair("lastName", lastName));
		params.add(new BasicNameValuePair("email", email));

		return APIConnection.<Member>createItem(urlUsers, "parseMember", params);
	}

	public static Member getUser(String userID){
		return APIConnection.<Member>getItem(urlUsers + "/" + userID, "parseMember");
	}

	public static boolean editUser(String userID, String firstName, 
			String lastName, String email, String website, 
			String universityGroup, String role, Date birthday,
			String description, String picture){
		// TODO editUser
		return false;
	}

	public static boolean deleteUser(String userID){
		// TODO deleteUser
		return false;
	}

	/*
	 * PROJECTS
	 */

	public static Project createProject(String name, String type, 
			String authorUsername){
		List<NameValuePair> params = new ArrayList<NameValuePair>(3);
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("authorUsername", authorUsername));

		return APIConnection.<Project>createItem(urlProjects, "parseProject", params);
	}

	public static boolean deleteProject(String projectID){
		// TODO deleteProject
		return false;
	}

	public static Project editProject(String projectID, String name, 
			int status, String description, int type, 
			String authorUsername){
		List<NameValuePair> params = new ArrayList<NameValuePair>(5);
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("status", String.valueOf(status)));
		params.add(new BasicNameValuePair("description", description));
		params.add(new BasicNameValuePair("type", String.valueOf(type)));
		params.add(new BasicNameValuePair("authorUsername", authorUsername));

		return APIConnection.<Project>editItem(urlProjects + "/" + projectID, "parseProject", params);
	}

	public static ArrayList<Project> getProjects(){
		return APIConnection.<Project>getItems(urlProjects, "parseProject");
	}

	public static Project getProject(String projectID){
		return APIConnection.<Project>getItem(urlProjects + "/" + projectID, "parseProject");
	}

	/*
	 * PROJECTUSERS
	 */

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

	/*
	 * MESSAGES
	 */

	public static Message createMessage(String projectID, String content){
		if (!isLogged()) return null;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("content", content));
		return APIConnection.<Message>createItem(urlMessages + "/" + projectID, "parseMessage", params);
	}

	public static boolean deleteMessage(String messageID){
		// TODO deleteMessage
		return false;
	}

	public static boolean editMessage(String messageID, String content){
		return false;
	}

	public static Message getMessage(String messageID){
		return APIConnection.<Message>getItem(urlMessage + "/" + messageID , "parseMessage");
	}

	public static ArrayList<Message> getMessages(String projectID) {
		return APIConnection.<Message>getItems(urlMessages + "/" + projectID, "parseMessage");
	}

	/*
	 * VOTES
	 */

	public static Vote getVote(String id){
		return null;
	}

	public static boolean createVote(String id){
		return false;
	}

	public static ArrayList<SurveyItem> getSurveyItems(String id){
		return null;
	}

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
		try {
			created = DateTools.parse(o.getString(tagUserCreated));
			birthday = DateTools.parse(o.getString(tagUserBirthday));
		} catch (ParseException e) {
			e.printStackTrace();
			created = new Date(0);
			birthday = new Date(0);
		}
		String universityGroup = o.getString(tagUserUniversityGroup);
		String id = o.getString(tagUserId);
		String website = o.getString(tagUserWebsite);

		return new Member(firstName, lastName, username, email, role, website, universityGroup,
				description, picture, created, birthday, id, level);
	}

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

	public static Vote parseVote(JSONObject o) throws JSONException {
		int negative = o.getInt(tagVoteNegative);
		int neutral = o.getInt(tagVoteNeutral);
		int positive = o.getInt(tagVotePositive);
		int total = o.getInt(tagVoteTotal);

		return new Vote(negative, neutral, positive, total);
	}

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
		String id = o.getString(tagSurveyItemId);

		return new Message(project, author, created, lastEdited, id, content);
	}
}
