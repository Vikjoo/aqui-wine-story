
package net.spark.cellar;


public class BaseItem
extends CellarObject {
    static final int LENGTH = 51;
    private String name = null;

    protected BaseItem() {
    }

    public ObjectType getType() {
        return ObjectType.None;
    }

    public final int getLength() {
        return 51;
    }


    public final String getName() {
        return this.name;
    }

    public final void setName(String s) {
        
    }

    public final String toString() {
        return this.name.trim();
    }

    public boolean isEmpty() {
       return true;
    }

	public void setSystemUid(String sysId) {
		// TODO Auto-generated method stub
		
	}
}

