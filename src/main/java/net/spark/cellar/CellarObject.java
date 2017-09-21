package net.spark.cellar;



public class CellarObject {
	 private Cellar m_cellar;
	    private String m_systemUid = "1111";
	    private ObjectState m_state = ObjectState.None;
	    private long m_offset = -1;
	 protected  void  setSystemUid(String uuid) {
		this.m_systemUid = uuid; 
	 }
	 protected String getSystemUid(){
		return this.m_systemUid; 
	 }
}
