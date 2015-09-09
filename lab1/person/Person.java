//----------------------------------------------------------------------------------------------
// Patrick Caffrey and Sally Moon
// Programming Languages
// Prof. Klassner
//  This file is the Person Class. It holds multiple pointers that will be used in the readIn
//    file to discover the relationships between people.
//----------------------------------------------------------------------------------------------

package person;

import java.util.ArrayList;

public class Person {
	protected String name, spouse;
	private boolean married;
	private int numChild;
	protected ArrayList<Person> children, parents, siblings;

	public Person(String name) {
		this.name = name;
		this.spouse = "";
		this.married = false;
		this.children = new ArrayList<Person>();
		this.parents = new ArrayList<Person>();
		this.siblings = new ArrayList<Person>();
		this.numChild = 0;
	}

	public Person(String name, String spouse){
		this.name = name;
		this.spouse = spouse;
		this.married = true;
		children = new ArrayList<Person>();
		parents = new ArrayList<Person>();
		siblings = new ArrayList<Person>();
		numChild = 0;
	}

	public String isMarried(Person person) {
		if(person.getMarried()) {
			return spouse;
		}
		else
			return (person.getName() + "is not married.");
	}

	public void addChild(Person name){
			children.add(name);
		}

	public ArrayList<Person> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Person> children) {
		this.children = children;
	}

	public ArrayList<Person> getParents() {
		return parents;
	}

	public void setParents(ArrayList<Person> parents) {
		this.parents = parents;
	}

	public int getNumChild() {
		return numChild;
	}

	public void setNumChild(int numChild) {
		this.numChild = numChild;
	}

	public String getParentX(String suspect) {
		if(parents.contains(suspect))
			return "Yes";
		else
			return "No.";
	}

	public void setParents(Person name, Person name2) {
		parents.add(name);
		parents.add(name2);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getMarried() {
		return married;
	}

	public String getSpouse() {
		return spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public ArrayList<Person> getSiblings() {
		if(!parents.isEmpty()){
			siblings = this.parents.get(0).getChildren();
			for(int i=0; i < this.parents.get(1).getChildren().size(); i++){
				if(!siblings.contains(this.parents.get(1).getChildren().get(i)))
					siblings.add(this.parents.get(1).getChildren().get(i));
			}
		}
		return siblings;
	}

}
