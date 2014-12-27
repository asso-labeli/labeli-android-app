package com.tools;

import java.util.ArrayList;

import com.app.labeli.event.ItemEvent;
import com.app.labeli.member.ItemMember;
import com.app.labeli.project.ItemProject;
import com.app.labeli.team.ItemTeam;

public abstract class APIDataParser {

	@SuppressWarnings("rawtypes")
	public static ArrayList<ItemEvent> parseEventList(haxe.root.Array a){
		ArrayList<ItemEvent> events = new ArrayList<ItemEvent>();

		for(int i=0; i < a.length; i++)
		{
			haxe.lang.DynamicObject event= (haxe.lang.DynamicObject) a.__get(i);
			events.add(parseEvent(event));
		}

		return events;
	}

	@SuppressWarnings("rawtypes")
	public static ArrayList<ItemMember> parseMemberList(haxe.root.Array a){
		ArrayList<ItemMember> members = new ArrayList<ItemMember>();

		for(int i=0; i < a.length; i++)
		{
			haxe.lang.DynamicObject event= (haxe.lang.DynamicObject) a.__get(i);
			members.add(parseMember(event));
		}

		return members;
	}
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<ItemProject> parseProjectList(haxe.root.Array a){
		ArrayList<ItemProject> projects = new ArrayList<ItemProject>();

		for(int i=0; i < a.length; i++)
		{
			haxe.lang.DynamicObject project= (haxe.lang.DynamicObject) a.__get(i);
			projects.add(parseProject(project));
		}

		return projects;
	}
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<ItemTeam> parseTeamList(haxe.root.Array a){
		ArrayList<ItemTeam> teams = new ArrayList<ItemTeam>();

		for(int i=0; i < a.length; i++)
		{
			haxe.lang.DynamicObject team= (haxe.lang.DynamicObject) a.__get(i);
			teams.add(parseTeam(team));
		}

		return teams;
	}

	public static ItemEvent parseEvent(haxe.lang.DynamicObject o){
		ItemMember author = parseMember((haxe.lang.DynamicObject) o.__hx_lookupField("author", true, false));
		String name = (String) o.__hx_lookupField("name", true, false);
		String description = (String) o.__hx_lookupField("description", true, false);
		String picture = (String) o.__hx_lookupField("picture", true, false);
		int status = (Integer) o.__hx_lookupField("status", true, false);
		int type = (Integer) o.__hx_lookupField("type", true, false);

		return new ItemEvent(author, name, description, picture, status, type);
	}

	public static ItemMember parseMember(haxe.lang.DynamicObject o){
		String firstName = (String) o.__hx_lookupField("firstName", true, false);
		String lastName = (String) o.__hx_lookupField("lastName", true, false);
		String email = (String) o.__hx_lookupField("email", true, false);
		String role = (String) o.__hx_lookupField("role", true, false);
		String universityGroup = (String) o.__hx_lookupField("universityGroup", true, false);
		String description = (String) o.__hx_lookupField("description", true, false);
		String picture = (String) o.__hx_lookupField("picture", true, false);
		double created = 0.0;
		if (o.__hx_lookupField("created", true, false) != null)
		try {
			created = (Double) o.__hx_lookupField("created", true, false);
		} catch (ClassCastException e) {
			created = Double.parseDouble(String.valueOf((Integer)o.__hx_lookupField("created", true, false)));
		}
		double birthday = 0.0;
		if (o.__hx_lookupField("birthday", true, false) != null)
			try {
				birthday = (Double) o.__hx_lookupField("birthday", true, false);
			} catch (ClassCastException e) {
				birthday = Double.parseDouble(String.valueOf((Integer)o.__hx_lookupField("birthday", true, false)));
			}
		int id = (Integer) o.__hx_lookupField("id", true, false);
		int type = (Integer) o.__hx_lookupField("type", true, false);

		return new ItemMember(firstName, lastName, email, role, universityGroup,
				description, picture, created, birthday, id, type);
	}
	
	public static ItemProject parseProject(haxe.lang.DynamicObject o){
		ItemMember author = parseMember((haxe.lang.DynamicObject) o.__hx_lookupField("author", true, false));
		String name = (String) o.__hx_lookupField("name", true, false);
		String description = (String) o.__hx_lookupField("description", true, false);
		String picture = (String) o.__hx_lookupField("picture", true, false);
		double created = 0.0;
		if (o.__hx_lookupField("created", true, false) != null)
		try {
			created = (Double) o.__hx_lookupField("created", true, false);
		} catch (ClassCastException e) {
			created = Double.parseDouble(String.valueOf((Integer)o.__hx_lookupField("created", true, false)));
		}
		int status = (Integer) o.__hx_lookupField("status", true, false);
		int type = (Integer) o.__hx_lookupField("type", true, false);

		return new ItemProject(author, name, description, picture, created, status, type);
	}
	
	public static ItemTeam parseTeam(haxe.lang.DynamicObject o){
		ItemMember author = parseMember((haxe.lang.DynamicObject) o.__hx_lookupField("author", true, false));
		String name = (String) o.__hx_lookupField("name", true, false);
		String description = (String) o.__hx_lookupField("description", true, false);
		String picture = (String) o.__hx_lookupField("picture", true, false);
		double created = 0.0;
		if (o.__hx_lookupField("created", true, false) != null)
		try {
			created = (Double) o.__hx_lookupField("created", true, false);
		} catch (ClassCastException e) {
			created = Double.parseDouble(String.valueOf((Integer)o.__hx_lookupField("created", true, false)));
		}
		int status = (Integer) o.__hx_lookupField("status", true, false);
		int type = (Integer) o.__hx_lookupField("type", true, false);

		return new ItemTeam(author, name, description, picture, created, status, type);
	}

}
