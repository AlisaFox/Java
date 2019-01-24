package assignment3;

public class Building {

	OneBuilding data;
	Building older;
	Building same;
	Building younger;
	
	public Building(OneBuilding data){
		this.data = data;
		this.older = null;
		this.same = null;
		this.younger = null;
	}
	
	public String toString(){
		String result = this.data.toString() + "\n";
		if (this.older != null){
			result += "older than " + this.data.toString() + " :\n";
			result += this.older.toString();
		}
		if (this.same != null){
			result += "same age as " + this.data.toString() + " :\n";
			result += this.same.toString();
		}
		if (this.younger != null){
			result += "younger than " + this.data.toString() + " :\n";
			result += this.younger.toString();
		}
		return result;
	}
	
	public Building addBuilding (OneBuilding b){
		
		//if there is nothing in the building, the ob to be added becomes the root
		if(this.data == null) {
			new Building(b);
			return this;
		}
		//next, check if it goes left or right 
		if(b.yearOfConstruction > this.data.yearOfConstruction) {
			//b is younger (year of construction larger)
			if (this.younger != null) {
				//if we have to go down the tree further
				//had to make a helper to act as a "parent" node. Otherwise the children of the tree disappear
				this.younger.addBuildingHelper(b, this, 'y'); 
			}else {
				//if we are at the leaf, we can just add the building 
				this.younger= new Building(b);
			}
		}else if(b.yearOfConstruction < this.data.yearOfConstruction) {
			//same thing as above, just on for an older b
			if (this.older != null) {
				this.older.addBuildingHelper(b, this, 'o'); 
			}else {
				this.older= new Building(b);
			}
		}else if(b.yearOfConstruction == this.data.yearOfConstruction) {
			//so there are two ways we can go
			//if the height of the object added is greater, we need to make it the new root
			if (b.height>this.data.height) {
				Building newRoot = new Building(b);
				//add the children of this to the new tree
				if(this.older!=null) {
					newRoot.older = this.older;
					this.older = null;
				}
				if(this.younger!=null) {
					newRoot.younger = this.younger;
					this.younger = null;
				}
				//make this the node under
				newRoot.same = this;
				//and return the new building
				return newRoot;
			}
			else {
				//otherwise you just add b normally
				if(this.same != null) {
					//a precaution to avoid stack overflow error
					if (this.same.data.equals(b)) {
						return this;
					}
					//if there is already a child (we need to compare again)
					this.same.addBuildingHelper(b, this, 's');
				}else {
					//if it was a "leaf"
					this.same = new Building(b);
				}
			}
		}
		return this; 
	}
			private Building addBuildingHelper(OneBuilding b, Building root, char c) {
				//acts exactly like addBuilding, with a bit more code in the same age section
				if(b.yearOfConstruction > this.data.yearOfConstruction) {
					//b is younger (year of construction larger)
					if (this.younger != null) {
						this.younger.addBuilding(b); 
					}else {
						this.younger= new Building(b);
					}
				}else if(b.yearOfConstruction < this.data.yearOfConstruction) {
					if (this.older != null) {
						this.older.addBuilding(b); 
					}else {
						this.older= new Building(b);
					}
				}else if(b.yearOfConstruction == this.data.yearOfConstruction) {
					//same age section
					if (b.height>this.data.height) {
						Building newRoot = new Building(b);
						if(this.older!=null) {
							newRoot.older = this.older;
							this.older = null;
						}
						if(this.younger!=null) {
							newRoot.younger = this.younger;
							this.younger = null;
						}
						newRoot.same = this;
						//this is the only code that is different, connecting the root and the new building
						if (c=='o') {
							//char represents the parent location
							root.older =newRoot;
						}
						if (c=='y') {
							root.younger = newRoot;
						}
						if (c=='s') {
							root.same = newRoot;
						}
						return newRoot;
					}
					else {
						if(this.same != null) {
							if (this.same.data.equals(b)) {
								return this;
							}
							this.same.addBuilding(b);
						}else {
							this.same = new Building(b);
						}
					}
				}
				return this;
			}
	
	public Building addBuildings (Building b){
			
		//this code traverses the tree and adds each onebuilding one by one
		this.addBuilding(b.data);
		   if (b.older != null) {
		      addBuildings(b.older);
		   }
		   if (b.younger != null) {
		      addBuildings(b.younger);
		   }
		   if (b.same != null) {
			   addBuildings(b.same);
		   }
			    
		return this; 
	}
		
	public Building removeBuilding (OneBuilding b){
	
		//find the building to be removed 
		//there is a helper method below
		Building x = findHelper(b, this);
		//just in case, make an unchangeable copy
		final Building g = x;
		
		//if the building to remove was not found, just return this
		if (x==null) {
			return this;
		}
		Building n = this;
		
		//find the path from the root (cuz again, there are no parent nodes)
		//another helper method below that returns a String
		String bigPath = pathHelper(b, this, this, "");
		String [] path = bigPath.split("");
		
		
		for (int i = 0; i< path.length-1; i++) {
	
			if(path [i].equals("o")) {
				n = n.older;
				continue;
			}
			if(path [i].equals("s")) {
				n = n.same;
				continue;
			}
			if(path [i].equals("y")) {
				n = n.younger;
				continue;
			}
		}
		//basically this method gives out smth like n.older.same.same
		
		//this is a big method for the occasion when we need to delete the root, since it has no parents
		//(aka we wont have a path)
		if (this.data.equals(b)) {
			Building c;
			if (g.same != null) {
				//if the root had same aged children, just make a new building with this.same as the new root
				c = new Building(g.same.data);
				if(g.same.same!=null) {
					c.addBuildings(g.same.same);
				}
				if(g.older !=null) {
					c.addBuildings(g.older);
				}
				if(g.younger !=null) {
					c.addBuildings(g.younger);
				}
				return c;
			}else if (g.older != null) {
				//otherwise use g.older as the new root
				c = new Building(g.older.data);
				if(g.same!=null) {
					c.addBuildings(g.same);
				}
				if(g.older.older !=null) {
					c.addBuildings(g.older.older);
				}
				if(g.younger !=null) {
					c.addBuildings(g.younger);
				}
				return c;
			}else if (g.younger != null) {
				//or this.younger
				c = new Building(g.younger.data);
				if(g.same!=null) {
					c.addBuildings(g.same);
				}
				if(g.older !=null) {
					c.addBuildings(g.older);
				}
				if(g.younger.younger !=null) {
					c.addBuildings(g.younger.younger);
				}
				//and return this new building
				return c;
			}
		}
		
		
		//add data from deleted building 
		
		//so once we have n (or ex. this.older.same.same)
		//we find and delete the child b
		if (n.same != null ) {
		   if(n.same.data.equals(b)) {
				n.same = null;
			}
		}else if (n.younger !=null && n.younger.data.equals(b)) {
			n.younger = null;
			}else if (n.older != null && n.older.data.equals(b)) {
				n.older = null;
			}
		
		//if the onebuilding b has any children, add them to the building
		if(x.same!=null) {
			this.addBuildings(x.same);
		}else if(x.older !=null) {
			this.addBuildings(x.older);
		}
		else if(x.younger !=null) {
			this.addBuildings(x.younger);
		}	
		return this; 
	}
		private Building findHelper(OneBuilding b, Building z) {
			if (z == null) {
				return null;
			}		
			//cycles through the tree, and finds the onebuilding to be deleted and its children
			  if (z.data.equals(b)) {
					return z;
				}
			  if(z.data.yearOfConstruction == b.yearOfConstruction) {
					return findHelper(b, z.same);
				}
				if (z.data.yearOfConstruction > b.yearOfConstruction) {
					return findHelper(b, z.older);
				}
				if (z.data.yearOfConstruction < b.yearOfConstruction) {
					return findHelper(b, z.younger);
				}
				return null;
			}
		
		private String pathHelper(OneBuilding b, Building z, Building o, String path) {
				if (z == null) {
					return path;
				}	
				//cycles through the tree and makes a String (basically an array)
				if (z.data.equals(b)) {
					return path;
				}
				if(z.data.yearOfConstruction == b.yearOfConstruction) {
				   path += "s"+pathHelper(b, z.same, z, path);				   
				}
				if (z.data.yearOfConstruction > b.yearOfConstruction) {
					path += "o"+pathHelper(b, z.older, z, path);
					
				}
				if (z.data.yearOfConstruction < b.yearOfConstruction) {
					path += "y"+pathHelper(b, z.younger, z, path);
					
				}
				return path;
		}
	
	public int oldest(){
		//since we know that the oldest buildings are on the left side of the tree, we just find the left-most one
		Building b = this;
		int age = b.data.yearOfConstruction;	
		//loop that updates the age as we go down the tree
		while(b.older != null) {			
			age = b.older.data.yearOfConstruction;
			b = b.older;
		}
		return age; 
	}
	
	public int highest(){
		// ADD YOUR CODE HERE
		Building b = this;
		//variable to represent highest
		int highest = b.data.height;
		//go down b older
		//we dont care about the same branch since we know the height will decrease
		while(b.older != null) {
			if(b.older.data.height>highest) {
				highest = b.older.data.height;
			}
			Building y = b.younger;
			//if at any point there is a younger branch, check it too
			while(y!=null) {
				if(y.data.height>highest) {
					highest = y.data.height;
				}
				y=y.younger;
			}			
			b = b.older;
		}
		//mirror same thing for youngest
		while(b.younger != null) {
			if(b.younger.data.height>highest) {
				highest = b.younger.data.height;
			}
			Building o = b.older;
			while(o!=null) {
				if(o.data.height>highest) {
					highest = o.data.height;
				}
				o=o.older;
			}			
			b = b.younger;
		}		
		return highest; 
	}
	
		
	
	public OneBuilding highestFromYear (int year){
		// ADD YOUR CODE HERE 
		//using helper, find the building with the given year
		Building b = HFYHelper(year, this);
		if (b == null) {
			return null;
		}
		//we already know that the top building of that branch is the highest
		//so we can just return it
		String name = b.data.name;
		int yearOfC = b.data.yearOfConstruction;
		int height = b.data.height;
		int yearOfR = b.data.yearForRepair;
		int cost = b.data.costForRepair;
		return new OneBuilding(name,yearOfC,height,yearOfR,cost); 
	}
			private Building HFYHelper(int year, Building z) {
				if (z == null) {
					return null;
				}	
				  if (z.data.yearOfConstruction==year) {
						return z;
					}		
					if (z.data.yearOfConstruction > year) {
						return HFYHelper(year, z.older);
					}
					if (z.data.yearOfConstruction < year) {
						return HFYHelper(year, z.younger);
					}
					return null;
				}
	
	public int numberFromYears (int yearMin, int yearMax){
		
		//check if yearMin is actually yearMin
		if (yearMin > yearMax) {
			return 0;
		}
		//set up a loop that counts all the buildings in one year, then goes onto the next
		int counter = 0;
		int overall = 0;
		for (int i = yearMin; i< yearMax+1; i++) {
			//find building
			Building b = HFYHelper(i, this);
			//if there are no buildings in that year, skip
			if (b==null) {
				continue;
			}
			counter++;
			//count all the "same" children
			while (b.same !=null) {
				if (b.same.data.toString().equals(b.data.toString())) {
					//just a precaution
					counter--;
				}
				counter++;
				b = b.same;
			}
			//add to the overall counter outside the loop
			overall +=counter;
			//reset counter
			counter = 0;
		}
		return overall; 
	}
	
	public int[] costPlanning (int nbYears){
	
		//same  strategy, count cost year by year
		int[] yearly = new int [nbYears];
		//loop through all the years
		for (int i = 0; i < nbYears; i++) {
			//cost will be counted by a helper method
			yearly[i]=costHelper(this, 2018+i, 0);
		}
	
		return yearly; 
	}
			private int costHelper(Building b, int year, int c) {
				//recursively cycles through tree
				//if year is the year we are looking for
				if (b.data.yearForRepair==year) {	
					//add cost to the cost counter c
					c+=b.data.costForRepair;
					if (b.same != null && b.same.data.equals(b.data)) {
						//just in case
						c-=b.data.costForRepair;
					}
				}
				   if (b.older != null) {
					   c+=costHelper(b.older, year, 0);
				   }
				   if (b.same != null) {
					   c+=costHelper(b.same, year, 0);
				   }
				   if (b.younger != null) {
				      c+=costHelper(b.younger, year, 0);
				   }
				   
			   	return c;
			}
	
}
