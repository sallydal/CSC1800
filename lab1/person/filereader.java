//--------------------------------------------------------------------------------------------
//Patrick Caffrey and Sally Moon
//Programming Languages
//Prof. Klassner
//  This file is meant to read in the information given to by the input file.
//   -Contains a few helper methods that need access to the hashtable.
//--------------------------------------------------------------------------------------------

package person;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;

public class filereader {
	protected static Hashtable<String, Person> list = new Hashtable<String, Person>();
	private ArrayList<String> relatives = new ArrayList<String>();

	public static void main (String[] args){
		filereader x = new filereader();
		x.run();
	}

	public void run()
	   {
		     list = new Hashtable<String, Person>();
			 //variable needed to store input from the file
			 // sample input file on the Dating Service specs on my web site
			 // initalize string
		     String name1 = "", action = "",name2 = "", name3 = "";
	         String line;

	         Scanner scan = new Scanner(System.in);
			 Scanner linescan;

	         while(scan.hasNext()) // this reads in lines from a file
	         {
	        	 line = scan.nextLine();
				 //System.out.println(line); // for debugging
				 linescan = new Scanner(line);//- an iterator -
				 if(linescan.hasNext()){
					 action = linescan.next();//Action to happen
					 name1 = linescan.next();
	            	 name2 = linescan.next();

//----------------------------------------------------------------------------------------
	               if(action.charAt(0) == 'E')//If we have an event
	               {
	                  //add to hashtable
	                  Person newPerson, newPerson2, newPerson3;
	                  if(!list.containsKey(name1)){
		                  newPerson = new Person(name1, name2);
	                  }
	                  else {
	                	  newPerson = list.get(name1);
	                	  newPerson.setSpouse(name2);;
	                  }

	                  if(!list.containsKey(name2)) {
		                  newPerson2 = new Person(name2, name1);
	                  }
	                  else {
	                	  newPerson2 = list.get(name2);
	                	  newPerson2.setSpouse(name1);
	                  }

	                  if(linescan.hasNext()){ //covers the optional third name
	            		  name3 = linescan.next();
	            		  newPerson3 = new Person(name3);
	            		  newPerson.addChild(newPerson3);
	            		  newPerson2.addChild(newPerson3);
	            		  if(!list.containsKey(name3)) {
	            			  list.put(name1, newPerson);
	            			  list.put(name2, newPerson2);
		                	  list.put(name3, newPerson3);
		                	  newPerson3.setParents(newPerson, newPerson2);
		                  }
	            	  }
	                  else{
	                	  list.put(name1, newPerson);
            			  list.put(name2, newPerson2);
        			  }
	                  //done adding to table
	               } // close event if container
//------------------------------------------------------------------------------------------
	               if(action.charAt(0) == 'X')//If an X prompt is readIn
	               {
	            	   if(linescan.hasNext())
		            		 name3 = linescan.next();
	            	   //Print the line of query
	            	   System.out.println();
	            	   System.out.println(line);

	            	   //Spouse Relation
	            	   if(name2.equalsIgnoreCase("spouse")){
	            		   Person person = list.get(name1);
	            		   if(person.getSpouse().equalsIgnoreCase(name3))
	            			   System.out.println("Yes");
	            		   else
	            			   System.out.println("No");
	            	   }

	            	   //Parent Relation
	            	   if(name2.equalsIgnoreCase("parent")){
	            		   Person person = list.get(name3);
	            		   System.out.println(person.getParentX(name1));
	            	   }

	            	   //Sibling Relation
	            	   if(name2.equalsIgnoreCase("sibling")){
	            		   Person person = list.get(name1);
	            		   Person person2 = list.get(name3);
	            		   if(person.getSiblings().contains(person2))
	            		   		System.out.println("Yes");
            		   		else
            		   			System.out.println("No");
	            	   }

	            	   //Ancestor Relation
	            	   if(name2.equalsIgnoreCase("ancestor")){
	            		   Person person = list.get(name3);
	            		   if(isAncestorX(person, name1))
	            			   System.out.println("Yes");
	            		   else
	            			   System.out.println("No");
	            	   }

	            	   //Relative Relation
	            	   if(name2.equalsIgnoreCase("relative")){
	            		   Person person = list.get(name1);
	            		   if(isRelativeX(person, name3))
	            			   System.out.println("Yes");
	            		   else
	            			   System.out.println("No");
	            	   }

	            	   //Unrelated Relation
	            	   if(name2.equalsIgnoreCase("unrelated")){
	            		   Person person = list.get(name1);
	            		   if(isAncestorX(person, name3) || isRelativeX(person, name3) ||
	            				   person.parents.contains(name3) ||
	            				   person.getSiblings().contains(name3))
	            			   System.out.println("No");
	            		   else
	            			   System.out.println("Yes");
	            	   }

	               } // close x if container
	//--------------------------------------------------------------------------------------
	               if(action.charAt(0) == 'W'){ //If a W prompt is readIn
	            	   //Print the line of query
	            	   System.out.println();
	            	   System.out.println(line);

	            	   //Spouse Relation
	            	   if(name1.equalsIgnoreCase("spouse")) {
	            		   Person person = list.get(name2);
	            		   System.out.println(person.getSpouse());
	            	   }

	            	   //Parent Relation
	            	   if(name1.equalsIgnoreCase("parent")) {
	            		   Person person = list.get(name2);
	            		   String[] parents = new String[person.parents.size()];
	            		   Person[] people = new Person[2];
	            		   person.parents.toArray(people);
	            		   for(int j=0; j < person.parents.size(); j++){
	            			   parents[j] = people[j].getName();

	            		   }
	            		   Arrays.sort(parents);
	            		   for(int i=0; i< parents.length; i++)
	            			   System.out.println(parents[i]);
	            	   }

	            	   //Sibling Relation
	            	   if(name1.equalsIgnoreCase("sibling")) {
	            		   Person person = list.get(name2);
	            		   String[] siblings = (String[])person.getSiblings().toArray();
	            		   Arrays.sort(siblings);
	            		   for(int i=0; i<siblings.length; i++)
	            			   System.out.println(siblings[i]);
	            	   }

	            	   //Ancestor Relation
	            	   if(name1.equalsIgnoreCase("ancestor")){
	            		   Set<String> ancestors = list.keySet();
	            		   Person person = new Person("");
	            		   person = list.get(name2);
	            		   String[] sancestors = new String[ancestors.size()];
	            		   ArrayList<String> array = new ArrayList<String>();
	            		   ancestors.toArray(sancestors);
	            		   for(int i=0; i<ancestors.size(); i++){
	            			   if(isAncestorX(person, sancestors[i]))
	            					   array.add(sancestors[i]);
	            		   }
	            		   String[] anc = new String[array.size()];
	            		   array.toArray(anc);
	            		   Arrays.sort(anc);
	            		   for(int j=0; j<anc.length; j++)
	            			   System.out.println(anc[j]);
	            	   }
	            	   //Relative Relation
	            	   if(name1.equalsIgnoreCase("relative")){

	            	   }
	            	   //Unrelated Relation
	            	   if(name1.equalsIgnoreCase("unrelated")){

	            	   }
	               }
//---------------------------------------------------------------------------------------------
	               if(action.charAt(0) =='R')
	               {
	            	   //Print the line of query
	            	   System.out.println();
	            	   System.out.println(line);

	            	   Person person1, person2;
	            	   person1 = list.get(name1);
	            	   person2 = list.get(name2);
	            	   if(person1 == null || person2 == null){
	            		   System.out.println("Unrelated");
	            	   }
	            	   else if(!person1.children.isEmpty())
	            		   if(person1.children.contains(person2)){
	            			   System.out.println("Parent");
	            		   }
            		   else if(person2.children.contains(person1)){
	            		   System.out.println("Child");
	            	   }
            		   else if(person1.getSiblings().contains(person2)){
	            		   System.out.println("Sibling");
	            	   }
            		   else if(person1.getSpouse().equalsIgnoreCase(person2.getName())){
	            		   System.out.println("Spouse");
	            	   }
            		   else if(isRelativeX(person1, person2.getName())){
	            		   System.out.println("Relative");
	            	   }
            		   else if(isAncestorX(person1, person2.getName()) || isAncestorX(person2, person1.getName())){
	            		   System.out.println("Ancestor");
	            	   }
	            	   else
	            		   System.out.println("Unrelated");

	               }
	            } // close if
	         } //close while
	         scan.close();
	   }// close method
//---------------------------------------------------------------------------------------------
	public boolean isAncestorX(Person person, String ancestor){
		//Base Case
		if(person.parents.isEmpty()){
			if(person.getName().equalsIgnoreCase(ancestor))
				return true;
			else
				return false;
		}
		Person[] parents = new Person[2];
		String[] sparents = new String[2];
		person.parents.toArray(parents);
		for(int i=0; i<2; i++){
			sparents[i] = parents[i].getName();
		}
		for(int i=0; i<2; i++){
			if(ancestor.equalsIgnoreCase(sparents[i]))
				return true;
		}

		//Recurse
		return(isAncestorX(parents[0], ancestor) || isAncestorX(parents[1], ancestor));
	}

	public boolean isRelativeX(Person person, String relative){
		if(person.getName().equalsIgnoreCase(relative))
			return true;
		if(!person.parents.isEmpty())
			if(person.parents.get(0).getName().equalsIgnoreCase(relative) ||
					person.parents.get(1).getName().equalsIgnoreCase(relative))
				return true;
		if(anuX(person, relative) && cousX(person, relative))
			return true;
		else
			return false;

	}

	public boolean  anuX(Person person, String target) {
		Person parents[] = new Person[2];
		//Base Case
		if(person.parents.isEmpty())
			return false;
		for(int i=0; i<person.getParents().size(); i++){
			Person children[] = new Person[50];
			person.getParents().toArray(parents);
			for(int j=0; j<parents[i].getChildren().size(); j++) {
				parents[i].getChildren().toArray(children);
				if(children[j].getName() == target)
					return true;
			}
		}
		return(anuX(parents[0], target) || anuX(parents[1], target));
	}

	public boolean cousX(Person person, String target) {
		Person siblings[] = new Person[50];
		Person children[] = new Person[50];
		ArrayList<Person> cousins = new ArrayList<Person>();
		for(int i=0; i<person.getSiblings().size(); i++){
			//Base Case
			if(person.getSiblings().isEmpty())
				return false;
			person.getSiblings().toArray(siblings);
			for(int j=0; j<siblings.length; j++){
				if(siblings[i].children.isEmpty())
					return false;
				siblings[i].getChildren().toArray(children);
				for(int z=0; z<children.length; z++){
					if(children[j].getName().equalsIgnoreCase(target)){
						cousins.add(children[j]);
						return true;
					}
				}
			}
		}
		return false;
	}
}
