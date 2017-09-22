package net.spark.cellar;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import net.spark.backend.data.entity.AbstractEntity;
@MappedSuperclass
public class CellarObject extends AbstractEntity{
	 //private Cellar m_cellar;
	    private ObjectState m_state = ObjectState.None;
	    private long m_offset = -1;
	    private String systemUid;
		@Id
		@GeneratedValue(generator = "system-uuid")
		@GenericGenerator(name = "system-uuid", strategy = "uuid")
		 protected  void  setSystemUid(String uuid) {
			this.systemUid = uuid; 
		 }
		 protected String getSystemUid(){
			return this.systemUid; 
		 }
}
